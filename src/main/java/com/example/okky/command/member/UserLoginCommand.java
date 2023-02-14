package com.example.okky.command.member;

import com.example.okky.command.Command;
import com.example.okky.daos.MemberDao;
import com.example.okky.dtos.members.MemberDto;
import com.example.okky.frontcontroller.View;
import com.example.okky.utils.CryptoUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@Slf4j
public class UserLoginCommand implements Command {

    MemberDao mdao = MemberDao.getInstance();

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ClassNotFoundException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("utf-8");

        String id = req.getParameter("email");
        System.out.println("id = " + id);
        String password = req.getParameter("password");
        System.out.println("password = " + password);
        String hashPw = CryptoUtils.hashSha512(password);
        System.out.println("hashPw = " + hashPw);
        PrintWriter out = resp.getWriter();
        MemberDto user = mdao.selectUserById(id, hashPw);
        System.out.println("user = " + user);

        log.info("admin = {}",user.isAdmin());
        if (user == null) {
            out.print("failure");
            out.flush();
            out.close();
            return null;
        } else {
        //System.out.println(user.isAdmin()); // 관리자 일반화원 에 따른 사이트 분리
            if (user.isAdmin()) {
                out.print("admin");
                out.flush();
                out.close();
                return null;
//                return new View("redirect:/adminView.do");
            } else if (user.getStatusValue().equals("SUS")) {
                out.print("suspended");
                out.flush();
                out.close();
                return null;
            } else if (user.getStatusValue().equals("RES")) {
                out.print("resigned");
                out.flush();
                out.close();
                return null;
            } else {
                out.print("success");
                out.flush();
                out.close();
                req.getSession().setAttribute("user", user);
                return new View("나무시하지마!!!");
            }
        }
    }
}
