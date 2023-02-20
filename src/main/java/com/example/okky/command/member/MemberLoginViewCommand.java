package com.example.okky.command.member;

import com.example.okky.command.Command;
import com.example.okky.frontcontroller.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberLoginViewCommand implements Command {

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        return new View("/members/memberLogin.jsp");
    }
}
