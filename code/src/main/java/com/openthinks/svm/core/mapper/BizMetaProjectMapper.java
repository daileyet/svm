package com.openthinks.svm.core.mapper;

import com.openthinks.svm.core.model.BizMetaProject;
import com.openthinks.svm.core.model.BizMetaProjectExample;
import java.util.List;
import net.sourceforge.orm.mybatis.Page;
import org.apache.ibatis.annotations.Param;

public interface BizMetaProjectMapper {
    long countByExample(BizMetaProjectExample example);

    int deleteByExample(BizMetaProjectExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BizMetaProject record);

    int insertSelective(BizMetaProject record);

    List<BizMetaProject> selectByExampleWithRowbounds(BizMetaProjectExample example, Page<BizMetaProject> rowBounds);

    BizMetaProject selectOneByExample(BizMetaProjectExample example);

    List<BizMetaProject> selectByExample(BizMetaProjectExample example);

    BizMetaProject selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BizMetaProject record, @Param("example") BizMetaProjectExample example);

    int updateByExample(@Param("record") BizMetaProject record, @Param("example") BizMetaProjectExample example);

    int updateByPrimaryKeySelective(BizMetaProject record);

    int updateByPrimaryKey(BizMetaProject record);
}