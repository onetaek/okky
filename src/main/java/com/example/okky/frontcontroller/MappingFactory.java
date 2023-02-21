package com.example.okky.frontcontroller;

import com.example.okky.command.Command;
import com.example.okky.command.admin.AdminViewCommand;
import com.example.okky.command.article.*;
import com.example.okky.command.comment.CommentDeleteCommand;
import com.example.okky.command.comment.CommentInsertCommand;
import com.example.okky.command.comment.CommentListViewCommand;
import com.example.okky.command.comment.ReplyInsertCommand;
import com.example.okky.command.member.*;

import java.util.HashMap;
import java.util.Map;

public class MappingFactory {
    private static final Map<String, Command> getMapping = new HashMap<>();
    private static final Map<String, Command> postMapping = new HashMap<>();
    static{
        //GET
        getMapping.put("/main/welcome",new WelcomeViewCommand());//메인 페이지 이동
        getMapping.put("/member/login",new MemberLoginViewCommand());//로그인 페이지 이동
        getMapping.put("/member/register",new RegisterViewCommand());//회원가입 페이지 이동
        getMapping.put("/member/logout",new MemberLogoutCommand());//로그아웃->post 바꿀까..?
        getMapping.put("/board", new BoardViewCommand());//게시판 페이지 이동
        getMapping.put("/article/write",new ArticleWriteViewCommand());//글 작성 페이지 이동
        getMapping.put("/article",new ArticleViewCommand());//게시글 페이지 이동
        getMapping.put("/article/update",new ArticleUpdateViewCommand());//게시글 수정 페이지 이동
        getMapping.put("/member/my",new MemberMyViewCommand());//마이페이지 이동
        getMapping.put("/comments",new CommentListViewCommand());//댓글들 JSON으로 API 응답
        getMapping.put("/article/like",new ArticleLikeViewCommand());//게시글 좋아요 갯수 API응답
        getMapping.put("/admin",new AdminViewCommand());//관리자 페이지 이동

        //POST
        postMapping.put("/member/register",new RegisterCommand());//회원가입 처리
        postMapping.put("/member/login",new MemberLoginCommand());//로그인 처리
        postMapping.put("/article/write", new ArticleWriteCommand());//글작성 처리
        postMapping.put("/article/update",new ArticleUpdateCommand());//글 수정 처리
        postMapping.put("/article/delete",new ArticleDeleteCommand());//글 삭제 처리
        postMapping.put("/member/update", new MemberUpdateCommand());//유저 정보 수정 처리
        postMapping.put("/member/pwCheck",new MemberMyPwCheckCommand());//유저 비밀번호 일치 확인(api)
        postMapping.put("/comment/insert",new CommentInsertCommand());//댓글 작성 처리(api)
        postMapping.put("/comment/delete",new CommentDeleteCommand());//댓글 삭제 처리(api)
        postMapping.put("/reply/insert",new ReplyInsertCommand());//대댓글 작성 처리(api)
    }

    public Command createCommand(String requestURI, String method){
        Command command = null;
        if(method.equals("GET")){
           command = getMapping.get(requestURI);
        }else if(method.equals("POST")){
           command = postMapping.get(requestURI);
        }
        return command;
    }
}
