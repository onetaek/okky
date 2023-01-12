package com.example.okky.command;

import com.example.okky.daos.ArticleDao;
import com.example.okky.dtos.bbs.TagDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ArticleWriteViewCommand implements Command{
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        String boardId = req.getParameter("boardId");
        ArticleDao adao = new ArticleDao();

        List<TagDto> tagDtoList = adao.selectTags();
        req.setAttribute("tags",tagDtoList);
        req.setAttribute("boardId",boardId);
    }
}
