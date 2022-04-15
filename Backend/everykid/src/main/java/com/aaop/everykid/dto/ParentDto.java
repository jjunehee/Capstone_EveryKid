package com.aaop.everykid.dto;

import com.aaop.everykid.entity.Parent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ParentDto {

    private String P_NAME;
    private String P_PHONE;
    private String P_EMAIL;
    private String P_ID;
    private String P_PWD;
    private String P_ALIAS;
    private String K_ID;
    private String T_ID;
    private String C_NAME;
    private String C_AGE;
    private String C_STATUS;

}