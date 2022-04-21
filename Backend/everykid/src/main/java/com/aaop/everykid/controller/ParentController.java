package com.aaop.everykid.controller;

import com.aaop.everykid.dto.ParentFormDto;
import com.aaop.everykid.entity.Parent;
import com.aaop.everykid.service.LoginService;
import com.aaop.everykid.service.ParentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@RequestMapping("/parents")
@RestController
@RequiredArgsConstructor
public class ParentController {

    private final ParentService parentService;
    private final LoginService loginService;
//    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/new")
    public ParentFormDto getParameter(ParentFormDto pa) {

        return pa;
    }

    //jpa 문자열
   @PostMapping(value = "/new")
    public ParentFormDto parentForm(@RequestBody ParentFormDto parentFormDto){
        Parent parent = Parent.createParent(parentFormDto);
        parentService.saveParent(parent);
       System.out.println("회원가입 시도" + parentFormDto);
    return parentFormDto;
    }

    @PostMapping(value = "login")
    public String loginId(@ModelAttribute Parent parent){
        System.out.println(parent+"aaaaa");
        System.out.println(loginService.login(parent));
        if(loginService.login(parent)){
            return "성공";
        }
        return "실패";
    }
}