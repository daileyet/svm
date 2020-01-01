package com.openthinks.svm.web.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.openthinks.svm.core.ReleaseType;
import com.openthinks.svm.core.model.BizReleaseAttr;
import com.openthinks.svm.core.model.BizReleaseInfo;
import com.openthinks.svm.core.model.BizReleaseInfoExample;
import com.openthinks.svm.core.model.BizReleaseInfoWithBLOBs;
import com.openthinks.svm.core.service.MetaService;
import com.openthinks.svm.core.service.ReleaseAttrService;
import com.openthinks.svm.core.service.ReleaseInfoService;
import com.openthinks.svm.core.service.VersionService;
import com.openthinks.svm.web.support.model.AttrInfo;
import com.openthinks.svm.web.support.util.ResponseJSON;

/**
 * @author dailey.dai@openthinks.com
 *
 */
@Controller
@RequestMapping("/release")
public class ReleaseInfoController {
  private static final Logger LOGGER = LoggerFactory.getLogger(ReleaseInfoController.class);
  @Autowired
  VersionService versionService;

  @Autowired
  ReleaseInfoService releaseInfoService;

  @Autowired
  ReleaseAttrService releaseAttrService;

  @Autowired
  MetaService metaService;

  @RequestMapping
  public String index() {
    return "release/index";
  }

  @RequestMapping("/index.html")
  public String toIndex() {
    return "release/index";
  }

  @RequestMapping("/view")
  public String toView(@RequestParam(name = "id", required = true) Long releaseId,
      HttpServletRequest req, RedirectAttributes redirectAttributes) {
    // check vnId
    BizReleaseInfoWithBLOBs record = releaseInfoService.searchByPrimaryKey(releaseId);
    if (record == null) {
      ResponseJSON respJson = new ResponseJSON();
      respJson.error("Release信息不存在!");
      redirectAttributes.addFlashAttribute("fmsg", respJson.getMessage());
      redirectAttributes.addFlashAttribute("fmsgType", respJson.getResult());
      return "redirect:/release";
    }
    List<BizReleaseAttr> attrs = releaseAttrService.searchByReleaseId(record.getId());
    req.setAttribute("releaseInfo", record);
    req.setAttribute("types", ReleaseType.values());
    req.setAttribute("releaseAttrs", attrs);
    return "release/view";
  }

  @RequestMapping("/view/{id}")
  public String toView2(@PathVariable("id") Long releaseId, HttpServletRequest req,
      RedirectAttributes redirectAttributes) {
    // check vnId
    BizReleaseInfoWithBLOBs record = releaseInfoService.searchByPrimaryKey(releaseId);
    if (record == null) {
      ResponseJSON respJson = new ResponseJSON();
      respJson.error("Release信息不存在!");
      redirectAttributes.addFlashAttribute("fmsg", respJson.getMessage());
      redirectAttributes.addFlashAttribute("fmsgType", respJson.getResult());
      return "redirect:/release";
    }
    List<BizReleaseAttr> attrs = releaseAttrService.searchByReleaseId(record.getId());
    req.setAttribute("releaseInfo", record);
    req.setAttribute("types", ReleaseType.values());
    req.setAttribute("releaseAttrs", attrs);
    return "release/view";
  }

  @RequestMapping("/content")
  @ResponseBody
  public String getContent(@RequestParam(name = "id", required = true) Long releaseId,
      HttpServletRequest req, RedirectAttributes redirectAttributes) {
    // check vnId
    BizReleaseInfoWithBLOBs record = releaseInfoService.searchByPrimaryKey(releaseId);
    if (record == null) {
      return "";
    }
    return record.getContent();
  }

  @RequestMapping("/add")
  public String toAdd(HttpServletRequest req, RedirectAttributes redirectAttributes) {
    req.setAttribute("types", ReleaseType.values());
    req.setAttribute("metaAttrMap", metaService.finadAllMetaAttrGroupByCategory());
    return "release/add";
  }

  @RequestMapping("/edit/{id}")
  public String toEdit(@PathVariable("id") Long releaseId, HttpServletRequest req,
      RedirectAttributes redirectAttributes) {
    BizReleaseInfo record = releaseInfoService.searchByPrimaryKey(releaseId);
    ResponseJSON respJson = new ResponseJSON();
    if (record == null) {
      respJson.error("Release信息不存在!");
      redirectAttributes.addFlashAttribute("fmsg", respJson.getMessage());
      redirectAttributes.addFlashAttribute("fmsgType", respJson.getResult());
      return "redirect:/release";
    }
    List<BizReleaseAttr> attrs = releaseAttrService.searchByReleaseId(record.getId());
    req.setAttribute("releaseInfo", record);
    req.setAttribute("releaseAttrs", attrs);
    req.setAttribute("metaAttrMap", metaService.finadAllMetaAttrGroupByCategory());
    return "release/edit";
  }

