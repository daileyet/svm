package com.openthinks.svm.core.model;

public class BizReleaseAttr {
    private Long id;

    private Long releaseId;

    private String itemName;

    private String itemValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReleaseId() {
        return releaseId;
    }

    public void setReleaseId(Long releaseId) {
        this.releaseId = releaseId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue == null ? null : itemValue.trim();
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
        BizReleaseAttr other = (BizReleaseAttr) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getReleaseId() == null ? other.getReleaseId() == null : this.getReleaseId().equals(other.getReleaseId()))
            && (this.getItemName() == null ? other.getItemName() == null : this.getItemName().equals(other.getItemName()))
            && (this.getItemValue() == null ? other.getItemValue() == null : this.getItemValue().equals(other.getItemValue()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getReleaseId() == null) ? 0 : getReleaseId().hashCode());
        result = prime * result + ((getItemName() == null) ? 0 : getItemName().hashCode());
        result = prime * result + ((getItemValue() == null) ? 0 : getItemValue().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", releaseId=").append(releaseId);
        sb.append(", itemName=").append(itemName);
        sb.append(", itemValue=").append(itemValue);
        sb.append("]");
        return sb.toString();
    }

    public static class Builder {
        private BizReleaseAttr obj;

        public Builder() {
            this.obj = new BizReleaseAttr();
        }

        public Builder id(Long id) {
            obj.id = id;
            return this;
        }

        public Builder releaseId(Long releaseId) {
            obj.releaseId = releaseId;
            return this;
        }

        public Builder itemName(String itemName) {
            obj.itemName = itemName;
            return this;
        }

        public Builder itemValue(String itemValue) {
            obj.itemValue = itemValue;
            return this;
        }

        public BizReleaseAttr build() {
            return this.obj;
        }
    }
}