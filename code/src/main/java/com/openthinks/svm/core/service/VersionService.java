package com.openthinks.svm.core.service;

import java.util.List;
import com.openthinks.svm.core.model.BizVersion;
import com.openthinks.svm.core.model.BizVersionExample;
import com.openthinks.svm.web.support.model.BuildInfo;
import net.sourceforge.orm.mybatis.Page;

/**
 * 版本信息service
 * 
 * @author dailey.dai@openthinks.com
 *
 */
public interface VersionService {

  long countBuildCount(BizVersionExample example);

  List<BizVersion> searchBuild(BizVersionExample example, Page<BizVersion> page);

  boolean save(BizVersion record);

  boolean saveAll(BizVersion record, BuildInfo buildInfo);

  boolean createAndIncreamentSequence(BizVersion record, Long projectId);

  BizVersion findByVersionNumber(String buildNumber);

  boolean deleteBuild(Long id);

  BizVersion findByPrimaryKey(Long vnId);

  boolean createAll(BuildInfo buildInfo);

  List<BizVersion> find(BizVersionExample example);

  void tagSynced(String syncVersionNumber);

}
