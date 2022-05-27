package com.aaop.everykid.service;

import com.aaop.everykid.entity.Parent;
import com.aaop.everykid.repository.ParentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ChildServiceImpl implements ChildService {

    private final ParentRepository repository;
    @Override
    @Transactional
    public void regist(Parent parent) throws Exception{
        repository.save(parent);
    }
}
