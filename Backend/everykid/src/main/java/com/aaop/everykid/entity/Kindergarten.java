package com.aaop.everykid.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Kindergarten {

    @Id
    @Column
    private String K_ID;

    @Column
    private String K_PHONE;

    @Column
    private String K_ADDRESS;

    @Column
    private String K_NAME;

    public Kindergarten(String k_ID, String k_PHONE, String k_ADDRESS, String k_NAME) {
        K_ID = k_ID;
        K_PHONE = k_PHONE;
        K_ADDRESS = k_ADDRESS;
        K_NAME = k_NAME;
    }

    public Kindergarten() {

    }

    @Override
    public String toString() {
        return "Kindergarten{" +
                "K_ID='" + K_ID + '\'' +
                ", K_PHONE='" + K_PHONE + '\'' +
                ", K_ADDRESS='" + K_ADDRESS + '\'' +
                ", K_NAME='" + K_NAME + '\'' +
                '}';
    }
}