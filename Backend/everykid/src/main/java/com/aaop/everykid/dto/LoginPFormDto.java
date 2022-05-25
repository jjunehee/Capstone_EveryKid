package com.aaop.everykid.dto;
//부모 회원가입 폼 dto
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginPFormDto {
    private String pID;
    private String pPWD;
}
