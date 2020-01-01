package com.openthinks.svm.core.mapper;

import com.openthinks.svm.core.model.BizVersionReleaseShip;
import com.openthinks.svm.core.model.BizVersionReleaseShipExample;
import java.util.List;
import net.sourceforge.orm.mybatis.Page;
import org.apache.ibatis.annotations.Param;

public interface BizVersionReleaseShipMapper {
    long countByExample(BizVersionReleaseShipExample example);

    int deleteByExample(BizVersionReleaseShipExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BizVersionReleaseShip record);

    int insertSelective(BizVersionReleaseShip record);

    List<BizVersionReleaseShip> selectByExampleWithRowbounds(BizVersionReleaseShipExample example, Page<BizVersionReleaseShip> rowBounds);

    BizVersionReleaseShip selectOneByExample(BizVersionReleaseShipExample example);

    List<BizVersionReleaseShip> selectByExample(BizVersionReleaseShipExample example);

    BizVersionReleaseShip selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BizVersionReleaseShip record, @Param("example") BizVersionReleaseShipExample example);

    int updateByExample(@Param("record") BizVersionReleaseShip record, @Param("example") BizVersionReleaseShipExample example);

    int updateByPrimaryKeySelective(BizVersionReleaseShip record);

    int updateByPrimaryKey(BizVersionReleaseShip record);
}