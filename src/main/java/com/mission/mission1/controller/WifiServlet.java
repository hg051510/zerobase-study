package com.mission.mission1.controller;

import com.mission.mission1.dao.WifiDAO;
import com.mission.mission1.vo.WifiVO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "WifiServlet", value = "/WifiServlet")
public class WifiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String message = "";
        String cmdReq="";

        cmdReq = request.getParameter("cmd");
        if(cmdReq.equals("insert")){
            WifiDAO wifiDAO = new WifiDAO();
            wifiDAO.delete();
            String[] wifiList = request.getParameterValues("lib");
            List<WifiVO> wifiVOS = new ArrayList<>();

            for(int i = 0; i < wifiList.length; i++){
                String[] tmp = wifiList[i].split("/");

                WifiVO vo = new WifiVO();
                vo.setX_swifi_mgr_no(tmp[0]);
                vo.setX_swifi_wrdofc(tmp[1]);
                vo.setX_swifi_main_nm(tmp[2]);
                vo.setX_swifi_adres1(tmp[3]);
                vo.setX_swifi_adres2(tmp[4]);
                vo.setX_swifi_instl_floor(tmp[5]);
                vo.setX_swifi_instl_ty(tmp[6]);
                vo.setX_swifi_instl_mby(tmp[7]);
                vo.setX_swifi_svc_se(tmp[8]);
                vo.setX_swifi_cmcwr(tmp[9]);
                vo.setX_swifi_cnstc_year(Integer.parseInt(tmp[10]));
                vo.setX_swifi_inout_door(tmp[11]);
                vo.setX_swifi_remars3(tmp[12]);
                vo.setLat(Float.parseFloat(tmp[13]));
                vo.setLnt(Float.parseFloat(tmp[14]));
                vo.setWork_dttm(tmp[15]);

                wifiVOS.add(vo);
            }
            int res = wifiDAO.insert(wifiVOS);
            if(res == wifiVOS.size()){
                message = wifiVOS.size()+"개의 WIFI 정보를 정상적으로 저장하였습니다.";
            }else{
                message = "WIFI 정보를 가져오지 못했습니다.";
            }
            request.setAttribute("message", message);

            RequestDispatcher view = request.getRequestDispatcher("load-wifi.jsp");
            view.forward(request, response);
        } else if (cmdReq.equals("openApi")) {
            response.sendRedirect("load-wifi.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String cmdReq="";
        cmdReq = request.getParameter("cmd");
        if(cmdReq.equals("insert")){
            WifiDAO dao = new WifiDAO();
            dao.delete();

            String[] wifiList = request.getParameterValues("lib");
            List<WifiVO> wifiVOS = new ArrayList<>();
            for(int i = 0; i < wifiList.length; i++){
                String[] tmp = wifiList[i].split("/");

                WifiVO vo = new WifiVO();
                vo.setX_swifi_mgr_no(tmp[0]);
                vo.setX_swifi_wrdofc(tmp[1]);
                vo.setX_swifi_main_nm(tmp[2]);
                vo.setX_swifi_adres1(tmp[3]);
                vo.setX_swifi_adres2(tmp[4]);
                vo.setX_swifi_instl_floor(tmp[5]);
                vo.setX_swifi_instl_ty(tmp[6]);
                vo.setX_swifi_instl_mby(tmp[7]);
                vo.setX_swifi_svc_se(tmp[8]);
                vo.setX_swifi_cmcwr(tmp[9]);
                vo.setX_swifi_cnstc_year(Integer.parseInt(tmp[10]));
                vo.setX_swifi_inout_door(tmp[11]);
                vo.setX_swifi_remars3(tmp[12]);
                vo.setLat(Float.parseFloat(tmp[13]));
                vo.setLnt(Float.parseFloat(tmp[14]));
                vo.setWork_dttm(tmp[15]);

                wifiVOS.add(vo);
            }

            int res = dao.insert(wifiVOS);
        }
        else if (cmdReq.equals("search")){

        }
    }
}
