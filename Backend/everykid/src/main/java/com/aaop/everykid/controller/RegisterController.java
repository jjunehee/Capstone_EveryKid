package com.aaop.everykid.controller;

import com.aaop.everykid.dto.*;
import com.aaop.everykid.dto.ParentLoginDto;
import com.aaop.everykid.entity.Parent;
import com.aaop.everykid.entity.Teacher;
import com.aaop.everykid.service.LoginService;
import com.aaop.everykid.service.RegisterPService;
import com.aaop.everykid.service.RegisterTService;
import com.aaop.everykid.service.ParentService;
import com.aaop.everykid.dto.TokenResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@RequestMapping("/register")
@RestController
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterPService registerPService;
    private final RegisterTService registerTService;
    private final ParentService parentService;
    private final LoginService loginService;
//    private final PasswordEncoder passwordEncoder;

    //jpa 문자열

    @PostMapping(value = "/parent")     //p_NAME
    public ResponseEntity signUp(RegisterPFormDto registerPFormDto) {
        //Parent parent = Parent.createParent(registerPFormDto);
        //registerPService.saveParent(parent);
        System.out.println("부모 회원가입 시도" + registerPFormDto);
        return parentService.findBypID(registerPFormDto.getPID()).isPresent()
                ? ResponseEntity.badRequest().build()
                : ResponseEntity.ok(parentService.signUp(registerPFormDto));
    }

    @PostMapping(value = "/teacher")     //p_NAME
    public RegisterTFormDto registerForm2(RegisterTFormDto registerTFormDto) {
        Teacher teacher = Teacher.createTeacher(registerTFormDto);
        registerTService.saveTeacher(teacher);
        System.out.println("선생 회원가입 시도" + registerTFormDto);
        return registerTFormDto;
    }

    @PostMapping(value = "/login")     //p_NAME
    public ResponseEntity<TokenResponseDto> signIn(RegisterPFormDto registerPFormDto) throws Exception {

        return ResponseEntity.ok().body(parentService.signIn(registerPFormDto));
    }

    @GetMapping("/info")
    public ResponseEntity<List<Parent>> findUser() {
        return ResponseEntity.ok().body(parentService.findUsers());
    }
}