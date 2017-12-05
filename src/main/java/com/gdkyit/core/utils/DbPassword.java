package com.gdkyit.core.utils;

import com.alibaba.druid.filter.config.ConfigTools;

/**
 * 数据库密码加密工具
 * 使用方法：修改变量password为数据库连接密码，然后执行本类，生成相应的key，把key填写进properties配置文件中
 */
public class DbPassword {
    private static String[] password = {"replace your password here"};
    public static void printPassword(){
      try {
          ConfigTools.main(password);
      } catch (Exception e) {
          e.printStackTrace();
      }

    }
    public static void main(String[] arg){
       printPassword();
    }
}
