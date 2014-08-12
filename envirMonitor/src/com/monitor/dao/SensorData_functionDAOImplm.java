package com.monitor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.monitor.util.DBConnection;

public class SensorData_functionDAOImplm implements SensorData_functionDAO {

	@Override
	/**
	 *根据参数开始时间startTime和结束时间endTime查询表，并将结果返回
	 *@param startTime
	 *@param endTime
	 *@return String[]
	 */
	public String[] searchSensorData(String startTime, String endTime) {
//		ArrayList  list = new ArrayList();
		String[] str =null;
		//建立连接
		//Connection conn = DBConnection.getConnection();
		Connection conn = DBConnection.getConnection();
		//开始时间     '2014-06-10 00:00:00' 	
		startTime="'"+startTime+"'";
		
		//结束时间
		endTime="'"+endTime+"'";
		String selectSQL = "select sensorDataID, from_unixtime(samplingTime/1000)"
				+" as samplingTime, value1, value2, value3 from sensordata"
				+" where from_unixtime(samplingTime/1000) >= "+startTime
				+ " and from_unixTime(samplingTime/1000) <= "+endTime;
		//预处理对象
		PreparedStatement pstmt = null;
		System.out.println(selectSQL);
		//声明结果集
		ResultSet rs = null;
		
		try{
			// 获得预处理对象并赋值
			pstmt = conn.prepareStatement(selectSQL); // 获得预处理对象并赋值
//			System.out.println("+++++++++++++++++++++++");
			//获得结果集
			rs = pstmt.executeQuery();
//			System.out.println("----------------------");
			System.out.println(rs);

			rs.last();
			str = new String[5*(rs.getRow())];
			
			//到结果集的最前面即是第一条记录的前面
			rs.beforeFirst();
			
			//临时变量，控制循环str数组下标从零开始
			int i = 0;
			while(rs.next()){
				str[i++] = rs.getString("sensorDataID");
//				System.out.println("sensorDataID = "+str[i-1]);
				String str1 =  rs.getString("samplingTime");
				str[i++] = str1.substring(0, str1.length()-2);
//				System.out.println("samplingTime = "+str[i-1]);
				str[i++] = rs.getString("value1");
				str[i++] = rs.getString("value2");
				str[i++] = rs.getString("value3");
//				sensordata sd = new sensordata();
//				System.out.println(rs.getInt("sensorDataID"));
//				sd.setSensorDataID(rs.getInt("sensorDataID"));
//				sd.getSensorDataID();
//				sd.setSamplingTime(rs.getString("samplingTime"));
//				sd.getSamplingTime();
//				sd.setValue1(rs.getDouble("value1"));
//				sd.getValue1();
//				sd.setValue2(rs.getDouble("value2"));
//				sd.getValue2();
//				sd.setValue3(rs.getDouble("value3"));
//				sd.getValue3();
//				list.add(sd);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBConnection.free(rs, conn, pstmt);
		}
//		System.out.println(list);
		//Iterator in = list.iterator();
		//while(in.hasNext()){
			//sensordata sd = new sensordata();
			
			//System.out.println(1);
	//	}
		return str;
	}

	@Override
	/**
	 * 根据参数sensorDataID，value1，value2，value3修改数据库中符合条件的记录
	 * @param sensorDataID
	 * @param samplingTime
	 * @param value1
	 * @param value2
	 * @param value3
	 * @return boolean
	 */
	public boolean changeSensorData(String sensorDataID, String samplingTime ,String value1,
			String value2, String value3) {
				boolean flag = false;
				
				//建立连接
				Connection conn = DBConnection.getConnection();
				//建立预处理对象
				PreparedStatement pstmt = null;
				
				//SQL语句
				String changeSQL = "update sensorData set samplingTime = UNIX_TIMESTAMP('"+samplingTime 
													+"') , value1 = "+value1 +", value2 ="
													+value2+ " ,value3 = "+value3
													+" where sensorDataID ="+sensorDataID; 
				
				System.out.println(changeSQL);
				try{
					//预处理
					pstmt = conn.prepareStatement(changeSQL);
					
					//访问数据库
					int n = pstmt.executeUpdate(changeSQL);
					if(n == 1){
						flag = true;
					}
				}catch(SQLException e){
					e.printStackTrace();
				}finally{
					DBConnection.free(null, conn, pstmt);
				}
				return flag;
	}

	@Override
    /**
     * 根据参数sensorDataID，删除数据库中符合条件的记录
     * @param sensorData
     * @return boolean
     */
	public boolean delSeneorData(String sensorDataID) {
		boolean flag = false;
		
		//建立连接
		Connection conn =DBConnection.getConnection();
		
		//建立预处理对象
		PreparedStatement pstmt = null;
		
		String delSQL = "delete from sensorData where sensorDataID = "+sensorDataID;
		
		System.out.println(delSQL);
		try{
			
			//预处理访问数据库
			pstmt = conn.prepareStatement(delSQL);
			
			int n = pstmt.executeUpdate();
			if(n == 1){
				flag = true;
			}
			System.out.println(flag);
			
		}catch(SQLException e){
			System.out.println("sensorData_functionDAOImplm:delSensorData() is failed !");
			e.printStackTrace();
		}finally{
			DBConnection.freeStatement(null, conn, pstmt);
		}
		return flag;
	}
}
