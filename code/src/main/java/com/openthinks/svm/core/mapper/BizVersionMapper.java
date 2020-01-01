package com.openthinks.svm.core.mapper;

import com.openthinks.svm.core.model.BizVersion;
import com.openthinks.svm.core.model.BizVersionExample;
import java.util.List;
import net.sourceforge.orm.mybatis.Page;
import org.apache.ibatis.annotations.Param;

public interface BizVersionMapper {
    long countByExample(BizVersionExample example);

    int deleteByExample(BizVersionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BizVersion record);

    int insertSelective(BizVersion record);

    List<BizVersion> selectByExampleWithBLOBsWithRowbounds(BizVersionExample example, Page<BizVersion> rowBounds);

    BizVersion selectOneByExampleWithBLOBs(BizVersionExample example);

    List<BizVersion> selectByExampleWithBLOBs(BizVersionExample example);

    List<BizVersion> selectByExampleWithRowbounds(BizVersionExample example, Page<BizVersion> rowBounds);

    BizVersion selectOneByExample(BizVersionExample example);

    List<BizVersion> selectByExample(BizVersionExample example);

    BizVersion selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BizVersion record, @Param("example") BizVersionExample example);

    int updateByExampleWithBLOBs(@Param("record") BizVersion record, @Param("example") BizVersionExample example);

    int updateByExample(@Param("record") BizVersion record, @Param("example") BizVersionExample example);

    int updateByPrimaryKeySelective(BizVersion record);

    int updateByPrimaryKeyWithBLOBs(BizVersion record);

    int updateByPrimaryKey(BizVersion record);
}