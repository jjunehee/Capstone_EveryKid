package com.aaop.everykid.dto;
//부모 회원가입 폼 dto
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegisterPFormDto {
    private String pID;
    private String pNAME;
    private String pPWD;
    private String pPHONE;
    private Long kKID;
    private String pEMAIL;
    private String pALIAS;
    private String C_NAME;
    private String C_AGE;
    private String C_IMG;
}