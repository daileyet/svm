package com.openthinks.svm.core.model;

public class BizMetaProductline {
    private Long id;

    private String category;

    private String prjId;

    private String plId;

    private String plName;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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
        BizMetaProductline other = (BizMetaProductline) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCategory() == null ? other.getCategory() == null : this.getCategory().equals(other.getCategory()))
            && (this.getPrjId() == null ? other.getPrjId() == null : this.getPrjId().equals(other.getPrjId()))
            && (this.getPlId() == null ? other.getPlId() == null : this.getPlId().equals(other.getPlId()))
            && (this.getPlName() == null ? other.getPlName() == null : this.getPlName().equals(other.getPlName()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCategory() == null) ? 0 : getCategory().hashCode());
        result = prime * result + ((getPrjId() == null) ? 0 : getPrjId().hashCode());
        result = prime * result + ((getPlId() == null) ? 0 : getPlId().hashCode());
        result = prime * result + ((getPlName() == null) ? 0 : getPlName().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", category=").append(category);
        sb.append(", prjId=").append(prjId);
        sb.append(", plId=").append(plId);
        sb.append(", plName=").append(plName);
        sb.append(", description=").append(description);
        sb.append("]");
        return sb.toString();
    }

    public static class Builder {
        private BizMetaProductline obj;

        public Builder() {
            this.obj = new BizMetaProductline();
        }

        public Builder id(Long id) {
            obj.id = id;
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

        public Builder plId(String plId) {
            obj.plId = plId;
            return this;
        }

        public Builder plName(String plName) {
            obj.plName = plName;
            return this;
        }

        public Builder description(String description) {
            obj.description = description;
            return this;
        }

        public BizMetaProductline build() {
            return this.obj;
        }
    }
}