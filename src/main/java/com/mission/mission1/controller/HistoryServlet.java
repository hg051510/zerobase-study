package com.mission.mission1.controller;

import com.mission.mission1.dao.HistoryDAO;
import com.mission.mission1.vo.HistoryVO;
import org.checkerframework.checker.units.qual.A;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static java.lang.System.out;

@WebServlet(name = "HistoryServlet", value = "/HistoryServlet")
public class HistoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String cmdReq="";
        cmdReq = request.getParameter("cmd");

        if (cmdReq.equals("historyList")) {
            HistoryDAO dao = new HistoryDAO();
            ArrayList<HistoryVO> historyList = dao.getHistoryList();
            request.setAttribute("historyList", historyList);
            RequestDispatcher view = request.getRequestDispatcher("history.jsp");
            view.forward(request, response);
        }

        else if (cmdReq.equals("delete")) {
            HistoryDAO dao = new HistoryDAO();
            HistoryVO vo = dao.read(Integer.parseInt(request.getParameter("id")));
            dao.delete(vo);
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.println("<script>alert('삭제가 완료되었습니다.'); location.href='HistoryServlet?cmd=historyList';</script>");
            writer.close();
        }

        else if(cmdReq.equals("insert")){
            HistoryVO vo = new HistoryVO();
            HistoryDAO dao = new HistoryDAO();
            float lat = Float.parseFloat(request.getParameter("lat1"));
            float lnt = Float.parseFloat(request.getParameter("lnt1"));

            vo.setX_point(lat);
            vo.setY_point(lnt);

            dao.historyInsert(vo);

            PrintWriter script = response.getWriter();
            script.println("<script>");
            script.println("history.go(-1)");
            script.println("</script>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
