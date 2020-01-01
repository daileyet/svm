package com.openthinks.svm.core.model;

public class BizMetaProjectProductLineVw {
    private Long metaPrjId;

    private String category;

    private String prjId;

    private String prjName;

    private Long metaClId;

    private String plId;

    private String plName;

    public Long getMetaPrjId() {
        return metaPrjId;
    }

    public void setMetaPrjId(Long metaPrjId) {
        this.metaPrjId = metaPrjId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getPrjId() {
        return prjId;
    }

    public void setPrjId(String prjId) {
        this.prjId = prjId == null ? null : prjId.trim();
    }

    public String getPrjName() {
        return prjName;
    }

    public void setPrjName(String prjName) {
        this.prjName = prjName == null ? null : prjName.trim();
    }

    public Long getMetaClId() {
        return metaClId;
    }

    public void setMetaClId(Long metaClId) {
        this.metaClId = metaClId;
    }

    public String getPlId() {
        return plId;
    }

    public void setPlId(String plId) {
        this.plId = plId == null ? null : plId.trim();
    }

    public String getPlName() {
        return plName;
    }

    public void setPlName(String plName) {
        this.plName = plName == null ? null : plName.trim();
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
        BizMetaProjectProductLineVw other = (BizMetaProjectProductLineVw) that;
        return (this.getMetaPrjId() == null ? other.getMetaPrjId() == null : this.getMetaPrjId().equals(other.getMetaPrjId()))
            && (this.getCategory() == null ? other.getCategory() == null : this.getCategory().equals(other.getCategory()))
            && (this.getPrjId() == null ? other.getPrjId() == null : this.getPrjId().equals(other.getPrjId()))
            && (this.getPrjName() == null ? other.getPrjName() == null : this.getPrjName().equals(other.getPrjName()))
            && (this.getMetaClId() == null ? other.getMetaClId() == null : this.getMetaClId().equals(other.getMetaClId()))
            && (this.getPlId() == null ? other.getPlId() == null : this.getPlId().equals(other.getPlId()))
            && (this.getPlName() == null ? other.getPlName() == null : this.getPlName().equals(other.getPlName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMetaPrjId() == null) ? 0 : getMetaPrjId().hashCode());
        result = prime * result + ((getCategory() == null) ? 0 : getCategory().hashCode());
        result = prime * result + ((getPrjId() == null) ? 0 : getPrjId().hashCode());
        result = prime * result + ((getPrjName() == null) ? 0 : getPrjName().hashCode());
        result = prime * result + ((getMetaClId() == null) ? 0 : getMetaClId().hashCode());
        result = prime * result + ((getPlId() == null) ? 0 : getPlId().hashCode());
        result = prime * result + ((getPlName() == null) ? 0 : getPlName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", metaPrjId=").append(metaPrjId);
        sb.append(", category=").append(category);
        sb.append(", prjId=").append(prjId);
        sb.append(", prjName=").append(prjName);
        sb.append(", metaClId=").append(metaClId);
        sb.append(", plId=").append(plId);
        sb.append(", plName=").append(plName);
        sb.append("]");
        return sb.toString();
    }

    public static class Builder {
        private BizMetaProjectProductLineVw obj;

        public Builder() {
            this.obj = new BizMetaProjectProductLineVw();
        }

        public Builder metaPrjId(Long metaPrjId) {
            obj.metaPrjId = metaPrjId;
            return this;
        }

        public Builder category(String category) {
            obj.category = category;
            return this;
        }

        public Builder prjId(String prjId) {
            obj.prjId = prjId;
            return this;
        }

        public Builder prjName(String prjName) {
            obj.prjName = prjName;
            return this;
        }

        public Builder metaClId(Long metaClId) {
            obj.metaClId = metaClId;
            return this;
        }

        public Builder plId(String plId) {
            obj.plId = plId;
            return this;
        }

        public Builder plName(String plName) {
            obj.plName = plName;
            return this;
        }

        public BizMetaProjectProductLineVw build() {
            return this.obj;
        }
    }
}