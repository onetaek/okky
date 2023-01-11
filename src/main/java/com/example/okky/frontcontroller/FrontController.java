package com.example.okky.frontcontroller;

import com.example.okky.command.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("*.do")
public class FrontController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doAction(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doAction(req, resp);
    }
    protected void doAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String viewPage = null;
        Command command = null;

        String uri = req.getRequestURI();
        String path = uri.substring(uri.lastIndexOf("/"));
//        String conPath = req.getContextPath();
//        String com = uri.substring(conPath.length());

        System.out.println("uri: "+uri);
//        System.out.println("conPath: "+conPath);
        System.out.println("com: "+path);

        if(path.equals("/registerView.do")){
            System.out.println("registerView.do equals!!");
            command = new RegisterViewCommand();
            command.execute(req,resp);
            viewPage = "/members/userRegister.jsp";
        }else if(path.equals("/login.do")){
            command = new UserLoginCommand();
            command.execute(req, resp);
            viewPage = "/main/welcome.jsp";
        }else if(path.equals("/userRegister.do")){
            command = new RegisterCommand();
            command.execute(req, resp);
            viewPage = "/members/userLogin.jsp";
        }else if(path.equals("/logout.do")){
            command = new UserLogoutCommand();
            command.execute(req, resp);
            viewPage = "/main/welcome.jsp";
        }else if(path.equals("/articleView.do")){
            System.out.println("들어왔나?");
            command = new ArticleViewCommand();
            command.execute(req, resp);
            viewPage = "/articles/board.jsp";
            System.out.println("last line!");
        }else if(path.equals("/write.do")){
            command = new ArticleWriteCommand();
            command.execute(req, resp);
            viewPage = "/main/welcome.jsp";
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
        dispatcher.forward(req,resp);
    }
}
