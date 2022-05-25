package com.aaop.everykid.repository;

import com.aaop.everykid.entity.Board;
import com.aaop.everykid.entity.Kindergarten;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KindergartenRepository extends JpaRepository<Kindergarten, Long> {

    Kindergarten findByKKID(Long id);

    Kindergarten findByKID(String id);

}
