package com.openthinks.svm.core.model;

public class BizPrjMetaRelShip {
    private Long id;

    private Long projectId;

    private Long metaReleaseId;

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

    public Long getMetaReleaseId() {
        return metaReleaseId;
    }

    public void setMetaReleaseId(Long metaReleaseId) {
        this.metaReleaseId = metaReleaseId;
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
        BizPrjMetaRelShip other = (BizPrjMetaRelShip) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProjectId() == null ? other.getProjectId() == null : this.getProjectId().equals(other.getProjectId()))
            && (this.getMetaReleaseId() == null ? other.getMetaReleaseId() == null : this.getMetaReleaseId().equals(other.getMetaReleaseId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProjectId() == null) ? 0 : getProjectId().hashCode());
        result = prime * result + ((getMetaReleaseId() == null) ? 0 : getMetaReleaseId().hashCode());
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
        sb.append(", metaReleaseId=").append(metaReleaseId);
        sb.append("]");
        return sb.toString();
    }

    public static class Builder {
        private BizPrjMetaRelShip obj;

        public Builder() {
            this.obj = new BizPrjMetaRelShip();
        }

        public Builder id(Long id) {
            obj.id = id;
            return this;
        }

        public Builder projectId(Long projectId) {
            obj.projectId = projectId;
            return this;
        }

        public Builder metaReleaseId(Long metaReleaseId) {
            obj.metaReleaseId = metaReleaseId;
            return this;
        }

        public BizPrjMetaRelShip build() {
            return this.obj;
        }
    }
}