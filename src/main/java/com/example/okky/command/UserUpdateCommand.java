package com.example.okky.command;

import com.example.okky.daos.MemberDao;
import com.example.okky.dtos.members.MemberDto;
import com.example.okky.frontcontroller.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserUpdateCommand implements Command{
    MemberDao mdao = MemberDao.getInstance();
    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        String email = req.getParameter("email");
        String name = req.getParameter("name");
        String nickName = req.getParameter("nickName");
        String contact = req.getParameter("contact");
        String pwCheck = req.getParameter("pwCheck");
        System.out.println("pwCheck = " + pwCheck);
        boolean pwCheckToBool = (pwCheck != null);
        if(!pwCheckToBool){
            MemberDto existingUser = mdao.selectUser(email);
            existingUser.setEmail(email);
            existingUser.setName(name);
            existingUser.setNickName(nickName);
            existingUser.setContact(contact);
            mdao.updateUser(existingUser);
            existingUser = mdao.selectUser(email);
            HttpSession session = req.getSession();
            session.setAttribute("user",existingUser);
        }


        return new View("/userMyView.do");
    }
}
