package com.openthinks.svm.web.controller.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.openthinks.libs.utilities.json.JSON;
import com.openthinks.libs.utilities.json.JSONArray;
import com.openthinks.libs.utilities.json.JSONObject;
import com.openthinks.svm.core.AppConstants;
import com.openthinks.svm.core.model.BizMetaRelease;
import com.openthinks.svm.core.model.BizProject;
import com.openthinks.svm.core.model.BizProjectExample;
import com.openthinks.svm.core.model.BizProjectExample.Criteria;
import com.openthinks.svm.core.service.ProjectService;
import com.openthinks.svm.web.support.model.SearchParams;
import com.openthinks.svm.web.support.util.JSONConvertersUtil;
import com.openthinks.svm.web.support.util.ResponseJSON;
import net.sourceforge.orm.mybatis.Page;

/**
 * ClassName: ProjectAPI <br>
 * date: Mar 15, 2019 1:19:33 PM <br>
 * 
 * @author dailey.dai@openthinks.com
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/api/project")
public class ProjectAPI {
  private static final Logger LOGGER = LoggerFactory.getLogger(ProjectAPI.class);
  @Autowired
  ProjectService projectService;


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

  @GetMapping("/{project_id}/meta_rels")
  @ResponseBody
  public Object metaRelease(@PathVariable("project_id") Long projectId) {
    JSONObject root = JSON.object();
    JSONArray data = root.createEmbedArray("data");
    projectService.findBindMetaReleases(projectId).forEach(e -> {
      data.add(JSONConvertersUtil.perparedAndGet(e));
    });
    return root;
  }

  private void buildPager(JSONObject pager, SearchParams searchParams) {
    pager.addProperty("page", searchParams.getPage());
    pager.addProperty("recTotal", searchParams.getRecTotal());
    pager.addProperty("recPerPage", searchParams.getRecPerPage());
  }

  private boolean buildData(JSONArray data, SearchParams searchParams) {
    BizProjectExample example = new BizProjectExample();
    Criteria cri = example.createCriteria();
    String versionF = searchParams.getSearch();
    if (versionF != null && versionF.trim().length() > 0) {
      cri.andShortNameLike("%" + versionF.trim() + "%");
    }
    long totalCount = projectService.count(example);
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
    Page<BizProject> page = new Page<>(searchParams.getPage(), searchParams.getRecPerPage());
    page.setTotalCount((int) totalCount);
    projectService.search(example, page).forEach(head -> {
      JSONObject jsonObject = JSONConvertersUtil.perparedAndGet(head);
      List<String> modules = new ArrayList<>();
      projectService.findBindMetaReleases(head.getId()).forEach(mr -> {
        modules.add(mr.getName());
      });
      Collections.sort(modules);
      String sb = modules.stream().collect(Collectors.joining(":"));
      jsonObject.addProperty("meta_releases", sb);
      data.add(jsonObject);
    });
    return true;
  }

  public JSONObject save(BizProject record) {
    boolean isSuccess = true;
    try {
      isSuccess = projectService.save(record);
    } catch (Exception e) {
      isSuccess = false;
      LOGGER.error("Failed to update BizProject:{} by reason:{}", record, e);
    }
    if (!isSuccess) {
      return new ResponseJSON().error("更新项目信息失败.").get();
    }
    return JSON.object();
  }

  public JSONObject save(BizProject record, List<BizMetaRelease> metaReleases) {
    boolean isSuccess = true;
    try {
      isSuccess = projectService.saveWithMetaReleases(record, metaReleases);
    } catch (Exception e) {
      isSuccess = false;
      LOGGER.error("Failed to update BizProject:{} by reason:{}", record, e);
    }
    if (!isSuccess) {
      return new ResponseJSON().error("更新项目信息失败.").get();
    }
    return JSON.object();
  }

  public JSONObject delete(Long id) {
    boolean isSuccess = true;
    try {
      isSuccess = projectService.delete(id);
    } catch (Exception e) {
      isSuccess = false;
      LOGGER.error("Failed to delete BizProject id:{} by reason:{}", id, e);
    }
    if (!isSuccess) {
      return new ResponseJSON().error("删除项目信息失败.").get();
    }
    return JSON.object();
  }

}
