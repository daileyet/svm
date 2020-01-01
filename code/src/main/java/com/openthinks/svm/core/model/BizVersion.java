package com.openthinks.svm.core.model;

import java.util.Date;

public class BizVersion {
    private Long id;

    private Long projectId;

    private String number;

    private Byte valid;

    private Date createDate;

    private Long createBy;

    private Date updateDate;

    private Long updateBy;

    private Byte syncStatus;

    private Date lastSyncDate;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public Byte getValid() {
        return valid;
    }

    public void setValid(Byte valid) {
        this.valid = valid;
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

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Byte getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(Byte syncStatus) {
        this.syncStatus = syncStatus;
    }

    public Date getLastSyncDate() {
        return lastSyncDate;
    }

    public void setLastSyncDate(Date lastSyncDate) {
        this.lastSyncDate = lastSyncDate;
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
        BizVersion other = (BizVersion) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProjectId() == null ? other.getProjectId() == null : this.getProjectId().equals(other.getProjectId()))
            && (this.getNumber() == null ? other.getNumber() == null : this.getNumber().equals(other.getNumber()))
            && (this.getValid() == null ? other.getValid() == null : this.getValid().equals(other.getValid()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getSyncStatus() == null ? other.getSyncStatus() == null : this.getSyncStatus().equals(other.getSyncStatus()))
            && (this.getLastSyncDate() == null ? other.getLastSyncDate() == null : this.getLastSyncDate().equals(other.getLastSyncDate()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProjectId() == null) ? 0 : getProjectId().hashCode());
        result = prime * result + ((getNumber() == null) ? 0 : getNumber().hashCode());
        result = prime * result + ((getValid() == null) ? 0 : getValid().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getSyncStatus() == null) ? 0 : getSyncStatus().hashCode());
        result = prime * result + ((getLastSyncDate() == null) ? 0 : getLastSyncDate().hashCode());
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
        sb.append(", projectId=").append(projectId);
        sb.append(", number=").append(number);
        sb.append(", valid=").append(valid);
        sb.append(", createDate=").append(createDate);
        sb.append(", createBy=").append(createBy);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", syncStatus=").append(syncStatus);
        sb.append(", lastSyncDate=").append(lastSyncDate);
        sb.append(", description=").append(description);
        sb.append("]");
        return sb.toString();
    }

    public static class Builder {
        private BizVersion obj;

        public Builder() {
            this.obj = new BizVersion();
        }

        public Builder id(Long id) {
            obj.id = id;
            return this;
        }

        public Builder projectId(Long projectId) {
            obj.projectId = projectId;
            return this;
        }

        public Builder number(String number) {
            obj.number = number;
            return this;
        }

        public Builder valid(Byte valid) {
            obj.valid = valid;
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

        public Builder updateDate(Date updateDate) {
            obj.updateDate = updateDate;
            return this;
        }

        public Builder updateBy(Long updateBy) {
            obj.updateBy = updateBy;
            return this;
        }

        public Builder syncStatus(Byte syncStatus) {
            obj.syncStatus = syncStatus;
            return this;
        }

        public Builder lastSyncDate(Date lastSyncDate) {
            obj.lastSyncDate = lastSyncDate;
            return this;
        }

        public Builder description(String description) {
            obj.description = description;
            return this;
        }

        public BizVersion build() {
            return this.obj;
        }
    }
}