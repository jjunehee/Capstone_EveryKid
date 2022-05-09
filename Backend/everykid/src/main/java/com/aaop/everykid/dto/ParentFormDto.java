package com.aaop.everykid.dto;
//회원가입 폼 dto
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegisterPFormDto {

    private String P_ID;

    private String P_NAME;

    private String P_PWD;

    private String P_PHONE;

    private String K_ID;

    private String P_EMAIL;

    private String P_ALIAS;

    //private String C_NAME;

    //private String C_AGE;

}