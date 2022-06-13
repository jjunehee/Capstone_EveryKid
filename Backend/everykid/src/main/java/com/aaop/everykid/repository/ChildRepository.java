package com.aaop.everykid.repository;

import com.aaop.everykid.entity.Child;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChildRepository extends JpaRepository<Child, Long> {
    Optional<Child> findByPKID(Long PKID);
    }

