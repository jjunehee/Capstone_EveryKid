package com.aaop.everykid.repository;

import com.aaop.everykid.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    Page<Board> findByKKID(Long id, Pageable pageable);

<<<<<<< HEAD
    Board findByBKID(int id);
=======
    Board findByBKID(Long id);
>>>>>>> parent of ec69cd4 (수정)

    @Query(value="select * from everykid.board where K_KID = ?2 AND " +
            "(P_ID LIKE CONCAT('%', ?1, '%')" +
            "OR T_ID LIKE CONCAT('%', ?1, '%')" +
            "OR CONTENTS LIKE CONCAT('%', ?1, '%'))", nativeQuery = true)
<<<<<<< HEAD
    Page<Board> searchBoard(String key, String kID, Pageable pageable);
=======
    Page<Board> searchBoard(String key, Long kID, Pageable pageable);
>>>>>>> parent of ec69cd4 (수정)

    @Query(value="select ifnull(max(B_KID), 0) from everykid.board", nativeQuery = true)
    int maxBKID();
}
