package com.openthinks.svm.web.controller;

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
import com.openthinks.svm.core.AppConstants.AttributeCategory;
import com.openthinks.svm.core.model.BizMetaAttr;
import com.openthinks.svm.core.service.MetaService;
import com.openthinks.svm.web.controller.api.MetaAttrAPI;
import com.openthinks.svm.web.support.util.ResponseJSON;

/**
 * 
 * @author dailey.dai@openthinks.com
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/attr")
public class MetaAttrController {
  @Autowired
  MetaService metaService;
  @Autowired
  MetaAttrAPI metaAttrAPI;

  @RequestMapping
  public String index() {
    return "attr/index";
  }

  @RequestMapping("/index.html")
  public String toIndex() {
    return index();
  }


  @RequestMapping("/add")
  public String toAdd(HttpServletRequest req) {
    req.setAttribute("attrCates", AttributeCategory.values());
    req.setAttribute("attrMap", metaService.finadAllMetaAttrGroupByCategory());
    return "attr/add";
  }

  @RequestMapping("/edit/{id}")
  public String toEdit(@PathVariable("id") Long id, HttpServletRequest req,
      HttpServletResponse resp, RedirectAttributes redirectAttributes) {
    BizMetaAttr record = metaService.findMetaAttrByPrimaryKey(id);
    if (record == null) {
      ResponseJSON respJson = new ResponseJSON();
      respJson.error("自定义属性记录不存在!");
      redirectAttributes.addFlashAttribute("fmsg", respJson.getMessage());
      redirectAttributes.addFlashAttribute("fmsgType", respJson.getResult());
      return "redirect:/attr";
    }
    req.setAttribute("attrInfo", record);
    req.setAttribute("attrCates", AttributeCategory.values());
    req.setAttribute("metaInfos", metaService.findAllMetaProjects());
    return "attr/edit";
  }

  @PostMapping("/save")
  public String save(BizMetaAttr record, RedirectAttributes redirectAttributes) {
    ResponseJSON respJson = new ResponseJSON();
    JSONObject resp = metaAttrAPI.save(record);
    respJson = new ResponseJSON(resp);
    redirectAttributes.addFlashAttribute("fmsg", respJson.getMessage());
    redirectAttributes.addFlashAttribute("fmsgType", respJson.getResult());
    return "redirect:/attr";
  }

  @RequestMapping("/del")
  public String delSeq(@RequestParam(name = "id", required = true) Long id,
      RedirectAttributes redirectAttributes) {
    JSONObject resp = metaAttrAPI.delete(id);
    ResponseJSON respJson = new ResponseJSON(resp);
    redirectAttributes.addFlashAttribute("fmsg", respJson.getMessage());
    redirectAttributes.addFlashAttribute("fmsgType", respJson.getResult());
    return "redirect:/attr";
  }
}
