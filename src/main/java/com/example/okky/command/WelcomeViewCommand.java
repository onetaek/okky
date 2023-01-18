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
    ArticleDao adao = ArticleDao.getInstance();
    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        ArrayList<ArticleDto> articleDtoArrayList = adao.selectAllArticle();
        ArrayList<BoardDto> boardDtoArrayList = adao.selectAllBoard();
        ArrayList<TagOfArticleDto> tagOfArticleDtoArraylist = adao.selectAllTag();

        req.setAttribute("articleList",articleDtoArrayList);
        req.setAttribute("boardList",boardDtoArrayList);
        req.setAttribute("tagList",tagOfArticleDtoArraylist);

        return new View("/main/welcome.jsp");
    }
}
