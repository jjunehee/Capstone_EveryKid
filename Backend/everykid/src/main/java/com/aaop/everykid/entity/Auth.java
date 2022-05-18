package com.aaop.everykid.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Getter
@RequiredArgsConstructor
@Table(name = "auth")
@Entity
public class Auth {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="ID")
    private Long ID;

    private String refreshToken;

    @ManyToOne
    @JoinColumn(name = "P_ID")
    private Parent parent;

    @Builder
    public Auth(String refreshToken, Parent parent) {
        this.refreshToken = refreshToken;
        this.parent = parent;
    }
    public void refreshUpdate(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
