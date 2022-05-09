package com.aaop.everykid.repository;

import com.aaop.everykid.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Teacher findTeacherBytID(Long id);

}
