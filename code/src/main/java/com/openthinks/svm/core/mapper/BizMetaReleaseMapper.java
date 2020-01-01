package com.openthinks.svm.core.mapper;

import com.openthinks.svm.core.model.BizMetaRelease;
import com.openthinks.svm.core.model.BizMetaReleaseExample;
import java.util.List;
import net.sourceforge.orm.mybatis.Page;
import org.apache.ibatis.annotations.Param;

public interface BizMetaReleaseMapper {
    long countByExample(BizMetaReleaseExample example);

    int deleteByExample(BizMetaReleaseExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BizMetaRelease record);

    int insertSelective(BizMetaRelease record);

    List<BizMetaRelease> selectByExampleWithRowbounds(BizMetaReleaseExample example, Page<BizMetaRelease> rowBounds);

    BizMetaRelease selectOneByExample(BizMetaReleaseExample example);

    List<BizMetaRelease> selectByExample(BizMetaReleaseExample example);

    BizMetaRelease selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BizMetaRelease record, @Param("example") BizMetaReleaseExample example);

    int updateByExample(@Param("record") BizMetaRelease record, @Param("example") BizMetaReleaseExample example);

    int updateByPrimaryKeySelective(BizMetaRelease record);

    int updateByPrimaryKey(BizMetaRelease record);
}