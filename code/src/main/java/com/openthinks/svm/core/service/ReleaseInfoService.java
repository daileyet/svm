package com.openthinks.svm.core.service;

import java.util.List;
import java.util.Map;
import com.openthinks.svm.core.model.BizReleaseAttr;
import com.openthinks.svm.core.model.BizReleaseInfo;
import com.openthinks.svm.core.model.BizReleaseInfoExample;
import com.openthinks.svm.core.model.BizReleaseInfoWithBLOBs;
import com.openthinks.svm.core.model.BizVersionReleaseShip;
import net.sourceforge.orm.mybatis.Page;

/**
 * Release信息service
 * 
 * @author dailey.dai@openthinks.com
 *
 */
public interface ReleaseInfoService {

  List<BizReleaseInfo> searchByVNID(Long id);

  List<BizVersionReleaseShip> searchShipByVNID(Long id);

  boolean saveWithBLOBs(BizReleaseInfoWithBLOBs record);

  boolean saveWithBLOBsAndAttr(BizReleaseInfoWithBLOBs record, List<BizReleaseAttr> attrs);

  BizReleaseInfoWithBLOBs searchByPrimaryKey(Long releaseId);

  boolean deleteByPrimaryKey(Long releaseId);

  long count(BizReleaseInfoExample example);

  List<BizReleaseInfo> search(BizReleaseInfoExample example, Page<BizReleaseInfo> page);

  Map<String, List<BizReleaseInfo>> searchAllReleaseGroupByType();

  Map<String, BizReleaseInfo> searchReleaseGroupByType(Long versionId);

}
