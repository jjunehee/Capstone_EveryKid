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

    Board findByBKID(Long id);

    @Query(value="select * from everykid.board where K_KID = ?2 AND " +
            "(WRITE_SUBJECT LIKE CONCAT('%', ?1, '%'))", nativeQuery = true)
    Page<Board> searchSubject(String key, Long kID, Pageable pageable);

    @Query(value="select * from everykid.board where K_KID = ?2 AND " +
            "(CONTENTS LIKE CONCAT('%', ?1, '%'))", nativeQuery = true)
    Page<Board> searchContents(String key, Long kID, Pageable pageable);

    @Query(value="select * from everykid.board where K_KID = ?2 AND " +
            "(P_ID LIKE CONCAT('%', ?1, '%')" +
            "OR T_ID LIKE CONCAT('%', ?1, '%'))", nativeQuery = true)
    Page<Board> searchWriter(String key, Long kID, Pageable pageable);

    @Query(value="select ifnull(max(B_KID), 0) from everykid.board", nativeQuery = true)
    int maxBKID();
}
