package com.aaop.everykid.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Parent {

    @Column
    private String P_NAME;

    @Column
    private String P_PHONE;

    @Column
    private String P_EMAIL;

    @Id
    @Column
    private String P_ID;

    @Column
    private String P_PWD;

    @Column
    private String P_ALIAS;

    @Column
    private String K_ID;

    @Column
    private String T_ID;

    public Parent(String p_NAME, String p_PHONE, String p_EMAIL, String p_ID, String p_PWD, String p_ALIAS, String k_ID, String t_ID) {
        P_NAME = p_NAME;
        P_PHONE = p_PHONE;
        P_EMAIL = p_EMAIL;
        P_ID = p_ID;
        P_PWD = p_PWD;
        P_ALIAS = p_ALIAS;
        K_ID = k_ID;
        T_ID = t_ID;
    }

    public Parent() {

    }

    @Override
    public String toString() {
        return "Parent{" +
                "P_NAME='" + P_NAME + '\'' +
                ", P_PHONE='" + P_PHONE + '\'' +
                ", P_EMAIL='" + P_EMAIL + '\'' +
                ", P_ID='" + P_ID + '\'' +
                ", P_PWD='" + P_PWD + '\'' +
                ", P_ALIAS='" + P_ALIAS + '\'' +
                ", K_ID='" + K_ID + '\'' +
                ", T_ID='" + T_ID + '\'' +
                '}';
    }
}