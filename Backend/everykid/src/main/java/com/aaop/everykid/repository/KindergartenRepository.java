package com.aaop.everykid.repository;

import com.aaop.everykid.entity.Kindergarten;
import com.aaop.everykid.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KindergartenRepository extends JpaRepository<Kindergarten, Long> {

    Optional<Kindergarten> findByKKID(Long id);

    Kindergarten findByKID(String id);

}
