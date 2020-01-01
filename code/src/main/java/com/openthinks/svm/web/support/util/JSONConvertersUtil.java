package com.openthinks.svm.web.support.util;

import com.openthinks.libs.utilities.json.JSONConverters;
import com.openthinks.libs.utilities.json.JSONObject;

/**
 * ClassName: JSONConvertersUtil </br>
 * date: Aug 17, 2018 2:35:57 PM </br>
 * 
 * @author dailey.dai@openthinks.com
 * @since JDK 1.8
 */
public final class JSONConvertersUtil {

  static {
    JSONConverters.reload("/converterConfig.properties");
    JSONConverters.ativeDefaultConveterAsUnderline();
  }

  public static final JSONObject perparedAndGet(Object obj) {
    return JSONConverters.perparedAndGet(obj);
  }

}
