package com.example.okky.command.article;

import com.example.okky.command.Command;
import com.example.okky.daos.ArticleDao;
import com.example.okky.dtos.bbs.ArticleDto;
import com.example.okky.dtos.bbs.TagOfArticleDto;
import com.example.okky.frontcontroller.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class ArticleViewCommand implements Command {
    ArticleDao adao = ArticleDao.getInstance();
    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        int articleIndex = Integer.parseInt(req.getParameter("articleIndex"));
        String boardId = req.getParameter("boardId");
        System.out.println("ArticleView Command boardId = " + boardId);
        ArticleDto articleDto = adao.selectArticleByIndex(articleIndex);

        //where 조건절어 articleIndex와 boardId를 사용해서 게시글을 가져옴
        ArrayList<String> tagOfArticle = adao.selectTagsByArticleIdxAndBoardId(articleIndex,boardId);

        req.setAttribute("articleDto",articleDto);
        req.setAttribute("boardId",boardId);
        req.setAttribute("tagOfArticle",tagOfArticle);

        return new View("/articles/article.jsp");
    }
}
