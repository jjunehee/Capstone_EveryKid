package com.capstone.everykid.Model;
import com.google.gson.annotations.SerializedName;
public class LoginRequestTeacher {
    @SerializedName("tid")
    public String TeacherId;

    @SerializedName("tpwd")
    public String TeacherPw;

    public String getTeacherId() {
        return TeacherId;
    }

    public String getTeacherPw() {
        return TeacherPw;
    }

    public void setTeacherId(String inputId) {
        this.TeacherId = inputId;
    }

    public void setTeacherPw(String inputPw) {
        this.TeacherPw = inputPw;
    }

    public LoginRequestTeacher(String inputId, String inputPw) {
        this.TeacherId = inputId;
        this.TeacherPw = inputPw;
    }
}
