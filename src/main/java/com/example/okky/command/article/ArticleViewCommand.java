package com.example.okky.command.article;

import com.example.okky.command.Command;
import com.example.okky.daos.ArticleDao;
import com.example.okky.daos.CommentDao;
import com.example.okky.dtos.bbs.ArticleDto;
import com.example.okky.dtos.bbs.BoardDto;
import com.example.okky.dtos.bbs.CommentDto;
import com.example.okky.dtos.bbs.TagOfArticleDto;
import com.example.okky.frontcontroller.View;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleViewCommand implements Command {
    ArticleDao adao = ArticleDao.getInstance();
    CommentDao cdao = CommentDao.getInstance();
    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, IOException {


        int articleIndex = Integer.parseInt(req.getParameter("articleIndex"));
        int boardId = Integer.parseInt(req.getParameter("boardId"));
        System.out.println("ArticleView Command boardId = " + boardId);
        ArticleDto articleDto = adao.selectArticleByIndex(articleIndex);
        BoardDto board = adao.selectBoardById(boardId);
        //where 조건절어 articleIndex와 boardId를 사용해서 게시글을 가져옴
        ArrayList<String> tagOfArticle = adao.selectTagsByArticleIdxAndBoardId(articleIndex,boardId);
        List<CommentDto> commentList = cdao.selectCommentByArticleIndex(articleIndex);


        req.setAttribute("articleDto",articleDto);
        req.setAttribute("boardId",boardId);
        req.setAttribute("board",board);
        req.setAttribute("tagOfArticle",tagOfArticle);
        req.setAttribute("commentList", commentList);

        return new View("/article/article");
    }
}
