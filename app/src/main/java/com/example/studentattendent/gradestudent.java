package com.example.studentattendent;

public class gradestudent {
    String gradet,termm,classs;

    public gradestudent() {
    }

    public gradestudent(String gradet,String termm,String classs) {
        this.gradet = gradet;
        this.termm = termm;
        this.classs = classs;
    }

    public String getGradet() {
        return gradet;
    }

    public void setGradet(String gradet) {
        this.gradet = gradet;
    }

    public String getTermm() {
        return termm;
    }

    public void setTermm(String termm) {
        this.termm = termm;
    }

    public String getClasss() {
        return classs;
    }

    public void setClasss(String classs) {
        this.classs = classs;
    }
}
