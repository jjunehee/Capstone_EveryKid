package com.aaop.everykid.controller;

import com.aaop.everykid.dto.BoardDto;
import com.aaop.everykid.entity.Board;
import com.aaop.everykid.entity.Parent;
import com.aaop.everykid.repository.BoardRepository;
import com.aaop.everykid.repository.ParentRepository;
import com.aaop.everykid.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    BoardService boardService;

    // 유저가 소속된 유치원의 글들을 불러옴
    @GetMapping("/board")
    public String getList(@PageableDefault(size = 10, sort = "bID", direction = Sort.Direction.ASC) Pageable pageable,
                        Model model, String kID) {
        model.addAttribute("boardList", boardService.getBoardList(kID, pageable));

        Page<Board> board =  (Page<Board>)model.getAttribute("boardList");

        System.out.println(board.getTotalElements());
        System.out.println(board.getTotalPages());
        System.out.println(board.getNumber()); //page는 0부터 시작
        System.out.println(board.getSize());
        System.out.println(board.getContent());

        return "";
    }

    //글쓰기
    @GetMapping("/write")
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
    @GetMapping("/delete")
    public String deleteContent(int b_ID) {

        Board board = boardRepository.findBybID(b_ID);

        boardRepository.delete(board);

        return "";
    }

    //수정 완료(아무나 수정하지 못하도록 수정)
    @GetMapping("/modify")
    public String updateContent(int b_ID, String contents) {

        boardService.update(b_ID, contents);

        return "";
    }

    @GetMapping("/search")
    public String searchContent(@PageableDefault(size = 10, sort = "b_ID", direction = Sort.Direction.ASC) Pageable pageable,
                                Model model, String key, String kID) {

        if(key.length() < 2)
            return "";

        model.addAttribute("boardList", boardRepository.searchBoard(key, kID, pageable));

        Page<Board> board = (Page<Board>)model.getAttribute("boardList");

        System.out.println(board.getTotalElements());
        System.out.println(board.getTotalPages());
        System.out.println(board.getNumber()); //page는 0부터 시작
        System.out.println(board.getSize());
        System.out.println(board.getContent());

        return "";
    }
}
