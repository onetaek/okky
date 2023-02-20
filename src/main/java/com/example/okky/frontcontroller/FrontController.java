package com.example.okky.frontcontroller;

import com.example.okky.command.*;
import com.example.okky.command.admin.AdminViewCommand;
import com.example.okky.command.article.*;
import com.example.okky.command.comment.CommentDeleteCommand;
import com.example.okky.command.comment.CommentInsertCommand;
import com.example.okky.command.comment.CommentListViewCommand;
import com.example.okky.command.comment.ReplyInsertCommand;
import com.example.okky.command.member.*;
import lombok.SneakyThrows;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@WebServlet("*.do")
public class FrontController extends HttpServlet {

    private Map<String,Command> commandMap = new HashMap<>();

    public FrontController() {
        commandMap.put("/welcome.do", new WelcomeViewCommand());
        commandMap.put("/memberLoginView.do", new MemberLogoutCommand());
        commandMap.put("/login.do",new MemberLogoutCommand());
        commandMap.put("/memberRegisterView.do",new RegisterViewCommand());
        commandMap.put("/memberRegister.do",new RegisterCommand());
        commandMap.put("/logout.do",new MemberLogoutCommand());
        commandMap.put("/articleListView.do",new ArticleListViewCommand());
        commandMap.put("/writeView.do",new ArticleWriteViewCommand());
        commandMap.put("/write.do",new ArticleWriteCommand());
        commandMap.put("/articleView.do",new ArticleViewCommand());
        commandMap.put("/articleUpdateView.do",new ArticleUpdateViewCommand());
        commandMap.put("/articleUpdate.do",new ArticleUpdateCommand());
        commandMap.put("/articleDelete.do",new ArticleDeleteCommand());
        commandMap.put("/memberMyView.do",new MemberLogoutCommand());
        commandMap.put("/memberUpdate.do",new MemberLogoutCommand());
        commandMap.put("/memberMyPwCheck.do",new MemberLogoutCommand());
        commandMap.put("/commentInsert.do",new CommentInsertCommand());
        commandMap.put("/ArticleLikeView.do",new ArticleLikeViewCommand());
        commandMap.put("/adminView.do", new AdminViewCommand());
        commandMap.put("/commentDelete.do",new CommentDeleteCommand());
        commandMap.put("/replyInsert.do",new ReplyInsertCommand());
        commandMap.put("/commentListView.do",new CommentListViewCommand());
    }

    @SneakyThrows
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        req.setCharacterEncoding("utf-8");
        String uri = req.getRequestURI(); //localhost:8080/ >>  members/memberLogin.jsp
        String path = uri.substring(uri.lastIndexOf("/")); //  /*.do
        System.out.println("uri: "+uri);
        System.out.println("com: "+path);

        Command command = commandMap.get(path);
        if(command == null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        View view = command.execute(req, resp);
//        System.out.println("접선 실패 ㅠㅜ");
        if(view != null) view.render(req, resp);
    }
}
