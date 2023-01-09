package com.example.okky.command;

import com.example.okky.daos.MemberDao;
import com.example.okky.dtos.members.ContactCountryDto;
import com.example.okky.dtos.members.TelecomDto;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class RegisterViewCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {

        MemberDao memberDao = new MemberDao();
//        List<ContactCountryDto> contactCountryDtoList = memberDao.selectContactCountry();
        List<TelecomDto> telecomDtoList = memberDao.selectTelecom();

//        req.setAttribute("contactCountryDtoList",contactCountryDtoList);
        req.setAttribute("telecomDtoList",telecomDtoList);

    }
}
