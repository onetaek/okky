package com.example.okky.command;

import com.example.okky.frontcontroller.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserLoginViewCommand implements Command{

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        return new View("/members/userLogin.jsp");
    }
}
