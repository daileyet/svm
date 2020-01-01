package com.openthinks.svm.web.support.model;

/**
 * 表单提交模式
 * 
 * @author dailey.dai@openthinks.com
 *
 */
public interface SubmitMode {
  public static final String MODEL_CREATE = "C";
  public static final String MODEL_UPDATE = "U";

  public default boolean isCreateMode() {
    return MODEL_CREATE.equalsIgnoreCase(getModel());
  }

  public String getModel();

}
