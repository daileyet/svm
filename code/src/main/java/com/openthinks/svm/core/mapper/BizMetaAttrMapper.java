package com.openthinks.svm.core.mapper;

import com.openthinks.svm.core.model.BizMetaAttr;
import com.openthinks.svm.core.model.BizMetaAttrExample;
import java.util.List;
import net.sourceforge.orm.mybatis.Page;
import org.apache.ibatis.annotations.Param;

public interface BizMetaAttrMapper {
    long countByExample(BizMetaAttrExample example);

    int deleteByExample(BizMetaAttrExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BizMetaAttr record);

    int insertSelective(BizMetaAttr record);

    List<BizMetaAttr> selectByExampleWithRowbounds(BizMetaAttrExample example, Page<BizMetaAttr> rowBounds);

    BizMetaAttr selectOneByExample(BizMetaAttrExample example);

    List<BizMetaAttr> selectByExample(BizMetaAttrExample example);

    BizMetaAttr selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BizMetaAttr record, @Param("example") BizMetaAttrExample example);

    int updateByExample(@Param("record") BizMetaAttr record, @Param("example") BizMetaAttrExample example);

    int updateByPrimaryKeySelective(BizMetaAttr record);

    int updateByPrimaryKey(BizMetaAttr record);
}