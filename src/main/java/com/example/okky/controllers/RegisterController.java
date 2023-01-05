package com.example.okky.controllers;

import com.example.okky.daos.MemberDao;
import com.example.okky.dtos.members.MemberDto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/userRegister")
public class RegisterController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action(req, resp);
    }

    protected void action(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");


        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String passwordCheck = req.getParameter("passwordCheck");
        String name = req.getParameter("name");
        String nickName = req.getParameter("nickName");
        String telecom = req.getParameter("telecom");
        String contact = req.getParameter("contact");
        String contactCountryValue = req.getParameter("contactCountryValue");
        String contactAuthCode = req.getParameter("contactAuthCode");
        String policyEmailSend = req.getParameter("policyEmailSend");


        System.out.println(email);
        System.out.println(password);
        System.out.println(passwordCheck);
        System.out.println(name);
        System.out.println(nickName);
        System.out.println(telecom);
        System.out.println(contact);
        System.out.println(contactCountryValue);
        System.out.println(contactAuthCode);
        System.out.println(policyEmailSend);


        MemberDao mdao = new MemberDao();
        MemberDto mdto = new MemberDto();


        mdto.setEmail(email);
        mdto.setPassword(password);
        mdto.setName(name);
        mdto.setNickName(nickName);
        mdto.setTelecom(telecom);
        mdto.setContact(contact);
        mdto.setcontactCountryValue(contactCountryValue);
        mdto.setPolicyEmailSend(Boolean.valueOf(policyEmailSend));


        mdao.insertUser(mdto);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/members/userLogin.jsp");
        dispatcher.forward(req, resp);
//        resp.sendRedirect("/members/userLogin.jsp");

    }
}
