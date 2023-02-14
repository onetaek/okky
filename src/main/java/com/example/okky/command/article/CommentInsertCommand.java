package com.example.okky.command.article;

import com.example.okky.command.Command;
import com.example.okky.daos.CommentDao;
import com.example.okky.frontcontroller.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CommentInsertCommand implements Command {

    CommentDao dao = CommentDao.getInstance();

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ClassNotFoundException {
        String content = req.getParameter("content");
        String boardId = req.getParameter("boardId");
        int articleIndex = Integer.parseInt(req.getParameter("articleIndex"));
        String userEmail = req.getParameter("userEmail");
        String userNickName = req.getParameter("userNickName");

        System.out.println(boardId+articleIndex+userEmail+userNickName+content);

        dao.commentInsert(boardId,articleIndex,userEmail,userNickName,content);

        return new View("redirect:/articleView.do?articleIndex="+articleIndex+"&boardId="+boardId);
    }
}
