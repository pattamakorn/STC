package com.example.studentattendent;

public class studentcheck {
    String followidsub,follownamesub,followidteach,follownameteach,followclassroom,followidcheck;


    public studentcheck() {
    }

    public studentcheck(String followidsub, String follownamesub, String followidteach, String follownameteach, String followclassroom, String followidcheck) {
        this.followidsub = followidsub;
        this.follownamesub = follownamesub;
        this.followidteach = followidteach;
        this.follownameteach = follownameteach;
        this.followclassroom = followclassroom;
        this.followidcheck = followidcheck;
    }


    public String getFollowidsub() {
        return followidsub;
    }

    public void setFollowidsub(String followidsub) {
        this.followidsub = followidsub;
    }

    public String getFollownamesub() {
        return follownamesub;
    }

    public void setFollownamesub(String follownamesub) {
        this.follownamesub = follownamesub;
    }

    public String getFollowidteach() {
        return followidteach;
    }

    public void setFollowidteach(String followidteach) {
        this.followidteach = followidteach;
    }

    public String getFollownameteach() {
        return follownameteach;
    }

    public void setFollownameteach(String follownameteach) {
        this.follownameteach = follownameteach;
    }

    public String getFollowclassroom() {
        return followclassroom;
    }

    public void setFollowclassroom(String followclassroom) {
        this.followclassroom = followclassroom;
    }

    public String getFollowidcheck() {
        return followidcheck;
    }

    public void setFollowidcheck(String followidcheck) {
        this.followidcheck = followidcheck;
    }
}
