package com.openthinks.svm.core.model;

import java.util.Date;

public class BizReleaseInfo {
    private Long id;

    private String releaseNum;

    private String type;

    private Date createDate;

    private Long creatBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReleaseNum() {
        return releaseNum;
    }

    public void setReleaseNum(String releaseNum) {
        this.releaseNum = releaseNum == null ? null : releaseNum.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getCreatBy() {
        return creatBy;
    }

    public void setCreatBy(Long creatBy) {
        this.creatBy = creatBy;
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
        BizReleaseInfo other = (BizReleaseInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getReleaseNum() == null ? other.getReleaseNum() == null : this.getReleaseNum().equals(other.getReleaseNum()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getCreatBy() == null ? other.getCreatBy() == null : this.getCreatBy().equals(other.getCreatBy()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getReleaseNum() == null) ? 0 : getReleaseNum().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getCreatBy() == null) ? 0 : getCreatBy().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", releaseNum=").append(releaseNum);
        sb.append(", type=").append(type);
        sb.append(", createDate=").append(createDate);
        sb.append(", creatBy=").append(creatBy);
        sb.append("]");
        return sb.toString();
    }

    public static class Builder {
        private BizReleaseInfo obj;

        public Builder() {
            this.obj = new BizReleaseInfo();
        }

        public Builder id(Long id) {
            obj.id = id;
            return this;
        }

        public Builder releaseNum(String releaseNum) {
            obj.releaseNum = releaseNum;
            return this;
        }

        public Builder type(String type) {
            obj.type = type;
            return this;
        }

        public Builder createDate(Date createDate) {
            obj.createDate = createDate;
            return this;
        }

        public Builder creatBy(Long creatBy) {
            obj.creatBy = creatBy;
            return this;
        }

        public BizReleaseInfo build() {
            return this.obj;
        }
    }
}