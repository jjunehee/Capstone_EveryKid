package com.aaop.everykid.repository;

import com.aaop.everykid.entity.Auth;
import com.aaop.everykid.entity.Auth2;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository2 extends JpaRepository<Auth2, Long> {

    Optional<Auth2> findByTeacherTKID(Long TKID);
}
