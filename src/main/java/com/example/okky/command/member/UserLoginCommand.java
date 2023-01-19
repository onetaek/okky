package com.example.okky.command.member;

import com.example.okky.command.Command;
import com.example.okky.daos.MemberDao;
import com.example.okky.dtos.members.MemberDto;
import com.example.okky.frontcontroller.View;
import com.example.okky.utils.CryptoUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserLoginCommand implements Command {

    MemberDao mdao = MemberDao.getInstance();
    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        String password = req.getParameter("password");

        String hashPw = CryptoUtils.hashSha512(password);

        MemberDto user = mdao.selectUserById(id, hashPw);

        req.getSession().setAttribute("user", user);

        return new View("/main/welcome.jsp");

    }
}
