package com.openthinks.svm.core.model;

public class BizMetaAttr {
    private Long id;

    private String category;

    private String attrName;

    private String attrDisplay;

    private String attrDesc;

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

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName == null ? null : attrName.trim();
    }

    public String getAttrDisplay() {
        return attrDisplay;
    }

    public void setAttrDisplay(String attrDisplay) {
        this.attrDisplay = attrDisplay == null ? null : attrDisplay.trim();
    }

    public String getAttrDesc() {
        return attrDesc;
    }

    public void setAttrDesc(String attrDesc) {
        this.attrDesc = attrDesc == null ? null : attrDesc.trim();
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
        BizMetaAttr other = (BizMetaAttr) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCategory() == null ? other.getCategory() == null : this.getCategory().equals(other.getCategory()))
            && (this.getAttrName() == null ? other.getAttrName() == null : this.getAttrName().equals(other.getAttrName()))
            && (this.getAttrDisplay() == null ? other.getAttrDisplay() == null : this.getAttrDisplay().equals(other.getAttrDisplay()))
            && (this.getAttrDesc() == null ? other.getAttrDesc() == null : this.getAttrDesc().equals(other.getAttrDesc()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCategory() == null) ? 0 : getCategory().hashCode());
        result = prime * result + ((getAttrName() == null) ? 0 : getAttrName().hashCode());
        result = prime * result + ((getAttrDisplay() == null) ? 0 : getAttrDisplay().hashCode());
        result = prime * result + ((getAttrDesc() == null) ? 0 : getAttrDesc().hashCode());
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
        sb.append(", attrName=").append(attrName);
        sb.append(", attrDisplay=").append(attrDisplay);
        sb.append(", attrDesc=").append(attrDesc);
        sb.append("]");
        return sb.toString();
    }

    public static class Builder {
        private BizMetaAttr obj;

        public Builder() {
            this.obj = new BizMetaAttr();
        }

        public Builder id(Long id) {
            obj.id = id;
            return this;
        }

        public Builder category(String category) {
            obj.category = category;
            return this;
        }

        public Builder attrName(String attrName) {
            obj.attrName = attrName;
            return this;
        }

        public Builder attrDisplay(String attrDisplay) {
            obj.attrDisplay = attrDisplay;
            return this;
        }

        public Builder attrDesc(String attrDesc) {
            obj.attrDesc = attrDesc;
            return this;
        }

        public BizMetaAttr build() {
            return this.obj;
        }
    }
}