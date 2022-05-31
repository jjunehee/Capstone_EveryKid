package com.aaop.everykid.repository;

import com.aaop.everykid.entity.Parent;
import com.aaop.everykid.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Optional<Teacher> findBytID(String TID);
}
