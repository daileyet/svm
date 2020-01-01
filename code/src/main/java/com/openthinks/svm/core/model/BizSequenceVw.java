package com.openthinks.svm.core.model;

public class BizSequenceVw {
    private Long id;

    private Long prjId;

    private Integer next;

    private Integer min;

    private Integer max;

    private Boolean continus;

    private String prjShortName;

    private String prjFullName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPrjId() {
        return prjId;
    }

    public void setPrjId(Long prjId) {
        this.prjId = prjId;
    }

    public Integer getNext() {
        return next;
    }

    public void setNext(Integer next) {
        this.next = next;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Boolean getContinus() {
        return continus;
    }

    public void setContinus(Boolean continus) {
        this.continus = continus;
    }

    public String getPrjShortName() {
        return prjShortName;
    }

    public void setPrjShortName(String prjShortName) {
        this.prjShortName = prjShortName == null ? null : prjShortName.trim();
    }

    public String getPrjFullName() {
        return prjFullName;
    }

    public void setPrjFullName(String prjFullName) {
        this.prjFullName = prjFullName == null ? null : prjFullName.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        BizSequenceVw other = (BizSequenceVw) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPrjId() == null ? other.getPrjId() == null : this.getPrjId().equals(other.getPrjId()))
            && (this.getNext() == null ? other.getNext() == null : this.getNext().equals(other.getNext()))
            && (this.getMin() == null ? other.getMin() == null : this.getMin().equals(other.getMin()))
            && (this.getMax() == null ? other.getMax() == null : this.getMax().equals(other.getMax()))
            && (this.getContinus() == null ? other.getContinus() == null : this.getContinus().equals(other.getContinus()))
            && (this.getPrjShortName() == null ? other.getPrjShortName() == null : this.getPrjShortName().equals(other.getPrjShortName()))
            && (this.getPrjFullName() == null ? other.getPrjFullName() == null : this.getPrjFullName().equals(other.getPrjFullName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPrjId() == null) ? 0 : getPrjId().hashCode());
        result = prime * result + ((getNext() == null) ? 0 : getNext().hashCode());
        result = prime * result + ((getMin() == null) ? 0 : getMin().hashCode());
        result = prime * result + ((getMax() == null) ? 0 : getMax().hashCode());
        result = prime * result + ((getContinus() == null) ? 0 : getContinus().hashCode());
        result = prime * result + ((getPrjShortName() == null) ? 0 : getPrjShortName().hashCode());
        result = prime * result + ((getPrjFullName() == null) ? 0 : getPrjFullName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", prjId=").append(prjId);
        sb.append(", next=").append(next);
        sb.append(", min=").append(min);
        sb.append(", max=").append(max);
        sb.append(", continus=").append(continus);
        sb.append(", prjShortName=").append(prjShortName);
        sb.append(", prjFullName=").append(prjFullName);
        sb.append("]");
        return sb.toString();
    }

    public static class Builder {
        private BizSequenceVw obj;

        public Builder() {
            this.obj = new BizSequenceVw();
        }

        public Builder id(Long id) {
            obj.id = id;
            return this;
        }

        public Builder prjId(Long prjId) {
            obj.prjId = prjId;
            return this;
        }

        public Builder next(Integer next) {
            obj.next = next;
            return this;
        }

        public Builder min(Integer min) {
            obj.min = min;
            return this;
        }

        public Builder max(Integer max) {
            obj.max = max;
            return this;
        }

        public Builder continus(Boolean continus) {
            obj.continus = continus;
            return this;
        }

        public Builder prjShortName(String prjShortName) {
            obj.prjShortName = prjShortName;
            return this;
        }

        public Builder prjFullName(String prjFullName) {
            obj.prjFullName = prjFullName;
            return this;
        }

        public BizSequenceVw build() {
            return this.obj;
        }
    }
}