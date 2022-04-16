package com.aaop.everykid.controller;

import com.aaop.everykid.dto.ParentFormDto;
import com.aaop.everykid.service.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aaop.everykid.entity.Parent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;


@RequestMapping("/parents")
@Controller
@RequiredArgsConstructor
public class ParentController {

    private final ParentService parentService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/new")
    public String parentForm(Model model){
        model.addAttribute("parentFormDto", new ParentFormDto());
        return "parent/parentForm";
    }

    @PostMapping(value="/new")
    public String parentForm(ParentFormDto parentFormDto){

        Parent parent = Parent.createParent(parentFormDto, passwordEncoder);
        parentService.saveParent(parent);

        return "redirect:/";
    }
    }