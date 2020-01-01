package com.openthinks.svm.core.mapper;

import com.openthinks.svm.core.model.BizSequenceVw;
import com.openthinks.svm.core.model.BizSequenceVwExample;
import java.util.List;
import net.sourceforge.orm.mybatis.Page;

public interface BizSequenceVwMapper {
    long countByExample(BizSequenceVwExample example);

    List<BizSequenceVw> selectByExampleWithRowbounds(BizSequenceVwExample example, Page<BizSequenceVw> rowBounds);

    BizSequenceVw selectOneByExample(BizSequenceVwExample example);

    List<BizSequenceVw> selectByExample(BizSequenceVwExample example);
}