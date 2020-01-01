/**
 * 
 */
package com.openthinks.svm.core.service;

import java.util.List;
import java.util.Map;
import com.openthinks.svm.core.AppConstants.AttributeCategory;
import com.openthinks.svm.core.model.BizMetaAttr;
import com.openthinks.svm.core.model.BizMetaAttrExample;
import com.openthinks.svm.core.model.BizMetaProductline;
import com.openthinks.svm.core.model.BizMetaProductlineExample;
import com.openthinks.svm.core.model.BizMetaProject;
import com.openthinks.svm.core.model.BizMetaProjectExample;
import com.openthinks.svm.core.model.BizMetaRelease;
import net.sourceforge.orm.mybatis.Page;

/**
 * 基础元信息service: 项目元信息,产品线元信息,元属性配置项信息
 * 
 * @author dailey.dai@openthinks.com
 *
 */
public interface MetaService {

  public List<BizMetaProject> findMetaProjects(BizMetaProjectExample example);

  public List<BizMetaProductline> findMetaProductlines(BizMetaProductlineExample example);

  public List<BizMetaProject> findAllMetaProjects();

  public List<BizMetaProject> findAllActiveMetaProjects();

  public List<BizMetaProject> findMetaProjects(String category);

  public BizMetaProject findMetaProjectByPrjId(String prjId);

  ///////////////////////////////////////////////////////////////////////////////////////////
  public List<BizMetaRelease> findAllMetaReleases();

  public boolean saveMetaRelease(BizMetaRelease mr);

  ///////////////////////////////////////////////////////////////////////////////////////////
  public List<BizMetaProductline> findMetaProductlines(String category, String prjId);

  public BizMetaProductline findMetaProductlineByPrimaryKey(Long id);

  public long countProductline(BizMetaProductlineExample example);

  public List<BizMetaProductline> searchProductline(BizMetaProductlineExample example,
      Page<BizMetaProductline> page);

  public boolean saveProductline(BizMetaProductline record);

  public boolean deleteProductline(Long id);
  ///////////////////////////////////////////////////////////////////////////////////////////

  public List<BizMetaAttr> searchMetaAttr(BizMetaAttrExample example, Page<BizMetaAttr> page);

  public BizMetaAttr findMetaAttrByPrimaryKey(Long id);

  public long countMetaAttr(BizMetaAttrExample example);

  public boolean saveMetaAttr(BizMetaAttr record);

  public boolean deleteMetaAttr(Long id);

  public Map<AttributeCategory, List<BizMetaAttr>> finadAllMetaAttrGroupByCategory();

  public List<BizMetaAttr> findMetaAttrByCategory(String category);

}
