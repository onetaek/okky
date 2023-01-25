package com.example.okky.utils;

import java.util.HashMap;
import java.util.Map;

public class Pagination {
    private int pageNum;//현제 페이지 -> 매개변수로 받음
    private int pageSize = 10;//한 패이지에 보이는 게시글의 갯수
    private int totalCount;//전체 페이지의 갯수
    private int startRow;//현재 페이지의 게시글 시작번호-> sql문에 limit에 사용
    private int number;//페이지 내에서 보여질 게시글의 넘버링을 위한 변수
    private int pageBlock = 10;//넘버링 할 하나의 묶음의 페이지 갯수 -> 사용자가 지정
    private int pageCount;//묶음의 갯수
    private int startPage;//시작 페이지
    private int endPage;//종료 페이지
    private boolean prev;//이전을 표시할지 말지
    private boolean next;//다음을 표시할지 말지
    public Pagination(String pageNum, int totalCount){
        this.pageNum = (pageNum == null) ? 1 : Integer.parseInt(pageNum);
        this.totalCount = totalCount;
        this.startRow = (this.pageNum - 1) * pageSize + 1;
        this.number = totalCount - (this.pageNum -1) * pageSize;
        this.pageCount = totalCount / pageSize + (totalCount % pageSize == 0 ? 0 : 1);
        if(this.pageNum % pageBlock != 0){
            this.startPage = (this.pageNum / pageBlock )*pageBlock +1;
        }else{
            this.startPage = ((this.pageNum / pageBlock) - 1) * pageBlock + 1;
        }
        this.endPage = startPage + pageBlock - 1;
        this.prev = (startPage != 1);
        this.next = (endPage <= pageCount);
        if(this.endPage > this.pageCount){
            //endPage가 총 페이지 수 보다 많을 경우 endPage를 마지막 페이지 번호로 맞추고 next버튼을 비활성화 한다.
            this.endPage = this.pageCount;
            this.next = false;
        }
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPageBlock() {
        return pageBlock;
    }

    public void setPageBlock(int pageBlock) {
        this.pageBlock = pageBlock;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public boolean isPrev() {
        return prev;
    }

    public void setPrev(boolean prev) {
        this.prev = prev;
    }

    public boolean isNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Pagination{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", totalCount=" + totalCount +
                ", startRow=" + startRow +
                ", number=" + number +
                ", pageBlock=" + pageBlock +
                ", pageCount=" + pageCount +
                ", startPage=" + startPage +
                ", endPage=" + endPage +
                ", prev=" + prev +
                ", next=" + next +
                '}';
    }
}
