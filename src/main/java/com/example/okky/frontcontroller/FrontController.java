package com.example.okky.frontcontroller;

import com.example.okky.command.*;
import com.example.okky.command.article.*;
import com.example.okky.command.member.*;

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
        commandMap.put("/userLoginView.do", new UserLoginViewCommand());
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
        commandMap.put("/userMyView.do",new UserMyViewCommand());
        commandMap.put("/userUpdate.do",new UserUpdateCommand());
        commandMap.put("/userMyPwCheck.do",new UserMyPwCheckCommand());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String uri = req.getRequestURI(); //localhost:8080/ >>  members/userLogin.jsp
        String path = uri.substring(uri.lastIndexOf("/")); //  /*.do
        System.out.println("uri: "+uri);
        System.out.println("com: "+path);

        Command command = commandMap.get(path);
        if(command == null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        View view = command.execute(req, resp);
        System.out.println("접선 실패 ㅠㅜ");
        if(view != null) view.render(req, resp);
    }
}
