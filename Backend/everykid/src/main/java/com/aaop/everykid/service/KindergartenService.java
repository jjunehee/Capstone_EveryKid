package com.aaop.everykid.service;

import com.aaop.everykid.entity.Board;
import com.aaop.everykid.entity.Kindergarten;
import com.aaop.everykid.repository.BoardRepository;
import com.aaop.everykid.repository.KindergartenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class KindergartenService {
    @Autowired
    KindergartenRepository kindergartenRepository;

    public Kindergarten findByKKID(Long KKID) {
        return kindergartenRepository.findByKKID(KKID);
    }

    public Kindergarten findByKID(String KID) {
        return kindergartenRepository.findByKID(KID);
    }
}
