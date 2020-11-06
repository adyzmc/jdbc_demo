package com.imooc.jdbc.sample;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.imooc.jdbc.common.DbUtils;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import javax.sql.DataSource;

/** Druid连接池配置与使用 */
public class DruidSample {
  public static void main(String[] args) {
    // 1、加载属性文件
    Properties properties = new Properties();
    String propertyFile = DruidSample.class.getResource("/druid-config.properties").getPath();
    //  空格->%20 /usr/local/java code/druid-config.properties
    // /usr/local/java%20code/druid-config.properties
    try {
      propertyFile = new URLDecoder().decode(propertyFile, "UTF-8");
      properties.load(new FileInputStream(propertyFile));
    } catch (Exception e) {
      e.printStackTrace();
    }

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      // 2. 获取DataSource数据源对象
      DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
      // 3. 创建数据库连接
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
      /** 不使用连接池:conn.close()关闭连接 使用连接池:conn.close()将回收至连接池 */
      DbUtils.closeConnection(rs, pstmt, conn);
    }
  }
}
