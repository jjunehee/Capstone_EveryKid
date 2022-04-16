package com.aaop.everykid.dto;

import com.aaop.everykid.entity.Parent;
import com.aaop.everykid.entity.Teacher;
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
    private boolean C_STATUS;

    public Parent toEntity() {
        return new Parent(P_NAME, P_PHONE, P_EMAIL, P_ID, P_PWD, P_ALIAS, K_ID, T_ID, C_NAME, C_AGE, C_STATUS);
    }

}