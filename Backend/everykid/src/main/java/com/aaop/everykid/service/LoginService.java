/*
package com.aaop.everykid.service;

import com.aaop.everykid.entity.Parent;
import com.aaop.everykid.dto.ParentFormDto;
import com.aaop.everykid.repository.ParentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {

    private final ParentRepository parentRepository;

    public boolean login(Parent parent){

        //Parent findParent = parentRepository.findByPNAME(parent.getPID());

        if(findParent == null){
            return false;

        }

        if(!findParent.getPPWD().equals(parent.getPPWD())){
            return false;
        }
        return true;
    }

};*/
