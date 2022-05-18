package com.aaop.everykid.dto;
//회원가입 폼 dto
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegisterTFormDto {

    private String tID;

    private String tNAME;

    private String tPWD;

    private String tPHONE;

    private String tEMAIL;

    private String kKID;

    private String pALIAS;

    //private String C_NAME;

    //private String C_AGE;

}