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
<<<<<<< HEAD
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
=======
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
>>>>>>> parent of ec69cd4 (수정)

@RestController
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    BoardService boardService;

    // 유저가 소속된 유치원의 글들을 불러옴
    @RequestMapping("/board/{kID}")
<<<<<<< HEAD
    public String getList(@PageableDefault(size = 10, sort = "BKID", direction = Sort.Direction.ASC) Pageable pageable,
=======
    public String getList(@PageableDefault(size = 10, sort = "BKID", direction = Sort.Direction.DESC) Pageable pageable,
>>>>>>> parent of ec69cd4 (수정)
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

    @RequestMapping ("/write/{kID}/{tID}/{pID}/{writeSUBJECT}/{contents}")
    public Boolean writeContent(@PathVariable("kID") Long kID, @PathVariable("tID") String tID, @PathVariable("pID") String pID,
                                @PathVariable("writeSUBJECT") String writeSUBJECT, @PathVariable("contents") String contents) {

        BoardDto boardDto = null;
<<<<<<< HEAD
        System.out.println("111");
        if(tID.equals("null")) {
            boardDto = new BoardDto(null, kID, null, pID, null, writeSUBJECT, contents, 0);
=======
        Date date = new Date();
        if(tID.equals("null")) {
            boardDto = new BoardDto(null, kID, null, pID, date, writeSUBJECT, contents, 0);
        } else {
            boardDto = new BoardDto(null, kID, tID, null, date, writeSUBJECT, contents, 0);
>>>>>>> parent of ec69cd4 (수정)
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
<<<<<<< HEAD
    public String deleteContent(int b_ID) {

        Board board = boardRepository.findByBKID(b_ID);
=======
    public String deleteContent(Long bKID) {

        Board board = boardRepository.findByBKID(bKID);
>>>>>>> parent of ec69cd4 (수정)

        boardRepository.delete(board);

        return "";
    }

<<<<<<< HEAD
    //수정 완료(아무나 수정하지 못하도록 수정)
    @RequestMapping("/modify")
    public String updateContent(int b_ID, String contents) {

        boardService.update(b_ID, contents);
=======
    @RequestMapping("/modify")
    public String updateContent(Long bKID, String contents) {

        boardService.update(bKID, contents);
>>>>>>> parent of ec69cd4 (수정)

        return "";
    }

<<<<<<< HEAD
    @RequestMapping("/search/{kID}/{key}")
    public String searchContent(@PageableDefault(size = 10, sort = "BKID", direction = Sort.Direction.ASC) Pageable pageable,
                                @PathVariable("key") String key,@PathVariable("kID") String kID) {

        if(key.length() < 2)
            return "";

        Page<Board> board = boardService.getSearchList(key, kID, pageable);
=======
    @RequestMapping("/search/{kID}")
    public String searchContent(@PageableDefault(size = 10, sort = "B_KID", direction = Sort.Direction.DESC) Pageable pageable,
                                @RequestParam("key") String key,@PathVariable("kID") Long kID) {

        if(key.length() < 2 && key.length() > 0 )
            return "";

        Page<Board> board;
        System.out.println("+++" + key.length() + "+++" + key + "+++");
        board = boardService.getSearchList(key, kID, pageable);
>>>>>>> parent of ec69cd4 (수정)

        System.out.println(board.getTotalElements());
        System.out.println(board.getTotalPages());
        System.out.println(board.getNumber()); //page는 0부터 시작
        System.out.println(board.getSize());
        System.out.println(board.getContent());

        BoardList boardList = new BoardList(board.getContent(), (int)board.getTotalElements(), board.getSize(), board.getNumber(), board.getTotalPages());

<<<<<<< HEAD
        Gson gson = new GsonBuilder().setDateFormat("MM, dd, yyyy HH:mm:ss a").create();
=======
        Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy HH:mm:ss a").create();
>>>>>>> parent of ec69cd4 (수정)
        String jsonString = gson.toJson(boardList);
        System.out.println(jsonString);

        return jsonString;
    }
<<<<<<< HEAD
=======

    @RequestMapping("/select/{bKID}")
    public void selectBoard(@PathVariable("bKID") Long bKID) {

        System.out.println(bKID);
        boardService.updateHITS(bKID);

        return;
    }
>>>>>>> parent of ec69cd4 (수정)
}
