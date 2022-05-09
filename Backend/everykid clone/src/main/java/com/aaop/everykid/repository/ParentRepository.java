package com.aaop.everykid.repository;

import com.aaop.everykid.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {

    Parent findParentBypID(Long id);
}
