package com.openthinks.svm.core.mapper;

import com.openthinks.svm.core.model.BizReleaseAttr;
import com.openthinks.svm.core.model.BizReleaseAttrExample;
import java.util.List;
import net.sourceforge.orm.mybatis.Page;
import org.apache.ibatis.annotations.Param;

public interface BizReleaseAttrMapper {
    long countByExample(BizReleaseAttrExample example);

    int deleteByExample(BizReleaseAttrExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BizReleaseAttr record);

    int insertSelective(BizReleaseAttr record);

    List<BizReleaseAttr> selectByExampleWithRowbounds(BizReleaseAttrExample example, Page<BizReleaseAttr> rowBounds);

    BizReleaseAttr selectOneByExample(BizReleaseAttrExample example);

    List<BizReleaseAttr> selectByExample(BizReleaseAttrExample example);

    BizReleaseAttr selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BizReleaseAttr record, @Param("example") BizReleaseAttrExample example);

    int updateByExample(@Param("record") BizReleaseAttr record, @Param("example") BizReleaseAttrExample example);

    int updateByPrimaryKeySelective(BizReleaseAttr record);

    int updateByPrimaryKey(BizReleaseAttr record);
}