package cdu.lhj.controller;

import cdu.lhj.model.Msg;
import cdu.lhj.model.User;
import cdu.lhj.service.MsgService;
import cdu.lhj.service.impl.MsgServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/addMsg")
public class AddMsgServlet extends HttpServlet {

    MsgService msgService = new MsgServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Msg msg = new Msg();
        msg.setUser(user);
        msg.setSubject(req.getParameter("subject"));
        msg.setContent(req.getParameter("content"));
        msgService.add(msg);
        resp.sendRedirect("list");

    }
}
