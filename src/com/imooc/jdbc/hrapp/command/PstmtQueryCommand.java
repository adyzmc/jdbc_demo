package com.imooc.jdbc.hrapp.command;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PstmtQueryCommand implements Command {

  @Override
  public void execute() {
    System.out.println("请输入部门名称：");
    Scanner in = new Scanner(System.in);
    String pdname = in.nextLine();
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    try {
      // 1.加载并注册JDBC驱动
      Class.forName("com.mysql.cj.jdbc.Driver");
      // 2.创建数据库连接
      String url =
          "jdbc:mysql://localhost:3306/immoc?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
      conn = DriverManager.getConnection(url, "root", "Dream600150.");
      // 3.创建Statement对象
      String sql = "SELECT * FROM employee WHERE dname =? AND eno>?";
      PreparedStatement pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, pdname); // 注意：索引从index1开始
      pstmt.setInt(2, 3500);
      // 结果集
      rs = pstmt.executeQuery();
      // 4.遍历查询结果
      // rs.next() 返回布尔值，代表是否存在下……一条记录
      // 如果有，返回true,同时结果集提取下一条记录
      // 如果没有，返回false，循环就会停止
      while (rs.next()) {
        Integer eno = rs.getInt(1); // jdbc中字段索引从1开始，而非0
        String ename = rs.getString("ename");
        Float salary = rs.getFloat("salary");
        String dname = rs.getString("dname");
        System.out.println(eno + "-" + ename + "-" + salary + "-" + dname);
      }
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    } finally {
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

    // 5.关闭连接，释放资源
  }
}
