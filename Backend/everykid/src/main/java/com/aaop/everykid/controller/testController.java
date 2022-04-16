package com.aaop.everykid.controller;

import com.aaop.everykid.dto.ParentDto;
import com.aaop.everykid.entity.Parent;
import com.aaop.everykid.repository.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class testController {

    @Autowired
    private ParentRepository parentRepository;

    @PostMapping("/test")
    public String login(ParentDto parentdto) {
        System.out.println(parentdto.toString());

        // 1. Dto -> Entity
        Parent parents = parentdto.toEntity();
        System.out.println(parents.toString());

        // 2. Repository에게 Entity를 DB에 저장하게 함
        Parent saved = parentRepository.save(parents);
        System.out.println(saved.toString());

        return "";
    }
}