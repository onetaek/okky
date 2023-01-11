package com.example.okky.command;

import com.example.okky.daos.ArticleDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ArticleWriteCommand implements Command{
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        String title = req.getParameter("title");
        String boardId = req.getParameter("boardId");
        String userEmail = req.getParameter("email");
        String[] tags = req.getParameterValues("tags");

        ArticleDao adao = new ArticleDao();

        String content = req.getParameter("content");
        adao.insertArticle(boardId,title,content,userEmail,tags);

        req.setAttribute("boardId",boardId);
    }
}
