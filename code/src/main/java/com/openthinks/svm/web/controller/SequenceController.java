package com.openthinks.svm.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.openthinks.libs.utilities.json.JSONObject;
import com.openthinks.svm.core.model.BizSequence;
import com.openthinks.svm.core.model.BizSequenceVw;
import com.openthinks.svm.core.service.ProjectService;
import com.openthinks.svm.core.service.SequenceService;
import com.openthinks.svm.web.controller.api.SequenceAPI;
import com.openthinks.svm.web.support.util.ResponseJSON;

/**
 * ClassName: SequenceController <br>
 * date: Mar 15, 2019 10:13:52 AM <br>
 * 
 * @author dailey.dai@openthinks.com
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/seq")
public class SequenceController {
  @Autowired
  SequenceService sequenceService;

  @Autowired
  ProjectService projectService;

  @Autowired
  SequenceAPI sequenceAPI;

  @RequestMapping
  public String index() {
    return "sequence/index";
  }

  @RequestMapping("/index.html")
  public String toIndex() {
    return index();
  }

  @RequestMapping("/add")
  public String toAdd(HttpServletRequest req) {
    req.setAttribute("prjInfos", projectService.findAllProjects());
    return "sequence/add";
  }

  @RequestMapping("/edit/{id}")
  public String toEdit(@PathVariable("id") Long seqId, HttpServletRequest req,
      HttpServletResponse resp, RedirectAttributes redirectAttributes) {
    BizSequenceVw record = sequenceService.findSequenceVwByPrimaryKey(seqId);
    if (record == null) {
      ResponseJSON respJson = new ResponseJSON();
      respJson.error("流水号记录不存在!");
      redirectAttributes.addFlashAttribute("fmsg", respJson.getMessage());
      redirectAttributes.addFlashAttribute("fmsgType", respJson.getResult());
      return "redirect:/seq";
    }
    req.setAttribute("seqInfo", record);
    return "sequence/edit";
  }

  @PostMapping("/save")
  public String saveBuild(BizSequence record, RedirectAttributes redirectAttributes) {
    JSONObject resp = sequenceAPI.saveSeq(record);
    ResponseJSON respJson = new ResponseJSON(resp);
    redirectAttributes.addFlashAttribute("fmsg", respJson.getMessage());
    redirectAttributes.addFlashAttribute("fmsgType", respJson.getResult());
    return "redirect:/seq";
  }

  @RequestMapping("/del/{id}")
  public String delSeq(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
    JSONObject resp = sequenceAPI.deleteSeq(id);
    ResponseJSON respJson = new ResponseJSON(resp);
    redirectAttributes.addFlashAttribute("fmsg", respJson.getMessage());
    redirectAttributes.addFlashAttribute("fmsgType", respJson.getResult());
    return "redirect:/seq";
  }
}
