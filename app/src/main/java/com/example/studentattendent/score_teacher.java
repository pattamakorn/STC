package com.example.studentattendent;

public class score_teacher {
    private String idstudent,fullnamestd,score,midterm,sfinal,sumscore;

    public score_teacher() {
    }

    public score_teacher(String idstudent, String fullnamestd, String score, String midterm, String sfinal, String sumscore) {
        this.idstudent = idstudent;
        this.fullnamestd = fullnamestd;
        this.score = score;
        this.midterm = midterm;
        this.sfinal = sfinal;
        this.sumscore = sumscore;
    }

    public String getIdstudent() {
        return idstudent;
    }

    public void setIdstudent(String idstudent) {
        this.idstudent = idstudent;
    }

    public String getFullnamestd() {
        return fullnamestd;
    }

    public void setFullnamestd(String fullnamestd) {
        this.fullnamestd = fullnamestd;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getMidterm() {
        return midterm;
    }

    public void setMidterm(String midterm) {
        this.midterm = midterm;
    }

    public String getSfinal() {
        return sfinal;
    }

    public void setSfinal(String sfinal) {
        this.sfinal = sfinal;
    }

    public String getSumscore() {
        return sumscore;
    }

    public void setSumscore(String sumscore) {
        this.sumscore = sumscore;
    }
}
