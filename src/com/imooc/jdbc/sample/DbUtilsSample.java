package com.imooc.jdbc.sample;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.imooc.jdbc.hrapp.entity.Employee;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

/** @Auther: 叶孤城 @Date: 2020/11/5 - 11 - 05 - 23:45 @Description: //TODO: @Version: 1.0 */
public class DbUtilsSample {
  public static void main(String[] args) {
    update();
  }

  public static void find() {
    Properties properties = new Properties();
    String propertyFile = DbUtilsSample.class.getResource("/druid-config.properties").getPath();
    try {
      propertyFile = new URLDecoder().decode(propertyFile, "UTF-8");
      properties.load(new FileInputStream(propertyFile));
      DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
      // 利用Appache DbUtils大幅简化了数据的提取过程
      QueryRunner qr = new QueryRunner(dataSource);
      List<Employee> list =
          qr.query(
              "select * from employee limit ?,10",
              new BeanListHandler<>(Employee.class),
              new Object[] {10});

      for (Employee emp : list) {
        System.out.println(emp.getEname());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void update() {
    Properties properties = new Properties();
    String propertyFile = DbUtilsSample.class.getResource("/druid-config.properties").getPath();
    Connection conn = null;
    try {
      propertyFile = new URLDecoder().decode(propertyFile, "UTF-8");
      properties.load(new FileInputStream(propertyFile));
      DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
      // 利用Appache DbUtils大幅简化了数据的提取过程
      conn = dataSource.getConnection();
      // 关闭事务自动提交
      conn.setAutoCommit(false);
      String sql1 = "update employee set salary=salary+1000 where eno=?";
      String sql2 = "update employee set salary=salary-500 where eno=?";
      QueryRunner qr = new QueryRunner();
      qr.update(conn, sql1, new Object[] {4000});
      qr.update(conn, sql2, new Object[] {4001});
      conn.commit();
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
      try {
        if (conn != null && !conn.isClosed()) {
          conn.close();
        }
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }
  }
}
