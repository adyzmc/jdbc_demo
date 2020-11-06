package com.imooc.jdbc.sample;

import com.imooc.jdbc.common.DbUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/** 批处理 */
public class BatchSample {

  public static void main(String[] args) {
    tc1();
    tc2();
  }

  // 标准方式插入若干数据
  private static void tc1() {
    Connection conn = null;
    PreparedStatement pstmt = null;
    try {
      long startTime = new Date().getTime();
      conn = DbUtils.getConnection();
      conn.setAutoCommit(false);
      String sql = "insert into employee(eno,ename,salary,dname) values(?,?,?,?)";
      for (int i = 100000; i < 200000; i++) {
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, i);
        pstmt.setString(2, "员工" + i);
        pstmt.setFloat(3, 4000f);
        pstmt.setString(4, "市场部");
        pstmt.executeUpdate();
      }
      conn.commit(); // 提交数据
      long endTime = new Date().getTime();
      System.out.println("tc1()执行时长： " + (endTime - startTime));
    } catch (Exception e) {
      e.printStackTrace();
      try {
        if (conn != null && !conn.isClosed()) {
          conn.rollback();
        }
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    } finally {
      //      pstm
      DbUtils.closeConnection(null, pstmt, conn);
    }
  }

  /** 使用批处理插入若干数据 */
  private static void tc2() {
    Connection conn = null;
    PreparedStatement pstmt = null;
    try {
      long startTime = new Date().getTime();
      conn = DbUtils.getConnection();
      conn.setAutoCommit(false);
      String sql = "insert into employee(eno,ename,salary,dname) values(?,?,?,?)";
      pstmt = conn.prepareStatement(sql);
      for (int i = 200000; i < 300000; i++) {
        pstmt.setInt(1, i);
        pstmt.setString(2, "员工" + i);
        pstmt.setFloat(3, 4000f);
        pstmt.setString(4, "市场部");
        pstmt.addBatch(); // 将参数加入批处理任务中
        //        pstmt.executeUpdate();
      }
      pstmt.executeBatch(); // 执行批处理任务
      conn.commit(); // 提交数据
      long endTime = new Date().getTime();
      System.out.println("tc2()执行时长： " + (endTime - startTime));
    } catch (Exception e) {
      e.printStackTrace();
      try {
        if (conn != null && !conn.isClosed()) {
          conn.rollback();
        }
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    } finally {
      //      pstm
      DbUtils.closeConnection(null, pstmt, conn);
    }
  }
}
