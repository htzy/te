package com.monitor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.monitor.util.DBConnection;

public class SensorDataType_functionDAOImplm implements SensorDataType_functionDAO {

	public String[] findSensorDataType() {
		// TODO Auto-generated method stub
		System.out.println("查询数据库");
		
		// 获得连接对象	
		Connection conn = DBConnection.getConnection();
		String selectSQL="select SensorDataTypeID, SensorDataTypeName from SensorDataType";
		
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
			
			//i为临时变量，控制循环str数组下标从零开始
			int i = 0;
			
			while(rs.next()){
		        //方法String getString(int columnIndex)以String 的形式获取此 rs对象的当前行中指定列的值。 
                str[i++] = rs.getString(1);
				//System.out.println("SensorDataTypeID = "+str[i-1]);
				str[i++] = rs.getString(2);
				//System.out.println("SensorDataTypeName = "+str[i-1]);
			}
			
		} catch (SQLException e) {
			System.out.println("SensorDataType_functionDAOImplm:findSensorDataType() is failed !");
			e.printStackTrace();
		} finally {
			
			//释放资源
			DBConnection.free(rs, conn, pstmt);
	   }
		return str;
	}

	public String[] searchSensorDataType(String sensorDataTypeID, String sensorDataTypeName) {

		String[] str = null;
		
		
		//修改生sql语句本页有列子"select SensorDataTypeID, SensorDataTypeName from SensorDataType"
		//+" where SensorDataTypeID like '%"+ SensorDataTypeID
		//+"%' and SensorDataTypeName like '%"+ SensorDataTypeName
		//+"%'";
		
		// 获得连接对象
		Connection conn = DBConnection.getConnection(); 
		String selectSQL="select sensorDataTypeID, sensorDataTypeName from sensorDataType"
							+" where sensorDataTypeID like '%"+ sensorDataTypeID
							+"%' and sensorDataTypeName like '%"+ sensorDataTypeName
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
			
			//i为临时变量，控制循环str数组下标从零开始
			int i = 0;
			
			//要修改System.out.println("SensorDataTypeID = "+str[i-1]);
			
			while(rs.next()){
				//方法String getString(int columnIndex)以String 的形式获取此 rs对象的当前行中指定列的值。 
				str[i++] = rs.getString(1);
				//System.out.println("SensorDataTypeID = "+str[i-1]);
				str[i++] = rs.getString(2);
				//System.out.println("SensorDataTypeName = "+str[i-1]);
			}
			
		} catch (SQLException e) {
			System.out.println("SensorDataType_functionDAOImplm:searchSensorDataType() is failed !");
			e.printStackTrace();
		} finally {
			//释放资源
			DBConnection.free(rs, conn, pstmt);
	   }
		return str;
	}
	

	public boolean changeSensorDataType(String SensorDataTypeID, String newSensorDataTypeName) {
		// TODO Auto-generated method stub
		boolean flag = false;
		System.out.println("查询数据库");
		
		// 获得连接对象
		Connection conn = DBConnection.getConnection(); 
		String selectSQL =  "update SensorDataType set SensorDataTypeName = '"
							+newSensorDataTypeName+ "' where SensorDataTypeID  ="
							+SensorDataTypeID;
		System.out.println(selectSQL);
		 
		// 声明预处理对象
		PreparedStatement pstmt = null;
		try {
			
			 // 获得预处理对象并赋值
			pstmt = conn.prepareStatement(selectSQL);
			
			// 执行查询
			//executeUpdate() 的返回值是一个整数，指示受影响的行数(即更新计数)
			int n = pstmt.executeUpdate(); 
			if(1 == n){
				flag = true;
			}
			
			
		} catch (SQLException e) {
			System.out.println("SensorDataType_functionDAOImplm:changeSensorDataType() is failed !");
			e.printStackTrace();
		} finally {
			
			//释放资源
			DBConnection.freeStatement(null, conn, pstmt);
	   }
		System.out.println(flag);
		return flag;
	}

	public boolean delSensorDataType(String SensorDataTypeID) {
		// TODO Auto-generated method stub
		boolean flag = false;
		System.out.println("删除数据记录");
		
		// 获得连接对象
		Connection conn = DBConnection.getConnection(); 
		String selectSQL="delete from SensorDataType where SensorDataTypeID = "+SensorDataTypeID;
		System.out.println(selectSQL);
		 
		// 声明预处理对象
		PreparedStatement pstmt = null;
		
		try {
			
			 // 获得预处理对象并赋值
			pstmt = conn.prepareStatement(selectSQL);
			
			// 执行查询
			//executeUpdate() 的返回值是一个整数，指示受影响的行数(即更新计数)
			int n = pstmt.executeUpdate();
			
			if(1 == n){
				flag = true;
			}
			
			
		} catch (SQLException e) {
			System.out.println("SensorDataType_functionDAOImplm:delSensorDataType() is failed !");
			e.printStackTrace();
		} finally {
			
			//释放资源
			DBConnection.freeStatement(null, conn, pstmt);
	   }
		System.out.println(flag);
		return flag;
	}

	
	/**
	 * 方法功能解释：通过参数单元类型名SensorDataTypeName，访问数据库
	 * 			 表项SensorDataType,数据表增加一条记录，返回增加记录是否成功
	 * @param SensorDataTypeNameS(包含一个或多个类型名的字符串)
	 * @return boolean
	 */
	public boolean addSensorDataType(String SensorDataTypeNameS) {
		// TODO Auto-generated method stub
       // boolean flag = false;
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
		
		String[] SensorDataTypeName = null;
		
		// 声明预处理对象
		PreparedStatement pstmt = null;
		
		int max = 0;
		
		try{
			String selectSQL = "select max(sensorDataTypeID) from sensordatatype";
			// 获得预处理对象并赋值
			pstmt = conn.prepareStatement(selectSQL);
			// 执行查询
			rs = pstmt.executeQuery();
			while(rs.next()){
				max = Integer.valueOf(rs.getString(1));
			}
		}catch(SQLException e){
			System.out.println("SensorDataType_functionDAOImplm:add_selectSensorDataType() is failed !");
			e.printStackTrace();
		}
		
		try{
			
			//创建Statement对象
			stmt = conn.createStatement();

			//设置JDBC事务的默认提交方式，不自动提交
			//conn.setAutoCommit()的功能是每执行一条SQL语句，就作为一次事务提交。
			//但一般在项目中很有可能需要执行多条SQL语句作为一个事务。若有一个执行不成功，就会rollback（）；
            // autoCommit - 为 true 表示启用自动提交模式；为 false 表示禁用该模式 
             conn.setAutoCommit(false);		
			
			SensorDataTypeName = SensorDataTypeNameS.split(",");
            //System.out.println("SensorDataTypeNameS = "+SensorDataTypeNameS);
			//System.out.println("SensorDataTypeName = "+SensorDataTypeName);
			
			for(int i=0; i<SensorDataTypeName.length; i++){
				System.out.println("SensorDataTypeName = "+SensorDataTypeName[i]);
				max = max + 1;
				//当修改user表为其他名字时，会产生SQLException异常
				//使用addBatch()与executeBatch()批量处理SQL
				stmt.addBatch("insert into sensordatatype(sensorDataTypeID,sensorDataTypeName) value("+max+",'"+SensorDataTypeName[i]+"')"); 
				
			}
			stmt.executeBatch();
			//事务提交
			//commit与rollback只能执行一个，不能同时执行
			conn.commit();
			
			//设置其自动提交模式
			conn.setAutoCommit(true);
			return true;
		}
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
			 //System.out.println("OK1");
		}
		//捕捉数字转换异常            
		//一个try可以跟多个catch()，每个catch()只能捕捉一个异常。
		catch(NumberFormatException e1){ 
		     e1.printStackTrace();
			 //System.out.println("OK2");
		}
		finally{
			
			//释放资源
			 DBConnection.freeStatement(rs, conn, stmt);
			 //System.out.println("OK3");
		}
		//System.out.println("OK4");
		return false;
	}

}
