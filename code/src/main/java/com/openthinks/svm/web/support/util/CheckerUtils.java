package com.openthinks.svm.web.support.util;

import com.openthinks.libs.utilities.Checker;
import com.openthinks.libs.utilities.exception.CheckerNoPassException;

/**
 * ClassName: CheckerUtils </br>
 * date: Aug 15, 2018 10:25:35 AM </br>
 * 
 * @author dailey.dai@openthinks.com
 * @since JDK 1.8
 */
public final class CheckerUtils {

  public static void isLong(Object checkObj, String msg) {
    try {
      Checker.require(checkObj).isLong();
    } catch (Exception e) {
      throw new CheckerNoPassException(msg, e);
    }
  }

  public static boolean isLong(Object checkObj) {
    try {
      Checker.require(checkObj).isLong();
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  public static boolean isInt(Object checkObj) {
    try {
      Checker.require(checkObj).isInteger();
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  public static void isInt(Object checkObj, String msg) {
    try {
      Checker.require(checkObj).isInteger();
    } catch (Exception e) {
      throw new CheckerNoPassException(msg, e);
    }
  }

  public static void notEmpty(Object checkObj, String msg) {
    try {
      Checker.require(checkObj).notEmpty();
    } catch (Exception e) {
      throw new CheckerNoPassException(msg, e);
    }
  }

  public static void notPassed(String msg) {
    Throwable e = null;
    throw new CheckerNoPassException(msg, e);
  }

  public static void notNull(Object checkObj, String msg) {
    try {
      Checker.require(checkObj).notNull();
    } catch (Exception e) {
      throw new CheckerNoPassException(msg, e);
    }
  }


}
