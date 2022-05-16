package com.aaop.everykid.repository;

import com.aaop.everykid.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {

    Page<Board> findBykID(String id, Pageable pageable);

    Board findBybID(int id);

    @Query(value="select * from everykid.board where K_ID = ?2 AND " +
            "(P_ID LIKE CONCAT('%', ?1, '%')" +
            "OR T_ID LIKE CONCAT('%', ?1, '%')" +
            "OR CONTENTS LIKE CONCAT('%', ?1, '%'))", nativeQuery = true)
    Page<Board> searchBoard(String key, String kID, Pageable pageable);
}
