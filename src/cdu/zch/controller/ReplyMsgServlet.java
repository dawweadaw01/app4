package cdu.zch.controller;

import cdu.zch.model.Msg;
import cdu.zch.model.User;
import cdu.zch.service.MsgService;
import cdu.zch.service.impl.MsgServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@WebServlet("/reply")
public class ReplyMsgServlet extends HttpServlet {

    MsgService msgService = new MsgServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
//        System.out.println("user$$$:" + user);
        String sid = req.getParameter("id");
        Msg msg1 = new Msg();
        msg1 = msgService.get(Integer.parseInt(sid));
//        System.out.println("msg1%%%%" + msg1);
        Msg msg = new Msg();
        msg.setId(msg1.getId());
        msg.setUser(user);
        msg.setSubject(msg1.getSubject());
        msg.setContent(msg1.getContent());
        msg.setAddMsgTime(msg1.getAddMsgTime());
        msg.setIsReplied(msg1.getIsReplied());
        msg.setReUser(msg1.getUser());
//        msgService.add(msg);
        session.setAttribute("msg", msg);
        System.out.println("msg:###" + msg);
//        resp.sendRedirect("list");
        req.getRequestDispatcher("reply.do").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        MsgService msgService = new MsgServiceImpl();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String sid = req.getParameter("id");
        if (sid == null || sid.equals("")) {
            resp.sendRedirect("list");
        }
        Msg msg = (Msg)session.getAttribute("msg");
        System.out.println("msg$$$$" + msg);
//        System.out.println("sid:@@@@" + sid);
//        msg.setId(Integer.parseInt(sid));
        msg.setIsReplied(2);
        msg.setReUser(user);
        msg.setReply(req.getParameter("reply"));
        msg.setReTime(new Date().getTime());
        msgService.reply(msg);
        System.out.println("msg!!!!" + msg);
        resp.sendRedirect("list");

    }
}
