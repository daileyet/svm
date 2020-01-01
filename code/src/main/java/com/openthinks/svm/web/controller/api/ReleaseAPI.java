package com.openthinks.svm.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.openthinks.libs.utilities.json.JSON;
import com.openthinks.libs.utilities.json.JSONArray;
import com.openthinks.libs.utilities.json.JSONObject;
import com.openthinks.svm.core.AppConstants;
import com.openthinks.svm.core.model.BizReleaseInfo;
import com.openthinks.svm.core.model.BizReleaseInfoExample;
import com.openthinks.svm.core.model.BizReleaseInfoExample.Criteria;
import com.openthinks.svm.core.service.ReleaseInfoService;
import com.openthinks.svm.web.support.model.SearchParams;
import com.openthinks.svm.web.support.util.JSONConvertersUtil;
import net.sourceforge.orm.mybatis.Page;

/**
 * ClassName: ProjectAPI <br>
 * date: Mar 15, 2019 1:19:33 PM <br>
 * 
 * @author dailey.dai@openthinks.com
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/api/release")
public class ReleaseAPI {
  // private static final Logger LOGGER = LoggerFactory.getLogger(ReleaseAPI.class);
  @Autowired
  ReleaseInfoService releaseInfoService;


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
    BizReleaseInfoExample example = new BizReleaseInfoExample();
    Criteria cri = example.createCriteria();
    String versionF = searchParams.getSearch();
    if (versionF != null && versionF.trim().length() > 0) {
      cri.andReleaseNumLike("%" + versionF.trim() + "%");
    }
    long totalCount = releaseInfoService.count(example);
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
    } else {
      example.setOrderByClause("create_date desc");
    }
    Page<BizReleaseInfo> page = new Page<>(searchParams.getPage(), searchParams.getRecPerPage());
    page.setTotalCount((int) totalCount);
    releaseInfoService.search(example, page).forEach(head -> {
      JSONObject jsonObject = JSONConvertersUtil.perparedAndGet(head);
      data.add(jsonObject);
    });
    return true;
  }

}
