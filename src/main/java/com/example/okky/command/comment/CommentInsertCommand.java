package com.example.okky.command.comment;

import com.example.okky.command.Command;
import com.example.okky.daos.CommentDao;
import com.example.okky.dtos.bbs.CommentDto;
import com.example.okky.frontcontroller.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class CommentInsertCommand implements Command {

    CommentDao cdao = CommentDao.getInstance();

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ClassNotFoundException {
        String content = req.getParameter("content");
        int boardId = Integer.parseInt(req.getParameter("boardId"));
        int articleIndex = Integer.parseInt(req.getParameter("articleIndex"));
        String userEmail = req.getParameter("userEmail");
        String userNickName = req.getParameter("userNickName");

        int count = cdao.commentInsert(boardId, articleIndex, userEmail, userNickName, content);

//        List<CommentDto> commentList = cdao.selectCommentByArticleIndex(articleIndex);
//        req.setAttribute("commentList", commentList);

        PrintWriter writer = resp.getWriter();
        if(count == 1){
            writer.print("success");
        }else {
            writer.print("failure");
        }
        writer.flush();
        writer.close();
        return null;
//        System.out.println(boardId+articleIndex+userEmail+userNickName+content);
//
//        dao.commentInsert(boardId,articleIndex,userEmail,userNickName,content);
//
//        return new View("redirect:/articleView.do?articleIndex="+articleIndex+"&boardId="+boardId);
    }
}
