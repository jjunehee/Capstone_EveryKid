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

    @Column(name = "K_KID")
<<<<<<< HEAD
    private String kKID;
=======
    private String kID;
>>>>>>> fe936ab4572fcdce4cd177c05708f8f4958a8169

    public static Teacher createTeacher(RegisterTFormDto registerTFormDto){
        Teacher teacher = new Teacher();
        teacher.setTNAME(registerTFormDto.getTNAME());
        teacher.setTPHONE(registerTFormDto.getTPHONE());
        teacher.setTEMAIL(registerTFormDto.getTEMAIL());
        teacher.setTID(registerTFormDto.getTID());
        //비밀번호 암호화
        teacher.setTPWD(registerTFormDto.getTPWD());
        teacher.setKKID(registerTFormDto.getKKID());
        //parent.setPALIAS(parentFormDto.getP_ALIAS());
        //parent.setCNAME(parentFormDto.getC_NAME());
        //parent.setCAGE(parentFormDto.getC_AGE());
        //parent.setRole(Role.PARENT);

        return teacher;
    }
}
