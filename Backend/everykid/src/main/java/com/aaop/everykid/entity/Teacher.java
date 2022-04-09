package com.aaop.everykid.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Teacher {

    @Column
    private String T_NAME;

    @Column
    private String T_PHONE;

    @Column
    private String T_EMAIL;

    @Id
    @Column
    private String T_ID;

    @Column
    private String T_PWD;

    @Column
    private String K_ID;

    public Teacher(String t_NAME, String t_PHONE, String t_EMAIL, String t_ID, String t_PWD, String k_ID) {
        T_NAME = t_NAME;
        T_PHONE = t_PHONE;
        T_EMAIL = t_EMAIL;
        T_ID = t_ID;
        T_PWD = t_PWD;
        K_ID = k_ID;
    }

    public Teacher() {

    }

    @Override
    public String toString() {
        return "Teacher{" +
                "T_NAME='" + T_NAME + '\'' +
                ", T_PHONE='" + T_PHONE + '\'' +
                ", T_EMAIL='" + T_EMAIL + '\'' +
                ", T_ID='" + T_ID + '\'' +
                ", T_PWD='" + T_PWD + '\'' +
                ", K_ID='" + K_ID + '\'' +
                '}';
    }
}
