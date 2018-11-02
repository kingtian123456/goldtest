package com.entity;

import java.util.Date;

public class Labs {
    private Integer labsId;

    private String labsNumber;

    private String labsUser;

    private Integer labsValidation;

    private Integer labsSend;

    private Date labsActtime;

    public Integer getLabsId() {
        return labsId;
    }

    public void setLabsId(Integer labsId) {
        this.labsId = labsId;
    }

    public String getLabsNumber() {
        return labsNumber;
    }

    public void setLabsNumber(String labsNumber) {
        this.labsNumber = labsNumber == null ? null : labsNumber.trim();
    }

    public String getLabsUser() {
        return labsUser;
    }

    public void setLabsUser(String labsUser) {
        this.labsUser = labsUser == null ? null : labsUser.trim();
    }

    public Integer getLabsValidation() {
        return labsValidation;
    }

    public void setLabsValidation(Integer labsValidation) {
        this.labsValidation = labsValidation;
    }

    public Integer getLabsSend() {
        return labsSend;
    }

    public void setLabsSend(Integer labsSend) {
        this.labsSend = labsSend;
    }

    public Date getLabsActtime() {
        return labsActtime;
    }

    public void setLabsActtime(Date labsActtime) {
        this.labsActtime = labsActtime;
    }
}