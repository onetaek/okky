package com.example.okky.dtos.bbs;

import java.util.Date;
import java.util.Objects;

public class CommentDto {
   private int index;
   private int group;
   private int sequence;
   private int level;
   private String boardId;
   private int articleIndex;
   private String userEmail;
   private String userNickName;
   private String content;
   private Date createdAt;


    public CommentDto() {
    }

    public CommentDto(int index, int group, int sequence, int level, String boardId, int articleIndex, String userEmail, String userNickName, String content, Date createdAt) {
        this.index = index;
        this.group = group;
        this.sequence = sequence;
        this.level = level;
        this.boardId = boardId;
        this.articleIndex = articleIndex;
        this.userEmail = userEmail;
        this.userNickName = userNickName;
        this.content = content;
        this.createdAt = createdAt;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
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

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
