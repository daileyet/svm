package com.openthinks.svm.web.support.model;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import com.google.gson.reflect.TypeToken;
import com.openthinks.svm.core.AppConstants.AuthRole;
import com.openthinks.svm.core.model.BizUserConfig;
import com.openthinks.svm.core.model.SystemRole;
import com.openthinks.svm.core.model.SystemUser;
import com.openthinks.svm.core.service.UserConfigService;
import com.openthinks.svm.web.support.util.JSONUtils;
import com.openthinks.svm.web.support.util.SpringContextUtil;

/**
 * ClassName: SystemUserWrapper </br>
 * date: Aug 23, 2018 10:31:01 AM </br>
 * 
 * @author dailey.dai@openthinks.com
 * @since JDK 1.8
 */
public final class SystemUserWrapper extends SystemUser implements SubmitMode {
  private int[] roles;

  private String userPass2;
  protected String model = MODEL_CREATE;

  private BizUserConfig userConfig;
  private Map<String, Object> configItemsMap;

  public SystemUserWrapper() {}

  public SystemUserWrapper(SystemUser record) {
    this(record, Collections.emptyList());
  }

  public SystemUserWrapper(SystemUser record, List<SystemRole> roles2) {
    this.setId(record.getId());
    this.setUserName(record.getUserName());
    this.setUserPass(record.getUserPass());
    this.setCreateTime(record.getCreateTime());
    this.roles = new int[roles2.size()];
    for (int i = 0; i < roles.length; i++) {
      this.roles[i] = AuthRole.toAuthRole(roles2.get(i).getRoleName()).value();
    }
    this.model = MODEL_UPDATE;
  }

  public final int[] getRoles() {
    return roles;
  }

  public final void setRoles(int[] roles) {
    this.roles = roles;
  }

  public final List<AuthRole> getAuthRoles() {
    List<AuthRole> roleList = new ArrayList<>();
    if (roles == null || roles.length == 0) {
      return roleList;
    }
    for (int roleVal : roles) {
      AuthRole role = AuthRole.toAuthRole(roleVal);
      if (role != null) {
        roleList.add(role);
      }
    }
    return roleList;
  }

  @Override
  public final String getModel() {
    return model;
  }

  public final void setModel(String model) {
    this.model = model;
  }

  public final String getUserPass2() {
    return userPass2;
  }

  public final void setUserPass2(String userPass2) {
    this.userPass2 = userPass2;
  }

  public final SystemUserWrapper hideUserPass() {
    this.setUserPass(null);
    return this;
  }

  public synchronized BizUserConfig getUserConfig() {
    if (userConfig == null) {
      userConfig =
          SpringContextUtil.getContext().getBean(UserConfigService.class).findUserConfig(getId());
    }
    return userConfig;
  }


  private final Type configType = new TypeToken<Map<String, Object>>() {}.getType();

  public synchronized Map<String, Object> getConfigItemsMap() {
    if (configItemsMap != null) {
      return configItemsMap;
    }
    BizUserConfig userConfigObj = getUserConfig();
    if (userConfigObj == null || userConfigObj.getContent() == null
        || userConfigObj.getContent().trim().length() == 0) {
      return Collections.emptyMap();
    }
    String jsonStr = userConfigObj.getContent();
    try {
      configItemsMap = JSONUtils.fromJSON(jsonStr, configType);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return configItemsMap == null ? Collections.emptyMap() : configItemsMap;
  }

}
