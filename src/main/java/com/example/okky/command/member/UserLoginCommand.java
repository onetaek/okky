package com.example.okky.command.member;

import com.example.okky.command.Command;
import com.example.okky.daos.MemberDao;
import com.example.okky.dtos.members.MemberDto;
import com.example.okky.frontcontroller.View;
import com.example.okky.utils.CryptoUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class UserLoginCommand implements Command {

    MemberDao mdao = MemberDao.getInstance();

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("utf-8");

        String id = req.getParameter("id");
        String password = req.getParameter("password");
        String hashPw = CryptoUtils.hashSha512(password);

        PrintWriter out = resp.getWriter();

        MemberDto user = mdao.selectUserById(id, hashPw);
        System.out.println(user.isAdmin()); //
        if (user.isAdmin()) {
            return null; // 관리자 주소 들어가는곳
        } else if (user.getStatusValue().equals("SUS")) {

            out.print("<script>");
            out.print("alert('해당 회원은 일시정지 [SUS] 된 회원입니다.')");
            out.print("</script>");
            out.flush();
            out.close();
            return new View("/members/userLogin.jsp");
        } else if (user.getStatusValue().equals("RES")) {

            out.print("<script>");
            out.print("alert('해당 회원은 탈퇴 [ SUS ] 회원입니다.')");
            out.print("</script>");
            out.flush();
            out.close();
            return new View("/members/userLogin.jsp");
        } else {
            req.getSession().setAttribute("user", user);
            return new View("/main/welcome.jsp");
        }


    }
}
