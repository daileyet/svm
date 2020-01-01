package com.openthinks.svm.web.support.model;

import java.util.Arrays;

/**
 * Third-party system API interaction objects; used for JSON
 * 
 * @author dailey.dai@openthinks.com
 *
 */
public class ThirdPartyAPIObjects {

  public static class APIBaseResponse {
    protected String result;
    protected String message;

    public String getResult() {
      return result;
    }

    public void setResult(String result) {
      this.result = result;
    }

    public String getMessage() {
      return message;
    }

    public void setMessage(String message) {
      this.message = message;
    }

    @Override
    public String toString() {
      return "APIBaseResponse [result=" + result + ", message=" + message + "]";
    }

  }

  /**
   * Token structure
   * 
   * @author dailey.dai@openthinks.com
   *
   */
  public static class TokenData {
    protected String token;
    protected String refresh_token;
    protected Integer exprie_in;

    public String getToken() {
      return token;
    }

    public void setToken(String token) {
      this.token = token;
    }

    public String getRefresh_token() {
      return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
      this.refresh_token = refresh_token;
    }

    public Integer getExprie_in() {
      return exprie_in;
    }

    public void setExprie_in(Integer exprie_in) {
      this.exprie_in = exprie_in;
    }


  }

  /**
   * Token request action response definition<BR>
   * JSON format:<BR>
   * <code>
   * {
   *    data:{
   *        token:"xxx",
   *        refresh_token:"",
   *        exprie_in:1
   *    }
   * }
   * </code>s
   * 
   * @author dailey.dai@openthinks.com
   *
   */
  public static class RequestTokenResponse extends APIBaseResponse {
    protected TokenData data;

    public TokenData getData() {
      return data;
    }

    public void setData(TokenData data) {
      this.data = data;
    }


  }

  /**
   * sync post action response
   * 
   * @author dailey.dai@openthinks.com
   *
   */
  public static class SyncPostResponse extends APIBaseResponse {
    protected String[] data = new String[0];

    public String[] getData() {
      return data;
    }

    public void setData(String[] data) {
      this.data = data;
    }

    @Override
    public String toString() {
      return "SyncPostResponse [data=" + Arrays.toString(data) + "]";
    }


  }

}
