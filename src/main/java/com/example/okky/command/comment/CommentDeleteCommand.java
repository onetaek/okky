package com.example.okky.command.comment;

import com.example.okky.command.Command;
import com.example.okky.daos.CommentDao;
import com.example.okky.frontcontroller.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CommentDeleteCommand implements Command {
    CommentDao cdao = CommentDao.getInstance();

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ClassNotFoundException {


        int index = Integer.parseInt(req.getParameter("index"));
        System.out.println("index = " + index);
//        cdao.commentDeleteByIndex(index);


        return null;
    }
}
