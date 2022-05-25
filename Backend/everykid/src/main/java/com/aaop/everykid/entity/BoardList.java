package com.aaop.everykid.entity;

import java.util.List;

public class BoardList {

    List<Board> boardList;
    int totalElement;
    int pageSize;
    int currentPage;
    int totalPage;

    public BoardList(List<Board> boardList, int totalElement, int pageSize, int currentPage, int totalPage) {
        this.boardList = boardList;
        this.totalElement = totalElement;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.totalPage = (int) Math.ceil(totalElement/(double)pageSize);
    }

    public List<Board> getBoardList() {
        return boardList;
    }

    public void setBoardList(List<Board> boardList) {
        this.boardList = boardList;
    }

    public int getTotalElement() {
        return totalElement;
    }

    public void setTotalElement(int totalElement) {
        this.totalElement = totalElement;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
