package com.aaop.everykid.entity;
import com.aaop.everykid.dto.RegisterCFormDto;
import com.aaop.everykid.dto.RegisterPFormDto;

import javax.persistence.*;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
//import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name="Parent")
@Getter @Setter
@ToString
@RequiredArgsConstructor
public class Parent {


    @Column(name="P_NAME")
    private String pNAME;

    @Column(name="P_PHONE")
    private String pPHONE;

    @Column(name="P_EMAIL")
    private String pEMAIL;

    @Id
    @Column(name="P_KID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long PKID;

    @Column(name="P_PWD")
    private String pPWD;

    @Column(name="P_ID")
    private String pID;

    @Column(name="K_KID")
    private Long kKID;

    @Column(name="T_NAME")
    private String tNAME;

    @Column(name="T_KID")
    private Long tKID;

    //@Enumerated(EnumType.STRING)
    //private Role role;


/*    public static Parent createParent(RegisterPFormDto registerPFormDto){
        Parent parent = new Parent();
        parent.setPNAME(registerPFormDto.getPNAME());
        parent.setPPHONE(registerPFormDto.getPPHONE());
        parent.setPEMAIL(registerPFormDto.getPEMAIL());
        parent.setPID(registerPFormDto.getPID());
        //비밀번호 암호화
        parent.setPPWD(registerPFormDto.getPPWD());
        parent.setKKID(registerPFormDto.getKKID());
        parent.setPALIAS(registerPFormDto.getPALIAS());
        //parent.setCNAME(parentFormDto.getC_NAME());
        //parent.setCAGE(parentFormDto.getC_AGE());
        //parent.setRole(Role.PARENT);
        return parent;
    }*/

    @Builder
    public Parent(Long PKID, String pID, String pPWD, String pNAME, String pPHONE, String pEMAIL, Long kKID,String tNAME) {
        this.PKID = PKID;
        this.pID = pID;
        this.pPWD = pPWD;
        this.pNAME = pNAME;
        this.pPHONE = pPHONE;
        this.pEMAIL = pEMAIL;
        this.kKID=kKID;
        this.tNAME=tNAME;
<<<<<<< HEAD
=======

>>>>>>> 6fbe78ffe763b0af56e599f2eb51973ead1ec444
    }
/*
    public static Parent createChild(RegisterCFormDto registerCFormDto){
        Parent child = new Parent();
        child.setPKID(registerCFormDto.getPKID());
        child.setCNAME(registerCFormDto.getCNAME());
        child.setCAGE(registerCFormDto.getCAGE());
        return child;
    }*/
}