package com.openthinks.svm.core.mapper;

import com.openthinks.svm.core.model.BizMetaProductline;
import com.openthinks.svm.core.model.BizMetaProductlineExample;
import java.util.List;
import net.sourceforge.orm.mybatis.Page;
import org.apache.ibatis.annotations.Param;

public interface BizMetaProductlineMapper {
    long countByExample(BizMetaProductlineExample example);

    int deleteByExample(BizMetaProductlineExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BizMetaProductline record);

    int insertSelective(BizMetaProductline record);

    List<BizMetaProductline> selectByExampleWithRowbounds(BizMetaProductlineExample example, Page<BizMetaProductline> rowBounds);

    BizMetaProductline selectOneByExample(BizMetaProductlineExample example);

    List<BizMetaProductline> selectByExample(BizMetaProductlineExample example);

    BizMetaProductline selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BizMetaProductline record, @Param("example") BizMetaProductlineExample example);

    int updateByExample(@Param("record") BizMetaProductline record, @Param("example") BizMetaProductlineExample example);

    int updateByPrimaryKeySelective(BizMetaProductline record);

    int updateByPrimaryKey(BizMetaProductline record);
}