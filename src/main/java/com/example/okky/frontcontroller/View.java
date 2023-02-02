package com.example.okky.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class View {
    private String viewPath;


    public View(String viewPath) {
        this.viewPath = viewPath;
    }

    public void render(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (viewPath.contains("redirect:")) {
            viewPath = viewPath.substring(viewPath.lastIndexOf("/") + 1 );
            resp.sendRedirect(viewPath);
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
            dispatcher.forward(req, resp);
        }

    }
}
