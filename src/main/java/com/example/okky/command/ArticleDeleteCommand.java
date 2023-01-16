package com.example.okky.command;

import com.example.okky.daos.ArticleDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ArticleDeleteCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        int articleIndex = Integer.parseInt(req.getParameter("articleIndex"));
        String boardId = req.getParameter("boardId");

        ArticleDao adao = new ArticleDao();
        adao.deleteArticle(articleIndex);

        req.setAttribute("boardId",boardId);
    }
}
