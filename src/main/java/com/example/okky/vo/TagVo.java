package com.example.okky.vo;

import com.example.okky.dtos.bbs.TagOfArticleDto;

public class TagVo extends TagOfArticleDto {
    private String  tagValue;
    private int tagRankCount;


    public TagVo() {
    }

    public TagVo(String tagValue, int tagRankCount) {
        this.tagValue = tagValue;
        this.tagRankCount = tagRankCount;
    }

    public String getTagValue() {
        return tagValue;
    }

    public void setTagValue(String tagValue) {
        this.tagValue = tagValue;
    }

    public int getTagRankCount() {
        return tagRankCount;
    }

    public void setTagRankCount(int tagRankCount) {
        this.tagRankCount = tagRankCount;
    }
}
