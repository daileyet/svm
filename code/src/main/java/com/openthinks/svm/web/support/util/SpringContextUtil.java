package com.openthinks.svm.web.support.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import net.sourceforge.springframework.SpringContext;

/**
 * ClassName: SpringContextUtil </br>
 * date: Aug 2, 2018 2:25:27 PM </br>
 * 
 * @author dailey.dai@openthinks.com
 * @since JDK 1.8
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public final class SpringContextUtil implements ApplicationContextAware {
  private static ApplicationContext applicationContext = null;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    if (SpringContextUtil.applicationContext == null) {
      SpringContextUtil.applicationContext = applicationContext;
      new SpringContext().setApplicationContext(applicationContext);
    }
  }

  public final static ApplicationContext getContext() {
    return applicationContext;
  }

}
