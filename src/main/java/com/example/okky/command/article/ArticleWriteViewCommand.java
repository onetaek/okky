package com.example.okky.command.article;

import com.example.okky.command.Command;
import com.example.okky.daos.ArticleDao;
import com.example.okky.dtos.bbs.TagDto;
import com.example.okky.dtos.members.MemberDto;
import com.example.okky.frontcontroller.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

public class ArticleWriteViewCommand implements Command {
    ArticleDao adao = ArticleDao.getInstance();
    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException {
        int boardId = Integer.parseInt(req.getParameter("boardId"));
        MemberDto member = (MemberDto)req.getSession().getAttribute("member");
        if (member == null) {
            System.out.println("Session이 없습니다!");
            return new View("/memberLoginView.do");
        }

        List<TagDto> tagDtoList = adao.selectTags();
        req.setAttribute("tags",tagDtoList);
        req.setAttribute("boardId",boardId);

        return new View("/articles/write.jsp");
    }
}
