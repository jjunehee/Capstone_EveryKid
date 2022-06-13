package com.aaop.everykid.service;

import com.aaop.everykid.entity.Child;
import com.aaop.everykid.entity.Teacher;
import com.aaop.everykid.repository.ChildRepository;
import com.aaop.everykid.repository.ParentRepository;
import com.aaop.everykid.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class RegisterCService {

    private final ChildRepository childRepository;
    private final ParentRepository parentRepository;


    public Child saveChild(Child child) {

        return childRepository.save(child);
    }

    public void deleteChild(Long pkid) {
        childRepository.deleteById(pkid);
    }
}