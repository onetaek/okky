package com.example.okky.command;

import com.example.okky.daos.MemberDao;
import com.example.okky.dtos.members.MemberDto;
import com.example.okky.utils.CryptoUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;

public class RegisterCommand implements Command{
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("회원가입 command");
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
        boolean policyEmailSendTobool = true;

        if(policyEmailSend == null){
            policyEmailSendTobool = false;
        }
        System.out.println("policyEmailSendTobool = " + policyEmailSendTobool);

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
        mdto.setPolicyEmailSend(policyEmailSendTobool);

        mdao.insertUser(mdto);
    }
}
