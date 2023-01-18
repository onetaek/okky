package com.example.okky.command;

import com.example.okky.daos.ArticleDao;
import com.example.okky.dtos.bbs.TagDto;
import com.example.okky.frontcontroller.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ArticleWriteViewCommand implements Command{
    ArticleDao adao = ArticleDao.getInstance();
    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        String boardId = req.getParameter("boardId");

        List<TagDto> tagDtoList = adao.selectTags();
        req.setAttribute("tags",tagDtoList);
        req.setAttribute("boardId",boardId);

        return new View("/articles/write.jsp");
    }
}
