package com.aaop.everykid.entity;
import com.aaop.everykid.dto.RegisterTFormDto;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name="Teacher")
@Getter
@Setter
@ToString
public class Teacher {

    @Column(name = "T_NAME")
    private String tNAME;

    @Column(name = "T_PHONE")
    private String tPHONE;

    @Column(name = "T_EMAIL")
    private String tEMAIL;

    @Id
    @Column(name = "T_ID")
    private String tID;

    @Column(name = "T_PWD")
    private String tPWD;

    @Column(name = "K_ID")
    private String kID;

    public static Teacher createTeacher(RegisterTFormDto registerTFormDto){
        Teacher teacher = new Teacher();
        teacher.setTNAME(registerTFormDto.getT_NAME());
        teacher.setTPHONE(registerTFormDto.getT_PHONE());
        teacher.setTEMAIL(registerTFormDto.getT_EMAIL());
        teacher.setTID(registerTFormDto.getT_ID());
        //비밀번호 암호화
        teacher.setTPWD(registerTFormDto.getT_PWD());
        teacher.setKID(registerTFormDto.getK_ID());
        //parent.setPALIAS(parentFormDto.getP_ALIAS());
        //parent.setCNAME(parentFormDto.getC_NAME());
        //parent.setCAGE(parentFormDto.getC_AGE());
        //parent.setRole(Role.PARENT);

        return teacher;
    }
}
