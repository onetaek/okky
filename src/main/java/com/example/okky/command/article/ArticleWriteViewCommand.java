package com.example.okky.command.article;

import com.example.okky.command.Command;
import com.example.okky.daos.ArticleDao;
import com.example.okky.dtos.bbs.TagDto;
import com.example.okky.dtos.members.MemberDto;
import com.example.okky.frontcontroller.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ArticleWriteViewCommand implements Command {
    ArticleDao adao = ArticleDao.getInstance();
    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        String boardId = req.getParameter("boardId");
        MemberDto user = (MemberDto)req.getSession().getAttribute("user");
        if (user == null) {
            System.out.println("Session이 없습니다!");
            return new View("/userLoginView.do");
        }

        List<TagDto> tagDtoList = adao.selectTags();
        req.setAttribute("tags",tagDtoList);
        req.setAttribute("boardId",boardId);

        return new View("/articles/write.jsp");
    }
}
