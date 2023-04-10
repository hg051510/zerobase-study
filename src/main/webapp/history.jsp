<%@ page import="com.mission.mission1.vo.HistoryVO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: kim_dudu
  Date: 2023-04-07
  Time: 오후 6:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>와이파이 정보 구하기</title>
        <link rel = "stylesheet" type = "text/css" href = "style.css?after"/>
    </head>
    <body>
        <h1>와이파이 정보 구하기</h1>
        <div>
            <a href="home.jsp">홈</a>
            |
            <a href="">위치 히스토리 목록</a>
            |
            <a href="">Open API 와이파이 정보 구하기</a>
        </div>
        <br>
        <table>
            <tr>
                <th>ID</th>
                <th>X좌표</th>
                <th>Y좌표</th>
                <th>조회일자</th>
                <th>비고</th>
            </tr>
            <%
                List<HistoryVO> historyList = (List<HistoryVO>)request.getAttribute("historyList");
                for(HistoryVO vo : historyList){
            %>
            <tr>
                <td><%= vo.getId()%></td>
                <td><%= vo.getX_point()%></td>
                <td><%= vo.getY_point()%></td>
                <td><%= vo.getTime()%></td>
                <td align="center"><button type="button" onclick="location.href='HistoryServlet?cmd=delete&id=<%=vo.getId()%>'">삭제</button></td>
            </tr>
            <%
                }
            %>
        </table>
    </body>
</html>
