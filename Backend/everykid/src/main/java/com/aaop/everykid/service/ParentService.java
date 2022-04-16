package com.aaop.everykid.service;

import com.aaop.everykid.entity.Parent;
import com.aaop.everykid.repository.ParentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@Service
@Transactional
@RequiredArgsConstructor

public class ParentService implements UserDetailsService {

    private final ParentRepository parentRepository;

    public Parent saveParent(Parent parent){
        validateDuplicateMember(parent);
        return parentRepository.save(parent);
    }

    private void validateDuplicateMember(Parent parent){
        Parent findParent = parentRepository.findByPPHONE(parent.getPPHONE());
        if(findParent != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String pPHONE) throws UsernameNotFoundException {

        Parent parent = parentRepository.findByPPHONE(pPHONE);

        if(parent == null){
            throw new UsernameNotFoundException(pPHONE);
        }

        return User.builder()
                .username(parent.getPPHONE())
                .password(parent.getPPWD())
                .roles(parent.getRole().toString())
                .build();
    }

}

