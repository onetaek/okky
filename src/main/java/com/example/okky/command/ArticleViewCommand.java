package com.example.okky.command;

import com.example.okky.daos.ArticleDao;
import com.example.okky.daos.BoardDao;
import com.example.okky.dtos.bbs.ArticleDto;
import com.example.okky.dtos.bbs.BoardDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class ArticleViewCommand implements Command{

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        String boardId = req.getParameter("boardId");
        System.out.println("ArticleViewCommand에서 받은 boardId: "+boardId);
        ArticleDao adao = new ArticleDao();
        ArrayList<ArticleDto> articleDtoList = adao.selectArticleByBoardId(boardId);

        BoardDao bdao = new BoardDao();
        BoardDto boardDto = bdao.selectBoardById(boardId);

        req.setAttribute("articleDtoList",articleDtoList);
        req.setAttribute("boardDto",boardDto);
    }
}
