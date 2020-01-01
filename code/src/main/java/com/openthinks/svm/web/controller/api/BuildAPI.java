package com.openthinks.svm.web.controller.api;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.openthinks.libs.utilities.exception.CheckerNoPassException;
import com.openthinks.libs.utilities.json.JSON;
import com.openthinks.libs.utilities.json.JSONArray;
import com.openthinks.libs.utilities.json.JSONObject;
import com.openthinks.svm.core.AppConstants;
import com.openthinks.svm.core.AppConstants.BuildSyncStatus;
import com.openthinks.svm.core.ReleaseType;
import com.openthinks.svm.core.model.BizMetaProductline;
import com.openthinks.svm.core.model.BizMetaProductlineExample;
import com.openthinks.svm.core.model.BizMetaRelease;
import com.openthinks.svm.core.model.BizProject;
import com.openthinks.svm.core.model.BizReleaseAttr;
import com.openthinks.svm.core.model.BizReleaseInfo;
import com.openthinks.svm.core.model.BizVersion;
import com.openthinks.svm.core.model.BizVersionExample;
import com.openthinks.svm.core.model.BizVersionExample.Criteria;
import com.openthinks.svm.core.service.MetaService;
import com.openthinks.svm.core.service.ProjectService;
import com.openthinks.svm.core.service.ReleaseAttrService;
import com.openthinks.svm.core.service.ReleaseInfoService;
import com.openthinks.svm.core.service.VersionService;
import com.openthinks.svm.web.job.BuildSyncJob;
import com.openthinks.svm.web.support.model.BuildInfo;
import com.openthinks.svm.web.support.model.BuildInfo.ReleaseModel;
import com.openthinks.svm.web.support.model.SearchParams;
import com.openthinks.svm.web.support.model.VersionWithReleaseInfo;
import com.openthinks.svm.web.support.util.JSONConvertersUtil;
import com.openthinks.svm.web.support.util.ResponseJSON;
import net.sourceforge.orm.mybatis.Page;

/**
 * @author dailey.dai@openthinks.com
 *
 */
@RestController
@RequestMapping("/api/build")
public class BuildAPI {
  private static final Logger LOGGER = LoggerFactory.getLogger(BuildAPI.class);
  @Autowired
  VersionService versionService;
  @Autowired
  ProjectService projectService;
  @Autowired
  BuildSyncJob syncJob;

  @Autowired
  MetaService metaService;

  @Autowired
  ReleaseAttrService releaseAttrService;

  @Autowired
  ReleaseInfoService releaseInfoService;

  /**
   * export/download main build/version info to XML format
   * 
   * @param buildNumber main build/version number
   * @return {@link ResponseEntity}
   */
  @RequestMapping("/export/{build_number}")
  public ResponseEntity<ByteArrayResource> exportXML(
      @PathVariable("build_number") String buildNumber) {
    StringBuffer content = new StringBuffer();
    BizVersion bizVersion = versionService.findByVersionNumber(buildNumber);
    if (bizVersion == null) {
      throw new CheckerNoPassException("该主版本号不存在！");
    }
    content.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>").append("\n");
    content.append("<SWVerInfo>").append("\n");
    content.append("<MAIN_SW_Version>").append(buildNumber).append("</MAIN_SW_Version>")
        .append("\n");
    Map<String, BizReleaseInfo> releaseMap =
        releaseInfoService.searchReleaseGroupByType(bizVersion.getId());
    releaseMap.forEach((key, val) -> {
      ReleaseType.toReleaseType(key).ifPresent(type -> {
        content.append("<").append(type.toXMLTag()).append(">").append(val.getReleaseNum())
            .append("</").append(type.toXMLTag()).append(">").append("\n");
      });
    });
    content.append("</SWVerInfo>").append("\n");
    byte[] exportArray = new byte[0];
    try {
      exportArray = content.toString().getBytes("UTF-8");
    } catch (UnsupportedEncodingException e) {
      LOGGER.warn("Failed to convert to byte array with UTF-8 format by reason:{}", e);
    }
    ByteArrayResource resource = new ByteArrayResource(exportArray);
    HttpHeaders headers = new HttpHeaders();
    String fileName = buildNumber + ".xml";
    headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
    headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", fileName));
    headers.add("Pragma", "no-cache");
    headers.add("Expires", "0");
    return ResponseEntity.ok().headers(headers).contentLength(resource.contentLength())
        .contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
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
    BizVersionExample example = new BizVersionExample();
    Criteria cri = example.createCriteria();
    String versionF = searchParams.getSearch();
    if (versionF != null && versionF.trim().length() > 0) {
      cri.andNumberLike("%" + versionF.trim() + "%");
    }
    Date startTime = searchParams.getStartTimeAsDate();
    if (startTime != null) {
      cri.andCreateDateGreaterThanOrEqualTo(startTime);
    }
    Date endTime = searchParams.getEndTimeAsDate();
    if (endTime != null) {
      cri.andCreateDateLessThanOrEqualTo(endTime);
    }
    long totalCount = versionService.countBuildCount(example);
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
    Page<BizVersion> page = new Page<>(searchParams.getPage(), searchParams.getRecPerPage());
    page.setTotalCount((int) totalCount);
    versionService.searchBuild(example, page).forEach(head -> {
      List<BizReleaseInfo> releaseInfos = releaseInfoService.searchByVNID(head.getId());
      JSONObject jsonObject = JSONConvertersUtil.perparedAndGet(head);
      jsonObject.addProperty("release_infos",
          VersionWithReleaseInfo.getReleaseInfosStr(releaseInfos));
      data.add(jsonObject);
    });
    return true;
  }

