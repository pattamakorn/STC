package com.example.studentattendent;

public class SSTDTran {
    String STeidstd,STeimagestd,STenamestd,STeclass;

    public SSTDTran() {
    }

    public SSTDTran(String STeidstd, String STeimagestd, String STenamestd, String STeclass) {
        this.STeidstd = STeidstd;
        this.STeimagestd = STeimagestd;
        this.STenamestd = STenamestd;
        this.STeclass = STeclass;
    }

    public String getSTeidstd() {
        return STeidstd;
    }

    public void setSTeidstd(String STeidstd) {
        this.STeidstd = STeidstd;
    }

    public String getSTeimagestd() {
        return STeimagestd;
    }

    public void setSTeimagestd(String STeimagestd) {
        this.STeimagestd = STeimagestd;
    }

    public String getSTenamestd() {
        return STenamestd;
    }

    public void setSTenamestd(String STenamestd) {
        this.STenamestd = STenamestd;
    }

    public String getSTeclass() {
        return STeclass;
    }

    public void setSTeclass(String STeclass) {
        this.STeclass = STeclass;
    }
}
