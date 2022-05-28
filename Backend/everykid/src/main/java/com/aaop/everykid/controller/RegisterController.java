package com.aaop.everykid.controller;

import com.aaop.everykid.dto.*;
import com.aaop.everykid.entity.Parent;
import com.aaop.everykid.entity.Teacher;
import com.aaop.everykid.service.*;
import com.aaop.everykid.dto.TokenResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

//import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@RequestMapping("/register")
@RestController
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterPService registerPService;
    private final RegisterTService registerTService;
    private final ParentService parentService;
    private final TeacherService teacherService;
    private final LoginService loginService;
//    private final PasswordEncoder passwordEncoder;

    //jpa 문자열

    @PostMapping(value = "/parent")     //p_NAME
    public ResponseEntity signUp(RegisterPFormDto registerPFormDto) {
        System.out.println("부모 회원가입 시도" + registerPFormDto);
        return parentService.findBypID(registerPFormDto.getPID()).isPresent()
                ? ResponseEntity.badRequest().build()
                : ResponseEntity.ok(parentService.signUp(registerPFormDto));
    }

/*    @PostMapping(value = "/teacher")     //p_NAME
    public RegisterTFormDto registerForm2(@RequestBody RegisterTFormDto registerTFormDto) {
        Teacher teacher = Teacher.createTeacher(registerTFormDto);
        registerTService.saveTeacher(teacher);
        System.out.println("선생 회원가입 시도" + registerTFormDto);
        return registerTFormDto;
    }*/

    @PostMapping(value = "/teacher")     //p_NAME
    public ResponseEntity signUp2(@RequestBody RegisterTFormDto registerTFormDto) {
        System.out.println("선생 회원가입 시도" + registerTFormDto);
        return teacherService.findBytID(registerTFormDto.getTID()).isPresent()
                ? ResponseEntity.badRequest().build()
                : ResponseEntity.ok(teacherService.signUp(registerTFormDto));
    }

    @PostMapping(value = "/plogin")     //p_NAME
    public ResponseEntity<TokenResponseDto> signIn(@RequestBody LoginPFormDto loginPFormDto) throws Exception {
        return ResponseEntity.ok().body(parentService.signIn(loginPFormDto));
    }

    @PostMapping(value = "/tlogin")     //p_NAME
    public ResponseEntity<TokenResponseDto2> signIn2(@RequestBody LoginTFormDto loginTFormDto) throws Exception {
        return ResponseEntity.ok().body(teacherService.signIn2(loginTFormDto));
    }

    @GetMapping("/info")
    public ResponseEntity<List<Parent>> findUser() {
        return ResponseEntity.ok().body(parentService.findUsers());
    }


/*
    @PostMapping(value="/child")
    public ResponseEntity<Parent> register(@RequestPart("parent") String parentString, @RequestPart("file")MultipartFile picture)
        throws Exception{
        log.info("parentString" + parentString);

        Parent parent = new ObjectMapper().readValue(parentString, Parent.class);

        Integer cAGE = parent.getCAGE();
        String cNAME = parent.getCNAME();

        parent.setPicture(picture);

        MultipartFile file = parent.getPicture();

        log.info("orginName:" + file.getOriginalFilename());
        log.info("size" + file.getSize());
        log.info("contentType" + file.getContentType());

        String createdFileName = uploadFile(file.getOriginalFilename(),file.getBytes());
        parent.setPictureUrl(createdFileName);
        this.parentService.regist(parent);

        Parent createParent = new Parent();

        return new ResponseEntity<>()(createdParent, HttpStatus.OK);
        }

    private String uploadFile(String originalName, bytep[] fileData) throws Exception{
        UUID uid = UUID.randomUUID();

        String createdFileName = uid.toString() + "_" + originalName;
        File target = new File(uploadPath, CreatedFileName);
        FileCopyUtils.copy(fileData, target);
        return createdFileName;

    }
*/


}

