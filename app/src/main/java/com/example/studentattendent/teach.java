package com.example.studentattendent;

public class teach {

    String idsub,classR,TimeT;

    public teach() {
    }

    public teach(String idsub, String classR, String timeT) {
        this.idsub = idsub;
        this.classR = classR;
        TimeT = timeT;
    }

    public String getIdsub() {
        return idsub;
    }

    public void setIdsub(String idsub) {
        this.idsub = idsub;
    }

    public String getClassR() {
        return classR;
    }

    public void setClassR(String classR) {
        this.classR = classR;
    }

    public String getTimeT() {
        return TimeT;
    }

    public void setTimeT(String timeT) {
        TimeT = timeT;
    }
}
