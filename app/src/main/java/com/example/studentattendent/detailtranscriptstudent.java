package com.example.studentattendent;

public class detailtranscriptstudent {
    String tranid,tranidsub,transubname,trangrade;

    public detailtranscriptstudent() {
    }

    public detailtranscriptstudent(String tranid, String tranidsub, String transubname, String trangrade) {
        this.tranid = tranid;
        this.tranidsub = tranidsub;
        this.transubname = transubname;
        this.trangrade = trangrade;
    }

    public String getTranid() {
        return tranid;
    }

    public void setTranid(String tranid) {
        this.tranid = tranid;
    }

    public String getTranidsub() {
        return tranidsub;
    }

    public void setTranidsub(String tranidsub) {
        this.tranidsub = tranidsub;
    }

    public String getTransubname() {
        return transubname;
    }

    public void setTransubname(String transubname) {
        this.transubname = transubname;
    }

    public String getTrangrade() {
        return trangrade;
    }

    public void setTrangrade(String trangrade) {
        this.trangrade = trangrade;
    }
}
