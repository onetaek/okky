package com.example.okky.command;

import com.example.okky.daos.ArticleDao;
import com.example.okky.daos.BoardDao;
import com.example.okky.dtos.bbs.ArticleDto;
import com.example.okky.dtos.bbs.BoardDto;
import com.example.okky.dtos.bbs.TagDto;
import com.example.okky.dtos.bbs.TagOfArticleDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class ArticleListViewCommand implements Command{

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        String boardId = req.getParameter("boardId");
        System.out.println("ArticleViewCommand에서 받은 boardId: "+boardId);

        BoardDao bdao = new BoardDao();
        //어떤 게시판 인지(ex 공지사항, 커뮤니티...)
        BoardDto boardDto = bdao.selectBoardById(boardId);

        ArticleDao adao = new ArticleDao();
        //게시글 가져오기(ex
        ArrayList<ArticleDto> articleDtoList = adao.selectArticleByBoardId(boardId);

         ArrayList<TagOfArticleDto> tagOfArticleDtos = adao.selectTagsByBoardId(boardId);

        req.setAttribute("tagsList",tagOfArticleDtos);
        req.setAttribute("articleDtoList",articleDtoList);
        req.setAttribute("boardDto",boardDto);
    }
}
