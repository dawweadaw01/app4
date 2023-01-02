package cdu.zch.controller;

import cdu.zch.model.Msg;
import cdu.zch.service.MsgService;
import cdu.zch.service.impl.MsgServiceImpl;
import cdu.zch.util.PageInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/list")
public class MsgListServlet extends HttpServlet {

    MsgService msgService = new MsgServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sPage = req.getParameter("page");
        int page = 1;
        int pageSize = 3;
        if (sPage != null && !sPage.equals("")) {
            page = Integer.parseInt(sPage);
        }
        int count = msgService.count(0);
        int pageCount = count % pageSize > 0 ? count / pageSize + 1 : count / pageSize;
        List<Msg> msgList = msgService.findByPage(page, pageSize, 0);
        req.setAttribute("msgList", msgList);
        req.setAttribute("page", page);
        req.setAttribute("pageCount", pageCount);
        req.setAttribute("count", count);
        req.getRequestDispatcher("list.do").forward(req, resp);

    }
}
