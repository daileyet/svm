package com.openthinks.svm.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * ClassName: AppConfig </br>
 */
// @PropertySource("classpath:conf/components.properties")
@PropertySource("classpath:application.properties")
@Component
public class AppConfig {

  /**
   * application version
   */
  @Value("${app.version}")
  private String appVersion;
  /**
   * application title, will used in site title
   */
  @Value("${app.title}")
  private String appTitle;
  /**
   * application id for third party RESTful auth
   */
  @Value("${app.appid}")
  private String appId;
  /**
   * application serct for third party RESTful auth
   */
  @Value("${app.appserct}")
  private String appSerct;

  //////////////////////////////////////////
  @Value("${spring.jackson.date-format}")
  private String dateFormatPattern;

  @Value("${logging.file}")
  private String logPath;

  ////////////////////////////////////////////////
  /**
   * need setup HTTP proxy or not for third party RESTful
   */
  @Value("${app.proxy.enable:false}")
  private boolean needProxy;
  /**
   * HTTP proxy host for third party RESTful
   */
  @Value("${app.proxy.host}")
  private String proxyHost;
  /**
   * HTTP proxy port for third party RESTful
   */
  @Value("${app.proxy.port}")
  private Integer proxyPort;
  /**
   * HTTP proxy need auth or not for third party RESTful
   */
  @Value("${app.proxy.auth.enable:false}")
  private boolean proxyAuthEnable;
  /**
   * HTTP proxy auth name for third party RESTful
   */
  @Value("${app.proxy.auth.name}")
  private String proxyAuthName;
  /**
   * HTTP proxy auth password for third party RESTful
   */
  @Value("${app.proxy.auth.pass}")
  private String proxyAuthPass;
  /**
   * Third party RESTful API URL
   */
  @Value("${third.sync.apiroot}")
  private String thirdSyncAPIRoot;
  //////////////////////////////////////////////////

  public String getAppVersion() {
    return appVersion;
  }

  public String getAppTitle() {
    return appTitle;
  }

  public String getDateFormatPattern() {
    return dateFormatPattern;
  }

  public String getLogPath() {
    return logPath;
  }

  public String getAppId() {
    return appId;
  }

  public String getAppSerct() {
    return appSerct;
  }

  public boolean isNeedProxy() {
    return needProxy;
  }

  public String getProxyHost() {
    return proxyHost;
  }

  public Integer getProxyPort() {
    return proxyPort;
  }

  public boolean isProxyAuthEnable() {
    return proxyAuthEnable;
  }

  public String getProxyAuthName() {
    return proxyAuthName;
  }

  public String getProxyAuthPass() {
    return proxyAuthPass;
  }

  public String getThirdPartySyncAPIRoot() {
    return thirdSyncAPIRoot;
  }

  @Override
  public String toString() {
    return "AppConfig [appVersion=" + appVersion + ", appTitle=" + appTitle + ", appId=" + appId
        + ", appSerct=" + appSerct + ", dateFormatPattern=" + dateFormatPattern + ", logPath="
        + logPath + ", needProxy=" + needProxy + ", proxyHost=" + proxyHost + ", proxyPort="
        + proxyPort + ", proxyAuthEnable=" + proxyAuthEnable + ", proxyAuthName=" + proxyAuthName
        + ", proxyAuthPass=" + proxyAuthPass + ", thirdSyncAPIRoot=" + thirdSyncAPIRoot + "]";
  }

}
