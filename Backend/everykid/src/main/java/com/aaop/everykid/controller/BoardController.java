package com.aaop.everykid.controller;

import com.aaop.everykid.dto.BoardDto;
import com.aaop.everykid.entity.Board;
import com.aaop.everykid.entity.BoardList;
import com.aaop.everykid.repository.BoardRepository;
import com.aaop.everykid.service.BoardService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import java.text.SimpleDateFormat;
@RestController
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    BoardService boardService;

    // 유저가 소속된 유치원의 글들을 불러옴
    @RequestMapping(value="/board/{kID}", produces="application/json;charset=UTF-8")
    public String getList(@PageableDefault(size = 10, sort = "BKID", direction = Sort.Direction.DESC) Pageable pageable,
                          @PathVariable("kID") Long kID) {

        Page<Board> board =  boardService.getBoardList(kID, pageable);

        System.out.println(board.getTotalElements());
        System.out.println(board.getTotalPages());
        System.out.println(board.getNumber()); //page는 0부터 시작
        System.out.println(board.getSize());
        System.out.println(board.getContent());
        System.out.println(board);

        BoardList boardList = new BoardList(board.getContent(), (int)board.getTotalElements(), board.getSize(), board.getNumber(), board.getTotalPages());

        Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy HH:mm:ss a").create();
        String jsonString = gson.toJson(boardList);
        System.out.println(jsonString);

        return jsonString;
    }

    @RequestMapping (value="/write/{kID}/{tID}/{pID}/{writeSUBJECT}/{contents}", produces="application/json;charset=UTF-8")
    public Boolean writeContent(@PathVariable("kID") Long kID, @PathVariable("tID") String tID, @PathVariable("pID") String pID,
                                @PathVariable("writeSUBJECT") String writeSUBJECT, @PathVariable("contents") String contents) {

        BoardDto boardDto = null;
        Date date = new Date();
        if(tID.equals("null")) {
            boardDto = new BoardDto(null, kID, null, pID, date, writeSUBJECT, contents, 0);
        } else {
            boardDto = new BoardDto(null, kID, tID, null, date, writeSUBJECT, contents, 0);

        }
        System.out.println(boardDto);

        // 1. Dto -> Entity
        Board board = boardDto.toEntity();
        //System.out.println(board.toString());

        // 2. Repository에게 Entity를 DB에 저장하게 함
        Board saved = boardRepository.save(board);
        //System.out.println(saved.toString());

        return true;
    }

    //글삭제
    @RequestMapping("/delete")
    public String deleteContent(@RequestParam("bKID") Long bKID) {

        Board board = boardRepository.findByBKID(bKID);

        boardRepository.delete(board);

        return "";
    }

    @RequestMapping("/modify")
    public String updateContent(@RequestParam("bKID") Long bKID, @RequestParam("subject") String subject, @RequestParam("contents") String contents) {

        boardService.update(bKID, subject, contents);

        return "";
    }


    @RequestMapping(value="/search/{kID}", produces="application/json;charset=UTF-8")
    public String searchContent(@PageableDefault(size = 10, sort = "B_KID", direction = Sort.Direction.DESC) Pageable pageable,
                                @RequestParam("key") String key, @RequestParam("SPINNER") String spinner, @PathVariable("kID") Long kID) {

        if(key.length() < 2 && key.length() > 0 )
            return "";

        Page<Board> board = null;
        System.out.println("+++" + key.length() + "+++" + key + "+++");
        switch(spinner) {
            case "제목": board = boardService.getSearchSubject(key, kID, pageable);
                break;
            case "내용": board = boardService.getSearchContents(key, kID, pageable);
                break;
            case "작성자": board = boardService.getSearchWriter(key, kID, pageable);
                break;
        }

        if(board == null)
            return "";
        BoardList boardList = new BoardList(board.getContent(), (int)board.getTotalElements(), board.getSize(), board.getNumber(), board.getTotalPages());

        Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy HH:mm:ss a").create();
        String jsonString = gson.toJson(boardList);
        System.out.println(jsonString);

        return jsonString;
    }

    @RequestMapping("/select/{bKID}")
    public void selectBoard(@PathVariable("bKID") Long bKID) {

        System.out.println(bKID);
        boardService.updateHITS(bKID);

        return;
    }
}
