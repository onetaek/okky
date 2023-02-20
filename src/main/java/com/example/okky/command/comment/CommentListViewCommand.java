package com.example.okky.command.comment;

import com.example.okky.daos.CommentDao;
import com.example.okky.dtos.bbs.CommentDto;
import com.example.okky.frontcontroller.View;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class CommentListViewCommand implements com.example.okky.command.Command {
    CommentDao cdao = CommentDao.getInstance();

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ClassNotFoundException {
        resp.setCharacterEncoding("utf-8");
        int articleIndex = Integer.parseInt(req.getParameter("articleIndex"));

        List<CommentDto> commentList = cdao.selectCommentByArticleIndex(articleIndex);

        JSONArray commentArrayJson = new JSONArray();
        JSONObject responseJson = new JSONObject();

        for (CommentDto comment : commentList) {
            JSONObject innerJson = new JSONObject();
            innerJson.put("index", comment.getIndex());
            innerJson.put("group", comment.getGroup());
            innerJson.put("sequence", comment.getSequence());
            innerJson.put("level", comment.getLevel());
            innerJson.put("boardId", comment.getBoardId());
            innerJson.put("articleIndex", comment.getArticleIndex());
            innerJson.put("memberEmail", comment.getMemberEmail());
            innerJson.put("memberNickName", comment.getMemberNickName());
            innerJson.put("content", comment.getContent());
            innerJson.put("createdAt", comment.getCreatedAt());

            commentArrayJson.put(innerJson);
        }
        PrintWriter out =  resp.getWriter();
        responseJson.put("result", commentArrayJson);
        out.print(responseJson.toString());
        out.flush();
        out.close();

        return null;
    }
}
