package com.yeepay.mysql;



import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Connection;

public class ConMySQL {
	private Properties prop;
	 private Connection conn;
	 
	 public Connection getMyslqConnection(){
	  prop = new Properties();
	  try {
	   prop.load(this.getClass().getClassLoader().getResourceAsStream("config"+File.separator+"db.properties"));
	  } catch (IOException e1) {
	   e1.printStackTrace();
	  }
	  String driver = prop.getProperty("mysqldriver");
	  String url = prop.getProperty("url");
	  String user = prop.getProperty("user");
	  String passwd = prop.getProperty("password");
	  System.out.println(user);
	  try {
	   Class.forName(driver);
	   conn = (Connection) DriverManager.getConnection(url, user, passwd);
	   System.out.println("Mysql Connected Success!");
	  } catch (SQLException e) {
	   System.err.println("sql exception:" + e.getMessage());
	  } catch (ClassNotFoundException e) {
	   System.err.println("Driver Class not found " + e.getMessage());
	  }
	  return conn;
	 }
	 
	 public void closeConnection(Connection conn) {
		  try {
		   if (conn != null) {
		    /** 判断当前连接连接对象如果没有被关闭就调用关闭方法 */
		    if (!conn.isClosed()) {
		     conn.close();
		     System.out.println("mysql链接释放……");
		    }
		   }
		  } catch (Exception ex) {
		   ex.printStackTrace();
		  }
		 }
	 
}
