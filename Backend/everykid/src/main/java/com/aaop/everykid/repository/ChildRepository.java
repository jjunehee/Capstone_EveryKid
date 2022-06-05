
package com.aaop.everykid.repository;

import com.aaop.everykid.entity.Child;
import com.aaop.everykid.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {
}
