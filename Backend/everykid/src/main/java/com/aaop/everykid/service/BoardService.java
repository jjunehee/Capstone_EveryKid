package com.aaop.everykid.service;

import com.aaop.everykid.dto.BoardDto;
import com.aaop.everykid.entity.Board;
import com.aaop.everykid.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
    @Autowired
    BoardRepository boardRepository;

    @Transactional
    public Page<Board> getBoardList(Long kID, Pageable pageable) {
        return boardRepository.findByKKID(kID, pageable);
    }

    @Transactional
<<<<<<< HEAD
    public Page<Board> getSearchList(String key, String kID, Pageable pageable) {
=======
    public Page<Board> getSearchList(String key, Long kID, Pageable pageable) {
>>>>>>> parent of ec69cd4 (수정)
        return boardRepository.searchBoard(key, kID, pageable);
    }

    @Transactional
<<<<<<< HEAD
    public void update(int b_ID, String contents) { //수정완료 눌렀을 때 실행
=======
    public void update(Long b_ID, String contents) { //수정완료 눌렀을 때 실행
>>>>>>> parent of ec69cd4 (수정)
        Board board = boardRepository.findByBKID(b_ID);
        board.modifyContents(contents); //수정된 content
    }

    @Transactional
<<<<<<< HEAD
=======
    public void updateHITS(Long bKID) {
        Board board = boardRepository.findByBKID(bKID);
        board.plusHITS();
    }

    @Transactional
>>>>>>> parent of ec69cd4 (수정)
    public int getMaxBKID() {
        return boardRepository.maxBKID();
    }
}
