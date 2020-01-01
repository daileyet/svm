package com.openthinks.svm.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.openthinks.svm.core.mapper.BizUserConfigMapper;
import com.openthinks.svm.core.model.BizUserConfig;
import com.openthinks.svm.core.service.UserConfigService;

/**
 * ClassName: UserConfigServiceImpl <br>
 * date: May 20, 2019 9:26:47 AM <br>
 * 
 * @author dailey.dai@openthinks.com
 * @since JDK 1.8
 */
@Service
public class UserConfigServiceImpl implements UserConfigService {

  @Autowired
  BizUserConfigMapper userConfigMapper;

  @Override
  public BizUserConfig findUserConfig(long userId) {
    return userConfigMapper.selectByPrimaryKey(userId);
  }

}
