package com.imooc.jdbc.sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 标准JDBC操作五步骤
 */
public class StandardJDBCSample {

  public static void main(String[] args) {
    Connection conn= null;
    try {
      //1.加载并注册JDBC驱动
      Class.forName("com.mysql.cj.jdbc.Driver");
      //2.创建数据库连接
      conn = DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/immoc?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai"
          ,"root","Dream600150."
      );
      //3.创建Statement对象
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT  * FROM employee");
      //4.遍历查询结果
      while (rs.next()){
        // 当前行指定位置的数据进行提取
        Integer eno= rs.getInt(1);  //eno
        String ename= rs.getString("ename");
        Float salary = rs.getFloat("salary");
        String dname = rs.getString("dname");
        System.out.println(dname + "-" + eno + "-" + ename + "-" + salary);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }  finally {
      //5.关闭连接，释放资源
      try {
        if(conn !=null && conn.isClosed() ==false){
          conn.close();
        }
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }
}
