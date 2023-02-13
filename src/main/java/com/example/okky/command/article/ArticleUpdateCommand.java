package com.example.okky.command.article;

import com.example.okky.command.Command;
import com.example.okky.daos.ArticleDao;
import com.example.okky.frontcontroller.View;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ArticleUpdateCommand implements Command {
    ArticleDao adao = ArticleDao.getInstance();


    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException {
        String boardId = req.getParameter("boardId");
        int articleIndex =Integer.parseInt(req.getParameter("articleIndex"));
        String userEmail = req.getParameter("userEmail");
        String title = req.getParameter("title");
        String[] tags = req.getParameterValues("tags");
        String content = req.getParameter("content");



        adao.updateArticle(articleIndex,userEmail, title, content, tags);

        req.setAttribute("boardId",boardId);
        req.setAttribute("articleIndex",articleIndex);
        return new View("/articleView.do");
    }
}
