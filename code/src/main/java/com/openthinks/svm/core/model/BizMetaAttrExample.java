package com.openthinks.svm.core.model;

import java.util.ArrayList;
import java.util.List;

public class BizMetaAttrExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BizMetaAttrExample() {
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

        public Criteria andCategoryIsNull() {
            addCriterion("category is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNotNull() {
            addCriterion("category is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryEqualTo(String value) {
            addCriterion("category =", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotEqualTo(String value) {
            addCriterion("category <>", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThan(String value) {
            addCriterion("category >", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("category >=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThan(String value) {
            addCriterion("category <", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThanOrEqualTo(String value) {
            addCriterion("category <=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLike(String value) {
            addCriterion("category like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotLike(String value) {
            addCriterion("category not like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryIn(List<String> values) {
            addCriterion("category in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotIn(List<String> values) {
            addCriterion("category not in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryBetween(String value1, String value2) {
            addCriterion("category between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotBetween(String value1, String value2) {
            addCriterion("category not between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andAttrNameIsNull() {
            addCriterion("attr_name is null");
            return (Criteria) this;
        }

        public Criteria andAttrNameIsNotNull() {
            addCriterion("attr_name is not null");
            return (Criteria) this;
        }

        public Criteria andAttrNameEqualTo(String value) {
            addCriterion("attr_name =", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameNotEqualTo(String value) {
            addCriterion("attr_name <>", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameGreaterThan(String value) {
            addCriterion("attr_name >", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameGreaterThanOrEqualTo(String value) {
            addCriterion("attr_name >=", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameLessThan(String value) {
            addCriterion("attr_name <", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameLessThanOrEqualTo(String value) {
            addCriterion("attr_name <=", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameLike(String value) {
            addCriterion("attr_name like", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameNotLike(String value) {
            addCriterion("attr_name not like", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameIn(List<String> values) {
            addCriterion("attr_name in", values, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameNotIn(List<String> values) {
            addCriterion("attr_name not in", values, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameBetween(String value1, String value2) {
            addCriterion("attr_name between", value1, value2, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameNotBetween(String value1, String value2) {
            addCriterion("attr_name not between", value1, value2, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrDisplayIsNull() {
            addCriterion("attr_display is null");
            return (Criteria) this;
        }

        public Criteria andAttrDisplayIsNotNull() {
            addCriterion("attr_display is not null");
            return (Criteria) this;
        }

        public Criteria andAttrDisplayEqualTo(String value) {
            addCriterion("attr_display =", value, "attrDisplay");
            return (Criteria) this;
        }

        public Criteria andAttrDisplayNotEqualTo(String value) {
            addCriterion("attr_display <>", value, "attrDisplay");
            return (Criteria) this;
        }

        public Criteria andAttrDisplayGreaterThan(String value) {
            addCriterion("attr_display >", value, "attrDisplay");
            return (Criteria) this;
        }

        public Criteria andAttrDisplayGreaterThanOrEqualTo(String value) {
            addCriterion("attr_display >=", value, "attrDisplay");
            return (Criteria) this;
        }

        public Criteria andAttrDisplayLessThan(String value) {
            addCriterion("attr_display <", value, "attrDisplay");
            return (Criteria) this;
        }

        public Criteria andAttrDisplayLessThanOrEqualTo(String value) {
            addCriterion("attr_display <=", value, "attrDisplay");
            return (Criteria) this;
        }

        public Criteria andAttrDisplayLike(String value) {
            addCriterion("attr_display like", value, "attrDisplay");
            return (Criteria) this;
        }

        public Criteria andAttrDisplayNotLike(String value) {
            addCriterion("attr_display not like", value, "attrDisplay");
            return (Criteria) this;
        }

        public Criteria andAttrDisplayIn(List<String> values) {
            addCriterion("attr_display in", values, "attrDisplay");
            return (Criteria) this;
        }

        public Criteria andAttrDisplayNotIn(List<String> values) {
            addCriterion("attr_display not in", values, "attrDisplay");
            return (Criteria) this;
        }

        public Criteria andAttrDisplayBetween(String value1, String value2) {
            addCriterion("attr_display between", value1, value2, "attrDisplay");
            return (Criteria) this;
        }

        public Criteria andAttrDisplayNotBetween(String value1, String value2) {
            addCriterion("attr_display not between", value1, value2, "attrDisplay");
            return (Criteria) this;
        }

        public Criteria andAttrDescIsNull() {
            addCriterion("attr_desc is null");
            return (Criteria) this;
        }

        public Criteria andAttrDescIsNotNull() {
            addCriterion("attr_desc is not null");
            return (Criteria) this;
        }

        public Criteria andAttrDescEqualTo(String value) {
            addCriterion("attr_desc =", value, "attrDesc");
            return (Criteria) this;
        }

        public Criteria andAttrDescNotEqualTo(String value) {
            addCriterion("attr_desc <>", value, "attrDesc");
            return (Criteria) this;
        }

        public Criteria andAttrDescGreaterThan(String value) {
            addCriterion("attr_desc >", value, "attrDesc");
            return (Criteria) this;
        }

        public Criteria andAttrDescGreaterThanOrEqualTo(String value) {
            addCriterion("attr_desc >=", value, "attrDesc");
            return (Criteria) this;
        }

        public Criteria andAttrDescLessThan(String value) {
            addCriterion("attr_desc <", value, "attrDesc");
            return (Criteria) this;
        }

        public Criteria andAttrDescLessThanOrEqualTo(String value) {
            addCriterion("attr_desc <=", value, "attrDesc");
            return (Criteria) this;
        }

        public Criteria andAttrDescLike(String value) {
            addCriterion("attr_desc like", value, "attrDesc");
            return (Criteria) this;
        }

        public Criteria andAttrDescNotLike(String value) {
            addCriterion("attr_desc not like", value, "attrDesc");
            return (Criteria) this;
        }

        public Criteria andAttrDescIn(List<String> values) {
            addCriterion("attr_desc in", values, "attrDesc");
            return (Criteria) this;
        }

        public Criteria andAttrDescNotIn(List<String> values) {
            addCriterion("attr_desc not in", values, "attrDesc");
            return (Criteria) this;
        }

        public Criteria andAttrDescBetween(String value1, String value2) {
            addCriterion("attr_desc between", value1, value2, "attrDesc");
            return (Criteria) this;
        }

        public Criteria andAttrDescNotBetween(String value1, String value2) {
            addCriterion("attr_desc not between", value1, value2, "attrDesc");
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