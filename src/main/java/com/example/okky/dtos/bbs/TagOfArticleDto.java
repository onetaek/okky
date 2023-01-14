package com.example.okky.dtos.bbs;

import java.util.Date;

public class TagOfArticleDto {
    private int index;
    private int articleIdx;
    private String tagValue;
    private Date writtenAt;
    public TagOfArticleDto(){}
    public TagOfArticleDto(int index, int articleIdx, String tagValue, Date writtenAt) {
        this.index = index;
        this.articleIdx = articleIdx;
        this.tagValue = tagValue;
        this.writtenAt = writtenAt;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getArticleIdx() {
        return articleIdx;
    }

    public void setArticleIdx(int articleIdx) {
        this.articleIdx = articleIdx;
    }

    public String getTagValue() {
        return tagValue;
    }

    public void setTagValue(String tagValue) {
        this.tagValue = tagValue;
    }

    public Date getWrittenAt() {
        return writtenAt;
    }

    public void setWrittenAt(Date writtenAt) {
        this.writtenAt = writtenAt;
    }
}
