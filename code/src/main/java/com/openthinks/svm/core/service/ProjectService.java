package com.openthinks.svm.core.service;

import java.util.List;
import com.openthinks.svm.core.model.BizMetaRelease;
import com.openthinks.svm.core.model.BizProject;
import com.openthinks.svm.core.model.BizProjectExample;
import net.sourceforge.orm.mybatis.Page;

/**
 * ClassName: ProjectService <br>
 * 项目信息service
 * 
 * @author dailey.dai@openthinks.com
 * @since JDK 1.8
 */
public interface ProjectService {
  public BizProject findByPrjShortName(String name);

  public long count(BizProjectExample example);

  public List<BizProject> search(BizProjectExample example, Page<BizProject> page);

  public List<BizProject> search(BizProjectExample example);

  public List<BizProject> findNeedSyncProjects();

  public BizProject findByPrimaryKey(Long key);

  public boolean save(BizProject record);

  public boolean delete(Long id);

  public boolean saveWithMetaReleases(BizProject record, List<BizMetaRelease> metaReleases);

  public List<Long> findBindMetaReleaseIds(Long projectId);

  public List<BizMetaRelease> findBindMetaReleases(Long projectId);

  public List<BizProject> findAllProjects();
}
