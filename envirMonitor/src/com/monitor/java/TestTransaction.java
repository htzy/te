package com.monitor.java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.monitor.util.DBConnection;

public class TestTransaction {       //用事务完成修改用户密码的功能

	/**
	 * @param args
	 */
	
	
	
	/*
	conn.setAutoCommit(false);
	PreparedStatement pstmt1 = con.prepareStatement(sql1);
	pstm1.setInt(1,xxxx)   //根据数据库的字段类型选择试用setInt，setLong，setString等。。。
	//设置完PreparedStatement 
	pstm1.execute(sql1);
	PreparedStatement pstmt2 = con.prepareStatement(sql2);
	pstm2.setInt(1,xxxx)   
	//设置完PreparedStatement
	pstm2.executeUpdate(sql2);
	conn.commit();
	自己加个各种关闭操作和异常处理
	 * */
	public static boolean  test(String UserPassword,int UserID){
		/*
		 * 这里实现的为Statement对象，也可以用PreparedStatement
		 */
		Connection conn = null;
		Statement stmt=null; 
		// 声明结果集对象
		ResultSet rs = null; 
		// 获得连接对象
		conn=DBConnection.getConnection(); 
		try{
			
			//创建Statement对象
			stmt = conn.createStatement();

			//设置JDBC事务的默认提交方式，不自动提交
			conn.setAutoCommit(false);		
			
			stmt.addBatch("update user set UserPassword='"+UserPassword+"' where UserID="+UserID);
			
			//当修改user表为其他名字时，会产生SQLException异常
			stmt.addBatch("insert into user(UserName,UserPassword,state) values('周' ,'8888','1')"); 
			
			stmt.executeBatch();
			
			//事务提交
			//commit与rollback只能执行一个，不能同时执行
			conn.commit();
			
			//设置回其自动提交模式
			conn.setAutoCommit(true);
			return true;
		}
		/*
		catch(Exception e){   //Exception是所有异常对象的基类
			 System.out.println("OK2");
		}*/
		catch(SQLException e){
			 //e.printStackTrace();
			//如果该链接不为空，说明执行了sql语句，但是该语句又抛出了异常，则回滚
			if(conn!=null){ 
				try {
					conn.rollback();
					
					//设置回其自动提交模式
					conn.setAutoCommit(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			 System.out.println("OK2");
		}
		//捕捉数字转换异常            //一个try可以跟多个catch()，每个catch()只能捕捉一个异常。
		catch(NumberFormatException e1){ 
		     e1.printStackTrace();
			 System.out.println("OK23333333");
		}
		finally{
			
			//释放资源
			 DBConnection.freeStatement(rs, conn, stmt);
			 System.out.println("OK3");
		}
		System.out.println("OK4");
		return false;
	}
	
	
	
	public static void main(String[] args) {
		boolean flag=test("566",14);
        System.out.println(flag);
	}

}
