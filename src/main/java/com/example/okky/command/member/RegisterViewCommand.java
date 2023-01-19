package com.example.okky.command.member;

import com.example.okky.command.Command;
import com.example.okky.daos.MemberDao;
import com.example.okky.dtos.members.ContactCountryDto;
import com.example.okky.dtos.members.TelecomDto;
import com.example.okky.frontcontroller.View;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class RegisterViewCommand implements Command {
    MemberDao mdao = MemberDao.getInstance();
    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {

//        List<ContactCountryDto> contactCountryDtoList = memberDao.selectContactCountry();
        List<TelecomDto> telecomDtoList = mdao.selectTelecom();

//        req.setAttribute("contactCountryDtoList",contactCountryDtoList);
        req.setAttribute("telecomDtoList",telecomDtoList);

        return new View("/members/userRegister.jsp");
    }
}
