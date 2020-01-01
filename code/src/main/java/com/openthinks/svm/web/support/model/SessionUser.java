package com.openthinks.svm.web.support.model;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * ClassName: SessionUser </br>
 * Function: Extend spring security {@link User}. </br>
 * date: Aug 24, 2018 11:06:38 AM </br>
 * 
 * @author dailey.dai@openthinks.com
 * @since JDK 1.8
 */
public final class SessionUser<T> extends User {
  private static final long serialVersionUID = 1151328630625320746L;
  private final T payload;

  public SessionUser(String username, String password, boolean enabled, boolean accountNonExpired,
      boolean credentialsNonExpired, boolean accountNonLocked,
      Collection<? extends GrantedAuthority> authorities, T payload) {
    super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
        authorities);
    this.payload = payload;
  }

  public SessionUser(String username, String password,
      Collection<? extends GrantedAuthority> authorities, T payload) {
    super(username, password, authorities);
    this.payload = payload;
  }


  public T getPayload() {
    return payload;
  }
}
