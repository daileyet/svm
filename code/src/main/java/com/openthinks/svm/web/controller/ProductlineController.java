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
import com.openthinks.svm.core.model.BizMetaProductline;
import com.openthinks.svm.core.service.MetaService;
import com.openthinks.svm.web.controller.api.ProductlineAPI;
import com.openthinks.svm.web.support.util.ResponseJSON;

/**
 * ClassName: ProductlineController <br>
 * date: Apr 24, 2019 11:01:20 AM <br>
 * 
 * @author dailey.dai@openthinks.com
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/productline")
public class ProductlineController {
  @Autowired
  MetaService metaService;
  @Autowired
  ProductlineAPI productlineAPI;

  @RequestMapping
  public String index() {
    return "productline/index";
  }

  @RequestMapping("/index.html")
  public String toIndex() {
    return index();
  }


  @RequestMapping("/add")
  public String toAdd(HttpServletRequest req) {
    req.setAttribute("metaInfos", metaService.findAllMetaProjects());
    return "productline/add";
  }

  @RequestMapping("/edit/{id}")
  public String toEdit(@PathVariable("id") Long id, HttpServletRequest req,
      HttpServletResponse resp, RedirectAttributes redirectAttributes) {
    BizMetaProductline record = metaService.findMetaProductlineByPrimaryKey(id);
    if (record == null) {
      ResponseJSON respJson = new ResponseJSON();
      respJson.error("车型记录不存在!");
      redirectAttributes.addFlashAttribute("fmsg", respJson.getMessage());
      redirectAttributes.addFlashAttribute("fmsgType", respJson.getResult());
      return "redirect:/productline";
    }
    req.setAttribute("productlineInfo", record);
    req.setAttribute("metaInfos", metaService.findAllMetaProjects());
    return "productline/edit";
  }

  @PostMapping("/save")
  public String save(BizMetaProductline record, RedirectAttributes redirectAttributes) {
    ResponseJSON respJson = new ResponseJSON();
    JSONObject resp = productlineAPI.save(record);
    respJson = new ResponseJSON(resp);
    redirectAttributes.addFlashAttribute("fmsg", respJson.getMessage());
    redirectAttributes.addFlashAttribute("fmsgType", respJson.getResult());
    return "redirect:/productline";
  }

  @RequestMapping("/del")
  public String delSeq(@RequestParam(name = "id", required = true) Long id,
      RedirectAttributes redirectAttributes) {
    JSONObject resp = productlineAPI.delete(id);
    ResponseJSON respJson = new ResponseJSON(resp);
    redirectAttributes.addFlashAttribute("fmsg", respJson.getMessage());
    redirectAttributes.addFlashAttribute("fmsgType", respJson.getResult());
    return "redirect:/productline";
  }
}
