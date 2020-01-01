package com.openthinks.svm.web.support.model;

import java.util.ArrayList;
import java.util.List;
import com.openthinks.svm.core.model.BizReleaseInfo;
import com.openthinks.svm.core.model.BizVersion;

/**
 * 主版本信息及所有关联的子模块版本信息模型
 * 
 * @author dailey.dai@openthinks.com
 *
 */
public class VersionWithReleaseInfo extends BizVersion {

  private List<BizReleaseInfo> releaseInfos;

  public VersionWithReleaseInfo(BizVersion record, List<BizReleaseInfo> releaseInfos) {
    this.setCreateBy(record.getCreateBy());
    this.setCreateDate(record.getCreateDate());
    this.setDescription(record.getDescription());
    this.setId(record.getId());
    this.setNumber(record.getNumber());
    this.setUpdateBy(record.getUpdateBy());
    this.setUpdateDate(record.getUpdateDate());
    this.setValid(record.getValid());
    this.releaseInfos = (releaseInfos == null ? new ArrayList<BizReleaseInfo>() : releaseInfos);
  }

  public String getReleaseInfosStr() {
    return getReleaseInfosStr(releaseInfos);
  }

  public static String getReleaseInfosStr(List<BizReleaseInfo> releaseInfos) {
    if (releaseInfos.isEmpty())
      return "";
    StringBuilder sb = new StringBuilder();
    for (BizReleaseInfo info : releaseInfos) {
      sb.append(info.getType()).append(":").append(info.getReleaseNum()).append(";");
    }
    return sb.substring(0, sb.length() - 1);
  }
}
