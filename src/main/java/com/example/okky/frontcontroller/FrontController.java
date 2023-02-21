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
import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
@Slf4j
@WebServlet("/")
public class FrontController extends HttpServlet {

    private Map<String,Command> commandMap = new HashMap<>();

    private Map<String,Command> getMapping = new HashMap<>();
    private Map<String,Command> postMapping = new HashMap<>();
    public FrontController() {
//        commandMap.put("/welcome.do", new WelcomeViewCommand());
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
        commandMap.put("/memberMyView.do",new MemberMyViewCommand());
        commandMap.put("/memberUpdate.do",new MemberUpdateCommand());
        commandMap.put("/memberMyPwCheck.do",new MemberMyPwCheckCommand());
        commandMap.put("/commentInsert.do",new CommentInsertCommand());
        commandMap.put("/ArticleLikeView.do",new ArticleLikeViewCommand());
        commandMap.put("/adminView.do", new AdminViewCommand());
        commandMap.put("/commentDelete.do",new CommentDeleteCommand());
        commandMap.put("/replyInsert.do",new ReplyInsertCommand());
        commandMap.put("/commentListView.do",new CommentListViewCommand());

        //GET
        getMapping.put("/main/welcome",new WelcomeViewCommand());

        //POST
    }

    @SneakyThrows
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        req.setCharacterEncoding("utf-8");
        String requestURI = req.getRequestURI();
        String method = req.getMethod();
        log.info("method = {}, requestURI = {}",req.getMethod(),requestURI);
        Command command = null;
        if(method.equals("GET")){
            command = getMapping.get(requestURI);
        }else if(method.equals("POST")){
            command = postMapping.get(requestURI);
        }
        if(command == null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        View view = command.execute(req, resp);
        if(view != null) view.render(req, resp);
    }
}
