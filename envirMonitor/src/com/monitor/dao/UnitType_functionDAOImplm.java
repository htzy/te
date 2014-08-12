package com.monitor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.monitor.util.DBConnection;

public class UnitType_functionDAOImplm implements UnitType_functionDAO {

	@Override
	/**
	 * 方法功能解释：访问数据库，将数据库表项unitType的所有记录全部取出
	 * @param 
	 * @return ResultSet(结果集)
	 */
	public String[] findUnitType() {
		// TODO Auto-generated method stub
		System.out.println("查询数据库");
		
		// 获得连接对象	
		Connection conn = DBConnection.getConnection();
		String selectSQL="select unitTypeID, unitTypeName from unitType";
		
		System.out.println(selectSQL);
		
		// 声明预处理对象
		PreparedStatement pstmt = null; 

		//声明结果集对象
		ResultSet rs = null; 
		String[] str = null;
		try {
			
			 // 获得预处理对象并赋值
			pstmt = conn.prepareStatement(selectSQL);
			
			// 执行查询
			rs = pstmt.executeQuery(); 
			
			//先到最后一行，获得结果集的总行数
			//到结果集的最后一行
			rs.last();
			str = new String[2*(rs.getRow())];
			
			//到结果集的最前面即是第一条记录的前面
			rs.beforeFirst();
			
			//临时变量，控制循环str数组下标从零开始
			int i = 0;
			
			while(rs.next()){
				str[i++] = rs.getString(1);
				//System.out.println("unitTypeID = "+str[i-1]);
				str[i++] = rs.getString(2);
				//System.out.println("unitTypeName = "+str[i-1]);
			}
			
		} catch (SQLException e) {
			System.out.println("unitType_functionDAOImplm:findUnitType() is failed !");
			e.printStackTrace();
		} finally {
			
			//释放资源
			DBConnection.free(rs, conn, pstmt);
	   }
		return str;
	}

	@Override
	/**
	 * 方法功能解释：通过参数单元类型名unitTypeName和unitTypeID，访问数据库
	 * 			 表项unitType，将符合条件的所有记录全部取出
	 * @param unitTypeID
	 * @param unitTypeName
	 * @return String[]
	 */
	public String[] searchUnitType(String unitTypeID, String unitTypeName) {

		String[] str = null;
		// 获得连接对象
		Connection conn = DBConnection.getConnection(); 
		String selectSQL="select unitTypeID, unitTypeName from unittype"
							+" where unitTypeID like '%"+ unitTypeID
							+"%' and unitTypeName like '%"+ unitTypeName
							+"%'";
		
		// 声明预处理对象
		PreparedStatement pstmt = null;
		
		// 声明结果集对象
		ResultSet rs = null; 
		try {

			// 获得预处理对象并赋值
			pstmt = conn.prepareStatement(selectSQL); 
			
			// 执行查询
			rs = pstmt.executeQuery(); 
			//先到最后一行，获得结果集的总行数
			//到结果集的最后一行
			rs.last();
			str = new String[2*(rs.getRow())];
			
			//到结果集的最前面即是第一条记录的前面
			rs.beforeFirst();
			
			//临时变量，控制循环str数组下标从零开始
			int i = 0;
			
			while(rs.next()){
				str[i++] = rs.getString(1);
				System.out.println("unitTypeID = "+str[i-1]);
				str[i++] = rs.getString(2);
				System.out.println("unitTypeName = "+str[i-1]);
			}
			
		} catch (SQLException e) {
			System.out.println("unitType_functionDAOImplm:searchUnitType() is failed !");
			e.printStackTrace();
		} finally {
			//释放资源
			DBConnection.free(rs, conn, pstmt);
	   }
		return str;
	}
	

