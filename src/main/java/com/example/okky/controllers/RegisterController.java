package com.example.okky.controllers;

import com.example.okky.daos.MemberDao;
import com.example.okky.dtos.MemberDto;

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
        dispatcher.forward(req,resp);

    }
}
