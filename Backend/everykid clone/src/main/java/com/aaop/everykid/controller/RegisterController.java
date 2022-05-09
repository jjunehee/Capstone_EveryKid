package com.aaop.everykid.controller;

import com.aaop.everykid.dto.RegisterPFormDto;
import com.aaop.everykid.dto.RegisterTFormDto;
import com.aaop.everykid.entity.Parent;
import com.aaop.everykid.entity.Teacher;
import com.aaop.everykid.service.LoginService;
import com.aaop.everykid.service.RegisterPService;
import com.aaop.everykid.service.RegisterTService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

//import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@RequestMapping("/register")
@RestController
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterPService parentService;
    private final RegisterTService teacherService;
    private final LoginService loginService;
//    private final PasswordEncoder passwordEncoder;

/*
    @GetMapping(value = "/new")
    public ParentFormDto getParameter(ParentFormDto pa) {

        return pa;
    }
*/

    //jpa 문자열
    @PostMapping(value = "/parent")     //p_NAME
    public RegisterPFormDto registerForm(@RequestBody RegisterPFormDto registerPFormDto) {
        Parent parent = Parent.createParent(registerPFormDto);
        parentService.saveParent(parent);
        System.out.println("부모 회원가입 시도" + registerPFormDto);
        return registerPFormDto;
    }

    @PostMapping(value = "/teacher")     //p_NAME
    public RegisterTFormDto registerForm2(@RequestBody RegisterTFormDto registerTFormDto) {
        Teacher teacher = Teacher.createTeacher(registerTFormDto);
        teacherService.saveTeacher(teacher);
        System.out.println("선생 회원가입 시도" + registerTFormDto);
        return registerTFormDto;
    }

/*    @PostMapping(value = "/login")    //pid
    public String loginId(Parent parent) {
        loginService.login(parent);
        System.out.println(parent + "aaaaa");
        System.out.println(loginService.login(parent));

        if (loginService.login(parent)) {
            return "성공";
        }
        return "실패";
    }*/
}

