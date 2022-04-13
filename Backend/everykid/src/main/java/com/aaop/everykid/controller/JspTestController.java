package com.aaop.everykid.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class JspTestController {

    @GetMapping("/")
    public String index(){
        return "index";
    }
}
