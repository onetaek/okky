package com.example.okky.command.article;

import com.example.okky.command.Command;
import com.example.okky.daos.ArticleDao;
import com.example.okky.frontcontroller.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ArticleDeleteCommand implements Command {
    ArticleDao adao = ArticleDao.getInstance();

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException {
        int articleIndex = Integer.parseInt(req.getParameter("articleIndex"));
        String boardId = req.getParameter("boardId");
        adao.deleteArticle(articleIndex);
        req.setAttribute("boardId",boardId);
        return new View("/articleListView.do");
    }
}
