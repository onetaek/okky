package com.example.okky.command;

import com.example.okky.daos.MemberDao;
import com.example.okky.dtos.members.MemberDto;
import com.example.okky.frontcontroller.View;
import com.example.okky.utils.CryptoUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserLoginCommand implements Command {


    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        String password = req.getParameter("password");

        String hashPw = CryptoUtils.hashSha512(password);

        MemberDao memberDao = new MemberDao();
        MemberDto user = memberDao.selectUserById(id, hashPw);

        req.getSession().setAttribute("user", user);

        return new View("/main/welcome.jsp");

    }
}
