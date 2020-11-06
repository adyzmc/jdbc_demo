package com.imooc.jdbc.hrapp;

import com.imooc.jdbc.hrapp.command.Command;
import com.imooc.jdbc.hrapp.command.DeleteCommand;
import com.imooc.jdbc.hrapp.command.InsertCommand;
import com.imooc.jdbc.hrapp.command.PaginationCommand;
import com.imooc.jdbc.hrapp.command.PstmtQueryCommand;
import com.imooc.jdbc.hrapp.command.UpdateCommand;
import java.util.Scanner;

public class HumanResourceApplication {
  public static void main(String[] args) {
    System.out.println("1-查询部门员工");
    System.out.println("2-办理员工入职");
    System.out.println("3-调整员工薪资");
    System.out.println("4-员工离职手续办理");
    System.out.println("5-分页查询员工数据");
    System.out.println("请选择功能:");
    Scanner in = new Scanner(System.in);
    Integer cmd = in.nextInt();
    Command command = null;
    switch (cmd) {
        // 查询部门员工
      case 1:
        command = new PstmtQueryCommand();
        command.execute();
        break;
        // 办理员工入职
      case 2:
        command = new InsertCommand();
        command.execute();
        break;
        // 员工调整薪资
      case 3:
        command = new UpdateCommand();
        command.execute();
        break;
        // 员工离职手续办理
      case 4:
        command = new DeleteCommand();
        command.execute();
        break;
      case 5:
        command = new PaginationCommand();
        command.execute();
        break;

      default:
    }
  }
}
