package com.aaop.everykid.service;

import com.aaop.everykid.entity.regionCode;
import com.aaop.everykid.repository.regionCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class regionCodeService {
    @Autowired
    regionCodeRepository regionCodeRepository;

//    public regionCode getRegionCode(String siDoName, String siGunGuName) {
//        return regionCodeRepository.findBysiDoNameAndsiGunGuName(siDoName, siGunGuName);
//    }

    public List<regionCode> getAll() {
        return regionCodeRepository.findAll();
    }
}
