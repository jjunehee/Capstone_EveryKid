package com.aaop.everykid.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    //test
    @GetMapping(value = "/")
    public String main(){
        return "main";
    }
}
