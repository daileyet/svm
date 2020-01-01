package com.openthinks.svm.web.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.openthinks.svm.core.AppConstants.AuthRole;
import com.openthinks.svm.core.model.SystemRole;
import com.openthinks.svm.core.service.SystemUserService;

/**
 * ClassName: WebInitialBean <br>
 * date: Mar 18, 2019 9:09:38 AM <br>
 * 
 * @author dailey.dai@openthinks.com
 * @since JDK 1.8
 */
@Order(Ordered.LOWEST_PRECEDENCE)
@Component
public class WebInitialBean implements InitializingBean {
  static Logger LOGGER = LoggerFactory.getLogger(WebInitialBean.class);

  @Autowired
  SystemUserService userService;

  @Override
  public void afterPropertiesSet() throws Exception {
    try {
      initialRole();
      // initialMetaReleaseType();
    } catch (Exception e) {
      LOGGER.error("Failed to do initial on WebInitialBean by reason:{}", e);
    }
  }

  private void initialRole() {
    if (!userService.listAllSystemRoles().isEmpty())
      return;
    for (AuthRole role : AuthRole.values()) {
      SystemRole record = new SystemRole.Builder().roleName(role.name())
          .roleDesc(role.shortName().toLowerCase()).build();
      try {
        userService.saveSystemRole(record);
      } catch (Exception e) {
        LOGGER.error("Failed to save SystemRole:{} by reason:{}", record, e);
      }
    }

  }

  /*
   * private void initialMetaReleaseType() { List<String> metaReleaseList =
   * metaService.findAllMetaReleases().stream() .map(mr ->
   * mr.getName()).collect(Collectors.toList());
   * 
   * if (!metaReleaseList.isEmpty()) return;
   * 
   * for (ReleaseType type : ReleaseType.values()) { BizMetaRelease record = new
   * BizMetaRelease.Builder().name(type.getValue()).build(); try {
   * metaService.saveMetaRelease(record); } catch (Exception e) {
   * LOGGER.error("Failed to save BizMetaRelease:{} by reason:{}", record, e); } } }
   */

}
