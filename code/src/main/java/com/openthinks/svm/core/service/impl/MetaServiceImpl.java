package com.openthinks.svm.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.openthinks.svm.core.AppConstants.AttributeCategory;
import com.openthinks.svm.core.AppConstants.MetaProjectState;
import com.openthinks.svm.core.mapper.BizMetaAttrMapper;
import com.openthinks.svm.core.mapper.BizMetaProductlineMapper;
import com.openthinks.svm.core.mapper.BizMetaProjectMapper;
import com.openthinks.svm.core.mapper.BizMetaReleaseMapper;
import com.openthinks.svm.core.model.BizMetaAttr;
import com.openthinks.svm.core.model.BizMetaAttrExample;
import com.openthinks.svm.core.model.BizMetaProductline;
import com.openthinks.svm.core.model.BizMetaProductlineExample;
import com.openthinks.svm.core.model.BizMetaProject;
import com.openthinks.svm.core.model.BizMetaProjectExample;
import com.openthinks.svm.core.model.BizMetaRelease;
import com.openthinks.svm.core.model.BizMetaReleaseExample;
import com.openthinks.svm.core.service.MetaService;
import net.sourceforge.orm.mybatis.Page;

/**
 * @author dailey.dai@openthinks.com
 *
 */
@Service
public class MetaServiceImpl implements MetaService {

  @Autowired
  BizMetaProjectMapper metaProjectMapper;

  @Autowired
  BizMetaProductlineMapper metaProductlineMapper;

  @Autowired
  BizMetaReleaseMapper metaReleaseMapper;

  @Autowired
  BizMetaAttrMapper metaAttrMapper;

  @Override
  public List<BizMetaProject> findMetaProjects(BizMetaProjectExample example) {
    return metaProjectMapper.selectByExample(example);
  }

  @Override
  public List<BizMetaProductline> findMetaProductlines(BizMetaProductlineExample example) {
    return metaProductlineMapper.selectByExample(example);
  }

  @Override
  public List<BizMetaProject> findMetaProjects(String category) {
    BizMetaProjectExample example = new BizMetaProjectExample();
    example.createCriteria().andCategoryEqualTo(category);
    example.setOrderByClause("prj_id");
    return findMetaProjects(example);
  }

  @Override
  public List<BizMetaProductline> findMetaProductlines(String category, String prjId) {
    BizMetaProductlineExample example = new BizMetaProductlineExample();
    example.createCriteria().andCategoryEqualTo(category).andPrjIdEqualTo(prjId);
    example.setOrderByClause("pl_id");
    return findMetaProductlines(example);
  }

  @Override
  public List<BizMetaProject> findAllMetaProjects() {
    BizMetaProjectExample example = new BizMetaProjectExample();
    example.setOrderByClause("prj_id");
    return findMetaProjects(example);
  }

  @Override
  public List<BizMetaRelease> findAllMetaReleases() {
    BizMetaReleaseExample example = new BizMetaReleaseExample();
    return metaReleaseMapper.selectByExample(example);
  }

  @Override
  public List<BizMetaProject> findAllActiveMetaProjects() {
    BizMetaProjectExample example = new BizMetaProjectExample();
    example.createCriteria().andActiveEqualTo(MetaProjectState.ACTIVE.value());
    example.setOrderByClause("prj_id");
    return findMetaProjects(example);
  }

  @Override
  public boolean saveMetaRelease(BizMetaRelease mr) {
    if (mr.getId() == null) {
      return metaReleaseMapper.insertSelective(mr) > 0;
    } else {
      return metaReleaseMapper.updateByPrimaryKeySelective(mr) > 0;
    }
  }

  @Override
  public BizMetaProductline findMetaProductlineByPrimaryKey(Long id) {
    return metaProductlineMapper.selectByPrimaryKey(id);
  }

  @Override
  public long countProductline(BizMetaProductlineExample example) {
    return metaProductlineMapper.countByExample(example);
  }

  @Override
  public List<BizMetaProductline> searchProductline(BizMetaProductlineExample example,
      Page<BizMetaProductline> page) {
    return metaProductlineMapper.selectByExampleWithRowbounds(example, page);
  }

  @Override
  public boolean saveProductline(BizMetaProductline record) {
    if (record.getId() != null) {
      return metaProductlineMapper.updateByPrimaryKeySelective(record) > 0;
    } else {
      return metaProductlineMapper.insertSelective(record) > 0;
    }
  }

  @Override
  public boolean deleteProductline(Long id) {
    return metaProductlineMapper.deleteByPrimaryKey(id) > 0;
  }

  @Override
  public BizMetaProject findMetaProjectByPrjId(String prjId) {
    BizMetaProjectExample example = new BizMetaProjectExample();
    example.createCriteria().andPrjIdEqualTo(prjId);
    return metaProjectMapper.selectOneByExample(example);
  }

  @Override
  public List<BizMetaAttr> searchMetaAttr(BizMetaAttrExample example, Page<BizMetaAttr> page) {
    return metaAttrMapper.selectByExampleWithRowbounds(example, page);
  }

  @Override
  public BizMetaAttr findMetaAttrByPrimaryKey(Long id) {
    return metaAttrMapper.selectByPrimaryKey(id);
  }

  @Override
  public long countMetaAttr(BizMetaAttrExample example) {
    return metaAttrMapper.countByExample(example);
  }

  @Override
  public boolean saveMetaAttr(BizMetaAttr record) {
    if (record.getId() != null) {
      return metaAttrMapper.updateByPrimaryKeySelective(record) > 0;
    } else {
      return metaAttrMapper.insertSelective(record) > 0;
    }
  }

  @Override
  public boolean deleteMetaAttr(Long id) {
    return metaAttrMapper.deleteByPrimaryKey(id) > 0;
  }

  @Override
  public Map<AttributeCategory, List<BizMetaAttr>> finadAllMetaAttrGroupByCategory() {
    BizMetaAttrExample example = new BizMetaAttrExample();
    List<BizMetaAttr> all = metaAttrMapper.selectByExample(example);
    Map<AttributeCategory, List<BizMetaAttr>> map = new ConcurrentHashMap<>();
    for (BizMetaAttr attr : all) {
      String cate = attr.getCategory();
      AttributeCategory key = AttributeCategory.toAttributeCategory(cate);
      List<BizMetaAttr> cateList = map.get(key);
      if (cateList == null) {
        cateList = new ArrayList<>();
      }
      cateList.add(attr);
      map.put(key, cateList);
    }
    return map;
  }

  @Override
  public List<BizMetaAttr> findMetaAttrByCategory(String category) {
    BizMetaAttrExample example = new BizMetaAttrExample();
    example.createCriteria().andCategoryEqualTo(category);
    return metaAttrMapper.selectByExample(example);
  }
}
