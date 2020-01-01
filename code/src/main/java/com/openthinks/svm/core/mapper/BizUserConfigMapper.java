package com.openthinks.svm.core.mapper;

import com.openthinks.svm.core.model.BizUserConfig;
import com.openthinks.svm.core.model.BizUserConfigExample;
import java.util.List;
import net.sourceforge.orm.mybatis.Page;
import org.apache.ibatis.annotations.Param;

public interface BizUserConfigMapper {
    long countByExample(BizUserConfigExample example);

    int deleteByExample(BizUserConfigExample example);

    int deleteByPrimaryKey(Long userId);

    int insert(BizUserConfig record);

    int insertSelective(BizUserConfig record);

    List<BizUserConfig> selectByExampleWithRowbounds(BizUserConfigExample example, Page<BizUserConfig> rowBounds);

    BizUserConfig selectOneByExample(BizUserConfigExample example);

    List<BizUserConfig> selectByExample(BizUserConfigExample example);

    BizUserConfig selectByPrimaryKey(Long userId);

    int updateByExampleSelective(@Param("record") BizUserConfig record, @Param("example") BizUserConfigExample example);

    int updateByExample(@Param("record") BizUserConfig record, @Param("example") BizUserConfigExample example);

    int updateByPrimaryKeySelective(BizUserConfig record);

    int updateByPrimaryKey(BizUserConfig record);
}