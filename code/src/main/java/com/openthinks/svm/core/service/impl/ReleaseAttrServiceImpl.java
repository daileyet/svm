package com.openthinks.svm.core.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.openthinks.svm.core.mapper.BizReleaseAttrMapper;
import com.openthinks.svm.core.model.BizReleaseAttr;
import com.openthinks.svm.core.model.BizReleaseAttrExample;
import com.openthinks.svm.core.service.ReleaseAttrService;

/**
 * ClassName: ReleaseAttrServiceImpl <br>
 * date: Apr 29, 2019 4:42:08 PM <br>
 * 
 * @author dailey.dai@openthinks.com
 * @since JDK 1.8
 */
@Service
public class ReleaseAttrServiceImpl implements ReleaseAttrService {

  @Autowired
  BizReleaseAttrMapper releaseAttrMapper;

  @Override
  public List<BizReleaseAttr> searchByReleaseId(Long id) {
    BizReleaseAttrExample example = new BizReleaseAttrExample();
    example.createCriteria().andReleaseIdEqualTo(id);
    return releaseAttrMapper.selectByExample(example);
  }

}
