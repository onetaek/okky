package com.example.okky.command.article;

import com.example.okky.command.Command;
import com.example.okky.daos.ArticleDao;
import com.example.okky.dtos.bbs.ArticleDto;
import com.example.okky.dtos.bbs.BoardDto;
import com.example.okky.dtos.bbs.TagOfArticleDto;
import com.example.okky.frontcontroller.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class WelcomeViewCommand implements Command {
    ArticleDao adao = ArticleDao.getInstance();
    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        List<ArticleDto> articleDtoArrayList = adao.selectAllArticle();
        List<BoardDto> boardDtoArrayList = adao.selectAllBoard();
        List<TagOfArticleDto> tagOfArticleDtoArraylist = adao.selectAllTag();

        req.setAttribute("articleList",articleDtoArrayList);
        req.setAttribute("boardList",boardDtoArrayList);
        req.setAttribute("tagList",tagOfArticleDtoArraylist);

        return new View("/main/welcome.jsp");
    }
}
