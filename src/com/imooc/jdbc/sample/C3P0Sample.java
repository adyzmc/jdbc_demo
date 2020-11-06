package com.imooc.jdbc.sample;

import com.imooc.jdbc.common.DbUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.sql.DataSource;

public class C3P0Sample {
  public static void main(String[] args) {
    // 1.加载配置文件
    // 2.创建DataSource
    DataSource dataSource = new ComboPooledDataSource();
    // 3.得到数据库连接
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = dataSource.getConnection();
      pstmt = conn.prepareStatement("select * from employee limit 0,10");
      rs = pstmt.executeQuery();
      while (rs.next()) {
        Integer eno = rs.getInt("eno");
        String ename = rs.getString("ename");
        Float salary = rs.getFloat("salary");
        System.out.println(eno + "-" + ename + "-" + salary + "-");
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DbUtils.closeConnection(rs, pstmt, conn);
    }
  }
}
