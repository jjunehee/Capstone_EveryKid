package com.aaop.everykid.entity;
import com.aaop.everykid.dto.RegisterPFormDto;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
//import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name="Parent")
@Getter @Setter
@ToString
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
    private Long pKID;

    @Column(name="P_PWD")
    private String pPWD;

    @Column(name="P_ID")
    private String pID;

    @Column(name="P_ALIAS")
    private String pALIAS;

    /*    @Column(name="K_KID")
        private String kID;*/
    @Column(name="K_KID")
    private String kID;

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


    //public static Parent createParent(ParentFormDto parentFormDto, PasswordEncoder passwordEncoder){
    public static Parent createParent(RegisterPFormDto registerPFormDto){
        Parent parent = new Parent();
        parent.setPNAME(registerPFormDto.getP_NAME());
        parent.setPPHONE(registerPFormDto.getP_PHONE());
        parent.setPEMAIL(registerPFormDto.getP_EMAIL());
        parent.setPID(registerPFormDto.getP_ID());
        //비밀번호 암호화
        parent.setPPWD(registerPFormDto.getP_PWD());
        parent.setKID(registerPFormDto.getK_ID());
        parent.setPALIAS(registerPFormDto.getP_ALIAS());
        //parent.setCNAME(parentFormDto.getC_NAME());
        //parent.setCAGE(parentFormDto.getC_AGE());
        //parent.setRole(Role.PARENT);

        return parent;
    }
}