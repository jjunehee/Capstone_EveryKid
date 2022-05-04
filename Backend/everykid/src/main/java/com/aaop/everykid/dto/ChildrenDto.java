package com.aaop.everykid.dto;

import com.aaop.everykid.entity.Children;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChildrenDto {
    private String C_ID;
    private int C_AGE;
    private String P_ID;
    private String K_ID;
    private String C_STATUS;

    public ChildrenDto(String c_ID, int c_AGE, String p_ID, String k_ID, String c_STATUS) {
        C_ID = c_ID;
        C_AGE = c_AGE;
        P_ID = p_ID;
        K_ID = k_ID;
        C_STATUS = c_STATUS;
    }

    @Override
    public String toString() {
        return "ChildrenDto{" +
                "C_ID='" + C_ID + '\'' +
                ", C_AGE=" + C_AGE +
                ", P_ID='" + P_ID + '\'' +
                ", K_ID='" + K_ID + '\'' +
                ", C_STATUS='" + C_STATUS + '\'' +
                '}';
    }

    public Children toEntity() {
        return new Children(C_ID, C_AGE, P_ID, K_ID, C_STATUS);
    }
}
