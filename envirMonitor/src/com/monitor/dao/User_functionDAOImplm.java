package com.monitor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.monitor.bean.unit;
import com.monitor.util.DBConnection;

public class User_functionDAOImplm implements User_functionDAO{

	@Override
	public int findUser(String username, String password) {
		int UserID=-1;
		String UserName="'"+username+"'";
		String UserPassword="'"+password+"'";
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		String selectSQL="select UserID from user where state='1' and UserName="+UserName+" and UserPassword="+UserPassword;
		System.out.println(selectSQL);
		PreparedStatement pstmt = null; // 声明预处理对象
		ResultSet rs = null; // 声明结果集对象
		try {
			pstmt = conn.prepareStatement(selectSQL); // 获得预处理对象并赋值
			rs = pstmt.executeQuery(); // 执行查询
			while(rs.next()) {
				UserID=rs.getInt("UserID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.free(rs, conn, pstmt);//释放资源
	   }
		return UserID;
	}

	@Override
	public int getUserRole(int UserID) {
		int roleID=-1;
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		String selectSQL="select roleID from user_role where UserID="+UserID;
		PreparedStatement pstmt = null; // 声明预处理对象
		ResultSet rs = null; // 声明结果集对象
		try {
			pstmt = conn.prepareStatement(selectSQL); // 获得预处理对象并赋值
			rs = pstmt.executeQuery(); // 执行查询
			while(rs.next()) {
				roleID=rs.getInt("roleID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.free(rs, conn, pstmt);//释放资源
	   }
		return roleID;
	}
}
