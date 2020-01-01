package com.openthinks.svm.core.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.openthinks.svm.core.mapper.BizReleaseAttrMapper;
import com.openthinks.svm.core.mapper.BizReleaseInfoMapper;
import com.openthinks.svm.core.mapper.BizVersionReleaseShipMapper;
import com.openthinks.svm.core.model.BizReleaseAttr;
import com.openthinks.svm.core.model.BizReleaseAttrExample;
import com.openthinks.svm.core.model.BizReleaseInfo;
import com.openthinks.svm.core.model.BizReleaseInfoExample;
import com.openthinks.svm.core.model.BizReleaseInfoWithBLOBs;
import com.openthinks.svm.core.model.BizVersionReleaseShip;
import com.openthinks.svm.core.model.BizVersionReleaseShipExample;
import com.openthinks.svm.core.service.ReleaseInfoService;
import com.openthinks.svm.core.util.DateUtils;
import net.sourceforge.orm.mybatis.Page;

/**
 * @author dailey.dai@openthinks.com
 *
 */
@Service
public class ReleaseInfoServiceImpl implements ReleaseInfoService {

  @Autowired
  BizReleaseInfoMapper releaseInfoMapper;

  @Autowired
  BizVersionReleaseShipMapper versionReleaseShipMapper;

  @Autowired
  BizReleaseAttrMapper releaseAttrMapper;

  @Override
  public List<BizReleaseInfo> searchByVNID(Long id) {
    BizVersionReleaseShipExample example = new BizVersionReleaseShipExample();
    example.createCriteria().andVIdEqualTo(id);
    List<Long> releaseIds = versionReleaseShipMapper.selectByExample(example).stream()
        .mapToLong(record -> record.getrId()).boxed().collect(Collectors.toList());
    if (releaseIds.isEmpty())
      return Collections.emptyList();
    BizReleaseInfoExample example2 = new BizReleaseInfoExample();
    example2.createCriteria().andIdIn(releaseIds);
    example2.setOrderByClause("type");
    return releaseInfoMapper.selectByExample(example2);
  }

  @Override
  public List<BizVersionReleaseShip> searchShipByVNID(Long id) {
    BizVersionReleaseShipExample example = new BizVersionReleaseShipExample();
    example.createCriteria().andVIdEqualTo(id);
    return versionReleaseShipMapper.selectByExample(example);
  }

  @Override
  public boolean saveWithBLOBs(BizReleaseInfoWithBLOBs record) {
    if (record.getId() == null) {
      if (record.getCreateDate() == null) {
        record.setCreateDate(DateUtils.now());
      }
      return releaseInfoMapper.insertSelective(record) > 0;
    } else {
      return releaseInfoMapper.updateByPrimaryKeySelective(record) > 0;
    }

  }

  @Transactional
  @Override
  public boolean saveWithBLOBsAndAttr(BizReleaseInfoWithBLOBs record, List<BizReleaseAttr> attrs) {
    boolean isSuccess = saveWithBLOBs(record);
    if (isSuccess) {
      long releaseId = record.getId();
      BizReleaseAttrExample example = new BizReleaseAttrExample();
      example.createCriteria().andReleaseIdEqualTo(releaseId);
      releaseAttrMapper.deleteByExample(example);
      for (BizReleaseAttr attr : attrs) {
        attr.setReleaseId(releaseId);
        releaseAttrMapper.insertSelective(attr);
      }
    }
    return isSuccess;
  }

  @Override
  public BizReleaseInfoWithBLOBs searchByPrimaryKey(Long releaseId) {
    return releaseInfoMapper.selectByPrimaryKey(releaseId);
  }

  @Override
  public boolean deleteByPrimaryKey(Long releaseId) {
    return releaseInfoMapper.deleteByPrimaryKey(releaseId) > 0;
  }

  @Override
  public long count(BizReleaseInfoExample example) {
    return releaseInfoMapper.countByExample(example);
  }


  @Override
  public List<BizReleaseInfo> search(BizReleaseInfoExample example, Page<BizReleaseInfo> page) {
    return releaseInfoMapper.selectByExampleWithRowbounds(example, page);
  }

  @Override
  public Map<String, List<BizReleaseInfo>> searchAllReleaseGroupByType() {
    Map<String, List<BizReleaseInfo>> map = new HashMap<>();
    BizReleaseInfoExample example = new BizReleaseInfoExample();
    example.setOrderByClause("create_date desc");
    List<BizReleaseInfo> allList = releaseInfoMapper.selectByExample(example);
    allList.forEach(record -> {
      List<BizReleaseInfo> list = map.get(record.getType());
      if (list == null) {
        list = new ArrayList<>();
      }
      list.add(record);
      map.put(record.getType(), list);
    });
    return map;
  }

  @Override
  public Map<String, BizReleaseInfo> searchReleaseGroupByType(Long versionId) {
    Map<String, BizReleaseInfo> map = new HashMap<>();
    List<BizReleaseInfo> releases = searchByVNID(versionId);
    releases.forEach(record -> {
      map.put(record.getType(), record);
    });
    return map;
  }
}
