package com.openthinks.svm.core.model;

public class BizVersionReleaseShip {
    private Long id;

    private Long vId;

    private Long rId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getvId() {
        return vId;
    }

    public void setvId(Long vId) {
        this.vId = vId;
    }

    public Long getrId() {
        return rId;
    }

    public void setrId(Long rId) {
        this.rId = rId;
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
        BizVersionReleaseShip other = (BizVersionReleaseShip) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getvId() == null ? other.getvId() == null : this.getvId().equals(other.getvId()))
            && (this.getrId() == null ? other.getrId() == null : this.getrId().equals(other.getrId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getvId() == null) ? 0 : getvId().hashCode());
        result = prime * result + ((getrId() == null) ? 0 : getrId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", vId=").append(vId);
        sb.append(", rId=").append(rId);
        sb.append("]");
        return sb.toString();
    }

    public static class Builder {
        private BizVersionReleaseShip obj;

        public Builder() {
            this.obj = new BizVersionReleaseShip();
        }

        public Builder id(Long id) {
            obj.id = id;
            return this;
        }

        public Builder vId(Long vId) {
            obj.vId = vId;
            return this;
        }

        public Builder rId(Long rId) {
            obj.rId = rId;
            return this;
        }

        public BizVersionReleaseShip build() {
            return this.obj;
        }
    }
}