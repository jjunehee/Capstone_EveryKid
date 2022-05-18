package com.aaop.everykid.repository;

import com.aaop.everykid.entity.Board;
import com.aaop.everykid.entity.regionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.aaop.everykid.entity.regionCode;

import java.util.List;

@Repository
public interface regionCodeRepository extends JpaRepository<regionCode, String> {
    List<regionCode> findAll();

//    @Query(value="select * from everykid.regionCode where 시도명 = ?1 AND 시군구명 = ?2"
//           , nativeQuery = true)
//    regionCode findBysiDoNameAndsiGunGuName(String siDoName, String siGunGuName);
}
