package com.openthinks.svm.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.openthinks.libs.utilities.json.JSONObject;
import com.openthinks.svm.core.model.SystemRole;
import com.openthinks.svm.core.model.SystemUser;
import com.openthinks.svm.core.service.SystemUserService;
import com.openthinks.svm.web.controller.api.AuthAPI;
import com.openthinks.svm.web.support.model.SystemUserWrapper;
import com.openthinks.svm.web.support.util.CheckerUtils;
import com.openthinks.svm.web.support.util.ResponseJSON;
import com.openthinks.svm.web.support.util.WebSessionUtils;

/**
 * ClassName: AuthCfgController </br>
 * 
 * @author dailey.dai@openthinks.com
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/auth")
public final class AuthController {
  @Autowired
  AuthAPI authCfgAPI;

  @Autowired
  SystemUserService userService;

  @RequestMapping
  public String toAuthIndex(Model model) {
    SystemUser user = WebSessionUtils.getCurrentUser();
    model.addAttribute("signUser", user);
    return "auth/index";
  }

  @RequestMapping("/eidt/{uid}")
  public String toEidtAuthCfg(Model model, @PathVariable(name = "uid") String uid) {
    if (uid == null || !CheckerUtils.isLong(uid)) {
      new ResponseJSON().error("无效的用户ID.").createException();
    }
    long userId = Long.valueOf(uid);
    SystemUser record = userService.getSystemUser(userId);
    if (record == null) {
      new ResponseJSON().error("无效的用户ID.").createException();
    }
    List<SystemRole> roles = userService.listSystemRolesByUser(userId);
    model.addAttribute("user", new SystemUserWrapper(record, roles));
    return "auth/edit";
  }

  @RequestMapping("/add")
  public String toAddAuthCfg() {
    return "auth/add";
  }

  @PostMapping("/save")
  public String saveAuthCfg(SystemUserWrapper userWrapper, RedirectAttributes redirectAttributes) {
    JSONObject resp = authCfgAPI.saveUser(userWrapper);
    ResponseJSON respJson = new ResponseJSON(resp);
    redirectAttributes.addFlashAttribute("fmsg", respJson.getMessage());
    redirectAttributes.addFlashAttribute("fmsgType", respJson.getResult());
    return "redirect:/auth";
  }

  @RequestMapping("/del/{uid}")
  public String toDelAuthCfg(Model model, @PathVariable(name = "uid") String uid,
      RedirectAttributes redirectAttributes) {
    JSONObject resp = authCfgAPI.removeUser(uid);
    ResponseJSON respJson = new ResponseJSON(resp);
    redirectAttributes.addFlashAttribute("fmsg", respJson.getMessage());
    redirectAttributes.addFlashAttribute("fmsgType", respJson.getResult());
    return "redirect:/auth";
  }

}
