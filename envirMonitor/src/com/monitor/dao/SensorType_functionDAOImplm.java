package com.monitor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.monitor.util.DBConnection;

/**
 * 
 * @author htzy
 * 
 */
public class SensorType_functionDAOImplm implements SensorType_functionDAO{

	@Override
	/**
	 * 方法功能解释：访问数据库，将数据库表项SensorType的所有记录全部取出
	 * @param 
	 * @return ResultSet(结果集) String[]
	 */
	public String[] findSensorType(){
		// TODO Auto-generated method stub

		System.out.println("查询所有传感器类型数据记录");
		// 获得连接对象
		Connection conn = DBConnection.getConnection();
		String selectSQL = "select sensorTypeID, sensorTypeName from sensortype";

		System.out.println(selectSQL);

		// 声明预处理对象
		PreparedStatement pstmt = null;

		// 声明结果集对象
		ResultSet rs = null;
		String[] str = null;
		try{

			// 获得预处理对象并赋值
			pstmt = conn.prepareStatement(selectSQL);

			// 执行查询
			rs = pstmt.executeQuery();

			// 先到最后一行，获得结果集的总行数
			// 到结果集的最后一行
			rs.last();
			str = new String[2 * (rs.getRow())];

			// 到结果集的最前面即是第一条记录的前面
			rs.beforeFirst();

			// 临时变量，控制循环str数组下标从零开始
			int i = 0;

			// 将查询结果交替放在str中
			while(rs.next()){
				str[i++] = rs.getString(1);
				str[i++] = rs.getString(2);
			}

		}catch(SQLException e){
			System.err
					.println("SensorType_functionDAOImplm:findSensorType() is failed !"
							+ "数据库服务可能没有打开，或者可能是数据库出现异常错误，请检查重试");
			e.printStackTrace();
		}
		finally{
			// 关闭连接，释放资源
			DBConnection.free(rs, conn, pstmt);
		}
		return str;
	}

	@Override
	/**
	 * 通过传感器单元类型名sensorTypeName访问数据库表项sensorType，将符合条件的所有记录全部取出
	 * @param SensorTypeID
	 * @param SensorTypeName
	 * @return ResultSet(结果集) String[]
	 */
	public String[] searchSensorType(String sensorTypeName){
		System.out.println("查询某条传感器类型数据记录");
		String[] str = null;
		// 获得连接对象
		Connection conn = DBConnection.getConnection();
		String selectSQL = "select sensorTypeID, sensorTypeName from sensorType"
				+ " where sensorTypeName like '%" + sensorTypeName + "%'";

		// 声明预处理对象
		PreparedStatement pstmt = null;

		// 声明结果集对象
		ResultSet rs = null;
		try{

			// 获得预处理对象并赋值
			pstmt = conn.prepareStatement(selectSQL);

			// 执行查询
			rs = pstmt.executeQuery();

			// 先到最后一行，获得结果集的总行数
			// 到结果集的最后一行
			rs.last();
			str = new String[2 * (rs.getRow())];

			// 到结果集的最前面即是第一条记录的前面
			rs.beforeFirst();

			// 临时变量，控制循环str数组下标从零开始
			int i = 0;

			// 将结果交替放在数组str中
			while(rs.next()){
				str[i++] = rs.getString(1);
				str[i++] = rs.getString(2);
			}

		}catch(SQLException e){
			System.err
					.println("SensorType_functionDAOImplm:searchSensorType() is failed !"
							+ "可能是数据库出现异常错误，请检查重试");
			e.printStackTrace();
		}
		finally{
			// 释放资源
			DBConnection.free(rs, conn, pstmt);
		}
		return str;
	}

	@Override
	/**
	 * 通过参数传感器类型ID——sensorTypeID，访问数据库表项sensorType,找到符合
	 * 条件的记录,将相应字段改为newSensorTypeName，返回修改结果是否成功
	 * @param SensorTypeID
	 * @return 修改成功返回true,修改失败返回false
	 */
	public boolean
			changeSensorType(String sensorTypeID, String newSensorTypeName){
		// TODO Auto-generated method stub
		System.out.println("修改某条传感器类型数据记录");
		boolean flag = false;

		// 获得连接对象
		Connection conn = DBConnection.getConnection();
		String selectSQL = "update SensorType set sensorTypeName='"
				+ newSensorTypeName + "' where sensorTypeID  =" + sensorTypeID;
		System.out.println(selectSQL);

		// 声明预处理对象
		PreparedStatement pstmt = null;
		try{

			// 获得预处理对象并赋值
			pstmt = conn.prepareStatement(selectSQL);

			// 执行修改
			int n = pstmt.executeUpdate();

			// 结果为1，说明修改且仅修改一条，说明执行成功
			if(1 == n){
				flag = true;
				System.out.println("修改成功");
			}

		}catch(SQLException e){
			System.err
					.println("SensorType_functionDAOImplm:changeSensorType() is failed !"
							+ "可能是数据库出现异常错误，请检查重试");
			e.printStackTrace();
		}
		finally{

			// 释放资源
			DBConnection.freeStatement(null, conn, pstmt);
		}
		return flag;
	}

