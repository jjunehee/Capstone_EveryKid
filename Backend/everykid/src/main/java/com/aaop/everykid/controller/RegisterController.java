package com.aaop.everykid.controller;

import com.aaop.everykid.dto.*;
import com.aaop.everykid.entity.Parent;
import com.aaop.everykid.entity.Teacher;
import com.aaop.everykid.service.*;
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
    private final TeacherService teacherService;
    private final LoginService loginService;
//    private final PasswordEncoder passwordEncoder;

    //jpa 문자열

    @PostMapping(value = "/parent")     //p_NAME
    public ResponseEntity signUp(@RequestBody RegisterPFormDto registerPFormDto) {
        //Parent parent = Parent.createParent(registerPFormDto);
        //registerPService.saveParent(parent);
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

    @PostMapping(value = "/login")     //p_NAME
    public ResponseEntity<TokenResponseDto> signIn(@RequestBody LoginFormDto loginFormDto) throws Exception {
        return ResponseEntity.ok().body(parentService.signIn(loginFormDto));
    }

    @GetMapping("/info")
    public ResponseEntity<List<Parent>> findUser() {
        return ResponseEntity.ok().body(parentService.findUsers());
    }

    @PostMapping(value="/child")
    public RegisterCFormDto registerChild(@RequestBody RegisterCFormDto registerCFormDto){
        Parent child = Parent.createChild(registerCFormDto);
        registerPService.saveParent(child);
        System.out.println("아이등록" + registerCFormDto);
        return registerCFormDto;
        }
    }

