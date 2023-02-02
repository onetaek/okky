package com.example.okky.command.article;

import com.example.okky.command.Command;
import com.example.okky.daos.ArticleDao;
import com.example.okky.daos.BoardDao;
import com.example.okky.daos.TagDao;
import com.example.okky.dtos.bbs.ArticleDto;
import com.example.okky.dtos.bbs.BoardDto;
import com.example.okky.dtos.bbs.TagDto;
import com.example.okky.dtos.bbs.TagOfArticleDto;
import com.example.okky.frontcontroller.View;
import com.example.okky.utils.Pagination;
import com.example.okky.vo.TagVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ArticleListViewCommand implements Command {
    ArticleDao adao = ArticleDao.getInstance();
    BoardDao bdao = BoardDao.getInstance();
    TagDao tdao = TagDao.getInstance();

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        String boardId = req.getParameter("boardId");
        System.out.println("jaklsdjfiiioio"+boardId);
        String pageNum = req.getParameter("pageNum");
        String keyWord = req.getParameter("keyWord");
        int totalCount = adao.selectArticleTotalCount(boardId);

        Pagination p = new Pagination(pageNum, totalCount);
        BoardDto boardDto = bdao.selectBoardById(boardId);//어떤 게시판 인지(ex 공지사항, 커뮤니티...)
        List<ArticleDto> articleDtoList = adao.selectArticleByPageNum(boardId, p.getStartRow(), p.getPageSize(),null);//페이지에 해당하는 aritcle만 가져오기
//        ArrayList<TagOfArticleDto> tag = adao.selectTagsByBoardId(boardId);
        List<TagOfArticleDto> tagOfArticleDtoArraylist = adao.selectAllTag();
        List<TagVo> tagRank = tdao.selectTagTop5();


        req.setAttribute("tagsList", tagOfArticleDtoArraylist);
        req.setAttribute("p",p);
        req.setAttribute("boardId",boardId);
        req.setAttribute("boardDto",boardDto);
        req.setAttribute("articleDtoList",articleDtoList);
        req.setAttribute("tagRank",tagRank);
        return new View("/articles/board.jsp");

    }
}
