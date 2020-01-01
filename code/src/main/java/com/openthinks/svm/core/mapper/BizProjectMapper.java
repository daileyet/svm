package com.openthinks.svm.core.mapper;

import com.openthinks.svm.core.model.BizProject;
import com.openthinks.svm.core.model.BizProjectExample;
import java.util.List;
import net.sourceforge.orm.mybatis.Page;
import org.apache.ibatis.annotations.Param;

public interface BizProjectMapper {
    long countByExample(BizProjectExample example);

    int deleteByExample(BizProjectExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BizProject record);

    int insertSelective(BizProject record);

    List<BizProject> selectByExampleWithRowbounds(BizProjectExample example, Page<BizProject> rowBounds);

    BizProject selectOneByExample(BizProjectExample example);

    List<BizProject> selectByExample(BizProjectExample example);

    BizProject selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BizProject record, @Param("example") BizProjectExample example);

    int updateByExample(@Param("record") BizProject record, @Param("example") BizProjectExample example);

    int updateByPrimaryKeySelective(BizProject record);

    int updateByPrimaryKey(BizProject record);
}