	@Override
	/**
	 * 方法功能解释：通过参数单元类型名unitTypeID，访问数据库
	 * 			 表项unitType,找到符合条件的记录,将相应字段改为newUnitTypeName，
	 *          返回修改结果
	 * @param unitTypeID
	 * @return boolean
	 */
	public boolean changeUnitType(String unitTypeID, String newUnitTypeName) {
		// TODO Auto-generated method stub
		boolean flag = false;
		System.out.println("查询数据库");
		
		// 获得连接对象
		Connection conn = DBConnection.getConnection(); 
		String selectSQL =  "update unitType set unitTypeName = '"
							+newUnitTypeName+ "' where unitTypeID  ="
							+unitTypeID;
		System.out.println(selectSQL);
		 
		// 声明预处理对象
		PreparedStatement pstmt = null;
		try {
			
			 // 获得预处理对象并赋值
			pstmt = conn.prepareStatement(selectSQL);
			
			// 执行查询
			int n = pstmt.executeUpdate(); 
			if(1 == n){
				flag = true;
			}
			
			
		} catch (SQLException e) {
			System.out.println("unitType_functionDAOImplm:changeUnitType() is failed !");
			e.printStackTrace();
		} finally {
			
			//释放资源
			DBConnection.freeStatement(null, conn, pstmt);
	   }
		System.out.println(flag);
		return flag;
	}

	@Override
	/**
	 * 方法功能解释：通过参数单元类型名unitTypeID，访问数据库
	 * 			 表项unitType,删除符合条件的记录，返回的删除记录是否成功
	 * @param unitTypeID
	 * @return boolean
	 */
	public boolean delUnitType(String unitTypeID) {
		// TODO Auto-generated method stub
		boolean flag = false;
		System.out.println("删除数据记录");
		
		// 获得连接对象
		Connection conn = DBConnection.getConnection(); 
		String selectSQL="delete from unittype where unitTypeID = "+unitTypeID;
		System.out.println(selectSQL);
		 
		// 声明预处理对象
		PreparedStatement pstmt = null;
		
		try {
			
			 // 获得预处理对象并赋值
			pstmt = conn.prepareStatement(selectSQL);
			
			// 执行查询
			int n = pstmt.executeUpdate();
			
			if(1 == n){
				flag = true;
			}
			
			
		} catch (SQLException e) {
			System.out.println("unitType_functionDAOImplm:delUnitType() is failed !");
			e.printStackTrace();
		} finally {
			
			//释放资源
			DBConnection.freeStatement(null, conn, pstmt);
	   }
		System.out.println(flag);
		return flag;
	}

	//@Override
	/**
	 * 方法功能解释：通过参数单元类型名unitTypeName，访问数据库
	 * 			 表项unitType,数据表增加一条记录，返回增加记录是否成功
	 * @param unitTypeNameS(包含一个或多个类型名的字符串)
	 * @return boolean
	 */
	public boolean addUnitType(String unitTypeNameS) {
//		boolean flag = false;
		System.out.println("向数据表增加记录");
		
		/*
		 * 这里实现的为Statement对象，也可以用PreparedStatement
		 */
		Connection conn = null;
		Statement stmt=null; 
		// 声明结果集对象
		ResultSet rs = null; 
		// 获得连接对象
		conn=DBConnection.getConnection(); 
		
		String[] unitTypeName = null;
		try{
			
			//创建Statement对象
			stmt = conn.createStatement();

			//设置JDBC事务的默认提交方式，不自动提交
			conn.setAutoCommit(false);		
			
			unitTypeName = unitTypeNameS.split(",");
			
			// 声明预处理对象
			PreparedStatement pstmt = null;
			
			int max = 0;
			
			try{
				String selectSQL = "select max(unitTypeID) from unittype";
				// 获得预处理对象并赋值
				pstmt = conn.prepareStatement(selectSQL);
				// 执行查询
				rs = pstmt.executeQuery();
				while(rs.next()){
					max = Integer.valueOf(rs.getString(1));
				}
			}catch(SQLException e){
				System.out.println("unitType_functionDAOImplm:add_selectUnit() is failed !");
				e.printStackTrace();
			}
			
			for(int i=0; i<unitTypeName.length; i++){
				System.out.println("unitTypeName = "+unitTypeName[i]);
				max = max + 1;  
				//当修改user表为其他名字时，会产生SQLException异常
				stmt.addBatch("insert into unitType(unitTypeID,unitTypeName) value("+max+" ,'"+unitTypeName[i]+"')"); 
				
			}
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

					// 设置回其自动提交模式
					conn.setAutoCommit(true);
				}catch(SQLException e1){
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

}