	@Override
	/**
	 * 通过参数单元类型名sensorTypeID，访问数据库表项SensorType,
	 * 删除符合条件的记录，返回的删除记录是否成功
	 * @param sensorTypeID
	 * @return 成功返回true，失败返回false
	 */
	public boolean delSensorType(String sensorTypeID){
		// TODO Auto-generated method stub
		boolean flag = false;
		System.out.println("删除数据记录");

		// 获得连接对象
		Connection conn = DBConnection.getConnection();
		String selectSQL = "delete from sensorType where SensorTypeID = "
				+ sensorTypeID;
		System.out.println(selectSQL);

		// 声明预处理对象
		PreparedStatement pstmt = null;

		try{

			// 获得预处理对象并赋值
			pstmt = conn.prepareStatement(selectSQL);

			// 执行删除
			int n = pstmt.executeUpdate();

			if(1 == n){
				flag = true;
				System.out.println("删除成功");
			}

		}catch(SQLException e){
			System.err
					.println("SensorType_functionDAOImplm:delSensorType() is failed !"
							+ "可能是数据库出现异常错误，请检查重试");
			e.printStackTrace();
		}
		finally{
			// 释放资源
			DBConnection.freeStatement(null, conn, pstmt);
		}
		return flag;
	}

	// @Override
	/**
	 * 通过参数单元类型名SensorTypeName，访问数据库 表项SensorType, 数据表增加一条记录，返回增加记录是否成功
	 * 
	 * @param sensorTypeNameS
	 *            (包含一个或多个传感器类型名的字符串)
	 * @return 增加成功返回true,增加失败返回false
	 */
	public boolean addSensorTypes(String sensorTypeNameS){
		// TODO Auto-generated method stub
		System.out.println("增加记录");

		Connection conn = null;
		Statement stmt = null;
		// 声明结果集对象
		ResultSet rs = null;
		// 获得连接对象
		conn = DBConnection.getConnection();

		String[] sensorTypeName = null;
		
		// 声明预处理对象
		PreparedStatement pstmt = null;
		
		int max = 0;
		
		try{
			String selectSQL = "select max(sensorTypeID) from sensortype";
			// 获得预处理对象并赋值
			pstmt = conn.prepareStatement(selectSQL);
			// 执行查询
			rs = pstmt.executeQuery();
			while(rs.next()){
				max = Integer.valueOf(rs.getString(1));
			}
		}catch(SQLException e){
			System.out.println("sensorType_functionDAOImplm:add_selectSensorType() is failed !");
			e.printStackTrace();
		}
		
		try{
			// 创建Statement对象
			stmt = conn.createStatement();

			// 设置JDBC事务的默认提交方式，不自动提交
			conn.setAutoCommit(false);

			sensorTypeName = sensorTypeNameS.split(",");

			for(int i = 0; i < sensorTypeName.length; i++){
				max = max + 1;
				// 当修改user表为其他名字时，会产生SQLException异常
				stmt.addBatch("insert into sensortype(sensorTypeID,sensorTypeName) values("+max +", '"
						+ sensorTypeName[i] + "')");

				stmt.executeBatch();

			}
			/*
			 * 事务提交,commit与rollback只能执行一个，不能同时执行
			 */
			conn.commit();

			// 设置回其自动提交模式
			conn.setAutoCommit(true);
			return true;
		}catch(SQLException e){
			if(conn != null){
				try{
					conn.rollback();
					// 设置回其自动提交模式
					conn.setAutoCommit(true);
				}catch(SQLException e1){
					e1.printStackTrace();
				}
			}
		}catch(NumberFormatException e1){
			e1.printStackTrace();
		}
		finally{
			// 释放资源
			DBConnection.freeStatement(rs, conn, stmt);
		}
		return false;
	}

}
