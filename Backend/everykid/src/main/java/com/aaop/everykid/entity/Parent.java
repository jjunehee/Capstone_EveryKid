package com.aaop.everykid.entity;
import com.aaop.everykid.dto.RegisterPFormDto;

import javax.persistence.*;

import lombok.*;
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

    @Column(name="P_ALIAS")
    private String pALIAS;

    @Column(name="K_KID")
    private String kKID;


    //@Column(name="T_ID")
    //private String tID;

    //@Enumerated(EnumType.STRING)
    //private Role role;

    //@Column(name="C_NAME")
    //private String cNAME;

    //@Column(name="C_AGE")
    //private String cAGE;

    //@Column(name="C_STATUS")
    //private boolean cSTATUS;

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
    public Parent(String pID, String pPWD, String pNAME, String pPHONE, String pEMAIL ,String pALIAS) {
        this.pID = pID;
        this.pPWD = pPWD;
        this.pNAME = pNAME;
        this.pPHONE = pPHONE;
        this.pEMAIL = pEMAIL;
        this.pALIAS = pALIAS;
    }

}