package com.mission.mission1.dao;

import com.mission.mission1.vo.HistoryVO;

import java.sql.*;
import java.util.ArrayList;

public class HistoryDAO {
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
    public boolean historyInsert(HistoryVO vo){
        connect();
        String sql = "insert into history (x_point, y_point) values (?, ?)";
        try{
            pstmt = connection.prepareStatement(sql);
            pstmt.setFloat(1, vo.getX_point());
            pstmt.setFloat(2, vo.getY_point());

            pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }finally {
            disconnect();
        }
        return true;
    }

    public HistoryVO read(int id){
        connect();
        HistoryVO historyVO = new HistoryVO();
        String sql = "select * from history where id = (?)";

        try{
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                historyVO.setId(rs.getInt("id"));
                historyVO.setX_point(rs.getFloat("x_point"));
                historyVO.setY_point(rs.getFloat("y_point"));
                historyVO.setTime(String.valueOf(rs.getDate("time")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            disconnect();
        }
        return historyVO;
    }

    public ArrayList<HistoryVO> getHistoryList(){
        connect();
        ArrayList<HistoryVO> historyList = new ArrayList<>();
        String sql = "select * from history";
        try {
            pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                HistoryVO vo = new HistoryVO();
                vo.setId(rs.getInt("id"));
                vo.setX_point(rs.getFloat("x_point"));
                vo.setY_point(rs.getFloat("y_point"));
                vo.setTime(rs.getString("time"));

                historyList.add(vo);
            }
            rs.close();
        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            disconnect();
        }
        return historyList;
    }

    public void delete(HistoryVO vo){
        connect();
        String sql = "delete from history where id = (?)";

        try{
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, vo.getId());
            pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            disconnect();
        }
    }
}
