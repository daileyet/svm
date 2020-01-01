package com.openthinks.svm.web.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.openthinks.libs.utilities.json.JSON;
import com.openthinks.libs.utilities.json.JSONArray;
import com.openthinks.libs.utilities.json.JSONObject;
import com.openthinks.svm.core.AppConstants;
import com.openthinks.svm.core.model.BizSequence;
import com.openthinks.svm.core.model.BizSequenceVw;
import com.openthinks.svm.core.model.BizSequenceVwExample;
import com.openthinks.svm.core.model.BizSequenceVwExample.Criteria;
import com.openthinks.svm.core.service.SequenceService;
import com.openthinks.svm.web.support.model.SearchParams;
import com.openthinks.svm.web.support.util.JSONConvertersUtil;
import com.openthinks.svm.web.support.util.ResponseJSON;
import net.sourceforge.orm.mybatis.Page;

/**
 * @author dailey.dai@openthinks.com
 *
 */
@RestController
@RequestMapping("/api/seq")
public class SequenceAPI {
  private static final Logger LOGGER = LoggerFactory.getLogger(SequenceAPI.class);
  @Autowired
  SequenceService sequenceService;

  @RequestMapping("/next")
  public JSONObject getNextSeq(@RequestParam(name = "prj_short") String prjShortName) {
    JSONObject jsonObj = JSON.object();
    jsonObj.addProperty("nextVal", sequenceService.next(prjShortName));
    return jsonObj;
  }

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
    BizSequenceVwExample example = new BizSequenceVwExample();
    Criteria cri = example.createCriteria();
    String versionF = searchParams.getSearch();
    if (versionF != null && versionF.trim().length() > 0) {
      cri.andPrjShortNameLike("%" + versionF.trim() + "%");
    }
    long totalCount = sequenceService.countSeqCount(example);
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
    Page<BizSequenceVw> page = new Page<>(searchParams.getPage(), searchParams.getRecPerPage());
    page.setTotalCount((int) totalCount);
    sequenceService.searchSeq(example, page).forEach(head -> {
      JSONObject jsonObject = JSONConvertersUtil.perparedAndGet(head);
      data.add(jsonObject);
    });
    return true;
  }

  public JSONObject saveSeq(BizSequence record) {
    boolean isSuccess = true;
    try {
      isSuccess = sequenceService.save(record);
    } catch (Exception e) {
      isSuccess = false;
      LOGGER.error("Failed to update BizSequence:{} by reason:{}", record, e);
    }
    if (!isSuccess) {
      return new ResponseJSON().error("更新流水号信息失败.").get();
    }
    return JSON.object();
  }

  public JSONObject deleteSeq(Long id) {
    boolean isSuccess = true;
    try {
      isSuccess = sequenceService.delete(id);
    } catch (Exception e) {
      isSuccess = false;
      LOGGER.error("Failed to delete BizSequence id:{} by reason:{}", id, e);
    }
    if (!isSuccess) {
      return new ResponseJSON().error("删除流水号信息失败.").get();
    }
    return JSON.object();
  }

}
