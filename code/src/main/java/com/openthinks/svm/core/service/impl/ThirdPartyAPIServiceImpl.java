package com.openthinks.svm.core.service.impl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import javax.annotation.PreDestroy;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.openthinks.svm.core.AppConfig;
import com.openthinks.svm.core.service.ThirdPartyAPIService;
import com.openthinks.svm.core.service.VersionService;
import com.openthinks.svm.web.support.model.ThirdPartyAPIObjects.RequestTokenResponse;
import com.openthinks.svm.web.support.model.ThirdPartyAPIObjects.SyncPostResponse;
import com.openthinks.svm.web.support.util.JSONUtils;

/**
 * ClassName: ThirdPartyAPIServiceImpl <br>
 * date: Apr 12, 2019 4:17:23 PM <br>
 * 
 * * @author dailey.dai@openthinks.com
 * 
 * @since JDK 1.8
 */
@Service
public class ThirdPartyAPIServiceImpl implements ThirdPartyAPIService {
  private static final Logger LOGGER = LoggerFactory.getLogger(ThirdPartyAPIServiceImpl.class);
  @Autowired
  AppConfig appConfig;
  @Autowired
  VersionService versionService;


  // final static String HOST = "http://third-party/restful-api";

  final static String REQUEST_TOKEN_URL = "/api/pub/token?appid={appid}&secrt={secrt}";

  final static String PSOT_VERSION_URL = "/api/pub/version/sync?token={token}";

  static volatile HttpClient httpClient;

  private String getFullAPI(String relativePath) {
    return appConfig.getThirdPartySyncAPIRoot() + relativePath;
  }

  synchronized HttpClient getHttpClient() {
    if (httpClient == null) {
      if (appConfig.isProxyAuthEnable()) {
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
            new AuthScope(appConfig.getProxyHost(), appConfig.getProxyPort()),
            new UsernamePasswordCredentials(appConfig.getProxyAuthName(),
                appConfig.getProxyAuthPass()));
        httpClient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
      } else {
        httpClient = HttpClients.custom().build();
      }
    }
    return httpClient;
  }


  private RequestConfig createRequestConfig() {
    HttpHost proxy = new HttpHost(appConfig.getProxyHost(), appConfig.getProxyPort());
    RequestConfig.Builder configBuilder = RequestConfig.custom();
    if (appConfig.isNeedProxy()) {
      return configBuilder.setProxy(proxy).build();
    }
    return configBuilder.build();
  }

  @Override
  public String requestToken() {
    final String url = getFullAPI(REQUEST_TOKEN_URL).replace("{appid}", appConfig.getAppId())
        .replace("{secrt}", appConfig.getAppSerct());
    HttpGet request = new HttpGet(url);
    request.setConfig(createRequestConfig());
    try {
      HttpResponse response = getHttpClient().execute(request);
      int statusCode = response.getStatusLine().getStatusCode();
      if (statusCode != 200) {
        throw new RuntimeException("Failed : HTTP error code : " + statusCode);
      }
      HttpEntity entity = response.getEntity();
      ContentType contentType = ContentType.getOrDefault(entity);
      Charset charset = contentType.getCharset();
      Reader reader = new InputStreamReader(entity.getContent(), charset);
      RequestTokenResponse tokenResp = JSONUtils.fromJSON(reader, RequestTokenResponse.class);
      if (tokenResp != null && tokenResp.getData() != null) {
        return tokenResp.getData().getToken();
      }
    } catch (Exception e) {
      LOGGER.error("Failed to request token by reason:{}", e);
    } finally {
      request.releaseConnection();
    }
    return null;
  }

  @Override
  public void postVersions(List<VersionSyncModel> syncModeList, String token) throws Exception {
    if (syncModeList == null || syncModeList.isEmpty()) {
      return;
    }
    VersionSyncModel[] array = new VersionSyncModel[syncModeList.size()];
    array = syncModeList.toArray(array);
    final String url = getFullAPI(PSOT_VERSION_URL).replace("{token}", token);
    HttpPost request = new HttpPost(url);
    request.setConfig(createRequestConfig());
    String jsonStr = JSONUtils.stringify(array);
    StringEntity entity = new StringEntity(jsonStr);
    entity.setContentType("application/json");
    entity.setContentEncoding("UTF-8");
    request.setEntity(entity);
    HttpResponse response = getHttpClient().execute(request);
    int statusCode = response.getStatusLine().getStatusCode();
    if (statusCode != 200) {
      throw new RuntimeException("Failed : HTTP error code : " + statusCode);
    }
    processSyncResponse(response.getEntity());
  }

  private void processSyncResponse(HttpEntity entity) throws ParseException, IOException {
    ContentType contentType = ContentType.getOrDefault(entity);
    Charset charset = contentType.getCharset();
    Reader reader = new InputStreamReader(entity.getContent(), charset);
    SyncPostResponse tokenResp = JSONUtils.fromJSON(reader, SyncPostResponse.class);
    LOGGER.debug("Get sync response from Third Party API:{}", tokenResp);
    if (tokenResp != null && tokenResp.getData() != null) {
      for (String syncVersion : tokenResp.getData()) {
        try {
          versionService.tagSynced(syncVersion);
        } catch (Exception e) {
          LOGGER.warn(
              "Failed to update sync status from not sync to sync for version number:{} by reason:{}.",
              syncVersion, e);
        }
      }
    }
  }

  @SuppressWarnings("deprecation")
  @PreDestroy
  public void tearDown() {
    if (httpClient != null) {
      httpClient.getConnectionManager().shutdown();
    }
  }

  public static void main(String[] args) throws ClientProtocolException, IOException {
    CredentialsProvider credsProvider = new BasicCredentialsProvider();
    credsProvider.setCredentials(new AuthScope("127.0.0.1", 12345),
        new UsernamePasswordCredentials("", ""));
    CloseableHttpClient httpclient =
        // HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
        HttpClients.custom().build();
    try {
      HttpHost target = new HttpHost("third-party", 80, "http");
      HttpHost proxy = new HttpHost("127.0.0.1", 12345);
      RequestConfig config = RequestConfig.custom()
          // .setProxy(proxy)
          .build();
      HttpGet httpget = new HttpGet(
          "http://third-party/restful-api/pub/token?appid=014lCHFjJe&secrt=5jLrb3rTk47GedLy");
      httpget.setConfig(config);

      System.out.println(
          "Executing request " + httpget.getRequestLine() + " to " + target + " via " + proxy);

      CloseableHttpResponse response = httpclient.execute(httpget);
      try {
        System.out.println("----------------------------------------");
        System.out.println(response.getStatusLine());
        System.out.println(EntityUtils.toString(response.getEntity()));
      } finally {
        response.close();
      }
    } finally {
      httpclient.close();
    }
  }


}
