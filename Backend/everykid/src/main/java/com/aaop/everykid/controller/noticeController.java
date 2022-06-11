package com.aaop.everykid.controller;

import com.aaop.everykid.dto.BoardDto;
import com.aaop.everykid.entity.Board;
import com.aaop.everykid.entity.BoardList;
import com.aaop.everykid.entity.Notice;
import com.aaop.everykid.repository.BoardRepository;
import com.aaop.everykid.repository.noticeRepository;
import com.aaop.everykid.service.BoardService;
import com.aaop.everykid.service.noticeService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/notice")
public class noticeController {
    @Autowired
    noticeRepository noticeRepository;
    @Autowired
    noticeService noticeService;

    @RequestMapping (value="/write/{KKID}/{DATE}/{WRITESUBJECT}/{CONTENTS}", produces="application/json;charset=UTF-8")
    public Boolean writeContent(@PathVariable("KKID") Long KKID, @PathVariable("DATE") @DateTimeFormat(pattern="yyyy-MM-dd") Date DATE, @PathVariable("WRITESUBJECT") String WRITESUBJECT, @PathVariable("CONTENTS") String CONTENTS) {

        SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formatDate = dtFormat.format(DATE);

        Notice notice = new Notice(KKID, DATE, WRITESUBJECT, CONTENTS);

        System.out.println(notice);

        try {
            Notice saved = noticeRepository.save(notice);
        } catch(DataIntegrityViolationException e) {
            System.out.println("111");
            noticeService.modifyNotice(KKID, DATE, WRITESUBJECT, CONTENTS);
        }
        System.out.println(notice);

        return true;
    }

    @RequestMapping(value="/notice/{KKID}", produces="application/json;charset=UTF-8")
    public String getList(@PathVariable("KKID") Long KKID) {

        List<Notice> noticeList =  noticeService.getAllNotice(KKID);
        System.out.println(noticeList);

        Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy HH:mm:ss a").create();
        String jsonString = gson.toJson(noticeList);
        System.out.println(jsonString);

        return jsonString;
    }
}
