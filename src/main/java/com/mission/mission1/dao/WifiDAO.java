package com.mission.mission1.dao;

import com.mission.mission1.vo.TestVO;
import com.mission.mission1.vo.WifiVO;

import java.sql.*;
import java.util.List;

public class WifiDAO {
    Connection connection = null;
    PreparedStatement pstmt = null;
    String driver = "org.mariadb.jdbc.Driver";
    String url = "jdbc:mariadb://192.168.0.2:3306/testdb1";
    String dbUserId = "testuser1";
    String dbPassword = "zerobase";

    void connect() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void disconnect() {
        try {
            if (pstmt != null && !pstmt.isClosed()) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int insert(List<WifiVO> wifiVOS){
        connect();
        int res = 0;

        //String sql = "insert into test1 (username, age, sex) values (?, ?, ?)";
        String sql = "insert into wifi " +
                "(x_swifi_mgr_no, x_swifi_wrdofc, x_swifi_main_nm, x_swifi_adres1, x_swifi_adres2, x_swifi_instl_floor, x_swifi_instl_ty, x_swifi_instl_mby, x_swifi_svc_se, x_swifi_cmcwr, x_swifi_cnstc_year, x_swifi_inout_door, x_swifi_remars3, lat, lnt, work_dttm) " +
                "values " +
                "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            pstmt = connection.prepareStatement(sql);
            for(int i = 0; i < wifiVOS.size(); i++){
                WifiVO vo = wifiVOS.get(i);
                pstmt.setString(1, vo.getX_swifi_mgr_no());
                pstmt.setString(2, vo.getX_swifi_wrdofc());
                pstmt.setString(3, vo.getX_swifi_main_nm());
                pstmt.setString(4, vo.getX_swifi_adres1());
                pstmt.setString(5, vo.getX_swifi_adres2());
                pstmt.setString(6, vo.getX_swifi_instl_floor());
                pstmt.setString(7, vo.getX_swifi_instl_ty());
                pstmt.setString(8, vo.getX_swifi_instl_mby());
                pstmt.setString(9, vo.getX_swifi_svc_se());
                pstmt.setString(10, vo.getX_swifi_cmcwr());
                pstmt.setInt(11, vo.getX_swifi_cnstc_year());
                pstmt.setString(12, vo.getX_swifi_inout_door());
                pstmt.setString(13, vo.getX_swifi_remars3());
                pstmt.setFloat(14, vo.getLat());
                pstmt.setFloat(15, vo.getLnt());
                pstmt.setTimestamp(16, java.sql.Timestamp.valueOf(vo.getWork_dttm()));

                pstmt.addBatch();
                if(i % 10000 == 0){
                    pstmt.executeBatch();
                    pstmt.clearBatch();
                    connection.commit();
                }
            }
            int[] result = pstmt.executeBatch();

            for(int i = 0; i < result.length; i++){
                if(result[i] >= 0){
                    res++;
                }
            }
            if(res==wifiVOS.size()){
                connection.commit();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            disconnect();
        }
        return res;
    }

    public boolean create(TestVO vo){
        connect();
        String sql = "insert into test1 (username, age, sex) values (?, ?, ?)";
        try{
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, vo.getName());
            pstmt.setInt(2, vo.getAge());
            pstmt.setString(3, vo.getSex());

            pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }finally {
            disconnect();
        }
        return true;
    }

    public TestVO read(String name){
        connect();
        TestVO testVO = new TestVO();
        String sql = "select * from test1 where name = (?)";

        try{
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                testVO.setName(rs.getString("name"));
                testVO.setAge(rs.getInt("age"));
                testVO.setSex(rs.getString("sex"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            disconnect();
        }

        return testVO;
    }

    void dbSelect() {

    }

    public int delete(){
        connect();
        String sql = " delete * from wifi ";
        int res = 0;
        try{
            pstmt = connection.prepareStatement(sql);
            res = pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            disconnect();
        }
        return res;
    }
}
