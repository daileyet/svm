package com.openthinks.svm.web.support.model;

import java.util.Map;
import com.openthinks.svm.core.ReleaseType;
import net.sf.ehcache.util.concurrent.ConcurrentHashMap;

/**
 * 主版本表单模型<BR>
 * 
 * @author dailey.dai@openthinks.com
 *
 */
public class BuildInfo {
  private Long id;
  // project instance id
  private Long prj_id;
  private String prj_short;
  private Integer seq_number;
  private boolean seq_continus = true;
  private String release_date;
  // meta project prj_id
  private String prj;
  private String productline;
  private String build_no;
  private String desc;

  private Map<String, ReleaseModel> map;

  public BuildInfo() {
    map = new ConcurrentHashMap<>();
  }

  public final ReleaseModel getReleaseModel(ReleaseType type) {
    ReleaseModel model = null;
    if (type != null && type.getValue() != null) {
      model = map.get(type.getValue());
      model.setType(type.getValue());
      return model;
    }
    return model;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getPrj_id() {
    return prj_id;
  }

  public void setPrj_id(Long prj_id) {
    this.prj_id = prj_id;
  }

  public String getPrj_short() {
    return prj_short;
  }

  public void setPrj_short(String prj_short) {
    this.prj_short = prj_short;
  }

  public Integer getSeq_number() {
    return seq_number;
  }

  public void setSeq_number(int seq_number) {
    this.seq_number = seq_number;
  }

  public boolean isSeq_continus() {
    return seq_continus;
  }

  public void setSeq_continus(boolean seq_continus) {
    this.seq_continus = seq_continus;
  }

  public String getRelease_date() {
    return release_date;
  }

  public void setRelease_date(String release_date) {
    this.release_date = release_date;
  }

  public String getPrj() {
    return prj;
  }

  public void setPrj(String prj) {
    this.prj = prj;
  }

  public String getProductline() {
    return productline;
  }

  public void setProductline(String productline) {
    this.productline = productline;
  }

  public String getBuild_no() {
    return build_no;
  }

  public void setBuild_no(String build_no) {
    this.build_no = build_no;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public void setSeq_number(Integer seq_number) {
    this.seq_number = seq_number;
  }

  public Map<String, ReleaseModel> getMap() {
    return map;
  }

  public void setMap(Map<String, ReleaseModel> map) {
    this.map = map;
  }

  static public class ReleaseModel {
    private Long id;
    private String type;

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
    }

    @Override
    public String toString() {
      return "ReleaseModel [id=" + id + ", type=" + type + "]";
    }

  }
}
