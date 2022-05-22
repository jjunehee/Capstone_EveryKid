package com.aaop.everykid.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Getter
@RequiredArgsConstructor
@Table(name = "auth")
@Entity
public class Auth2 {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="ID")
    private Long ID;

    private String refreshToken;

    @ManyToOne
    @JoinColumn(name = "T_ID")
    private Teacher teacher;

    @Builder
    public Auth2(String refreshToken, Teacher teacher) {
        this.refreshToken = refreshToken;
        this.teacher = teacher;
    }
    public void refreshUpdate(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
