package com.openthinks.svm.core.model;

import java.util.Date;

public class BizProject {
    private Long id;

    private String shortName;

    private String fullName;

    private String decription;

    private Date createDate;

    private Long createBy;

    private String metaPrjId;

    private Boolean needSync;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName == null ? null : shortName.trim();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName == null ? null : fullName.trim();
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription == null ? null : decription.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public String getMetaPrjId() {
        return metaPrjId;
    }

    public void setMetaPrjId(String metaPrjId) {
        this.metaPrjId = metaPrjId == null ? null : metaPrjId.trim();
    }

    public Boolean getNeedSync() {
        return needSync;
    }

    public void setNeedSync(Boolean needSync) {
        this.needSync = needSync;
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
        BizProject other = (BizProject) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getShortName() == null ? other.getShortName() == null : this.getShortName().equals(other.getShortName()))
            && (this.getFullName() == null ? other.getFullName() == null : this.getFullName().equals(other.getFullName()))
            && (this.getDecription() == null ? other.getDecription() == null : this.getDecription().equals(other.getDecription()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getMetaPrjId() == null ? other.getMetaPrjId() == null : this.getMetaPrjId().equals(other.getMetaPrjId()))
            && (this.getNeedSync() == null ? other.getNeedSync() == null : this.getNeedSync().equals(other.getNeedSync()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getShortName() == null) ? 0 : getShortName().hashCode());
        result = prime * result + ((getFullName() == null) ? 0 : getFullName().hashCode());
        result = prime * result + ((getDecription() == null) ? 0 : getDecription().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getMetaPrjId() == null) ? 0 : getMetaPrjId().hashCode());
        result = prime * result + ((getNeedSync() == null) ? 0 : getNeedSync().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", shortName=").append(shortName);
        sb.append(", fullName=").append(fullName);
        sb.append(", decription=").append(decription);
        sb.append(", createDate=").append(createDate);
        sb.append(", createBy=").append(createBy);
        sb.append(", metaPrjId=").append(metaPrjId);
        sb.append(", needSync=").append(needSync);
        sb.append("]");
        return sb.toString();
    }

    public static class Builder {
        private BizProject obj;

        public Builder() {
            this.obj = new BizProject();
        }

        public Builder id(Long id) {
            obj.id = id;
            return this;
        }

        public Builder shortName(String shortName) {
            obj.shortName = shortName;
            return this;
        }

        public Builder fullName(String fullName) {
            obj.fullName = fullName;
            return this;
        }

        public Builder decription(String decription) {
            obj.decription = decription;
            return this;
        }

        public Builder createDate(Date createDate) {
            obj.createDate = createDate;
            return this;
        }

        public Builder createBy(Long createBy) {
            obj.createBy = createBy;
            return this;
        }

        public Builder metaPrjId(String metaPrjId) {
            obj.metaPrjId = metaPrjId;
            return this;
        }

        public Builder needSync(Boolean needSync) {
            obj.needSync = needSync;
            return this;
        }

        public BizProject build() {
            return this.obj;
        }
    }
}