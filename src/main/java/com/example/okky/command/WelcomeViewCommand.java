package com.example.okky.command;

import com.example.okky.daos.ArticleDao;
import com.example.okky.dtos.bbs.ArticleDto;
import com.example.okky.dtos.bbs.BoardDto;
import com.example.okky.dtos.bbs.TagOfArticleDto;
import com.example.okky.frontcontroller.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class WelcomeViewCommand implements Command{
    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        ArticleDao dao = new ArticleDao();
        ArrayList<ArticleDto> articleDtoArrayList = dao.selectAllArticle();
        ArrayList<BoardDto> boardDtoArrayList = dao.selectAllBoard();
        ArrayList<TagOfArticleDto> tagOfArticleDtoArraylist = dao.selectAllTag();

        req.setAttribute("articleList",articleDtoArrayList);
        req.setAttribute("boardList",boardDtoArrayList);
        req.setAttribute("tagList",tagOfArticleDtoArraylist);

        return new View("/main/welcome.jsp");
    }
}
