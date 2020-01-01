package com.openthinks.svm.core.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.openthinks.svm.core.ReleaseType;
import com.openthinks.svm.web.support.util.JSONUtils;

/**
 * ClassName: ThirdPartyAPIService <br>
 * Function: Third party system API service. <br>
 * date: Apr 12, 2019 4:05:46 PM <br>
 * 
 * @author dailey.dai@openthinks.com
 * @since JDK 1.8
 */
public interface ThirdPartyAPIService {

  /**
   * 
   * requestToken:request API token from Third Party system API. <br>
   * 
   * @return String token
   */
  String requestToken();

  /**
   * 
   * postVersions: post sync version data to Third Party system. <br>
   * 
   */
  void postVersions(List<VersionSyncModel> syncModeList, String token) throws Exception;


  /**
   * the entry data model which need sync to third-party system<BR>
   * JSON format:<BR>
   * <code>
   * {
   *    v:"P1_191111_10",
   *    rels:{
   *        "1":"APP_1111",
   *        "2":"MCU_1011"
   *    }
   * }
   * </code>
   * 
   * @author dailey.dai@openthinks.com
   *
   */
  public static class VersionSyncModel {
    public static final String PROP_NAME_V = "v";
    public static final String PROP_NAME_RELS = "rels";
    /**
     * main version
     */
    private String v;
    /**
     * related release/sub component informations;JSON format for each element
     */
    // private String[] rels = new String[ReleaseType.values().size()];

    private Map<String, String> rels = new HashMap<>();

    public String getV() {
      return v;
    }

    public void setV(String v) {
      this.v = v;
    }

    public Map<String, String> getRels() {
      return rels;
    }

    public void setRels(Map<String, String> rels) {
      this.rels = rels;
    }

    public String getReleaseVersion(ReleaseType type) {
      if (rels == null) {
        return null;
      }
      return rels.get(type.ordinalStr());
    }

    public void setReleaseVersion(ReleaseType type, String val) {
      if (rels == null) {
        return;
      }
      rels.put(type.ordinalStr(), val);
    }

    public String getReleaseJSON() {
      Map<String, String> rels = new HashMap<>();
      for (ReleaseType type : ReleaseType.values()) {
        rels.put(type.toXMLTag(), getReleaseVersion(type));
      }
      return JSONUtils.stringify(rels);
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((rels == null) ? 0 : rels.hashCode());
      result = prime * result + ((v == null) ? 0 : v.hashCode());
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      VersionSyncModel other = (VersionSyncModel) obj;
      if (rels == null) {
        if (other.rels != null)
          return false;
      } else if (!rels.equals(other.rels))
        return false;
      if (v == null) {
        if (other.v != null)
          return false;
      } else if (!v.equals(other.v))
        return false;
      return true;
    }

    @Override
    public String toString() {
      return "VersionSyncModel [v=" + v + ", rels=" + rels + "]";
    }
  }
}
