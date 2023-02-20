package com.example.okky.command.article;

import com.example.okky.command.Command;
import com.example.okky.daos.ArticleDao;
import com.example.okky.frontcontroller.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class ArticleLikeViewCommand implements Command {
    ArticleDao adao = ArticleDao.getInstance();



    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ClassNotFoundException {
        PrintWriter out = resp.getWriter();
        String action = req.getParameter("action");
        String memberEmail = req.getParameter("memberEmail");
        int articleIndex = Integer.parseInt(req.getParameter("articleIndex"));

        System.out.println("action = " + action);
        System.out.println("memberEmail = " + memberEmail);
        System.out.println("articleIndex = " +articleIndex);

        int likeCount = 0;

        likeCount = adao.selectLikeByCount(articleIndex);
        if (action == null) {
            System.out.println("likeCount = " + likeCount);
        } else {
            if(action.equals("up")){
                System.out.println("upupupupupupupupupp");
                adao.insertLike(memberEmail,articleIndex);
                likeCount++;
                out.print(likeCount+"");
                out.flush();
                out.close();
                return null;
            } else if(action.equals("down")){
                System.out.println("downdowndowndown");
                adao.deleteLike(memberEmail,articleIndex);
                likeCount--;
                out.print(likeCount+"");
                out.flush();
                out.close();
                return null;
            }
        }



        out.print(likeCount + "");
        out.flush();
        out.close();

        return null;
    }
}
