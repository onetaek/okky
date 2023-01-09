package com.example.okky.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserLogoutCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().invalidate();

    }
}