  public JSONObject createBuild(BuildInfo buildInfo) {
    ResponseJSON responseJSON = checkBuild(buildInfo);
    if (responseJSON.isError()) {
      return responseJSON.get();
    }
    boolean isSuccess = true;
    try {
      isSuccess = versionService.createAll(buildInfo);
    } catch (Exception e) {
      isSuccess = false;
      LOGGER.error("Failed to create BuildInfo:{} by reason:{}", buildInfo, e);
    }
    if (!isSuccess) {
      return new ResponseJSON().error("保存主版本失败.").get();
    } else {
      syncJob.pushTrigger();
    }
    return JSON.object();
  }

  private ResponseJSON checkBuild(BuildInfo buildInfo) {
    ResponseJSON responseJSON = new ResponseJSON();
    Long prj_id = buildInfo.getPrj_id();
    Integer prj_seq = buildInfo.getSeq_number();
    if (prj_id == null || prj_seq == null) {
      return responseJSON.error("项目缩写和流水号不能为空！");
    }
    BizProject record = projectService.findByPrimaryKey(prj_id);
    if (record == null) {
      return responseJSON.error("选择的关联的项目不存在！");
    }
    BizVersionExample example = new BizVersionExample();
    example.createCriteria().andNumberEqualTo(buildInfo.getBuild_no());
    if (versionService.countBuildCount(example) > 0) {
      return responseJSON.error("主版本号已经存在！");
    }

    List<BizMetaRelease> metaReleaseList = projectService.findBindMetaReleases(prj_id);
    if (!metaReleaseList.isEmpty()) {
      metaReleaseList.forEach(mr -> {
        ReleaseType.toReleaseType(mr.getName()).ifPresent(type -> {
          ReleaseModel model = buildInfo.getReleaseModel(type);
          if (model == null || model.getId() == null) {
            responseJSON.error(type + " Release 不能为空！");
          }
        });
      });
    }

    return responseJSON;
  }

  public JSONObject saveBuild(BizVersion record, BuildInfo buildInfo) {
    ResponseJSON responseJSON = new ResponseJSON();
    List<BizMetaRelease> metaReleaseList =
        projectService.findBindMetaReleases(record.getProjectId());
    if (!metaReleaseList.isEmpty()) {
      metaReleaseList.forEach(mr -> {
        ReleaseType.toReleaseType(mr.getName()).ifPresent(type -> {
          ReleaseModel model = buildInfo.getReleaseModel(type);
          if (model == null || model.getId() == null) {
            responseJSON.error(type + " Release 不能为空！");
          }
        });
      });
    }
    if (responseJSON.isError()) {
      return responseJSON.get();
    }
    boolean isSuccess = true;
    try {
      record.setSyncStatus(BuildSyncStatus.NOT_SYNC.value());
      isSuccess = versionService.saveAll(record, buildInfo);
    } catch (Exception e) {
      isSuccess = false;
      LOGGER.error("Failed to update BizVersion:{} by reason:{}", record, e);
    }
    if (!isSuccess) {
      return new ResponseJSON().error("更新主版本信息失败.").get();
    } else {
      syncJob.pushTrigger();
    }
    return JSON.object();
  }

  public JSONObject deleteBuild(Long id) {
    boolean isSuccess = true;
    try {
      isSuccess = versionService.deleteBuild(id);
    } catch (Exception e) {
      isSuccess = false;
      LOGGER.error("Failed to delete BizVersion id:{} by reason:{}", id, e);
    }
    if (!isSuccess) {
      return new ResponseJSON().error("删除主版本信息失败.").get();
    }
    return JSON.object();
  }


  public JSONObject getReleaseNote(Long id) {
    final JSONObject root = JSON.object();
    final JSONObject releasesObj = JSON.object();

    BizVersion version = versionService.findByPrimaryKey(id);
    String[] versionNumberParts = version.getNumber().split("_");
    String prj_carline = versionNumberParts[versionNumberParts.length - 1];
    String cnPrjId = "0" + prj_carline.substring(0, 1);
    // projectService.findByPrimaryKey(version.getProjectId()).getMetaPrjId();
    String cnCarlineId = prj_carline.substring(1);
    BizMetaProductlineExample example = new BizMetaProductlineExample();
    example.createCriteria().andPrjIdEqualTo(cnPrjId).andPlIdEqualTo(cnCarlineId);
    List<BizMetaProductline> list = metaService.findMetaProductlines(example);
    String productline = "N/A";
    if (list.size() > 0) {
      productline = list.get(0).getPlName();
    }
    Map<String, BizReleaseInfo> releaseMap =
        releaseInfoService.searchReleaseGroupByType(version.getId());
    for (ReleaseType releaseType : ReleaseType.values()) {
      final JSONObject releaseObj = releasesObj.createEmbedJSONObj(releaseType.getValue());
      BizReleaseInfo relaseInfo = releaseMap.get(releaseType.value());
      if (relaseInfo != null) {
        releaseObj.addProperty("version", relaseInfo.getReleaseNum());
        List<BizReleaseAttr> attrs = releaseAttrService.searchByReleaseId(relaseInfo.getId());
        JSONObject attrsObj = releaseObj.createEmbedJSONObj("attrs");
        for (BizReleaseAttr att : attrs) {
          attrsObj.addProperty(att.getItemName(), att.getItemValue());
        }
      }
    }
    List<BizMetaRelease> requiredReleaseTypes =
        projectService.findBindMetaReleases(version.getProjectId());
    List<String> requiredReleaseModels = new ArrayList<>();
    requiredReleaseTypes.forEach(mr -> {
      requiredReleaseModels.add(mr.getName());
    });
    root.addProperty("reqiredType", requiredReleaseModels);
    root.addProperty("productline", productline);
    root.addProperty("main", version.getNumber());
    root.addProperty("releases", releasesObj);
    return root;
  }
}
