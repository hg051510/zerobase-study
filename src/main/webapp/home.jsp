<%--
  Created by IntelliJ IDEA.
  User: kim_dudu
  Date: 2023-04-09
  Time: 오후 1:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
    <head>
        <title>와이파이 정보 구하기</title>
        <link rel = "stylesheet" type = "text/css" href = "style.css?after"/>
        <style>
            .target{
                margin-left : 5px;
                margin-right : 2px;
            }
            #default{
                text-align : center;
                height: 100px;
            }
            #special{
                background-color: white;
            }
        </style>
        <script>
            function clickBtn(){
                window.navigator.geolocation.getCurrentPosition(function (position){
                    var lat = position.coords.latitude;
                    var lnt = position.coords.longitude;
                    // $('#lat').val(lat);
                    // $('#lnt').val(lnt);

                    document.getElementById('lat').value = lat;
                    document.getElementById('lnt').value = lnt;

                    location.href = "HistoryServlet?cmd=insert&lat1=" + lat + "&lnt1="+ lnt;
                },
                function (error){
                    switch (error.code){
                        case error.PERMISSION_DENIED:
                            str = "사용자 거부"
                            break;
                        case error.POSITION_UNAVAILABLE:
                            str = "지리정보 없음"
                            break;
                        case error.TIMEOUT:
                            str = "시간 초과"
                            break;
                        case error.UNKNOWN_ERROR:
                            str = "알수없는 에러";
                            break;
                    }
                    document.getElementById('target').innerHTML=str;
                })


                const table = document.getElementById('default');
                table.style.display = 'none';
            }
        </script>
    </head>
    <body>
        <h1>와이파이 정보 구하기</h1>
        <div>
            <a href="home.jsp" target = "_self">홈</a>
            |
            <a href="HistoryServlet?cmd=historyList" target = "_self">위치 히스토리 목록</a>
            |
            <a href="WifiServlet?cmd=openApi" target = "_self">Open API 와이파이 정보 구하기</a>
        </div>
        <div>
            </br>
            <form action="WifiServlet?cmd=search" method="post">
                <label>LAT:<input class = "target" id = "lat" type="number" step = "0.0000001" placeholder="위도" value="0.0"></label>,
                <label>LNT:<input class = "target" id = "lnt" type="number" step = "0.0000001" placeholder="경도" value="0.0"></label>
                <input type="button" value = "내 위치 가져오기" onclick="clickBtn()">
                <input type="submit" value = "근처 WIFI 정보 보기">
            </form>
        </div>
        <div>
            <table>
                <tr>
                    <th>거리 <br>(Km)</th>
                    <th>관리번호</th>
                    <th>자치구</th>
                    <th>와이파이명</th>
                    <th>도로명주소</th>
                    <th>상세주소</th>
                    <th>설치위치<br>(층)</th>
                    <th>설치유형</th>
                    <th>설치기관</th>
                    <th>서비스구분</th>
                    <th>망종류</th>
                    <th>설치년도</th>
                    <th>실내외구분</th>
                    <th>WIFI접속환경</th>
                    <th>X좌표</th>
                    <th>Y좌표</th>
                    <th>작업일자</th>
                </tr>
            </table>
            <table id = "default">
                <tr>
                    <td id = "special">위치 정보를 입력한 후에 조회해 주세요.</td>
                </tr>
            </table>
        </div>

    </body>
</html>
