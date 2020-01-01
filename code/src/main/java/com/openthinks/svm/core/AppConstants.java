package com.openthinks.svm.core;

import java.util.HashSet;
import java.util.Set;
import com.openthinks.libs.utilities.json.Valueable;
import com.openthinks.svm.core.model.SystemRole;

/**
 * ClassName: AppConstants </br>
 * date: Aug 3, 2018 5:01:55 PM </br>
 * 
 * @author dailey.dai@openthinks.com
 * @since JDK 1.8
 */
public final class AppConstants {
  private AppConstants() {}

  public static final String JOIN_TOKEN = ":";
  public static final String ORDER_ASC = "asc";
  public static final String ORDER_DESC = "desc";
  //////////////////////////////////////////////////////////
  /**
   * 元项目及生产线的类别默认信息
   */
  public static final String META_CATEGORY_DEFAULT = "CN";
  ///////////////////////////////////////////////////////////////////////////

  /**
   * 主版本有效状态枚举
   * 
   * @author dailey.dai@openthinks.com
   *
   */
  public static enum BuildState implements Valueable<Byte> {
    VALID {
      @Override
      public Byte value() {
        return 1;
      }
    },
    INVALID {
      @Override
      public Byte value() {
        return 0;
      }
    };
  }

  /**
   * 主版本同步状态枚举
   * 
   * @author dailey.dai@openthinks.com
   *
   */
  public static enum BuildSyncStatus implements Valueable<Byte> {
    NOT_SYNC {
      @Override
      public Byte value() {
        return 0;
      }
    },
    SYNC {
      @Override
      public Byte value() {
        return 1;
      }
    };
  }

  /**
   * 项目状态枚举
   * 
   * @author dailey.dai@openthinks.com
   *
   */
  public static enum MetaProjectState implements Valueable<Byte> {
    ACTIVE {
      @Override
      public Byte value() {
        return 1;
      }
    },
    IN_ACTIVE {
      @Override
      public Byte value() {
        return 0;
      }
    };
  }

  /**
   * 用户角色
   * 
   * @author dailey.dai@openthinks.com
   *
   */
  public static enum AuthRole implements Valueable<Integer> {
    /**
     * 可以查看，修改系统用户
     */
    ROLE_ADMIN {
      @Override
      public Integer value() {
        return 3;
      }
    },
    ROLE_CONTRIBUTOR {
      @Override
      public Integer value() {
        return 2;
      }
    },
    ROLE_VIEWER {
      @Override
      public Integer value() {
        return 1;
      }
    },
    ROLE_ANONYMOUS {
      @Override
      public Integer value() {
        return 0;
      }
    };

    public String shortName() {
      return name().split("_")[1];
    }

    public static AuthRole toAuthRole(Object obj) {
      if (obj == null)
        return null;
      Object target = obj;
      if (obj instanceof SystemRole) {
        target = ((SystemRole) obj).getRoleName();
      }
      for (AuthRole state : AuthRole.values()) {
        try {
          if (state.value().intValue() == Integer.valueOf(target.toString())) {
            return state;
          }
        } catch (NumberFormatException e) {
          if (state.name().equalsIgnoreCase(target.toString())) {
            return state;
          }
        }
      }
      return null;
    }

    public static Set<AuthRole> toAuthRoles(Object... objects) {
      Set<AuthRole> set = new HashSet<>();
      if (objects != null) {
        for (Object obj : objects) {
          AuthRole role = AuthRole.toAuthRole(obj);
          if (role != null) {
            set.add(role);
          }
        }
      }
      return set;
    }
  }


  /**
   * 元配置项信息类别常量枚举类
   * 
   * @author dailey.dai@openthinks.com
   *
   */
  public static enum AttributeCategory implements Valueable<String> {
    RELEASE("Release"), DEFAULT("Default");
    private String display;

    private AttributeCategory(String display) {
      this.display = display;
    }

    @Override
    public String value() {
      return toString();
    }

    public String getValue() {
      return value();
    }

    public String getDisplay() {
      return display;
    }

    public static AttributeCategory toAttributeCategory(String val) {
      for (AttributeCategory rtype : AttributeCategory.values()) {
        if (rtype.value().equalsIgnoreCase(val))
          return rtype;
      }
      return AttributeCategory.DEFAULT;
    }
  }

}
