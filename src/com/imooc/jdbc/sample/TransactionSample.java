package com.imooc.jdbc.sample;

import com.imooc.jdbc.common.DbUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/** JDBC中的事务控制 */
public class TransactionSample {
  public static void main(String[] args) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    try {
      conn = DbUtils.getConnection();
      conn.setAutoCommit(false);
      String sql = "insert into employee(eno,ename,salary,dname) values(?,?,?,?)";
      for (int i = 4000; i < 5000; i++) {
        if (i == 4505) {
          //          throw new RuntimeException("插入失败");
        }
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, i);
        pstmt.setString(2, "员工" + i);
        pstmt.setFloat(3, 4000f);
        pstmt.setString(4, "市场部");
        pstmt.executeUpdate();
      }
      conn.commit(); // 提交数据
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
