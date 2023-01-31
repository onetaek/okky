package com.example.okky.command.member;

import com.example.okky.command.Command;
import com.example.okky.daos.MemberDao;
import com.example.okky.dtos.members.MemberDto;
import com.example.okky.frontcontroller.View;
import com.example.okky.utils.CryptoUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterCommand implements Command {
    MemberDao mdao = MemberDao.getInstance();
    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("RegisterCommand Method : execute Start!!!!!!!!!!!!!!!!");
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

        System.out.println("policyEmailSend : " + policyEmailSend);
//        boolean policy = policyEmailSend.equals("on");

        MemberDto mdto = new MemberDto();

        String hashPassword = CryptoUtils.hashSha512(password);
        mdto.setEmail(email);
        mdto.setPassword(hashPassword);
        mdto.setName(name);
        mdto.setNickName(nickName);
        mdto.setTelecom(telecom);
        mdto.setContact(contact);
        mdto.setcontactCountryValue(contactCountryValue);
        if (policyEmailSend == null) {
            //        System.out.println(Boolean.valueOf(policyEmailSend));
            mdto.setPolicyEmailSend(false);
        } else {
            //        System.out.println(Boolean.valueOf(policyEmailSend));
            mdto.setPolicyEmailSend(true);
        }



        mdao.insertUser(mdto);
        System.out.println("RegisterCommand Method : execute End!!!!!!!!!!!!!!!!");
        return new View("/members/userLogin.jsp");
    }
}
