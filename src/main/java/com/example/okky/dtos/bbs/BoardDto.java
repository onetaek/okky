package com.example.okky.dtos.bbs;

import java.util.Objects;

public class BoardDto {
    private int id;
    private String text;

    public BoardDto() {
    }

    public BoardDto(int id, String text) {
        this.id = id;
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardDto boardDto = (BoardDto) o;
        return Objects.equals(id, boardDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
