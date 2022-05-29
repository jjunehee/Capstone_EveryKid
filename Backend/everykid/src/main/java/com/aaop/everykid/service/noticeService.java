package com.aaop.everykid.service;

import com.aaop.everykid.entity.Board;
import com.aaop.everykid.entity.Notice;
import com.aaop.everykid.repository.BoardRepository;
import com.aaop.everykid.repository.noticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class noticeService {
    @Autowired
    noticeRepository noticeRepository;
    SimpleDateFormat dtFormat;
    String formatDate;

    public Notice getNotice(Long KKID, Date date) {
        dtFormat = new SimpleDateFormat("yyyy-MM-dd");
        formatDate = dtFormat.format(date);
        return noticeRepository.findByKKIDANDwriteDate(KKID, formatDate);
    }

    public List<Notice> getAllNotice(Long KKID) {
        return noticeRepository.findByKKID(KKID);
    }

    public boolean modifyNotice(Long KKID, Date DATE, String WRITESUBJECT, String CONTENTS) {
        System.out.println(DATE);
        dtFormat = new SimpleDateFormat("yyyy-MM-dd");
        formatDate = dtFormat.format(DATE);
        System.out.println(formatDate);
        Notice notice = noticeRepository.findByKKIDANDwriteDate(KKID, formatDate);
        notice.setWriteSubject(WRITESUBJECT);
        notice.setContents(CONTENTS);
        return true;
    }
}
