package com.openthinks.svm.core.model;

public class BizMetaProject {
    private Long id;

    private String category;

    private String prjId;

    private String prjName;

    private String description;

    private Byte active;

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

    public String getPrjName() {
        return prjName;
    }

    public void setPrjName(String prjName) {
        this.prjName = prjName == null ? null : prjName.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Byte getActive() {
        return active;
    }

    public void setActive(Byte active) {
        this.active = active;
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
        BizMetaProject other = (BizMetaProject) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCategory() == null ? other.getCategory() == null : this.getCategory().equals(other.getCategory()))
            && (this.getPrjId() == null ? other.getPrjId() == null : this.getPrjId().equals(other.getPrjId()))
            && (this.getPrjName() == null ? other.getPrjName() == null : this.getPrjName().equals(other.getPrjName()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getActive() == null ? other.getActive() == null : this.getActive().equals(other.getActive()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCategory() == null) ? 0 : getCategory().hashCode());
        result = prime * result + ((getPrjId() == null) ? 0 : getPrjId().hashCode());
        result = prime * result + ((getPrjName() == null) ? 0 : getPrjName().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getActive() == null) ? 0 : getActive().hashCode());
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
        sb.append(", prjName=").append(prjName);
        sb.append(", description=").append(description);
        sb.append(", active=").append(active);
        sb.append("]");
        return sb.toString();
    }

    public static class Builder {
        private BizMetaProject obj;

        public Builder() {
            this.obj = new BizMetaProject();
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

        public Builder prjName(String prjName) {
            obj.prjName = prjName;
            return this;
        }

        public Builder description(String description) {
            obj.description = description;
            return this;
        }

        public Builder active(Byte active) {
            obj.active = active;
            return this;
        }

        public BizMetaProject build() {
            return this.obj;
        }
    }
}