package com.aaop.everykid.controller;

import com.aaop.everykid.dto.BoardDto;
import com.aaop.everykid.entity.Board;
import com.aaop.everykid.entity.Kindergarten;
import com.aaop.everykid.repository.BoardRepository;
import com.aaop.everykid.repository.KindergartenRepository;
import com.aaop.everykid.service.KindergartenService;
import com.aaop.everykid.service.regionCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

@RestController
@RequestMapping("/kindergarten")
public class KindergartenController {
    @Autowired
    KindergartenRepository kindergartenRepository;
    @Autowired
    KindergartenService kindergartenService;

    @RequestMapping ("/find/{KID}/{KPHONE}/{KADDRESS}/{KNAME}")
    public Long selectKindergarten(@PathVariable("KID") String kID, @PathVariable("KPHONE") String kPHONE, @PathVariable("KADDRESS") String kADDRESS,
                                @PathVariable("KNAME") String kNAME) {

        Kindergarten kindergarten = new Kindergarten(null, kID, kPHONE, kADDRESS, kNAME);

        System.out.println(kindergarten);

        // 1. Dto -> Entity
        //Board board = boardDto.toEntity();
        //System.out.println(board.toString());

        // 2. Repository에게 Entity를 DB에 저장하게 함
        Kindergarten saved;
        try {
            saved = kindergartenRepository.save(kindergarten);
        } catch (Exception e) {
            saved = kindergartenService.findByKID(kID);
        }
        //System.out.println(saved.toString());

        return saved.getKKID();
    }
}
