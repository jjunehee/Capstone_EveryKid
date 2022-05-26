package com.aaop.everykid.service;

import com.aaop.everykid.entity.Kindergarten;
import com.aaop.everykid.repository.KindergartenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class KindergartenService {
    @Autowired
    KindergartenRepository kindergartenRepository;

    public Optional<Kindergarten> findByKKID(Long KKID) {

        return kindergartenRepository.findByKKID(KKID);
    }

    public Kindergarten findByKID(String KID) {

        return kindergartenRepository.findByKID(KID);
    }
}
