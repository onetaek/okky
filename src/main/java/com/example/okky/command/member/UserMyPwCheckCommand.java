package com.example.okky.command.member;

import com.example.okky.command.Command;
import com.example.okky.daos.MemberDao;
import com.example.okky.dtos.members.MemberDto;
import com.example.okky.frontcontroller.View;
import com.example.okky.utils.CryptoUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserMyPwCheckCommand implements Command {
    MemberDao mdao = MemberDao.getInstance();
    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String email = req.getParameter("email");
        String oripassword = req.getParameter("password");
        String password = CryptoUtils.hashSha512(oripassword);

        System.out.println(email+oripassword);

        MemberDto memberDto = mdao.selectUserById(email, password);

        if(memberDto != null){
            PrintWriter out = resp.getWriter();
            out.println("success");
            out.flush();
            out.close();

        }else {
            PrintWriter out = resp.getWriter();
            out.println("failed");
            out.flush();
            out.close();
        }

        return null;
    }
}
