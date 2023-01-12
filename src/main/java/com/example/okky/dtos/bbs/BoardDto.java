package com.example.okky.dtos.bbs;

import java.util.Objects;

public class BoardDto {
    private String value;
    private String text;

    public BoardDto() {
    }

    public BoardDto(String value, String text) {
        this.value = value;
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardDto boardDto = (BoardDto) o;
        return Objects.equals(value, boardDto.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
    
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
