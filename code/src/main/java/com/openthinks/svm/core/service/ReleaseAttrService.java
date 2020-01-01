package com.openthinks.svm.core.service;

import java.util.List;
import com.openthinks.svm.core.model.BizReleaseAttr;

/**
 * ClassName: ReleaseAttrService <br>
 * date: Apr 29, 2019 4:20:19 PM <br>
 * Release信息扩展属性service
 * 
 * @author dailey.dai@openthinks.com
 * @since JDK 1.8
 */
public interface ReleaseAttrService {

  List<BizReleaseAttr> searchByReleaseId(Long id);

}
