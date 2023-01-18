package com.example.okky.command;

import com.example.okky.frontcontroller.View;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {
    View execute (HttpServletRequest req, HttpServletResponse resp) throws IOException;
}
