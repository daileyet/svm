/**
 * 
 */
package com.openthinks.svm.core.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.openthinks.svm.core.mapper.BizProjectMapper;
import com.openthinks.svm.core.mapper.BizSequenceMapper;
import com.openthinks.svm.core.mapper.BizSequenceVwMapper;
import com.openthinks.svm.core.model.BizProject;
import com.openthinks.svm.core.model.BizProjectExample;
import com.openthinks.svm.core.model.BizSequence;
import com.openthinks.svm.core.model.BizSequenceExample;
import com.openthinks.svm.core.model.BizSequenceVw;
import com.openthinks.svm.core.model.BizSequenceVwExample;
import com.openthinks.svm.core.service.SequenceService;
import net.sourceforge.orm.mybatis.Page;

/**
 * @author dailey.dai@openthinks.com
 *
 */
@Service
public class SequenceServiceImpl implements SequenceService {
  @Autowired
  BizSequenceMapper sequenceMapper;
  @Autowired
  BizSequenceVwMapper sequenceVwMapper;
  @Autowired
  BizProjectMapper projectMapper;

  @Override
  public BizSequence findSequenceByPrjName(String name) {
    BizProjectExample example = new BizProjectExample();
    example.createCriteria().andShortNameEqualTo(name);
    BizProject record = projectMapper.selectOneByExample(example);
    if (record == null)
      return null;
    return findSequenceByPrjId(record.getId());
  }

  @Override
  public BizSequence findSequenceByPrjId(Long projectId) {
    BizSequenceExample example2 = new BizSequenceExample();
    example2.createCriteria().andPrjIdEqualTo(projectId);
    return sequenceMapper.selectOneByExample(example2);
  }

  @Override
  public boolean saveSequence(BizSequence record) {
    if (record.getId() != null) {
      return sequenceMapper.updateByPrimaryKeySelective(record) > 0;
    } else {
      return sequenceMapper.insertSelective(record) > 0;
    }

  }

  @Override
  public boolean increament(Long id) {
    BizSequence sequence = sequenceMapper.selectByPrimaryKey(id);
    return increament(sequence);
  }

  private synchronized boolean increament(BizSequence sequence) {
    if (sequence == null) {
      return false;
    } else {
      if (!sequence.getContinus()) {
        return false;
      }
      Integer currentVal = sequence.getNext();
      Integer minVal = sequence.getMin();
      Integer maxVal = sequence.getMax();
      minVal = (minVal == null ? 1 : minVal);
      maxVal = (maxVal == null ? Integer.MAX_VALUE : maxVal);
      currentVal = (currentVal == null ? minVal : currentVal);
      int newVal = currentVal + 1;
      if (newVal > maxVal) {
        newVal = minVal;
      }
      if (newVal < minVal) {
        newVal = minVal;
      }
      sequence.setNext(newVal);
      return sequenceMapper.updateByPrimaryKeySelective(sequence) > 0;
    }
  }

  @Override
  public boolean increament(String name) {
    BizSequence sequence = findSequenceByPrjName(name);
    return increament(sequence);
  }

  @Override
  public int next(Long id) {
    BizSequence sequence = sequenceMapper.selectByPrimaryKey(id);
    return next(sequence);
  }

  private synchronized int next(BizSequence sequence) {
    if (sequence == null)
      return 1;
    Integer currentVal = sequence.getNext();
    Integer minVal = sequence.getMin();
    Integer maxVal = sequence.getMax();
    minVal = (minVal == null ? 1 : minVal);
    maxVal = (maxVal == null ? Integer.MAX_VALUE : maxVal);
    currentVal = (currentVal == null ? minVal : currentVal);
    if (!sequence.getContinus()) {
      return currentVal;
    }
    int newVal = currentVal + 1;
    if (newVal > maxVal) {
      newVal = minVal;
    }
    if (newVal < minVal) {
      newVal = minVal;
    }
    return newVal;
  }

  @Override
  public int next(String name) {
    BizSequence sequence = findSequenceByPrjName(name);
    return next(sequence);
  }

  @Override
  public long countSeqCount(BizSequenceExample example) {
    return sequenceMapper.countByExample(example);
  }

  @Override
  public long countSeqCount(BizSequenceVwExample example) {
    return sequenceVwMapper.countByExample(example);
  }

  @Override
  public List<BizSequence> searchSeq(BizSequenceExample example, Page<BizSequence> page) {
    return sequenceMapper.selectByExampleWithRowbounds(example, page);
  }

  @Override
  public List<BizSequenceVw> searchSeq(BizSequenceVwExample example, Page<BizSequenceVw> page) {
    return sequenceVwMapper.selectByExampleWithRowbounds(example, page);
  }

  @Override
  public BizSequence findSequenceByPrimaryKey(Long seqId) {
    return sequenceMapper.selectByPrimaryKey(seqId);
  }

  @Override
  public BizSequenceVw findSequenceVwByPrimaryKey(Long seqId) {
    BizSequenceVwExample example = new BizSequenceVwExample();
    example.createCriteria().andIdEqualTo(seqId);
    return sequenceVwMapper.selectOneByExample(example);
  }

  @Override
  public boolean save(BizSequence record) {
    if (record.getId() == null) {
      return sequenceMapper.insertSelective(record) > 0;
    }
    return sequenceMapper.updateByPrimaryKeySelective(record) > 0;
  }

  @Override
  public boolean delete(Long id) {
    return sequenceMapper.deleteByPrimaryKey(id) > 0;
  }

}
