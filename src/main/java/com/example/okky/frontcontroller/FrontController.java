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
@WebServlet(urlPatterns = {"/main/*","/member/*","/article/*","/admin/*","/board/*"})
public class FrontController extends HttpServlet {
    @SneakyThrows
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        req.setCharacterEncoding("utf-8");
        String requestURI = req.getRequestURI();
        String method = req.getMethod();
        log.info("method = {}, requestURI = {}",req.getMethod(),requestURI);
        Command command = new MappingFactory().createCommand(requestURI, method);

        if(command == null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        View view = command.execute(req, resp);
        if(view != null) view.render(req, resp);
    }
}
