package com.aaop.everykid.dto;

import com.aaop.everykid.entity.Kindergarten;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KindergartenDto {

    private Long K_KID;
    private String K_ID;
    private String K_PHONE;
    private String K_ADDRESS;
    private String K_NAME;

    public KindergartenDto(Long k_KID, String k_ID, String k_PHONE, String k_ADDRESS, String k_NAME) {
        K_KID = k_KID;
        K_ID = k_ID;
        K_PHONE = k_PHONE;
        K_ADDRESS = k_ADDRESS;
        K_NAME = k_NAME;
    }

    @Override
    public String toString() {
        return "KindergartenDto{" +
                "K_KID=" + K_KID +
                ", K_ID='" + K_ID + '\'' +
                ", K_PHONE='" + K_PHONE + '\'' +
                ", K_ADDRESS='" + K_ADDRESS + '\'' +
                ", K_NAME='" + K_NAME + '\'' +
                '}';
    }

    public Kindergarten toEntity() {
        return new Kindergarten(K_KID, K_ID, K_PHONE, K_ADDRESS, K_NAME);
    }
}

