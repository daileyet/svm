package com.openthinks.svm.web.controller.api;

import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.openthinks.libs.utilities.json.JSON;
import com.openthinks.libs.utilities.json.JSONArray;
import com.openthinks.libs.utilities.json.JSONObject;
import com.openthinks.svm.core.AppConstants;
import com.openthinks.svm.core.AppConstants.AuthRole;
import com.openthinks.svm.core.model.SystemUser;
import com.openthinks.svm.core.model.SystemUserExample;
import com.openthinks.svm.core.model.SystemUserExample.Criteria;
import com.openthinks.svm.core.service.SystemUserService;
import com.openthinks.svm.web.support.model.SearchParams;
import com.openthinks.svm.web.support.model.SystemUserWrapper;
import com.openthinks.svm.web.support.util.CheckerUtils;
import com.openthinks.svm.web.support.util.JSONConvertersUtil;
import com.openthinks.svm.web.support.util.ResponseJSON;
import com.openthinks.svm.web.support.util.WebSessionUtils;
import net.sourceforge.orm.mybatis.Page;

/**
 * ClassName: AuthCfgController </br>
 * date: Aug 23, 2018 9:55:28 AM </br>
 * 
 * @author dailey.dai@openthinks.com
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/api/auth")
public final class AuthAPI {
  private static final Logger LOGGER = LoggerFactory.getLogger(AuthAPI.class);

  @Autowired
  SystemUserService userService;

  @Autowired
  BCryptPasswordEncoder passwordEncoder;

  @RequestMapping("/search")
  public Object searchUsers(SearchParams searchParams) {
    LOGGER.debug("Get request parameter:{}", searchParams);
    JSONObject root = JSON.object();
    JSONArray data = root.createEmbedArray("data");
    boolean isSuccess = buildData(data, searchParams);
    JSONObject pager = root.createEmbedJSONObj("pager");
    buildPager(pager, searchParams);
    root.addProperty("result", isSuccess ? "success" : "fail");
    return root;
  }

  @PostMapping("/save")
  public JSONObject saveUser(SystemUserWrapper userWrapper) {
    ResponseJSON resp = new ResponseJSON();
    if (userWrapper.getId() != null && WebSessionUtils.isCurrentUser(userWrapper)) {
      return resp.error("不能修改当前登录用户的权限.").get();
    }
    if (userWrapper.getUserName() == null || userWrapper.getUserName().trim().length() == 0) {
      return resp.error("无效的用户名.").get();
    }
    if (userWrapper.getAuthRoles().size() == 0) {
      return resp.error("无效的用户权限.").get();
    }
    if (userWrapper.isCreateMode()) {
      String pass1 = userWrapper.getUserPass();
      if (pass1 == null || pass1.trim().length() < 6) {
        return resp.error("无效的用户密码，不能为空且长度至少6位.").get();
      }
      String pass2 = userWrapper.getUserPass2();
      if (!pass1.equals(pass2)) {
        return resp.error("两次密码不匹配.").get();
      }
      userWrapper.setUserPass(passwordEncoder.encode(pass1));
    } else {
      userWrapper.setUserPass(null);
    }
    boolean isSuccess = userService.saveSystemUser(userWrapper, userWrapper.getAuthRoles());
    String action = userWrapper.isCreateMode() ? "新增" : "更新";
    if (!isSuccess) {
      resp.error(action + "用户失败 !");
    } else {
      resp.success(action + "用户成功 !");
    }
    return resp.get();
  }

  @RequestMapping("/remove")
  public JSONObject removeUser(@RequestParam(name = "uid", required = false) String uid) {
    ResponseJSON responseJSON = new ResponseJSON();
    if (uid == null || !CheckerUtils.isLong(uid)) {
      return responseJSON.error("无效的用户ID值.").get();
    }
    boolean isSuccess = userService.removeSystemUser(Long.valueOf(uid));
    if (!isSuccess) {
      responseJSON.error("用户删除失败.").get();
    } else {
      responseJSON.success("用户删除成功 !");
    }
    return responseJSON.get();
  }

  private void buildPager(JSONObject pager, SearchParams searchParams) {
    pager.addProperty("page", searchParams.getPage());
    pager.addProperty("recTotal", searchParams.getRecTotal());
    pager.addProperty("recPerPage", searchParams.getRecPerPage());
  }

  private boolean buildData(JSONArray data, SearchParams searchParams) {
    SystemUserExample example = new SystemUserExample();
    Criteria cri = example.createCriteria();
    String userName = searchParams.getSearch();
    if (userName != null && userName.trim().length() > 0) {
      cri.andUserNameLike("%" + userName.trim() + "%");
    }
    long totalCount = userService.countSystemUser(example);
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
    Page<SystemUser> page = new Page<>(searchParams.getPage(), searchParams.getRecPerPage());
    page.setTotalCount((int) totalCount);
    userService.listSystemUser(example, page).forEach(user -> {
      JSONObject e = JSONConvertersUtil.perparedAndGet(user);
      e.addProperty("user_roles", getUserRoles(user));
      data.add(e);
    });
    return true;
  }

  /**
   * getUserRoles: get system user roles join string. </br>
   * 
   * @param user
   */
  private String getUserRoles(SystemUser user) {
    final StringBuilder sb = new StringBuilder();
    Set<AuthRole> authRoles =
        AuthRole.toAuthRoles(userService.listSystemRolesByUser(user.getId()).toArray());
    authRoles.forEach(auth -> sb.append(auth.value()).append(AppConstants.JOIN_TOKEN));
    if (sb.length() > 0) {
      sb.setLength(sb.length() - AppConstants.JOIN_TOKEN.length());
    }
    return sb.toString();
  }

}
