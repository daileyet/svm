package com.openthinks.svm.web.controller;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.openthinks.libs.utilities.DateFormatUtil;
import com.openthinks.libs.utilities.json.JSON;
import com.openthinks.libs.utilities.json.JSONArray;
import com.openthinks.libs.utilities.json.JSONObject;
import com.openthinks.svm.core.model.BizMetaRelease;
import com.openthinks.svm.core.model.BizReleaseInfo;
import com.openthinks.svm.core.model.BizVersion;
import com.openthinks.svm.core.service.MetaService;
import com.openthinks.svm.core.service.ProjectService;
import com.openthinks.svm.core.service.ReleaseInfoService;
import com.openthinks.svm.core.service.SequenceService;
import com.openthinks.svm.core.service.VersionService;
import com.openthinks.svm.web.controller.api.BuildAPI;
import com.openthinks.svm.web.support.model.BuildInfo;
import com.openthinks.svm.web.support.util.ResponseJSON;

/**
 * @author dailey.dai@openthinks.com
 *
 */
@Controller
@RequestMapping("/build")
public class BuildController {

  @Autowired
  BuildAPI buildAPI;

  @Autowired
  VersionService versionService;

  @Autowired
  SequenceService sequenceService;

  @Autowired
  ReleaseInfoService releaseInfoService;

  @Autowired
  MetaService metaService;

  @Autowired
  ProjectService projectService;

  @RequestMapping
  public String index() {
    return "build/index";
  }

  @RequestMapping("/index.html")
  public String toIndex() {
    return "build/index";
  }

  @RequestMapping("/add")
  public String toAdd(HttpServletRequest req) {
    BuildInfo record = new BuildInfo();
    // record.setPrj_short(AppConstants.PRJ_SHORT_NAME_DEFAULT);
    record.setRelease_date(DateFormatUtil.format("yyMMdd", new Date()));
    // record.setSeq_number(sequenceService.next(AppConstants.PRJ_SHORT_NAME_DEFAULT));
    req.setAttribute("versionInfo", record);
    req.setAttribute("metaProjects", metaService.findAllActiveMetaProjects());
    req.setAttribute("prjInfos", projectService.findAllProjects());
    req.setAttribute("metaReleases", metaService.findAllMetaReleases());
    req.setAttribute("allReleaseMap", releaseInfoService.searchAllReleaseGroupByType());
    return "build/add";
  }

  @RequestMapping("/view/{build_number}")
  public String toView(@PathVariable("build_number") String buildNumber, HttpServletRequest req,
      HttpServletResponse resp, RedirectAttributes redirectAttributes) {
    BizVersion record = versionService.findByVersionNumber(buildNumber);
    if (record == null) {
      ResponseJSON respJson = new ResponseJSON();
      respJson.error("主版本记录不存在!");
      redirectAttributes.addFlashAttribute("fmsg", respJson.getMessage());
      redirectAttributes.addFlashAttribute("fmsgType", respJson.getResult());
      return "redirect:/build";
    }
    req.setAttribute("buildInfo", record);
    req.setAttribute("prjInfo", projectService.findByPrimaryKey(record.getProjectId()));
    List<BizReleaseInfo> list = releaseInfoService.searchByVNID(record.getId());
    req.setAttribute("releaseInfos", list);
    return "build/view";
  }

  @RequestMapping("/edit/{build_number}")
  public String toEdit(@PathVariable("build_number") String buildNumber, HttpServletRequest req,
      HttpServletResponse resp, RedirectAttributes redirectAttributes) {
    BizVersion record = versionService.findByVersionNumber(buildNumber);
    return toEdit_(record, req, redirectAttributes);
  }

  @RequestMapping("/edit")
  public String toEdit2(@RequestParam(name = "id", required = true) Long vnId,
      HttpServletRequest req, HttpServletResponse resp, RedirectAttributes redirectAttributes) {
    BizVersion record = versionService.findByPrimaryKey(vnId);
    return toEdit_(record, req, redirectAttributes);
  }

