package com.aaop.everykid.dto;

import com.aaop.everykid.entity.Parent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParentDto {

    private String P_NAME;
    private String P_PHONE;
    private String P_EMAIL;
    private String P_ID;
    private String P_PWD;
    private String P_ALIAS;
    private String K_ID;
    private String T_ID;

    public ParentDto(String p_NAME, String p_PHONE, String p_EMAIL, String p_ID, String p_PWD, String p_ALIAS, String k_ID, String t_ID) {
        P_NAME = p_NAME;
        P_PHONE = p_PHONE;
        P_EMAIL = p_EMAIL;
        P_ID = p_ID;
        P_PWD = p_PWD;
        P_ALIAS = p_ALIAS;
        K_ID = k_ID;
        T_ID = t_ID;
    }

    @Override
    public String toString() {
        return "ParentDto{" +
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

    public Parent toEntity() {
        return new Parent(P_NAME, P_PHONE, P_EMAIL, P_ID, P_PWD, P_ALIAS, K_ID, T_ID);
    }
}
