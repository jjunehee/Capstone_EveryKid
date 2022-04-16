package com.aaop.everykid.controller;

import com.aaop.everykid.dto.ParentFormDto;
import com.aaop.everykid.service.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/parents")
@Controller
@RequiredArgsConstructor
public class ParentController {

    private final ParentService parentService;

    @GetMapping(value = "/new")
    public String parentForm(Model model) {     //회원가입 페이지 이동
        model.addAttribute("parentFormDto", new ParentFormDto());
        return "parent/parentForm";
    }

    @GetMapping(value = "/login")
    public String loginMember() {
        return "/parent/parentLoginForm";
    }

    @GetMapping(value = "/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        return "/parent/parentLoginForm";
    }

}