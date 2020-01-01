package com.openthinks.svm.core.model;

import java.util.ArrayList;
import java.util.List;

public class BizSequenceVwExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BizSequenceVwExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andPrjIdIsNull() {
            addCriterion("prj_id is null");
            return (Criteria) this;
        }

        public Criteria andPrjIdIsNotNull() {
            addCriterion("prj_id is not null");
            return (Criteria) this;
        }

        public Criteria andPrjIdEqualTo(Long value) {
            addCriterion("prj_id =", value, "prjId");
            return (Criteria) this;
        }

        public Criteria andPrjIdNotEqualTo(Long value) {
            addCriterion("prj_id <>", value, "prjId");
            return (Criteria) this;
        }

        public Criteria andPrjIdGreaterThan(Long value) {
            addCriterion("prj_id >", value, "prjId");
            return (Criteria) this;
        }

        public Criteria andPrjIdGreaterThanOrEqualTo(Long value) {
            addCriterion("prj_id >=", value, "prjId");
            return (Criteria) this;
        }

        public Criteria andPrjIdLessThan(Long value) {
            addCriterion("prj_id <", value, "prjId");
            return (Criteria) this;
        }

        public Criteria andPrjIdLessThanOrEqualTo(Long value) {
            addCriterion("prj_id <=", value, "prjId");
            return (Criteria) this;
        }

        public Criteria andPrjIdIn(List<Long> values) {
            addCriterion("prj_id in", values, "prjId");
            return (Criteria) this;
        }

        public Criteria andPrjIdNotIn(List<Long> values) {
            addCriterion("prj_id not in", values, "prjId");
            return (Criteria) this;
        }

        public Criteria andPrjIdBetween(Long value1, Long value2) {
            addCriterion("prj_id between", value1, value2, "prjId");
            return (Criteria) this;
        }

        public Criteria andPrjIdNotBetween(Long value1, Long value2) {
            addCriterion("prj_id not between", value1, value2, "prjId");
            return (Criteria) this;
        }

        public Criteria andNextIsNull() {
            addCriterion("next is null");
            return (Criteria) this;
        }

        public Criteria andNextIsNotNull() {
            addCriterion("next is not null");
            return (Criteria) this;
        }

        public Criteria andNextEqualTo(Integer value) {
            addCriterion("next =", value, "next");
            return (Criteria) this;
        }

        public Criteria andNextNotEqualTo(Integer value) {
            addCriterion("next <>", value, "next");
            return (Criteria) this;
        }

        public Criteria andNextGreaterThan(Integer value) {
            addCriterion("next >", value, "next");
            return (Criteria) this;
        }

        public Criteria andNextGreaterThanOrEqualTo(Integer value) {
            addCriterion("next >=", value, "next");
            return (Criteria) this;
        }

        public Criteria andNextLessThan(Integer value) {
            addCriterion("next <", value, "next");
            return (Criteria) this;
        }

        public Criteria andNextLessThanOrEqualTo(Integer value) {
            addCriterion("next <=", value, "next");
            return (Criteria) this;
        }

        public Criteria andNextIn(List<Integer> values) {
            addCriterion("next in", values, "next");
            return (Criteria) this;
        }

        public Criteria andNextNotIn(List<Integer> values) {
            addCriterion("next not in", values, "next");
            return (Criteria) this;
        }

        public Criteria andNextBetween(Integer value1, Integer value2) {
            addCriterion("next between", value1, value2, "next");
            return (Criteria) this;
        }

        public Criteria andNextNotBetween(Integer value1, Integer value2) {
            addCriterion("next not between", value1, value2, "next");
            return (Criteria) this;
        }

        public Criteria andMinIsNull() {
            addCriterion("min is null");
            return (Criteria) this;
        }

        public Criteria andMinIsNotNull() {
            addCriterion("min is not null");
            return (Criteria) this;
        }

        public Criteria andMinEqualTo(Integer value) {
            addCriterion("min =", value, "min");
            return (Criteria) this;
        }

        public Criteria andMinNotEqualTo(Integer value) {
            addCriterion("min <>", value, "min");
            return (Criteria) this;
        }

        public Criteria andMinGreaterThan(Integer value) {
            addCriterion("min >", value, "min");
            return (Criteria) this;
        }

        public Criteria andMinGreaterThanOrEqualTo(Integer value) {
            addCriterion("min >=", value, "min");
            return (Criteria) this;
        }

        public Criteria andMinLessThan(Integer value) {
            addCriterion("min <", value, "min");
            return (Criteria) this;
        }

        public Criteria andMinLessThanOrEqualTo(Integer value) {
            addCriterion("min <=", value, "min");
            return (Criteria) this;
        }

        public Criteria andMinIn(List<Integer> values) {
            addCriterion("min in", values, "min");
            return (Criteria) this;
        }

        public Criteria andMinNotIn(List<Integer> values) {
            addCriterion("min not in", values, "min");
            return (Criteria) this;
        }

        public Criteria andMinBetween(Integer value1, Integer value2) {
            addCriterion("min between", value1, value2, "min");
            return (Criteria) this;
        }

        public Criteria andMinNotBetween(Integer value1, Integer value2) {
            addCriterion("min not between", value1, value2, "min");
            return (Criteria) this;
        }

        public Criteria andMaxIsNull() {
            addCriterion("max is null");
            return (Criteria) this;
        }

        public Criteria andMaxIsNotNull() {
            addCriterion("max is not null");
            return (Criteria) this;
        }

        public Criteria andMaxEqualTo(Integer value) {
            addCriterion("max =", value, "max");
            return (Criteria) this;
        }

        public Criteria andMaxNotEqualTo(Integer value) {
            addCriterion("max <>", value, "max");
            return (Criteria) this;
        }

        public Criteria andMaxGreaterThan(Integer value) {
            addCriterion("max >", value, "max");
            return (Criteria) this;
        }

        public Criteria andMaxGreaterThanOrEqualTo(Integer value) {
            addCriterion("max >=", value, "max");
            return (Criteria) this;
        }

        public Criteria andMaxLessThan(Integer value) {
            addCriterion("max <", value, "max");
            return (Criteria) this;
        }

        public Criteria andMaxLessThanOrEqualTo(Integer value) {
            addCriterion("max <=", value, "max");
            return (Criteria) this;
        }

        public Criteria andMaxIn(List<Integer> values) {
            addCriterion("max in", values, "max");
            return (Criteria) this;
        }

        public Criteria andMaxNotIn(List<Integer> values) {
            addCriterion("max not in", values, "max");
            return (Criteria) this;
        }

        public Criteria andMaxBetween(Integer value1, Integer value2) {
            addCriterion("max between", value1, value2, "max");
            return (Criteria) this;
        }

        public Criteria andMaxNotBetween(Integer value1, Integer value2) {
            addCriterion("max not between", value1, value2, "max");
            return (Criteria) this;
        }

        public Criteria andContinusIsNull() {
            addCriterion("continus is null");
            return (Criteria) this;
        }

        public Criteria andContinusIsNotNull() {
            addCriterion("continus is not null");
            return (Criteria) this;
        }

        public Criteria andContinusEqualTo(Boolean value) {
            addCriterion("continus =", value, "continus");
            return (Criteria) this;
        }

        public Criteria andContinusNotEqualTo(Boolean value) {
            addCriterion("continus <>", value, "continus");
            return (Criteria) this;
        }

        public Criteria andContinusGreaterThan(Boolean value) {
            addCriterion("continus >", value, "continus");
            return (Criteria) this;
        }

        public Criteria andContinusGreaterThanOrEqualTo(Boolean value) {
            addCriterion("continus >=", value, "continus");
            return (Criteria) this;
        }

        public Criteria andContinusLessThan(Boolean value) {
            addCriterion("continus <", value, "continus");
            return (Criteria) this;
        }

        public Criteria andContinusLessThanOrEqualTo(Boolean value) {
            addCriterion("continus <=", value, "continus");
            return (Criteria) this;
        }

        public Criteria andContinusIn(List<Boolean> values) {
            addCriterion("continus in", values, "continus");
            return (Criteria) this;
        }

        public Criteria andContinusNotIn(List<Boolean> values) {
            addCriterion("continus not in", values, "continus");
            return (Criteria) this;
        }

        public Criteria andContinusBetween(Boolean value1, Boolean value2) {
            addCriterion("continus between", value1, value2, "continus");
            return (Criteria) this;
        }

        public Criteria andContinusNotBetween(Boolean value1, Boolean value2) {
            addCriterion("continus not between", value1, value2, "continus");
            return (Criteria) this;
        }

        public Criteria andPrjShortNameIsNull() {
            addCriterion("prj_short_name is null");
            return (Criteria) this;
        }

        public Criteria andPrjShortNameIsNotNull() {
            addCriterion("prj_short_name is not null");
            return (Criteria) this;
        }

        public Criteria andPrjShortNameEqualTo(String value) {
            addCriterion("prj_short_name =", value, "prjShortName");
            return (Criteria) this;
        }

        public Criteria andPrjShortNameNotEqualTo(String value) {
            addCriterion("prj_short_name <>", value, "prjShortName");
            return (Criteria) this;
        }

        public Criteria andPrjShortNameGreaterThan(String value) {
            addCriterion("prj_short_name >", value, "prjShortName");
            return (Criteria) this;
        }

        public Criteria andPrjShortNameGreaterThanOrEqualTo(String value) {
            addCriterion("prj_short_name >=", value, "prjShortName");
            return (Criteria) this;
        }

        public Criteria andPrjShortNameLessThan(String value) {
            addCriterion("prj_short_name <", value, "prjShortName");
            return (Criteria) this;
        }

        public Criteria andPrjShortNameLessThanOrEqualTo(String value) {
            addCriterion("prj_short_name <=", value, "prjShortName");
            return (Criteria) this;
        }

        public Criteria andPrjShortNameLike(String value) {
            addCriterion("prj_short_name like", value, "prjShortName");
            return (Criteria) this;
        }

        public Criteria andPrjShortNameNotLike(String value) {
            addCriterion("prj_short_name not like", value, "prjShortName");
            return (Criteria) this;
        }

        public Criteria andPrjShortNameIn(List<String> values) {
            addCriterion("prj_short_name in", values, "prjShortName");
            return (Criteria) this;
        }

        public Criteria andPrjShortNameNotIn(List<String> values) {
            addCriterion("prj_short_name not in", values, "prjShortName");
            return (Criteria) this;
        }

        public Criteria andPrjShortNameBetween(String value1, String value2) {
            addCriterion("prj_short_name between", value1, value2, "prjShortName");
            return (Criteria) this;
        }

        public Criteria andPrjShortNameNotBetween(String value1, String value2) {
            addCriterion("prj_short_name not between", value1, value2, "prjShortName");
            return (Criteria) this;
        }

        public Criteria andPrjFullNameIsNull() {
            addCriterion("prj_full_name is null");
            return (Criteria) this;
        }

        public Criteria andPrjFullNameIsNotNull() {
            addCriterion("prj_full_name is not null");
            return (Criteria) this;
        }

        public Criteria andPrjFullNameEqualTo(String value) {
            addCriterion("prj_full_name =", value, "prjFullName");
            return (Criteria) this;
        }

        public Criteria andPrjFullNameNotEqualTo(String value) {
            addCriterion("prj_full_name <>", value, "prjFullName");
            return (Criteria) this;
        }

        public Criteria andPrjFullNameGreaterThan(String value) {
            addCriterion("prj_full_name >", value, "prjFullName");
            return (Criteria) this;
        }

        public Criteria andPrjFullNameGreaterThanOrEqualTo(String value) {
            addCriterion("prj_full_name >=", value, "prjFullName");
            return (Criteria) this;
        }

        public Criteria andPrjFullNameLessThan(String value) {
            addCriterion("prj_full_name <", value, "prjFullName");
            return (Criteria) this;
        }

        public Criteria andPrjFullNameLessThanOrEqualTo(String value) {
            addCriterion("prj_full_name <=", value, "prjFullName");
            return (Criteria) this;
        }

        public Criteria andPrjFullNameLike(String value) {
            addCriterion("prj_full_name like", value, "prjFullName");
            return (Criteria) this;
        }

        public Criteria andPrjFullNameNotLike(String value) {
            addCriterion("prj_full_name not like", value, "prjFullName");
            return (Criteria) this;
        }

        public Criteria andPrjFullNameIn(List<String> values) {
            addCriterion("prj_full_name in", values, "prjFullName");
            return (Criteria) this;
        }

        public Criteria andPrjFullNameNotIn(List<String> values) {
            addCriterion("prj_full_name not in", values, "prjFullName");
            return (Criteria) this;
        }

        public Criteria andPrjFullNameBetween(String value1, String value2) {
            addCriterion("prj_full_name between", value1, value2, "prjFullName");
            return (Criteria) this;
        }

        public Criteria andPrjFullNameNotBetween(String value1, String value2) {
            addCriterion("prj_full_name not between", value1, value2, "prjFullName");
            return (Criteria) this;
        }

        public Criteria andGenericSubquery(String subQueryClause) {
            addCriterion(subQueryClause);
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}