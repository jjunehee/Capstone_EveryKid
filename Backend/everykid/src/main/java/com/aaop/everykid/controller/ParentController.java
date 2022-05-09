<<<<<<< HEAD:Backend/everykid clone/src/main/java/com/aaop/everykid/controller/ParentController.java
/*
package com.aaop.everykid.controller;

import com.aaop.everykid.dto.RegisterFormDto;
import com.aaop.everykid.entity.Parent;
import com.aaop.everykid.service.LoginService;
import com.aaop.everykid.service.ParentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public RegisterFormDto getParameter(RegisterFormDto pa) {

        return pa;
    }

    //jpa 문자열
    @PostMapping(value = "/new")     //p_NAME
    public RegisterFormDto parentForm(@RequestBody RegisterFormDto parentFormDto) {
        Parent parent = Parent.createParent(parentFormDto);
        parentService.saveParent(parent);
        System.out.println("회원가입 시도" + parentFormDto);
        return parentFormDto;
    }

    @PostMapping(value = "/login")    //pid
    public String loginId(Parent parent) {

        loginService.login(parent);
        System.out.println(parent + "aaaaa");
        System.out.println(loginService.login(parent));

        if (loginService.login(parent)) {
            return "성공";
        }
        return "실패";
    }

}*/
=======
package com.aaop.everykid.controller;

import com.aaop.everykid.dto.ParentFormDto;
import com.aaop.everykid.entity.Parent;
import com.aaop.everykid.service.LoginService;
import com.aaop.everykid.service.ParentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    @PostMapping(value = "/new")     //p_NAME
    public ParentFormDto parentForm(ParentFormDto parentFormDto) {
        Parent parent = Parent.createParent(parentFormDto);
        parentService.saveParent(parent);
        System.out.println("회원가입 시도" + parentFormDto);
        return parentFormDto;
    }

    @PostMapping(value = "/login")    //pid
    public String loginId(Parent parent) {

        loginService.login(parent);
        System.out.println(parent + "aaaaa");
        System.out.println(loginService.login(parent));

        if (loginService.login(parent)) {
            return "성공";
        }
        return "실패";
    }

}
>>>>>>> 2258f481c3957e464960762509bcfe4ff8700d61:Backend/everykid/src/main/java/com/aaop/everykid/controller/ParentController.java
