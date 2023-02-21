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
import java.sql.SQLException;

public class MemberUpdateCommand implements Command {
    MemberDao mdao = MemberDao.getInstance();

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ClassNotFoundException {

        HttpSession session = req.getSession();

        String email = req.getParameter("email");
        String name = req.getParameter("name");
        String nickName = req.getParameter("nickName");
        String contact = req.getParameter("contact");
        String pwCheck = req.getParameter("pwCheck");
        System.out.println("pwCheck = " + pwCheck);

        MemberDto existingMember = mdao.selectmember(email);
        if (existingMember == null || session.getAttribute("member") == null) {
            resp.sendRedirect("login.do");
        }

        existingMember.setName(name);
        existingMember.setNickName(nickName);
        existingMember.setContact(contact);

        if (pwCheck != null) {
            String pw = req.getParameter("NewPw");
            String hashPw = CryptoUtils.hashSha512(pw);
            existingMember.setPassword(hashPw);
        }


        mdao.updatemember(existingMember);
        session.setAttribute("member", existingMember);
        return new View("/memberMyView.do");
    }
}
