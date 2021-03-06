package com.aaop.everykid.service;

import com.aaop.everykid.entity.Parent;
import com.aaop.everykid.repository.ParentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService  {

    @Autowired
    private final ParentRepository parentRepository;

/* 기존 로그인 방식
    public boolean login(Parent parent){
        Parent findParent = parentRepository.findParentBypID(parent.getPKID());
        if(findParent == null){
            return false;
        }
        else return true;
    }*/
}
