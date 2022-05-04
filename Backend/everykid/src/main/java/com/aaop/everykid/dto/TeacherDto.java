package com.aaop.everykid.dto;

import com.aaop.everykid.entity.Teacher;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherDto {
    private String T_NAME;
    private String T_PHONE;
    private String T_EMAIL;
    private String T_ID;
    private String T_PWD;
    private String K_ID;

    public TeacherDto(String t_NAME, String t_PHONE, String t_EMAIL, String t_ID, String t_PWD, String k_ID) {
        T_NAME = t_NAME;
        T_PHONE = t_PHONE;
        T_EMAIL = t_EMAIL;
        T_ID = t_ID;
        T_PWD = t_PWD;
        K_ID = k_ID;
    }

    @Override
    public String toString() {
        return "TeacherDto{" +
                "T_NAME='" + T_NAME + '\'' +
                ", T_PHONE='" + T_PHONE + '\'' +
                ", T_EMAIL='" + T_EMAIL + '\'' +
                ", T_ID='" + T_ID + '\'' +
                ", T_PWD='" + T_PWD + '\'' +
                ", K_ID='" + K_ID + '\'' +
                '}';
    }

    public Teacher toEntity() {
        return new Teacher(T_NAME, T_PHONE, T_EMAIL, T_ID, T_PWD, K_ID);
    }
}
