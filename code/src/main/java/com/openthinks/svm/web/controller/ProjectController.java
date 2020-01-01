package com.openthinks.svm.web.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.openthinks.libs.utilities.json.JSONObject;
import com.openthinks.svm.core.model.BizMetaRelease;
import com.openthinks.svm.core.model.BizProject;
import com.openthinks.svm.core.service.MetaService;
import com.openthinks.svm.core.service.ProjectService;
import com.openthinks.svm.web.controller.api.ProjectAPI;
import com.openthinks.svm.web.support.util.ResponseJSON;

/**
 * ClassName: ProjectController <br>
 * date: Mar 15, 2019 1:14:51 PM <br>
 * 
 * @author dailey.dai@openthinks.com
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/project")
public class ProjectController {
  @Autowired
  ProjectService projectService;

  @Autowired
  MetaService metaService;

  @Autowired
  ProjectAPI projectAPI;

  @RequestMapping
  public String index() {
    return "project/index";
  }

  @RequestMapping("/index.html")
  public String toIndex() {
    return index();
  }


  @RequestMapping("/add")
  public String toAdd(HttpServletRequest req) {
    req.setAttribute("metaInfos", metaService.findAllMetaReleases());
    req.setAttribute("metaProjects", metaService.findAllActiveMetaProjects());
    return "project/add";
  }

  @RequestMapping("/edit/{id}")
  public String toEdit(@PathVariable("id") Long id, HttpServletRequest req,
      HttpServletResponse resp, RedirectAttributes redirectAttributes) {
    BizProject record = projectService.findByPrimaryKey(id);
    if (record == null) {
      ResponseJSON respJson = new ResponseJSON();
      respJson.error("项目记录不存在!");
      redirectAttributes.addFlashAttribute("fmsg", respJson.getMessage());
      redirectAttributes.addFlashAttribute("fmsgType", respJson.getResult());
      return "redirect:/project";
    }
    req.setAttribute("prjInfo", record);
    req.setAttribute("metaInfos", metaService.findAllMetaReleases());
    req.setAttribute("metaProjects", metaService.findAllActiveMetaProjects());
    req.setAttribute("hasMetaInfos", projectService.findBindMetaReleases(id));
    return "project/edit";
  }

  @PostMapping("/save")
  public String save(BizProject record, @RequestParam("metaIds") Long[] metaIds,
      RedirectAttributes redirectAttributes) {
    List<BizMetaRelease> list = new ArrayList<>();
    ResponseJSON respJson = new ResponseJSON();
    if (metaIds != null && metaIds.length > 0) {
      for (Long metaId : metaIds) {
        list.add(new BizMetaRelease.Builder().id(metaId).build());
      }
      JSONObject resp = projectAPI.save(record, list);
      respJson = new ResponseJSON(resp);
    } else {
      respJson.error("Release模块不能为空!");
    }
    redirectAttributes.addFlashAttribute("fmsg", respJson.getMessage());
    redirectAttributes.addFlashAttribute("fmsgType", respJson.getResult());
    return "redirect:/project";
  }

  @RequestMapping("/del/{id}")
  public String delSeq(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
    JSONObject resp = projectAPI.delete(id);
    ResponseJSON respJson = new ResponseJSON(resp);
    redirectAttributes.addFlashAttribute("fmsg", respJson.getMessage());
    redirectAttributes.addFlashAttribute("fmsgType", respJson.getResult());
    return "redirect:/project";
  }



}
