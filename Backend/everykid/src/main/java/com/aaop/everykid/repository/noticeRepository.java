package com.aaop.everykid.repository;

import com.aaop.everykid.entity.Kindergarten;
import com.aaop.everykid.entity.Notice;
import com.aaop.everykid.entity.NoticeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface noticeRepository extends JpaRepository<Notice, NoticeKey> {

    List<Notice> findByKKID(Long KKID);

    @Query(value="select * from everykid.notice where K_KID = ?1 AND " +
            "WRITE_DATE = ?2", nativeQuery = true)
    Notice findByKKIDANDwriteDate(Long KKID, String date);
}
