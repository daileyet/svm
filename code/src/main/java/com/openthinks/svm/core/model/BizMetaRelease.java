package com.openthinks.svm.core.model;

public class BizMetaRelease {
    private Long id;

    private String name;

    private Integer ordinal;

    private String xmlTag;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }

    public String getXmlTag() {
        return xmlTag;
    }

    public void setXmlTag(String xmlTag) {
        this.xmlTag = xmlTag == null ? null : xmlTag.trim();
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
        BizMetaRelease other = (BizMetaRelease) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getOrdinal() == null ? other.getOrdinal() == null : this.getOrdinal().equals(other.getOrdinal()))
            && (this.getXmlTag() == null ? other.getXmlTag() == null : this.getXmlTag().equals(other.getXmlTag()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getOrdinal() == null) ? 0 : getOrdinal().hashCode());
        result = prime * result + ((getXmlTag() == null) ? 0 : getXmlTag().hashCode());
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
        sb.append(", name=").append(name);
        sb.append(", ordinal=").append(ordinal);
        sb.append(", xmlTag=").append(xmlTag);
        sb.append(", description=").append(description);
        sb.append("]");
        return sb.toString();
    }

    public static class Builder {
        private BizMetaRelease obj;

        public Builder() {
            this.obj = new BizMetaRelease();
        }

        public Builder id(Long id) {
            obj.id = id;
            return this;
        }

        public Builder name(String name) {
            obj.name = name;
            return this;
        }

        public Builder ordinal(Integer ordinal) {
            obj.ordinal = ordinal;
            return this;
        }

        public Builder xmlTag(String xmlTag) {
            obj.xmlTag = xmlTag;
            return this;
        }

        public Builder description(String description) {
            obj.description = description;
            return this;
        }

        public BizMetaRelease build() {
            return this.obj;
        }
    }
}