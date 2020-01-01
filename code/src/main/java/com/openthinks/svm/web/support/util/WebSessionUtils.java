package com.openthinks.svm.web.support.util;

import java.util.Collections;
import java.util.Map;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.openthinks.svm.core.model.SystemUser;
import com.openthinks.svm.web.support.model.SessionUser;
import com.openthinks.svm.web.support.model.SystemUserWrapper;

/**
 * ClassName: WebSessionUtils </br>
 * date: Aug 23, 2018 2:42:15 PM </br>
 * 
 * @author dailey.dai@openthinks.com
 * @since JDK 1.8
 */
public final class WebSessionUtils {

  public static boolean isCurrentUser(SystemUser user) {
    SystemUser sessionUser = getCurrentUser();
    if (sessionUser != null && user != null && sessionUser.getId().equals(user.getId())) {
      return true;
    }
    return false;
  }

  @SuppressWarnings("unchecked")
  public static SystemUser getCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (!(authentication instanceof AnonymousAuthenticationToken)) {
      Object principal = authentication.getPrincipal();
      if (principal instanceof SessionUser<?>) {
        return ((SessionUser<SystemUserWrapper>) principal).getPayload();
      }
    }
    return null;
  }

  public static Map<String, Object> getCurrentUserConfig() {
    SystemUser user = getCurrentUser();
    if (user instanceof SystemUserWrapper) {
      return ((SystemUserWrapper) user).getConfigItemsMap();
    } else {
      return Collections.emptyMap();
    }
  }

}
