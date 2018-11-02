package com.entity;

import java.util.Date;

public class Place {
	
    private Integer plid;

    private String plcode;

    private String plvalue;

    private Date plintime;

    public Integer getPlid() {
        return plid;
    }

    public void setPlid(Integer plid) {
        this.plid = plid;
    }

    public String getPlcode() {
        return plcode;
    }

    public void setPlcode(String plcode) {
        this.plcode = plcode == null ? null : plcode.trim();
    }

    public String getPlvalue() {
        return plvalue;
    }

    public void setPlvalue(String plvalue) {
        this.plvalue = plvalue == null ? null : plvalue.trim();
    }

    public Date getPlintime() {
        return plintime;
    }

    public void setPlintime(Date plintime) {
        this.plintime = plintime;
    }
}