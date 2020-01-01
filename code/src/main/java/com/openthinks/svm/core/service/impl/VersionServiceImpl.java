package com.openthinks.svm.core.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.openthinks.svm.core.AppConstants;
import com.openthinks.svm.core.ReleaseType;
import com.openthinks.svm.core.mapper.BizReleaseInfoMapper;
import com.openthinks.svm.core.mapper.BizVersionMapper;
import com.openthinks.svm.core.mapper.BizVersionReleaseShipMapper;
import com.openthinks.svm.core.model.BizSequence;
import com.openthinks.svm.core.model.BizVersion;
import com.openthinks.svm.core.model.BizVersionExample;
import com.openthinks.svm.core.model.BizVersionReleaseShip;
import com.openthinks.svm.core.model.BizVersionReleaseShipExample;
import com.openthinks.svm.core.service.SequenceService;
import com.openthinks.svm.core.service.VersionService;
import com.openthinks.svm.core.util.DateUtils;
import com.openthinks.svm.web.support.model.BuildInfo;
import com.openthinks.svm.web.support.model.BuildInfo.ReleaseModel;
import net.sourceforge.orm.mybatis.Page;

/**
 * @author dailey.dai@openthinks.com
 *
 */
@Service
public class VersionServiceImpl implements VersionService {

  @Autowired
  BizVersionMapper versionMapper;

  @Autowired
  BizReleaseInfoMapper releaseInfoMapper;

  @Autowired
  BizVersionReleaseShipMapper versionReleaseShipMapper;

  @Autowired
  SequenceService sequenceService;

  @Override
  public long countBuildCount(BizVersionExample example) {
    return versionMapper.countByExample(example);
  }

  @Override
  public List<BizVersion> searchBuild(BizVersionExample example, Page<BizVersion> page) {
    return versionMapper.selectByExampleWithRowbounds(example, page);
  }

  @Override
  public boolean save(BizVersion record) {
    if (record.getId() == null) {
      if (record.getCreateDate() == null)
        record.setCreateDate(new Date());
      boolean isSuccess = versionMapper.insertSelective(record) > 0;
      return isSuccess;
    } else {
      if (record.getUpdateDate() == null)
        record.setUpdateDate(DateUtils.now());
      return versionMapper.updateByPrimaryKeySelective(record) > 0;
    }
  }

  @Override
  public BizVersion findByVersionNumber(String buildNumber) {
    BizVersionExample example = new BizVersionExample();
    example.createCriteria().andNumberEqualTo(buildNumber);
    return versionMapper.selectOneByExampleWithBLOBs(example);
  }

  @Override
  public boolean deleteBuild(Long id) {
    return versionMapper.deleteByPrimaryKey(id) > 0;
  }

  @Override
  public BizVersion findByPrimaryKey(Long vnId) {
    BizVersionExample example = new BizVersionExample();
    example.createCriteria().andIdEqualTo(vnId);
    return versionMapper.selectOneByExampleWithBLOBs(example);
  }

  @Transactional
  @Override
  public boolean createAndIncreamentSequence(BizVersion record, Long projectId) {
    if (record.getCreateDate() == null)
      record.setCreateDate(DateUtils.now());
    boolean isSuccess = versionMapper.insertSelective(record) > 0;
    if (isSuccess) {
      BizSequence sequence = sequenceService.findSequenceByPrjId(projectId);
      if (sequence == null) {
        sequence = new BizSequence.Builder().prjId(projectId).build();
        sequenceService.saveSequence(sequence);
      }
      sequenceService.increament(sequence.getId());
    }
    return isSuccess;
  }


  @Transactional
  @Override
  public boolean createAll(BuildInfo buildInfo) {
    BizVersion record =
        new BizVersion.Builder().number(buildInfo.getBuild_no()).projectId(buildInfo.getPrj_id())
            .valid(AppConstants.BuildState.VALID.value()).description(buildInfo.getDesc()).build();
    boolean isSuccess = false;
    if (buildInfo.isSeq_continus()) {
      isSuccess = createAndIncreamentSequence(record, buildInfo.getPrj_id());
    } else {
      isSuccess = save(record);
    }
    final Long vId = record.getId();
    if (isSuccess) {
      for (ReleaseType type : ReleaseType.values()) {
        ReleaseModel model = buildInfo.getReleaseModel(type);
        if (model != null && model.getId() != null) {
          BizVersionReleaseShip ship =
              new BizVersionReleaseShip.Builder().vId(vId).rId(model.getId()).build();
          versionReleaseShipMapper.insertSelective(ship);
        }
      }
    }
    return isSuccess;
  }

  @Transactional
  @Override
  public boolean saveAll(BizVersion record, BuildInfo buildInfo) {
    boolean isSuccess = save(record);
    final Long vId = record.getId();
    if (isSuccess) {
      BizVersionReleaseShipExample example = new BizVersionReleaseShipExample();
      example.createCriteria().andVIdEqualTo(vId);
      versionReleaseShipMapper.deleteByExample(example);
      for (ReleaseType type : ReleaseType.values()) {
        ReleaseModel model = buildInfo.getReleaseModel(type);
        if (model != null && model.getId() != null) {
          BizVersionReleaseShip ship =
              new BizVersionReleaseShip.Builder().vId(vId).rId(model.getId()).build();
          versionReleaseShipMapper.insertSelective(ship);
        }
      }
    }
    return isSuccess;
  }

  @Override
  public List<BizVersion> find(BizVersionExample example) {
    return versionMapper.selectByExample(example);
  }

  @Override
  public void tagSynced(String syncVersionNumber) {
    BizVersion record = findByVersionNumber(syncVersionNumber);
    if (record != null) {
      record.setSyncStatus(AppConstants.BuildSyncStatus.SYNC.value());
      record.setLastSyncDate(DateUtils.now());
    }
    versionMapper.updateByPrimaryKeySelective(record);
  }
}
