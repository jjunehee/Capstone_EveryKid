package com.aaop.everykid.service;

import com.aaop.everykid.dto.BoardDto;
import com.aaop.everykid.entity.Board;
import com.aaop.everykid.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class BoardService {
    @Autowired
    BoardRepository boardRepository;

    @Transactional
    public Page<Board> getBoardList(String kID, Pageable pageable) {
        return boardRepository.findBykID(kID, pageable);
    }

    @Transactional
    public void update(int b_ID, String contents) { //수정완료 눌렀을 때 실행
        Board board = boardRepository.findBybID(b_ID);
        board.modifyContents(contents); //수정된 content
    }
}

