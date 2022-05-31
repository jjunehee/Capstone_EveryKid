package com.aaop.everykid.entity;
import com.aaop.everykid.dto.RegisterCFormDto;
import com.aaop.everykid.dto.RegisterTFormDto;

import javax.persistence.*;

import lombok.*;


@Entity
@Table(name="Teacher")
@Getter @Setter
@ToString
@RequiredArgsConstructor
public class Teacher {


    @Column(name="T_NAME")
    private String tNAME;

    @Column(name="T_PHONE")
    private String tPHONE;

    @Column(name="T_EMAIL")
    private String tEMAIL;

    @Id
    @Column(name="T_KID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long TKID;

    @Column(name="T_PWD")
    private String tPWD;

    @Column(name="T_ID")
    private String tID;

 //   @Column(name="T_ALIAS")
  //  private String tALIAS;

    @Column(name="K_KID")
    private Long kKID;

    //@Enumerated(EnumType.STRING)
    //private Role role;

    @Builder
<<<<<<< HEAD
    public Teacher(Long TKID, String tID, String tPWD, String tNAME, String tPHONE, String tEMAIL,Long kKID) {
=======
    public Teacher(Long TKID, String tID, String tPWD, String tNAME, String tPHONE, String tEMAIL, String kKID) {
>>>>>>> 84f3358ab0cc5472a1de8073e7fe8807e21cc243
        this.TKID = TKID;
        this.tID = tID;
        this.tPWD = tPWD;
        this.tNAME = tNAME;
        this.tPHONE = tPHONE;
        this.tEMAIL = tEMAIL;
        this.kKID = kKID;
       // this.tALIAS = tALIAS;
    }
}
