package com.example.okky.command.article;

import com.example.okky.command.Command;
import com.example.okky.daos.ArticleDao;
import com.example.okky.daos.BoardDao;
import com.example.okky.dtos.bbs.ArticleDto;
import com.example.okky.dtos.bbs.BoardDto;
import com.example.okky.dtos.bbs.TagDto;
import com.example.okky.dtos.bbs.TagOfArticleDto;
import com.example.okky.frontcontroller.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ArticleListViewCommand implements Command {
    ArticleDao adao = ArticleDao.getInstance();
    BoardDao bdao = BoardDao.getInstance();
    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        String boardId = req.getParameter("boardId");
        int countListPerPage = 10;
        Integer pageNum = Integer.parseInt(req.getParameter("pageNum"));
        String keyWord = req.getParameter("keyWord");
        int totalRecord = 0;
        Integer totalPage = (totalRecord%countListPerPage == 0) ? totalRecord/countListPerPage : (totalRecord/countListPerPage)+1;
        BoardDto boardDto = null;
        List<ArticleDto> articleDtoList = null;
        int startNum = (pageNum - 1) * countListPerPage;
        int block = 5;
        int blockTotal = totalPage % block == 0 ? totalPage/block : totalPage / block + 1;
        int blockThis = ((pageNum - 1)/block)+1;
        int blockThisFirstPage = ((blockThis -1 )*block)+1;
        int blockThisLastPage = blockThis * block;
        blockThisLastPage = (blockThisLastPage>totalPage)?totalPage:blockThisLastPage;

        if(pageNum == null) pageNum = 1;

        totalRecord = adao.selectArticleTotalCount(boardId);
        boardDto = bdao.selectBoardById(boardId);//어떤 게시판 인지(ex 공지사항, 커뮤니티...)
        articleDtoList = adao.selectArticleByPageNum(boardId,startNum,countListPerPage,keyWord);//페이지에 해당하는 aritcle만 가져오기
        ArrayList<TagOfArticleDto> tagOfArticleDtos = adao.selectTagsByBoardId(boardId);


        req.setAttribute("tagsList",tagOfArticleDtos);
        req.setAttribute("articleDtoList",articleDtoList);
        req.setAttribute("boardDto",boardDto);
        req.setAttribute("pageNum",pageNum);
        req.setAttribute("totalPage",totalPage);
        req.setAttribute("totalRecord",totalRecord);
        req.setAttribute("block",block);
        req.setAttribute("blockTotal",blockTotal);
        req.setAttribute("blockThis",blockThis);
        req.setAttribute("blockThisFirstPage",blockThisFirstPage);
        req.setAttribute("blockThisLastPage",blockThisLastPage);


        return new View("/articles/board.jsp");
    }
}
