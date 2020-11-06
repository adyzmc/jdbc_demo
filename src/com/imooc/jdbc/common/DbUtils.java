package com.imooc.jdbc.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/** @Auther: 叶孤城 @Date: 2020/11/3 - 11 - 03 - 18:50 @Description: //TODO: @Version: 1.0 */
public class DbUtils {

  /**
   * 创建新的数据库连接
   *
   * @return 新的Connection对象
   * @throws ClassNotFoundException
   * @throws SQLException
   */
  public static Connection getConnection() throws ClassNotFoundException, SQLException {
    // 1.加载并注册JDBC驱动
    Class.forName("com.mysql.cj.jdbc.Driver");
    // 2.创建数据库连接
    Connection conn =
        DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/immoc?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai",
            "root",
            "Dream600150.");
    return conn;
  }

  /**
   * 关闭连接，释放资源
   *
   * @param rs 结果集对象
   * @param stmt Statement对象
   * @param conn Connection对象
   */
  public static void closeConnection(ResultSet rs, Statement stmt, Connection conn) {
    // 5.关闭连接，释放资源
    try {
      if (rs != null) {
        rs.close();
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    try {
      if (stmt != null) {
        stmt.close();
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    try {
      if (conn != null && !conn.isClosed()) {
        conn.close();
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }
}
