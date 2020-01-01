package com.openthinks.svm.core.mapper;

import com.openthinks.svm.core.model.BizSequence;
import com.openthinks.svm.core.model.BizSequenceExample;
import java.util.List;
import net.sourceforge.orm.mybatis.Page;
import org.apache.ibatis.annotations.Param;

public interface BizSequenceMapper {
    long countByExample(BizSequenceExample example);

    int deleteByExample(BizSequenceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BizSequence record);

    int insertSelective(BizSequence record);

    List<BizSequence> selectByExampleWithRowbounds(BizSequenceExample example, Page<BizSequence> rowBounds);

    BizSequence selectOneByExample(BizSequenceExample example);

    List<BizSequence> selectByExample(BizSequenceExample example);

    BizSequence selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BizSequence record, @Param("example") BizSequenceExample example);

    int updateByExample(@Param("record") BizSequence record, @Param("example") BizSequenceExample example);

    int updateByPrimaryKeySelective(BizSequence record);

    int updateByPrimaryKey(BizSequence record);
}