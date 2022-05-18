package com.aaop.everykid.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;

@Getter
@Setter
@ToString
public class regionCodeDto {

    private String siDoName;
    private String siDoCode;
    private String siGunGuName;
    private String siGunGuCode;

    public regionCodeDto(String siDoName, String siDoCode, String siGunGuName, String siGunGuCode) {
        this.siDoName = siDoName;
        this.siDoCode = siDoCode;
        this.siGunGuName = siGunGuName;
        this.siGunGuCode = siGunGuCode;
    }
}
