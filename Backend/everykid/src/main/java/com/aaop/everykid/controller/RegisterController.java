package com.aaop.everykid.controller;

import com.aaop.everykid.dto.*;
import com.aaop.everykid.entity.Child;
import com.aaop.everykid.entity.Parent;
import com.aaop.everykid.service.*;
import com.aaop.everykid.dto.TokenResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
    //private final ChildService childService;

    //jpa 문자열

    @PostMapping(value = "/parent")     //p_NAME
    public ResponseEntity signUp(RegisterPFormDto registerPFormDto) {
        System.out.println("부모 회원가입 시도" + registerPFormDto);
        return parentService.findBypID(registerPFormDto.getPID()).isPresent()
                ? ResponseEntity.badRequest().build()
                : ResponseEntity.ok(parentService.signUp(registerPFormDto));
    }

    @PostMapping(value = "/teacher")     //p_NAME
    public ResponseEntity signUp2(RegisterTFormDto registerTFormDto) {
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
}
/*
    @GetMapping("/info")
    public ResponseEntity<List<Parent>> findUser() {
        return ResponseEntity.ok().body(parentService.findUsers());
    }
*/

/*    @PostMapping(value = "/teacher")     //p_NAMEpu
    public RegisterTFormDto registerForm2(@RequestBody RegisterTFormDto registerTFormDto) {
        Teacher teacher = Teacher.createTeacher(registerTFormDto);
        registerTService.saveTeacher(teacher);
        System.out.println("선생 회원가입 시도" + registerTFormDto);
        return registerTFormDto;
    }*/
