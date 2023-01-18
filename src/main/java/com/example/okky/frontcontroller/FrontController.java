package com.example.okky.frontcontroller;

import com.example.okky.command.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("*.do")
public class FrontController extends HttpServlet {

    private Map<String,Command> commandMap = new HashMap<>();

    public FrontController() {
        commandMap.put("/welcome.do", new WelcomeViewCommand());
        commandMap.put("/login.do",new UserLoginCommand());
        commandMap.put("/userRegisterView.do",new RegisterViewCommand());
        commandMap.put("/userRegister.do",new RegisterCommand());
        commandMap.put("/logout.do",new UserLogoutCommand());
        commandMap.put("/articleListView.do",new ArticleListViewCommand());
        commandMap.put("/writeView.do",new ArticleWriteViewCommand());
        commandMap.put("/write.do",new ArticleWriteCommand());
        commandMap.put("/articleView.do",new ArticleViewCommand());
        commandMap.put("/articleUpdateView.do",new ArticleUpdateViewCommand());
        commandMap.put("/articleUpdate.do",new ArticleUpdateCommand());
        commandMap.put("/articleDelete.do",new ArticleDeleteCommand());


    }

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
        String uri = req.getRequestURI(); //localhost:8080/ >>  members/userLogin.jsp
        String path = uri.substring(uri.lastIndexOf("/")); //  /*.do
        System.out.println("uri: "+uri);
        System.out.println("com: "+path);

        String viewPage = null;
        Command command = commandMap.get(path);
        if(command == null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        View view = command.execute(req, resp);
        view.render(req, resp);













        if(path.equals("/index.jsp") || path.equals("/") || path.equals("/index.do")) {//메인 페이지로 이동
            viewPage = "/main/welcome.jsp";
        } else if(path.equals("/userLoginView.do")){//사용자 로그인 화면 처리
            viewPage = "/members/userLogin.jsp";
        }

//        else if(path.equals("/login.do")){//사용자 로그인 처리
//            command = new UserLoginCommand();
//            command.execute(req, resp);
//            viewPage = "/main/welcome.jsp";
//
//        }
//        else if(path.equals("/userRegister.do")){//사용자 회원가입 처리
//            command = new RegisterCommand();
//            command.execute(req, resp);
//            viewPage = "/members/userLogin.jsp";
//        }
//        else if(path.equals("/logout.do")){//사용자 로그아웃 처리
//            command = new UserLogoutCommand();
//            command.execute(req, resp);
//            viewPage = "/main/welcome.jsp";
//        }
//        else if(path.equals("/articleListView.do")){//게시글 전체 보여주기
//            command = new ArticleListViewCommand();
//            command.execute(req, resp);
//            viewPage = "/articles/board.jsp";
//            System.out.println("last line!");
//        }
//        else if(path.equals("/writeView.do")){//글쓰기 페이지 보여주기
//            command = new ArticleWriteViewCommand();
//            command.execute(req, resp);
//            viewPage = "/articles/write.jsp";
//        }
//        else if(path.equals("/write.do")){//글쓰기 처리
//            command = new ArticleWriteCommand();
//            command.execute(req, resp);
//            viewPage = "articleListView.do";
//        }
//        else if(path.equals("/articleView.do")){//하나의 게시글 보여주기
//            command = new ArticleViewCommand();
//            command.execute(req, resp);
//            viewPage = "/articles/article.jsp";
//        }
//        else if(path.equals("/articleUpdateView.do")){//게시글 수정 페이지 보여주기
//            command = new ArticleUpdateViewCommand();
//            command.execute(req, resp);
//            viewPage = "/articles/update.jsp";
//        }
//        else if(path.equals("/articleUpdate.do")){//게시글 수정 처리
//            command = new ArticleUpdateCommand();
//            command.execute(req, resp);
//            viewPage = "/articleView.do";
//        }
//        else if(path.equals("/articleDelete.do")){
//            command = new ArticleDeleteCommand();
//            command.execute(req, resp);
//            viewPage = "/articleListView.do";
//        }

        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
        dispatcher.forward(req,resp);
    }
}
