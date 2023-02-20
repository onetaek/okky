package com.example.okky.dtos.bbs;

import java.util.Date;
import java.util.Objects;

public class ArticleLikeDto {
    private String memberEmail;
    private String articleIndex;
    private Date createdAt;

    public ArticleLikeDto() {
    }

    public ArticleLikeDto(String memberEmail, String articleIndex, Date createdAt) {
        this.memberEmail = memberEmail;
        this.articleIndex = articleIndex;
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleLikeDto that = (ArticleLikeDto) o;
        return Objects.equals(memberEmail, that.memberEmail) && Objects.equals(articleIndex, that.articleIndex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberEmail, articleIndex);
    }

    public String getmemberEmail() {
        return memberEmail;
    }

    public void setmemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
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
