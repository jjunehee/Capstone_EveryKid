package com.aaop.everykid.entity;

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

    @Transient
    private MultipartFile picture;

    @Column(name = "C_IMG")
    private String pictureUrl;
}
