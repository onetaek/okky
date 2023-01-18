package com.example.okky.command;

import com.example.okky.daos.ArticleDao;
import com.example.okky.dtos.bbs.ArticleDto;
import com.example.okky.dtos.bbs.TagDto;
import com.example.okky.frontcontroller.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ArticleUpdateViewCommand implements Command{
    ArticleDao adao = ArticleDao.getInstance();
    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        //request값 받기
        System.out.println("articleIndex???????: "+req.getParameter("articleIndex"));
        int articleIndex = Integer.parseInt(req.getParameter("articleIndex"));
        String boardId = req.getParameter("boardId");



        //DAO메서드 실행
        ArticleDto articleDto = adao.selectArticleByIndex(articleIndex);//게시글 하나 가져오기
        List<TagDto> tagDtoList = adao.selectTags();//tags table에 있는 tag들 가져오기
        ArrayList<String> tagOfArticle =
                adao.selectTagsByArticleIdxAndBoardId(articleIndex,boardId);//tagOfArticle table에 있는 tag들 가져오기

        req.setAttribute("articleDto",articleDto);//게시글
        req.setAttribute("boardId",boardId);//게시판 Id
        req.setAttribute("tagOfArticle",tagOfArticle);//작성자가 입력한 tag값들
        req.setAttribute("tags",tagDtoList);//전체 tag값들

        return new View("/articles/update.jsp");
    }
}
