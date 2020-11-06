package com.imooc.jdbc.hrapp.command;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class QueryCommand implements Command {

  @Override
  public void execute(){
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
      stmt = conn.createStatement();
      //sql注入攻击根本原理：在输入的数据中并没有对单引号这样子的特殊字符进行处理，导致单引号原封不动的加入到sql中，导致查询失效,这就是sql注入
      rs =  stmt.executeQuery("SELECT * FROM employee WHERE dname='"+pdname+"'");
      // 4.遍历查询结果
      // rs.next() 返回布尔值，代表是否存在下一条记录
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
    } finally{
      //5.关闭连接，释放资源
      try {
        if(rs !=null ){
          rs.close();
        }
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
      try {
        if(stmt !=null){
          stmt.close();
        }
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
      try {
        if(conn !=null && !conn.isClosed()){
          conn.close();
        }
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }

    }

    //5.关闭连接，释放资源
  }

}
