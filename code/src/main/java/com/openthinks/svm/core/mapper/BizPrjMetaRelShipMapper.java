package com.openthinks.svm.core.mapper;

import com.openthinks.svm.core.model.BizPrjMetaRelShip;
import com.openthinks.svm.core.model.BizPrjMetaRelShipExample;
import java.util.List;
import net.sourceforge.orm.mybatis.Page;
import org.apache.ibatis.annotations.Param;

public interface BizPrjMetaRelShipMapper {
    long countByExample(BizPrjMetaRelShipExample example);

    int deleteByExample(BizPrjMetaRelShipExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BizPrjMetaRelShip record);

    int insertSelective(BizPrjMetaRelShip record);

    List<BizPrjMetaRelShip> selectByExampleWithRowbounds(BizPrjMetaRelShipExample example, Page<BizPrjMetaRelShip> rowBounds);

    BizPrjMetaRelShip selectOneByExample(BizPrjMetaRelShipExample example);

    List<BizPrjMetaRelShip> selectByExample(BizPrjMetaRelShipExample example);

    BizPrjMetaRelShip selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BizPrjMetaRelShip record, @Param("example") BizPrjMetaRelShipExample example);

    int updateByExample(@Param("record") BizPrjMetaRelShip record, @Param("example") BizPrjMetaRelShipExample example);

    int updateByPrimaryKeySelective(BizPrjMetaRelShip record);

    int updateByPrimaryKey(BizPrjMetaRelShip record);
}