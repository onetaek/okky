package com.example.okky.dtos.bbs;

import java.util.Date;
import java.util.Objects;

public class ArticleDto {
    private int index;
    private String boardId;
    private String tag;
    private String userEmail;
    private String title;
    private String content;
    private long view;
    private Date createdAt;

    public ArticleDto() {
    }

    public ArticleDto(int index, String boardId, String tag, String userEmail, String title, String content, long view, Date createdAt) {
        this.index = index;
        this.boardId = boardId;
        this.tag = tag;
        this.userEmail = userEmail;
        this.title = title;
        this.content = content;
        this.view = view;
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleDto that = (ArticleDto) o;
        return index == that.index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(index);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getView() {
        return view;
    }

    public void setView(long view) {
        this.view = view;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
