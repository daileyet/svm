package com.openthinks.svm.core.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.openthinks.svm.core.mapper.BizMetaReleaseMapper;
import com.openthinks.svm.core.mapper.BizPrjMetaRelShipMapper;
import com.openthinks.svm.core.mapper.BizProjectMapper;
import com.openthinks.svm.core.model.BizMetaRelease;
import com.openthinks.svm.core.model.BizMetaReleaseExample;
import com.openthinks.svm.core.model.BizPrjMetaRelShip;
import com.openthinks.svm.core.model.BizPrjMetaRelShipExample;
import com.openthinks.svm.core.model.BizProject;
import com.openthinks.svm.core.model.BizProjectExample;
import com.openthinks.svm.core.service.ProjectService;
import com.openthinks.svm.core.util.DateUtils;
import net.sourceforge.orm.mybatis.Page;

/**
 * ClassName: ProjectServiceImpl <br>
 * date: Mar 15, 2019 11:38:25 AM <br>
 * 
 * @author dailey.dai@openthinks.com
 * @since JDK 1.8
 */
@Service
public class ProjectServiceImpl implements ProjectService {
  @Autowired
  BizProjectMapper projectMapper;

  @Autowired
  BizMetaReleaseMapper metaReleaseMapper;

  @Autowired
  BizPrjMetaRelShipMapper prjMetaRelShipMapper;


  @Override
  public BizProject findByPrjShortName(String name) {
    BizProjectExample example = new BizProjectExample();
    example.createCriteria().andShortNameEqualTo(name);
    return projectMapper.selectOneByExample(example);
  }

  @Override
  public long count(BizProjectExample example) {
    return projectMapper.countByExample(example);
  }

  @Override
  public List<BizProject> search(BizProjectExample example, Page<BizProject> page) {
    return projectMapper.selectByExampleWithRowbounds(example, page);
  }

  @Override
  public List<BizProject> search(BizProjectExample example) {
    return projectMapper.selectByExample(example);
  }

  @Override
  public List<BizProject> findNeedSyncProjects() {
    BizProjectExample example = new BizProjectExample();
    example.createCriteria().andNeedSyncIsNotNull().andNeedSyncEqualTo(true);
    return projectMapper.selectByExample(example);
  }

  @Override
  public BizProject findByPrimaryKey(Long key) {
    return projectMapper.selectByPrimaryKey(key);
  }

  @Override
  public boolean save(BizProject record) {
    if (record.getId() == null) {
      if (record.getCreateDate() == null)
        record.setCreateDate(DateUtils.now());
      return projectMapper.insertSelective(record) > 0;
    }
    return projectMapper.updateByPrimaryKeySelective(record) > 0;
  }

  /**
   * @see com.openthinks.svm.core.service.ProjectService#delete(java.lang.Long)
   */
  @Override
  public boolean delete(Long id) {
    return projectMapper.deleteByPrimaryKey(id) > 0;
  }

  /**
   * @see com.openthinks.svm.core.service.ProjectService#saveWithMetaReleases(com.openthinks.svm.core.model.BizSequence,
   *      java.util.List)
   */
  @Transactional
  @Override
  public boolean saveWithMetaReleases(BizProject record, List<BizMetaRelease> metaReleases) {
    boolean isSuccess = save(record);
    if (isSuccess) {
      BizPrjMetaRelShipExample example = new BizPrjMetaRelShipExample();
      example.createCriteria().andProjectIdEqualTo(record.getId());
      prjMetaRelShipMapper.deleteByExample(example);
      metaReleases.forEach(mrel -> {
        BizPrjMetaRelShip ship = new BizPrjMetaRelShip.Builder().projectId(record.getId())
            .metaReleaseId(mrel.getId()).build();
        prjMetaRelShipMapper.insertSelective(ship);
      });
    }
    return isSuccess;
  }

  @Override
  public List<BizMetaRelease> findBindMetaReleases(Long projectId) {
    BizPrjMetaRelShipExample example = new BizPrjMetaRelShipExample();
    example.createCriteria().andProjectIdEqualTo(projectId);
    List<BizPrjMetaRelShip> list = prjMetaRelShipMapper.selectByExample(example);
    if (list.isEmpty())
      return Collections.emptyList();
    BizMetaReleaseExample example2 = new BizMetaReleaseExample();
    List<Long> metaRelIds = list.stream().mapToLong(ship -> ship.getMetaReleaseId()).boxed()
        .collect(Collectors.toList());
    example2.createCriteria().andIdIn(metaRelIds);
    return metaReleaseMapper.selectByExample(example2);
  }

  @Override
  public List<Long> findBindMetaReleaseIds(Long projectId) {
    BizPrjMetaRelShipExample example = new BizPrjMetaRelShipExample();
    example.createCriteria().andProjectIdEqualTo(projectId);
    List<BizPrjMetaRelShip> list = prjMetaRelShipMapper.selectByExample(example);
    if (list == null) {
      list = new ArrayList<>();
    }
    List<Long> ids = new ArrayList<>();
    list.forEach(ship -> ids.add(ship.getId()));
    return ids;
  }

  @Override
  public List<BizProject> findAllProjects() {
    return projectMapper.selectByExample(new BizProjectExample());
  }

}
