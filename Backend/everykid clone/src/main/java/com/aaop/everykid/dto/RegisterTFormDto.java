package com.aaop.everykid.dto;
//회원가입 폼 dto
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegisterTFormDto {

    private String T_ID;

    private String T_NAME;

    private String T_PWD;

    private String T_PHONE;

    private String T_EMAIL;

    private String K_ID;

    private String P_ALIAS;

    //private String C_NAME;

    //private String C_AGE;

}