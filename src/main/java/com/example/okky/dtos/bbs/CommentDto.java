package com.example.okky.dtos.bbs;

import java.util.Date;
import java.util.Objects;

public class CommentDto {
    private int index;
    private int commentIndex;
    private int articleIndex;
    private String userEmail;
    private String contact;
    private Date createdAt;

    public CommentDto() {
    }

    public CommentDto(int index, int commentIndex, int articleIndex, String userEmail, String contact, Date createdAt) {
        this.index = index;
        this.commentIndex = commentIndex;
        this.articleIndex = articleIndex;
        this.userEmail = userEmail;
        this.contact = contact;
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentDto that = (CommentDto) o;
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

    public int getCommentIndex() {
        return commentIndex;
    }

    public void setCommentIndex(int commentIndex) {
        this.commentIndex = commentIndex;
    }

    public int getArticleIndex() {
        return articleIndex;
    }

    public void setArticleIndex(int articleIndex) {
        this.articleIndex = articleIndex;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
