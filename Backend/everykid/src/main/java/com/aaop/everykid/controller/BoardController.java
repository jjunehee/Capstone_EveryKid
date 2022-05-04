package com.aaop.everykid.controller;

import com.aaop.everykid.dto.BoardDto;
import com.aaop.everykid.entity.Board;
import com.aaop.everykid.repository.BoardRepository;
import com.aaop.everykid.service.BoardService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    BoardService boardService;

    // 유저가 소속된 유치원의 글들을 불러옴
    @RequestMapping("/board/{kID}")
    public String getList(@PageableDefault(size = 10, sort = "bID", direction = Sort.Direction.ASC) Pageable pageable,
                          @PathVariable("kID") String kID) {

        Page<Board> board =  boardService.getBoardList(kID, pageable);

        System.out.println(board.getTotalElements());
        System.out.println(board.getTotalPages());
        System.out.println(board.getNumber()); //page는 0부터 시작
        System.out.println(board.getSize());
        System.out.println(board.getContent());
        System.out.println(board);

        Gson gson = new Gson();
        String jsonString = gson.toJson(board);
        System.out.println(jsonString);

        return jsonString;
    }

    //글쓰기
    @RequestMapping ("/write")
    public String writeContent(BoardDto boardDto) {
        System.out.println(boardDto.toString());

        // 1. Dto -> Entity
        Board board = boardDto.toEntity();
        System.out.println(board.toString());

        // 2. Repository에게 Entity를 DB에 저장하게 함
        Board saved = boardRepository.save(board);
        System.out.println(saved.toString());

        return "";
    }

    //글삭제(아무나 삭제하지 못하도록 수정)
    @RequestMapping("/delete")
    public String deleteContent(int b_ID) {

        Board board = boardRepository.findBybID(b_ID);

        boardRepository.delete(board);

        return "";
    }

    //수정 완료(아무나 수정하지 못하도록 수정)
    @RequestMapping("/modify")
    public String updateContent(int b_ID, String contents) {

        boardService.update(b_ID, contents);

        return "";
    }

    @RequestMapping("/search/{kID}/{key}")
    public String searchContent(@PageableDefault(size = 10, sort = "b_ID", direction = Sort.Direction.ASC) Pageable pageable,
                                @PathVariable("key") String key,@PathVariable("kID") String kID) {

        if(key.length() < 2)
            return "";

        Page<Board> board = boardRepository.searchBoard(key, kID, pageable);

        System.out.println(board.getTotalElements());
        System.out.println(board.getTotalPages());
        System.out.println(board.getNumber()); //page는 0부터 시작
        System.out.println(board.getSize());
        System.out.println(board.getContent());

        Gson gson = new Gson();
        String jsonString = gson.toJson(board);
        System.out.println(jsonString);

        return jsonString;
    }
}
