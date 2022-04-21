package com.aaop.everykid.repository;

import com.aaop.everykid.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface ParentRepository extends JpaRepository<Parent, Long> {

    Parent findBypID(Long id);
}
