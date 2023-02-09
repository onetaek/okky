package com.example.okky.command.admin;

import com.example.okky.command.Command;
import com.example.okky.frontcontroller.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminViewCommand implements Command {
    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {



        return new View("/admin/admin.jsp");
    }
}
