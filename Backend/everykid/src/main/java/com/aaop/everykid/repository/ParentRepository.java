package com.aaop.everykid.repository;

import com.aaop.everykid.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {

    Parent findParentBypID(Long id);
}
