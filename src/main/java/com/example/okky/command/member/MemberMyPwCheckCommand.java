package com.example.okky.command.member;

import com.example.okky.command.Command;
import com.example.okky.daos.MemberDao;
import com.example.okky.dtos.members.MemberDto;
import com.example.okky.frontcontroller.View;
import com.example.okky.utils.CryptoUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class MemberMyPwCheckCommand implements Command {
    MemberDao mdao = MemberDao.getInstance();
    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ClassNotFoundException {
        String email = req.getParameter("email");
        String oripassword = req.getParameter("password");
        String password = CryptoUtils.hashSha512(oripassword);

        MemberDto memberDto = mdao.selectmemberById(email, password);
        PrintWriter out = resp.getWriter();
        if (memberDto != null){
            out.print("success");
        } else {
            out.print("failed");
        }
        out.flush();
        out.close();
        return null;
    }


}
