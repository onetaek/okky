package com.example.okky.command.article;

import com.example.okky.command.Command;
import com.example.okky.daos.ArticleDao;
import com.example.okky.frontcontroller.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ArticleWriteCommand implements Command {
    ArticleDao adao = ArticleDao.getInstance();
    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException {
        String title = req.getParameter("title");
        int boardId = Integer.parseInt(req.getParameter("boardId"));
        System.out.println("boardId!boardId :" + boardId);
        String memberEmail = req.getParameter("email");
        String[] tags = req.getParameterValues("tags");
        String content = req.getParameter("content");
        adao.insertArticle(boardId,title,content,memberEmail,tags);

        return new View("redirect:/articleListView.do?boardId="+boardId);

    }
}
