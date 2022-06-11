package com.aaop.everykid.repository;


import com.aaop.everykid.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Auth, Long> {

    Optional<Auth> findByParentPKID(Long PKID);
}