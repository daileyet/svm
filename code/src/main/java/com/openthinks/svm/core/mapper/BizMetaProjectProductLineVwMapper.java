package com.openthinks.svm.core.mapper;

import com.openthinks.svm.core.model.BizMetaProjectProductLineVw;
import com.openthinks.svm.core.model.BizMetaProjectProductLineVwExample;
import java.util.List;
import net.sourceforge.orm.mybatis.Page;

public interface BizMetaProjectProductLineVwMapper {
    long countByExample(BizMetaProjectProductLineVwExample example);

    List<BizMetaProjectProductLineVw> selectByExampleWithRowbounds(BizMetaProjectProductLineVwExample example, Page<BizMetaProjectProductLineVw> rowBounds);

    BizMetaProjectProductLineVw selectOneByExample(BizMetaProjectProductLineVwExample example);

    List<BizMetaProjectProductLineVw> selectByExample(BizMetaProjectProductLineVwExample example);
}