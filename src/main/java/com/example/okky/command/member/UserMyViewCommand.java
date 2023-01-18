package com.example.okky.command.member;

import com.example.okky.command.Command;
import com.example.okky.dtos.members.MemberDto;
import com.example.okky.frontcontroller.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserMyViewCommand implements Command {

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        MemberDto user = (MemberDto)req.getSession().getAttribute("user");
        req.setAttribute("user",user);
        return new View("/members/userMy.jsp");
    }
}
