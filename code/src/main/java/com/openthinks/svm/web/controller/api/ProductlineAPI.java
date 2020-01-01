package com.openthinks.svm.web.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.openthinks.libs.utilities.json.JSON;
import com.openthinks.libs.utilities.json.JSONArray;
import com.openthinks.libs.utilities.json.JSONObject;
import com.openthinks.svm.core.AppConstants;
import com.openthinks.svm.core.model.BizMetaProductline;
import com.openthinks.svm.core.model.BizMetaProductlineExample;
import com.openthinks.svm.core.model.BizMetaProductlineExample.Criteria;
import com.openthinks.svm.core.model.BizMetaProject;
import com.openthinks.svm.core.service.MetaService;
import com.openthinks.svm.web.support.model.SearchParams;
import com.openthinks.svm.web.support.util.JSONConvertersUtil;
import com.openthinks.svm.web.support.util.ResponseJSON;
import net.sourceforge.orm.mybatis.Page;

/**
 * ClassName: ProductlineAPI <br>
 * date: Apr 24, 2019 11:08:14 AM <br>
 * 
 * @author dailey.dai@openthinks.com
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/api/productline")
public class ProductlineAPI {
  private static final Logger LOGGER = LoggerFactory.getLogger(ProductlineAPI.class);
  @Autowired
  MetaService metaService;

  @GetMapping("/search")
  public Object searchBuilds(SearchParams searchParams) {
    JSONObject root = JSON.object();
    JSONArray data = root.createEmbedArray("data");
    boolean isSuccess = buildData(data, searchParams);
    JSONObject pager = root.createEmbedJSONObj("pager");
    buildPager(pager, searchParams);
    root.addProperty("result", isSuccess ? "success" : "fail");
    return root;
  }

  private void buildPager(JSONObject pager, SearchParams searchParams) {
    pager.addProperty("page", searchParams.getPage());
    pager.addProperty("recTotal", searchParams.getRecTotal());
    pager.addProperty("recPerPage", searchParams.getRecPerPage());
  }

  private boolean buildData(JSONArray data, SearchParams searchParams) {
    BizMetaProductlineExample example = new BizMetaProductlineExample();
    Criteria cri = example.createCriteria();
    String versionF = searchParams.getSearch();
    if (versionF != null && versionF.trim().length() > 0) {
      cri.andPlNameLike("%" + versionF.trim() + "%");
    }
    long totalCount = metaService.countProductline(example);
    if (totalCount == 0) {
      return true;
    }
    searchParams.setRecTotal((int) totalCount);
    String sortByF = searchParams.getSortBy();
    if (sortByF != null && sortByF.trim().length() > 0) {
      String orderF = searchParams.getOrder();
      String order = AppConstants.ORDER_ASC;
      if (orderF != null && orderF.trim().length() > 0) {
        if ("desc".equalsIgnoreCase(orderF.trim())) {
          order = AppConstants.ORDER_DESC;
        }
      }
      example.setOrderByClause(sortByF + " " + order);
    }
    Page<BizMetaProductline> page =
        new Page<>(searchParams.getPage(), searchParams.getRecPerPage());
    page.setTotalCount((int) totalCount);
    metaService.searchProductline(example, page).forEach(head -> {
      JSONObject jsonObject = JSONConvertersUtil.perparedAndGet(head);
      BizMetaProject project = metaService.findMetaProjectByPrjId(head.getPrjId());
      jsonObject.addProperty("prj_name", project == null ? "" : project.getPrjName());
      data.add(jsonObject);
    });
    return true;
  }

  public JSONObject save(BizMetaProductline record) {
    boolean isSuccess = true;
    try {
      isSuccess = metaService.saveProductline(record);
    } catch (Exception e) {
      isSuccess = false;
      LOGGER.error("Failed to update BizMetaCarline:{} by reason:{}", record, e);
    }
    if (!isSuccess) {
      return new ResponseJSON().error("更新车型信息失败.").get();
    }
    return JSON.object();
  }

  public JSONObject delete(Long id) {
    boolean isSuccess = true;
    try {
      isSuccess = metaService.deleteProductline(id);
    } catch (Exception e) {
      isSuccess = false;
      LOGGER.error("Failed to delete BizMetaCarline id:{} by reason:{}", id, e);
    }
    if (!isSuccess) {
      return new ResponseJSON().error("删除车型信息失败.").get();
    }
    return JSON.object();
  }

}
