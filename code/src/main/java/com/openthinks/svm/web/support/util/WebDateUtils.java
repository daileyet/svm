package com.openthinks.svm.web.support.util;

import java.util.Date;
import com.openthinks.libs.utilities.DateFormatUtil;
import com.openthinks.svm.core.AppConfig;
import com.openthinks.svm.core.util.DateUtils;

/**
 * ClassName: WebDateUtils </br>
 * date: Aug 14, 2018 11:31:20 AM </br>
 * 
 * @author dailey.dai@openthinks.com
 * @since JDK 1.8
 */
public final class WebDateUtils extends DateUtils {

  public static final Date parse(String pattern, String dateStr) {
    try {
      return DateFormatUtil.parse(pattern, dateStr, DateUtils.getTimeZone());
    } catch (Exception e) {
      return null;
    }
  }

  public static final Date parse(String dateStr) {
    return parse(getAppDateFormatPattern(), dateStr);
  }

  public static final String getAppDateFormatPattern() {
    return SpringContextUtil.getContext().getBean(AppConfig.class).getDateFormatPattern();
  }

}
