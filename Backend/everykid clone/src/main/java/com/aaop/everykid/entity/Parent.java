package com.aaop.everykid.entity;

import javax.persistence.*;
import com.aaop.everykid.constant.Role;
import com.aaop.everykid.dto.ParentFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static javax.persistence.GenerationType.IDENTITY;
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
    @Column(name="P_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pID;

    @Column(name="P_PWD")
    private String pPWD;

    //@Column(name="P_ALIAS")
    //private String pALIAS;

    //@Column(name="K_ID")
    //private String kID;

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
    public static Parent createParent(ParentFormDto parentFormDto){
        Parent parent = new Parent();
        parent.setPNAME(parentFormDto.getP_NAME());
        parent.setPPHONE(parentFormDto.getP_PHONE());
        parent.setPEMAIL(parentFormDto.getP_EMAIL());
        //parent.setPID(parentFormDto.getP_ID());
        //비밀번호 암호화
        parent.setPPWD(parentFormDto.getP_PWD());
        //parent.setPALIAS(parentFormDto.getP_ALIAS());
        //parent.setCNAME(parentFormDto.getC_NAME());
        //parent.setCAGE(parentFormDto.getC_AGE());
        //parent.setRole(Role.PARENT);

        return parent;
    }
}