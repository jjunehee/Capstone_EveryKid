package com.aaop.everykid.service;

import com.aaop.everykid.entity.Teacher;
import com.aaop.everykid.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class RegisterTService {

    private final TeacherRepository teacherRepository;

    public Teacher saveTeacher(Teacher teacher) {

        return teacherRepository.save(teacher);
    }
}