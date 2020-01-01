package com.openthinks.svm.core.service;

import java.util.List;
import com.openthinks.svm.core.model.BizSequence;
import com.openthinks.svm.core.model.BizSequenceExample;
import com.openthinks.svm.core.model.BizSequenceVw;
import com.openthinks.svm.core.model.BizSequenceVwExample;
import net.sourceforge.orm.mybatis.Page;

/**
 * 版本序列信息service
 * 
 * @author dailey.dai@openthinks.com
 *
 */
public interface SequenceService {

  public BizSequence findSequenceByPrjId(Long projectId);

  public BizSequence findSequenceByPrjName(String name);

  public boolean saveSequence(BizSequence record);

  public boolean increament(Long id);

  public boolean increament(String name);

  public int next(Long id);

  public int next(String name);

  public long countSeqCount(BizSequenceExample example);

  public long countSeqCount(BizSequenceVwExample example);

  public List<BizSequence> searchSeq(BizSequenceExample example, Page<BizSequence> page);

  public List<BizSequenceVw> searchSeq(BizSequenceVwExample example, Page<BizSequenceVw> page);

  public BizSequence findSequenceByPrimaryKey(Long seqId);

  public BizSequenceVw findSequenceVwByPrimaryKey(Long seqId);

  public boolean save(BizSequence record);

  public boolean delete(Long id);

}
