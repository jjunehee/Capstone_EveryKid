package com.aaop.everykid.entity;

import com.aaop.everykid.dto.ChildFormDto;
import com.aaop.everykid.dto.RegisterCFormDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Table(name="Child")
@Getter
@Setter
@ToString
public class Child {

    @Id
    @Column(name="C_KID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CKID;

    @Column(name ="C_AGE")
    private String cAGE;

    @Column(name ="C_NAME")
    private String cNAME;

    @Column(name ="P_KID")
    private Long PKID;

    public static Child createChild(RegisterCFormDto registerCFormDto){
        Child child = new Child();
        child.setCNAME(registerCFormDto.getCNAME());
        child.setCAGE(registerCFormDto.getCAGE());
        child.setPKID(registerCFormDto.getPKID());
        return child;
    }


/*    @Transient
    private MultipartFile picture;

    @Column(name = "C_IMG")
    private String pictureUrl;*/
}
