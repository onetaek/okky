package com.example.okky.dtos.bbs;

import java.util.Date;
import java.util.Objects;

public class ArticleLikeDto {
    private String userEmail;
    private String articleIndex;
    private Date createdAt;

    public ArticleLikeDto() {
    }

    public ArticleLikeDto(String userEmail, String articleIndex, Date createdAt) {
        this.userEmail = userEmail;
        this.articleIndex = articleIndex;
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleLikeDto that = (ArticleLikeDto) o;
        return Objects.equals(userEmail, that.userEmail) && Objects.equals(articleIndex, that.articleIndex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userEmail, articleIndex);
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getArticleIndex() {
        return articleIndex;
    }

    public void setArticleIndex(String articleIndex) {
        this.articleIndex = articleIndex;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
