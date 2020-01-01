package com.openthinks.svm.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import com.openthinks.libs.utilities.json.Valueable;
import com.openthinks.svm.core.service.MetaService;
import com.openthinks.svm.web.support.util.SpringContextUtil;

/**
 * Release版本类型,子模块类型;往往一个独立的产品软件由很多组成部分;<BR>
 * 例如: 汽车中控台的软件版本由 1)系统软件 2)上层应用软件 3)底层驱动软件 4)MCU软件 5)芯片供应商软件
 * 
 * @author dailey.dai@openthinks.com
 *
 */
public abstract class ReleaseType implements Valueable<String> {

  private Long id;
  /**
   * Release版本类型名称,子模块类型名称
   */
  private String name;
  private String description;
  /**
   * XML tag for this type
   */
  private String xmlTag;

  /**
   * 排序
   */
  private Integer ordinal;

  @Override
  public String value() {
    return name;
  }

  public String getValue() {
    return value();
  }

  public String getDescription() {
    return description;
  }

  public Long getId() {
    return id;
  }

  public String getXmlTag() {
    return xmlTag;
  }

  public Integer getOrdinal() {
    return ordinal;
  }

  public String toXMLTag() {
    if (xmlTag == null || xmlTag.trim().isEmpty()) {
      return getValue();
    }
    return xmlTag;
  }


  public Integer ordinal() {
    return getOrdinal();
  }

  public String ordinalStr() {
    return String.valueOf(ordinal());
  }

  @Override
  public String toString() {
    return getValue();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ReleaseType other = (ReleaseType) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    return true;
  }



  // Cached ReleaseTypes from database
  private static final List<ReleaseType> CACHED_TYPES = new ArrayList<>();

  /**
   * fetch and return all release types
   * 
   * @return list of {@link ReleaseType}
   */
  public static final synchronized List<ReleaseType> values() {
    if (CACHED_TYPES.size() == 0) {
      SpringContextUtil.getContext().getBean(MetaService.class).findAllMetaReleases()
          .forEach((e) -> {
            ReleaseType type = new ReleaseType() {};
            type.description = e.getDescription();
            type.id = e.getId();
            type.name = e.getName();
            type.xmlTag = e.getXmlTag();
            CACHED_TYPES.add(type);
          });
    }
    return Collections.unmodifiableList(CACHED_TYPES);
  }

  /**
   * convert release type name string value to {@link ReleaseType}
   * 
   * @param val release type name
   * @return instance of {@link ReleaseType} or empty
   */
  public static final Optional<ReleaseType> toReleaseType(String val) {
    for (ReleaseType rtype : ReleaseType.values()) {
      if (rtype.value().equalsIgnoreCase(val))
        return Optional.of(rtype);
    }
    return Optional.empty();
  }

}
