package com.openthinks.svm.core.service;

import com.openthinks.svm.core.model.BizUserConfig;

/**
 * ClassName: UserConfigService <br>
 * date: May 20, 2019 9:11:34 AM <br>
 * 
 * @author dailey.dai@openthinks.com
 * @since JDK 1.8
 */
public interface UserConfigService {

  BizUserConfig findUserConfig(long userId);

}
