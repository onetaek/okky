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

//        boolean pwCheckToBool = (pwCheck != null);

//        boolean checkBool = pwCheck.equals("on");


//        if(!pwCheckToBool){
        MemberDto existingmember = mdao.selectmember(email);
        if (existingmember == null || session.getAttribute("member") == null) {
            resp.sendRedirect("login.do");
        }

        existingmember.setName(name);
        existingmember.setNickName(nickName);
        existingmember.setContact(contact);

        if (pwCheck != null) {
            String pw = req.getParameter("NewPw");
            String hashPw = CryptoUtils.hashSha512(pw);
            existingmember.setPassword(hashPw);
        }


        mdao.updatemember(existingmember);
        session.setAttribute("member", existingmember);
        return new View("/memberMyView.do");
    }
}