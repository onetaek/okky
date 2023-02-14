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
import java.sql.SQLException;

public class UserMyPwCheckCommand implements Command {
    MemberDao mdao = MemberDao.getInstance();
    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ClassNotFoundException {
        System.out.println("Ajax 실행 메서드 작동 완료1111");
        String email = req.getParameter("email");
        System.out.println("email : "+email);
        String oripassword = req.getParameter("password");
        System.out.println("password : " +oripassword);
        String password = CryptoUtils.hashSha512(oripassword);
        System.out.println("Ajax 실행 메서드 작동 완료222222");

        MemberDto memberDto = mdao.selectUserById(email, password);
        System.out.println("Ajax 실행 메서드 작동 333333333");
        if(memberDto != null){
            System.out.println("Ajax 실행 메서드 작동 완료44444444444444");
            PrintWriter out = resp.getWriter();
            out.print("success");
            out.flush();
            out.close();

        } else {
            System.out.println("Ajax 실행 메서드 작동 완료5555555555555555");
            PrintWriter out = resp.getWriter();
            out.print("failed");
            out.flush();
            out.close();
        }
        System.out.println("Ajax 실행 메서드 작동 완료66666666666666666666");
        return null;
    }


}
