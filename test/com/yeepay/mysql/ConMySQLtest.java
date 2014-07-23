package com.yeepay.mysql;


import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
public class ConMySQLtest {
	
	public static void main(String[] args) throws SQLException {
		ConMySQL conslq=new ConMySQL();
		Connection conn;
		Statement sm=null;
		ResultSet rs =null;
		PreparedStatement ps = null;
		conn=conslq.getMyslqConnection();
		String sql="select * from frp;";
		sm=(Statement) conn.createStatement();		
		 rs = (ResultSet) sm.executeQuery(sql);
		
		while(rs.next()){
		System.out.println(rs.getInt("id"));
		}
		conslq.closeConnection(conn);
		
	}
}
