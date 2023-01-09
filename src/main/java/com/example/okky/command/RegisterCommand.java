package com.example.okky.command;

import com.example.okky.daos.MemberDao;
import com.example.okky.dtos.members.MemberDto;
import com.example.okky.utils.CryptoUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterCommand implements Command{
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {

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

        boolean policy = false;
        if (policyEmailSend.equals("on")) {
            policy = true;
        } else if (policyEmailSend == null) {
            policy= false;
        }
        System.out.println(email);
        System.out.println(password);
        System.out.println(passwordCheck);
        System.out.println(name);
        System.out.println(nickName);
        System.out.println(telecom);
        System.out.println(contact);
        System.out.println(contactCountryValue);
        System.out.println(contactAuthCode);
        System.out.println(policy);

        MemberDao mdao = new MemberDao();
        MemberDto mdto = new MemberDto();

        String hashPassword = CryptoUtils.hashSha512(password);
        System.out.println(hashPassword);

        mdto.setEmail(email);
        mdto.setPassword(hashPassword);
        mdto.setName(name);
        mdto.setNickName(nickName);
        mdto.setTelecom(telecom);
        mdto.setContact(contact);
        mdto.setcontactCountryValue(contactCountryValue);
//        System.out.println(Boolean.valueOf(policyEmailSend));
        mdto.setPolicyEmailSend(policy);


        mdao.insertUser(mdto);
    }
}
