package com.example.okky.command;

import com.example.okky.daos.ArticleDao;
import com.example.okky.frontcontroller.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ArticleUpdateCommand implements Command {
    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        String boardId = req.getParameter("boardId");
        int articleIndex =Integer.parseInt(req.getParameter("articleIndex"));
        String userEmail = req.getParameter("userEmail");
        String title = req.getParameter("title");
        String[] tags = req.getParameterValues("tags");
        String content = req.getParameter("content");


        ArticleDao adao = new ArticleDao();
        adao.updateArticle(articleIndex,userEmail, title, content, tags);

        req.setAttribute("boardId",boardId);
        req.setAttribute("articleIndex",articleIndex);
        return new View("/articleView.do");
    }
}
