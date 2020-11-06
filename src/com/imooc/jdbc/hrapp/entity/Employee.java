package com.imooc.jdbc.hrapp.entity;

import java.util.Date;

/** 员工实体类 */
public class Employee {

  private Integer eno;
  private String ename;
  private Float salary;
  private String dname;
  private Date hiredate;

  /** 1.具备默认构造函数 2.属性私有 3.存在getter与setter */
  public Employee() {}

  public void setEno(Integer eno) {
    this.eno = eno;
  }

  public void setEname(String ename) {
    this.ename = ename;
  }

  public void setSalary(Float salary) {
    this.salary = salary;
  }

  public void setDname(String dname) {
    this.dname = dname;
  }

  public void setHiredate(Date hiredate) {
    this.hiredate = hiredate;
  }

  public Integer getEno() {
    return eno;
  }

  public String getEname() {
    return ename;
  }

  public Float getSalary() {
    return salary;
  }

  public String getDname() {
    return dname;
  }

  public Date getHiredate() {
    return hiredate;
  }
}
