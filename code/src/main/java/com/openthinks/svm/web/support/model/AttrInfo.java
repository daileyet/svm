package com.openthinks.svm.web.support.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.openthinks.svm.core.model.BizReleaseAttr;

/**
 * ClassName: AttrInfo <br>
 * 子模块自定义属性表单模型<BR>
 * date: Apr 29, 2019 3:53:18 PM <br>
 * 
 * @author dailey.dai@openthinks.com
 * @since JDK 1.8
 */
public class AttrInfo {
  public static final String ATTR_SPLIT_TOKEN = "#";
  private String[] attrP = new String[0];

  private transient List<BizReleaseAttr> attrList;

  public String[] getAttrP() {
    return attrP;
  }

  public void setAttrP(String[] attrP) {
    this.attrP = attrP;
  }

  public int size() {
    return attrP == null ? 0 : attrP.length;
  }

  public List<BizReleaseAttr> convertTo() {
    if (attrList != null) {
      return attrList;
    }

    List<BizReleaseAttr> list = new ArrayList<>();
    for (int i = 0, j = size(); i < j; i++) {
      String attribute = attrP[i];
      if (attribute == null || attribute.trim().length() == 0
          || attribute.indexOf(ATTR_SPLIT_TOKEN) == -1) {
        continue;
      }
      String[] attrs = attribute.split(ATTR_SPLIT_TOKEN);
      if (attrs[0] == null || attrs[0].trim().length() == 0) {
        continue;
      }
      BizReleaseAttr record =
          new BizReleaseAttr.Builder().itemName(attrs[0]).itemValue(attrs[1]).build();
      list.add(record);
    }
    this.attrList = list;
    return list;
  }

  public boolean isValid() {
    List<BizReleaseAttr> attrs = convertTo();
    Set<String> nameSet = new HashSet<>();
    for (BizReleaseAttr attr : attrs) {
      nameSet.add(attr.getItemName());
    }
    return nameSet.size() == attrs.size();
  }

}
