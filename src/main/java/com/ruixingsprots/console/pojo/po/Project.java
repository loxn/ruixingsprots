package com.ruixingsprots.console.pojo.po;

import java.util.Date;

public class Project {
    private Integer id;

    private String name;

    private Double passUpperLimit;

    private Double passLoweLimit;

    private String qualifiedInterval;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Double getPassUpperLimit() {
        return passUpperLimit;
    }

    public void setPassUpperLimit(Double passUpperLimit) {
        this.passUpperLimit = passUpperLimit;
    }

    public Double getPassLoweLimit() {
        return passLoweLimit;
    }

    public void setPassLoweLimit(Double passLoweLimit) {
        this.passLoweLimit = passLoweLimit;
    }

    public String getQualifiedInterval() {
        return qualifiedInterval;
    }

    public void setQualifiedInterval(String qualifiedInterval) {
        this.qualifiedInterval = qualifiedInterval == null ? null : qualifiedInterval.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}