  private String toEdit_(BizVersion record, HttpServletRequest req,
      RedirectAttributes redirectAttributes) {
    if (record == null) {
      ResponseJSON respJson = new ResponseJSON();
      respJson.error("主版本记录不存在!");
      redirectAttributes.addFlashAttribute("fmsg", respJson.getMessage());
      redirectAttributes.addFlashAttribute("fmsgType", respJson.getResult());
      return "redirect:/build";
    }
    List<BizMetaRelease> requiredReleaseTypes =
        projectService.findBindMetaReleases(record.getProjectId());
    Map<String, Boolean> requiredReleaseModels = new HashMap<>();
    requiredReleaseTypes.forEach(mr -> {
      requiredReleaseModels.put(mr.getName(), true);
    });
    req.setAttribute("versionInfo", record);
    // req.setAttribute("metaReleases", metaService.findAllMetaReleases());
    req.setAttribute("metaReleases", requiredReleaseTypes);
    req.setAttribute("allReleaseMap", releaseInfoService.searchAllReleaseGroupByType());
    req.setAttribute("releaseMap", releaseInfoService.searchReleaseGroupByType(record.getId()));
    req.setAttribute("releaseRequiredMap", requiredReleaseModels);
    req.setAttribute("prjInfo", projectService.findByPrimaryKey(record.getProjectId()));


    req.setAttribute("releaseInfos", Collections.emptyList());
    return "build/edit";
  }

  @PostMapping("/create")
  public String createBuild(BuildInfo buildInfo, RedirectAttributes redirectAttributes) {
    JSONObject resp = buildAPI.createBuild(buildInfo);
    ResponseJSON respJson = new ResponseJSON(resp);
    redirectAttributes.addFlashAttribute("fmsg", respJson.getMessage());
    redirectAttributes.addFlashAttribute("fmsgType", respJson.getResult());
    return "redirect:/build";
  }

  @PostMapping("/save")
  public String saveBuild(BizVersion record, BuildInfo buildInfo,
      RedirectAttributes redirectAttributes) {
    JSONObject resp = buildAPI.saveBuild(record, buildInfo);
    ResponseJSON respJson = new ResponseJSON(resp);
    redirectAttributes.addFlashAttribute("fmsg", respJson.getMessage());
    redirectAttributes.addFlashAttribute("fmsgType", respJson.getResult());
    return "redirect:/build";
  }

  @RequestMapping("/del/{id}")
  public String delBuild(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
    JSONObject resp = buildAPI.deleteBuild(id);
    ResponseJSON respJson = new ResponseJSON(resp);
    redirectAttributes.addFlashAttribute("fmsg", respJson.getMessage());
    redirectAttributes.addFlashAttribute("fmsgType", respJson.getResult());
    return "redirect:/build";
  }

  @RequestMapping("/content")
  @ResponseBody
  public String getContent(@RequestParam(name = "id", required = true) Long vnId,
      HttpServletRequest req, RedirectAttributes redirectAttributes) {
    // check vnId
    BizVersion record = versionService.findByPrimaryKey(vnId);
    if (record == null) {
      return "";
    }
    return record.getDescription();
  }

  @RequestMapping("/notes")
  public String toReleaseNotes(Long[] vids, HttpServletRequest req,
      @RequestParam(name = "style", defaultValue = "1") String style) {
    JSONArray notes = JSON.array();
    for (Long id : vids) {
      JSONObject object = buildAPI.getReleaseNote(id);
      notes.add(object);
    }
    Object[] allReleaseTypes = new Object[notes.size()];
    int maxLen = 0, maxIndex = 0;
    for (int i = 0, j = allReleaseTypes.length; i < j; i++) {
      allReleaseTypes[i] = ((JSONObject) notes.get(i)).getProperty("reqiredType");
      if (allReleaseTypes[i] instanceof List) {
        @SuppressWarnings("rawtypes")
        int len = ((List) allReleaseTypes[i]).size();
        if (len > maxLen) {
          maxLen = len;
          maxIndex = i;
        }
      }
    }
    req.setAttribute("releaseTypes", allReleaseTypes[maxIndex]);
    req.setAttribute("buildInfos", notes);
    return "tmp/release_note_style_" + style;
  }

}
