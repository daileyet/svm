package com.openthinks.svm.core.mapper;

import com.openthinks.svm.core.model.BizReleaseInfo;
import com.openthinks.svm.core.model.BizReleaseInfoExample;
import com.openthinks.svm.core.model.BizReleaseInfoWithBLOBs;
import java.util.List;
import net.sourceforge.orm.mybatis.Page;
import org.apache.ibatis.annotations.Param;

public interface BizReleaseInfoMapper {
    long countByExample(BizReleaseInfoExample example);

    int deleteByExample(BizReleaseInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BizReleaseInfoWithBLOBs record);

    int insertSelective(BizReleaseInfoWithBLOBs record);

    List<BizReleaseInfoWithBLOBs> selectByExampleWithBLOBsWithRowbounds(BizReleaseInfoExample example, Page<BizReleaseInfo> rowBounds);

    BizReleaseInfoWithBLOBs selectOneByExampleWithBLOBs(BizReleaseInfoExample example);

    List<BizReleaseInfoWithBLOBs> selectByExampleWithBLOBs(BizReleaseInfoExample example);

    List<BizReleaseInfo> selectByExampleWithRowbounds(BizReleaseInfoExample example, Page<BizReleaseInfo> rowBounds);

    BizReleaseInfoWithBLOBs selectOneByExample(BizReleaseInfoExample example);

    List<BizReleaseInfo> selectByExample(BizReleaseInfoExample example);

    BizReleaseInfoWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BizReleaseInfoWithBLOBs record, @Param("example") BizReleaseInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") BizReleaseInfoWithBLOBs record, @Param("example") BizReleaseInfoExample example);

    int updateByExample(@Param("record") BizReleaseInfo record, @Param("example") BizReleaseInfoExample example);

    int updateByPrimaryKeySelective(BizReleaseInfoWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(BizReleaseInfoWithBLOBs record);

    int updateByPrimaryKey(BizReleaseInfo record);
}