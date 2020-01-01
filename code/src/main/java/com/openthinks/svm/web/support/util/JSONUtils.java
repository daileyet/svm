package com.openthinks.svm.web.support.util;

import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import com.google.gson.Gson;

/**
 * ClassName: JSONUtils </br>
 * date: Aug 8, 2018 2:09:00 PM </br>
 * 
 * @author dailey.dai@openthinks.com
 * @since JDK 1.8
 */
public final class JSONUtils {
  private JSONUtils() {}

  private final static Gson GSON;

  static {
    GSON = new Gson();
  }

  public static final String stringify(Object jsonObject) {
    return GSON.toJson(jsonObject);
  }

  public static final byte[] bytes(Object jsonObject) {
    try {
      return stringify(jsonObject).getBytes("UTF-8");
    } catch (UnsupportedEncodingException e) {
      return stringify(jsonObject).getBytes();
    }
  }

  public static final <T> T fromJSON(String jsonStr, Class<T> type) {
    return GSON.fromJson(jsonStr, type);
  }

  public static final <T> T fromJSON(String jsonStr, Type type) {
    return GSON.fromJson(jsonStr, type);
  }

  public static final <T> T fromJSON(Reader reader, Class<T> type) {
    return GSON.fromJson(reader, type);
  }

}