  @RequestMapping("/edit")
  public String toEdit2(@RequestParam(name = "id", required = true) Long releaseId,
      HttpServletRequest req, RedirectAttributes redirectAttributes) {
    BizReleaseInfo record = releaseInfoService.searchByPrimaryKey(releaseId);
    ResponseJSON respJson = new ResponseJSON();
    if (record == null) {
      respJson.error("Release信息不存在!");
      redirectAttributes.addFlashAttribute("fmsg", respJson.getMessage());
      redirectAttributes.addFlashAttribute("fmsgType", respJson.getResult());
      return "redirect:/release";
    }
    List<BizReleaseAttr> attrs = releaseAttrService.searchByReleaseId(record.getId());
    req.setAttribute("releaseInfo", record);
    req.setAttribute("releaseAttrs", attrs);
    req.setAttribute("metaAttrMap", metaService.finadAllMetaAttrGroupByCategory());
    return "release/edit";
  }

  @PostMapping("/create")
  public String create(BizReleaseInfoWithBLOBs record, AttrInfo attrInfo,
      RedirectAttributes redirectAttributes) {
    boolean isSuccess = true;
    ResponseJSON respJson = new ResponseJSON();
    if (attrInfo != null && attrInfo.size() > 1 && !attrInfo.isValid()) {
      respJson.error("关联属性不允许存在重复!");
    } else {
      // check duplicate type release info
      BizReleaseInfoExample example = new BizReleaseInfoExample();
      example.createCriteria().andReleaseNumEqualTo(record.getReleaseNum())
          .andTypeEqualTo(record.getType());
      if (record.getId() == null && releaseInfoService.count(example) > 0) {
        respJson.error("创建的Release信息已存在!");
      }
      if (!respJson.isError()) {
        try {
          if (attrInfo == null || attrInfo.size() == 0) {
            isSuccess = releaseInfoService.saveWithBLOBs(record);
          } else {
            isSuccess = releaseInfoService.saveWithBLOBsAndAttr(record, attrInfo.convertTo());
          }
        } catch (Exception e) {
          isSuccess = false;
          LOGGER.error("Failed to create BizReleaseInfoWithBLOBs:{}", record);
        }
        if (!isSuccess) {
          respJson.error("保存Release信息失败!");
        }
      }
    }
    redirectAttributes.addFlashAttribute("fmsg", respJson.getMessage());
    redirectAttributes.addFlashAttribute("fmsgType", respJson.getResult());
    return "redirect:/release";
  }

  @PostMapping("/save")
  public String save(BizReleaseInfoWithBLOBs record, AttrInfo attrInfo,
      RedirectAttributes redirectAttributes) {
    boolean isSuccess = true;
    ResponseJSON respJson = new ResponseJSON();
    if (attrInfo != null && attrInfo.size() > 1 && !attrInfo.isValid()) {
      respJson.error("关联属性不允许存在重复!");
    } else {
      // check duplicate type release info
      BizReleaseInfo old = releaseInfoService.searchByPrimaryKey(record.getId());
      if (old == null) {
        respJson.error("修改的Release信息不存在!");
      }
      if (!respJson.isError()) {
        try {
          isSuccess = releaseInfoService.saveWithBLOBsAndAttr(record, attrInfo.convertTo());
        } catch (Exception e) {
          isSuccess = false;
          LOGGER.error("Failed to update BizReleaseInfoWithBLOBs:{}", record);
        }
        if (!isSuccess) {
          respJson.error("保存Release信息失败!");
        }
      }
    }
    redirectAttributes.addFlashAttribute("fmsg", respJson.getMessage());
    redirectAttributes.addFlashAttribute("fmsgType", respJson.getResult());
    return "redirect:/release";
  }

  @RequestMapping("/del")
  public String delete(@RequestParam(name = "id", required = true) Long releaseId,
      RedirectAttributes redirectAttributes) {
    ResponseJSON respJson = new ResponseJSON();
    BizReleaseInfo record = releaseInfoService.searchByPrimaryKey(releaseId);
    if (record == null) {
      respJson.error("Release信息不存在!");
      redirectAttributes.addFlashAttribute("fmsg", respJson.getMessage());
      redirectAttributes.addFlashAttribute("fmsgType", respJson.getResult());
      return "redirect:/release";
    }
    boolean isSuccess = true;
    try {
      isSuccess = releaseInfoService.deleteByPrimaryKey(releaseId);
    } catch (Exception e) {
      isSuccess = false;
      LOGGER.error("Failed to delete BizReleaseInfo:{}", record);
    }
    if (!isSuccess) {
      respJson.error("删除Release信息失败!");
    }
    redirectAttributes.addFlashAttribute("fmsg", respJson.getMessage());
    redirectAttributes.addFlashAttribute("fmsgType", respJson.getResult());
    return "redirect:/release";
  }
}
