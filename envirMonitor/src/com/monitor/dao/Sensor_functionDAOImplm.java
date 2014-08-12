package com.monitor.dao;

import java.io.Console;
import java.math.BigInteger;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.monitor.bean.sensor;
import com.monitor.util.DBConnection;
import com.monitor.bean.sensordata;

public  class Sensor_functionDAOImplm implements Sensor_functionDAO{


	@Override
	public ArrayList findSensorByUnitID(int unitID) {
		ArrayList list=new ArrayList();//初始化链表
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		String selectSQL="select * from sensor where unitID="+unitID;
		PreparedStatement pstmt = null; // 声明预处理对象
		ResultSet rs = null; // 声明结果集对象
		try {
			pstmt = conn.prepareStatement(selectSQL); // 获得预处理对象并赋值
			rs = pstmt.executeQuery(); // 执行查询
			while(rs.next()) {
				sensor sen=new sensor();
				sen.setSensorID(rs.getInt("sensorID"));
				sen.setUnitID(rs.getInt("unitID"));
				sen.setEmbededIP(rs.getInt("embededIP"));
				sen.setNodeIP(rs.getInt("nodeIP"));
				sen.setChannelNo(rs.getInt("channelNo"));
				sen.setPortType(rs.getInt("portType"));
				sen.setSensorTypeID1(rs.getInt("sensorTypeID1"));
				sen.setSensorDataTypeID1(rs.getInt("sensorDataTypeID1"));
				sen.setMinValue1(rs.getDouble("minValue1"));
				sen.setMaxValue1(rs.getDouble("maxValue1"));
				sen.setSensorTypeID2(rs.getInt("sensorTypeID2"));
				sen.setSensorDataTypeID2(rs.getInt("sensorDataTypeID2"));
				sen.setMinValue2(rs.getDouble("minValue2"));
				sen.setMaxValue2(rs.getDouble("maxValue2"));
				sen.setSensorTypeID3(rs.getInt("sensorTypeID3"));
				sen.setSensorDataTypeID3(rs.getInt("sensorDataTypeID3"));
				sen.setMinValue3(rs.getDouble("minValue3"));
				sen.setMaxValue3(rs.getDouble("maxValue3"));
				sen.setX(rs.getInt("x"));
				sen.setY(rs.getInt("y"));
				sen.setWidth(rs.getInt("width"));
				sen.setHeight(rs.getInt("height"));
				list.add(sen);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.free(rs, conn, pstmt);//释放资源
	   }
		return list;
	}

	@Override
	public ArrayList findSensorDataBySensorID(int sensorID,String time,String endTime){
		ArrayList list=new ArrayList();//初始化链表
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		
		time="'"+time+"'";  //开始时间     '2014-06-10 00:00:00'  
		endTime="'"+endTime+"'";//结束时间
		String selectSQL="select sensorDataID,sensorID,from_unixtime(samplingTime/1000) as samplingTime," +
				"value1,value2,value3 from sensordata where sensorID="+sensorID +" and from_unixtime(samplingTime/1000)>="+time+"  and from_unixtime(samplingTime/1000)<="+endTime;		
		PreparedStatement pstmt = null; // 声明预处理对象
		ResultSet rs = null; // 声明结果集对象
		try {
			pstmt = conn.prepareStatement(selectSQL); // 获得预处理对象并赋值
			rs = pstmt.executeQuery(); // 执行查询
			while(rs.next()) {
				sensordata sd=new sensordata();
				sd.setSensorDataID(rs.getInt("sensorDataID"));
				sd.setSensorID(rs.getInt("sensorID"));
				sd.setSamplingTime(rs.getString("samplingTime"));
				sd.setValue1(rs.getDouble("value1"));
				sd.setValue2(rs.getDouble("value2"));
				sd.setValue3(rs.getDouble("value3"));
				list.add(sd);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.free(rs, conn, pstmt);//释放资源
	   }
		System.out.println(list.size()+"  "+selectSQL);
		return list;
	}
	
	
	/**
	 *findSensor是将查询的结果显示到showAllSensor.jsp中 
	 */
	public String[] findSensor() {
		// TODO Auto-generated method stub
        System.out.println("查询数据库");
		
		// 获得连接对象	
		Connection conn = DBConnection.getConnection();
		String selectSQL="select sensorID, unitName, embededIP, nodeIP, portType, channelNo,"+
			"b.sensorTypeName, e.sensorDataTypeName, minValue1, maxValue1,"+
        	"c.sensorTypeName, f.sensorDataTypeName, minValue2, maxValue2,"+
        	"d.sensorTypeName, g.sensorDataTypeName, minValue3, maxValue3, "+
        	"a.x ,a.y, a.width, a.height " +
            	"from sensor a "+
            	"left join sensortype b on a.sensorTypeID1=b.sensorTypeID "+ 
            	"left join sensortype c on a.sensorTypeID2=c.sensorTypeID "+
            	"left join sensortype d on a.sensorTypeID3=d.sensorTypeID "+
            	"left join sensordatatype e on a.sensorDataTypeID1=e.sensorDataTypeID "+
            	"left join sensordatatype f on a.sensorDataTypeID2=f.sensorDataTypeID "+
            	"left join sensordatatype g on a.sensorDataTypeID3=g.sensorDataTypeID,"+
            	"unit h " +
            		"where a.unitID=h.unitID " +
            			"order by sensorID desc";
    		
		System.out.println(selectSQL);
	//	String unitName = "select unitName from unit where unitID = " + unitID;
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
			str = new String[22*(rs.getRow())];
			
			//到结果集的最前面即是第一条记录的前面
			rs.beforeFirst();
			
			//临时变量，控制循环str数组下标从零开始
			int i = 0;
			
			while(rs.next()){
				
				str[i++] = rs.getString(1);
				str[i++] = rs.getString(2);
				str[i++] = rs.getString(3);
				str[i++] = rs.getString(4);
				str[i++] = rs.getString(5);
				str[i++] = rs.getString(6);
				str[i++] = rs.getString(7);
				str[i++] = rs.getString(8);
				str[i++] = rs.getString(9);
				str[i++] = rs.getString(10);
				str[i++] = rs.getString(11);
				str[i++] = rs.getString(12);
				str[i++] = rs.getString(13);
				str[i++] = rs.getString(14);
				str[i++] = rs.getString(15);
				str[i++] = rs.getString(16);
				str[i++] = rs.getString(17);
				str[i++] = rs.getString(18);
				str[i++] = rs.getString(19);
				str[i++] = rs.getString(20);
				str[i++] = rs.getString(21);
				str[i++] = rs.getString(22);
			}
			
		} catch (SQLException e) {
			System.out.println("sensor_functionDAOImplm:findSensor() is failed !");
			e.printStackTrace();
		} finally {
			
			//释放资源
			DBConnection.free(rs, conn, pstmt);
	   }
		return str;
	}

	@Override
	/**
	 * 方法功能解释：通过参数传感器ID号名sensorID，访问数据库
	 * 			 表项sensorID,删除符合条件的记录，返回的删除记录是否成功
	 * @param sensorID
	 * @return boolean
	 */
	public boolean delSensor(String sensorID) {
		// TODO Auto-generated method stub
		boolean flag = false;
		System.out.println("删除数据记录");
		
		// 获得连接对象
		Connection conn = DBConnection.getConnection(); 
		String selectSQL="delete from sensor where sensorID = "+sensorID;
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
			System.out.println("sensor_functionDAOImplm:delSensor() is failed !");
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
	 * 方法功能解释：通过参数集合sensorAllArray将所有数据项以一个长字符
	 * 串存储,数据表增加n条记录，返回增加记录是否成功
	 * 	 * @param sensorAllArray(包含一个或多个类型名的字符串)
	 * @return boolean
	 */
	public boolean addSensor(String[] sensorAllArray){
		Connection conn = null;
		Statement stmt=null; 
		// 	声明结果集对象
		ResultSet rs = null; 
		// 	获得连接对象
		conn=DBConnection.getConnection();
		//max是为了获取最大的sensorID，其结果加1即为当前sensorID
		int max = 0;
		String unitName = null; 
		String embededIP = null; 
		String nodeIP = null;
		String channelNo = null; 
		String portType = null;
		String sensorTypeName1 = null;
		String sensorDataTypeName1 = null;
		String minValue1 = null;
		String maxValue1 = null;
		String sensorTypeName2 = null;
		String sensorDataTypeName2= null;
		String minValue2 = null;
		String maxValue2 = null;
		String sensorTypeName3 = null;
		String sensorDataTypeName3 = null;
		String minValue3 = null;
		String maxValue3 = null;
		String x = null; 
		String y = null; 
		String width = null; 
		String height = null;
		// 声明预处理对象
		PreparedStatement pstmt = null;
		
		try{
			String selectSQL = "select max(sensorID) from sensor";
			// 获得预处理对象并赋值
			pstmt = conn.prepareStatement(selectSQL);
			// 执行查询
			rs = pstmt.executeQuery();
			while(rs.next()){
				max = Integer.valueOf(rs.getString(1));
			}
		}catch(SQLException e){
			System.out.println("Sensor_functionDAOImplm:add_selectSensorID() is failed !");
			e.printStackTrace();
		}
		try{
		
			//创建Statement对象
			stmt = conn.createStatement();

			//设置JDBC事务的默认提交方式，不自动提交
			conn.setAutoCommit(false);		
		
			//		System.out.println("unitTypeNameS = "+unitTypeNameS);
			//System.out.println("unitTypeName = "+unitTypeName);
		String flag = null;
			for(int i=0; i < sensorAllArray.length; ){
				unitName = sensorAllArray[i++];
				embededIP = sensorAllArray[i++];
				nodeIP = sensorAllArray[i++];
				channelNo = sensorAllArray[i++];
				portType = sensorAllArray[i++];
				sensorTypeName1 = sensorAllArray[i++];
				sensorDataTypeName1 = sensorAllArray[i++];
				flag = sensorAllArray[i++];
				minValue1 = (flag.isEmpty())?"null":flag;
				flag = sensorAllArray[i++];
				maxValue1 = (flag.isEmpty())?"null":flag;
				sensorTypeName2 = sensorAllArray[i++];
				sensorDataTypeName2 = sensorAllArray[i++];
				flag = sensorAllArray[i++];
				minValue2 = (flag.isEmpty())?"null":flag;
				flag = sensorAllArray[i++];
				maxValue2 = (flag.isEmpty())?"null":flag;
				sensorTypeName3 = sensorAllArray[i++];
				sensorDataTypeName3 = sensorAllArray[i++];
				flag = sensorAllArray[i++];
				minValue3 = (flag.isEmpty())?"null":flag;
				flag = sensorAllArray[i++];
				maxValue3 = (flag.isEmpty())?"null":flag;
				x = sensorAllArray[i++];
				y = sensorAllArray[i++];
				width = sensorAllArray[i++];
				height = sensorAllArray[i++];
				max++;
			
				//	当修改user表为其他名字时，会产生SQLException异常
				String sqlstr = "insert into sensor (sensorID, unitID,embededIP,nodeIP, portType, channelNo, " +
						"sensorTypeID1, sensorDataTypeID1, minValue1, maxValue1, " +
						"sensorTypeID2, sensorDataTypeID2, minValue2, maxValue2, " +
						"sensorTypeID3, sensorDataTypeID3, minValue3, maxValue3, " +
						"x, y, width, height) values('" + max +"'," +
						"(select unitID from unit where unit.unitName='" + unitName + "')" + ",'" + embededIP + "','" + nodeIP + "','" + portType + "','" + channelNo + "'," +
						"(select sensorTypeID from sensorType a where a.sensorTypeName='" + sensorTypeName1 + "')" + "," + 
						"(select sensorDataTypeID from sensorDataType a where a.sensorDataTypeName='"+sensorDataTypeName1 + "')," + minValue1 + "," +maxValue1 + "," +
						"(select sensorTypeID from sensorType a where a.sensorTypeName='" + sensorTypeName2 + "')" + "," + 
						"(select sensorDataTypeID from sensorDataType a where a.sensorDataTypeName='"+sensorDataTypeName2 + "')," + minValue2 + "," +maxValue2 + "," +
						"(select sensorTypeID from sensorType a where a.sensorTypeName='" + sensorTypeName3 + "')" + "," + 
						"(select sensorDataTypeID from sensorDataType a where a.sensorDataTypeName='"+sensorDataTypeName3 + "')," + minValue3 + "," +maxValue3 + ",'" +
						x + "','" + y + "','" + width + "','" + height + "')"; 
				System.out.println(sqlstr);
				System.err.println(sqlstr);

				stmt.addBatch(sqlstr); 
			}
			
			//执行sql语句
			stmt.executeBatch();
			//	事务提交
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

	/**
	 * 用于查询相应的单元名称，传感器类型名称，传感器数据类型名称
	 *
	 */
	public String[][] loadAboutSensor(){
		 System.out.println("查询数据库");
			
			// 获得连接对象	
			Connection conn = DBConnection.getConnection();
			String selectSQL = "select unitName from unit;";
			String selectSQL1 = "select sensorTypeName from sensortype;";
			String selectSQL2 = "select sensorDataTypeName from sensordatatype;";
			System.out.println(selectSQL);
			System.out.println(selectSQL1);
			System.out.println(selectSQL2);
			
			
			// 声明预处理对象
			PreparedStatement pstmt = null;
			PreparedStatement pstmt1 = null;
			PreparedStatement pstmt2 = null;

			//声明结果集对象
			ResultSet rs = null;
			ResultSet rs1 = null;
			ResultSet rs2 = null;
			
			String[][] str = new String[3][];
			try {
				
				 // 获得预处理对象并赋值
				pstmt = conn.prepareStatement(selectSQL);
				pstmt1 = conn.prepareStatement(selectSQL1);
				pstmt2 = conn.prepareStatement(selectSQL2);
				
				// 执行查询
				rs = pstmt.executeQuery();
				rs1 = pstmt1.executeQuery();
				rs2 = pstmt2.executeQuery();
				
				//先到最后一行，获得结果集的总行数
				//到结果集的最后一行
				rs.last();
				str[0] = new String[rs.getRow()];
				
				rs1.last();
				str[1] = new String[rs1.getRow()];
				
				rs2.last();
				str[2] = new String[rs2.getRow()];
				
				//到结果集的最前面即是第一条记录的前面
				rs.beforeFirst();
				rs1.beforeFirst();
				rs2.beforeFirst();
				
				//临时变量，控制循环str数组下标从零开始
				int i = 0;
				
				while(rs.next()){
					str[0][i++] = rs.getString(1);
				}
				
				i = 0;
				
				while(rs1.next()){
					str[1][i++] = rs1.getString(1);
				}
				
				i = 0;
				
				while(rs2.next()){
					str[2][i++] = rs2.getString(1);
				}
			} catch (SQLException e) {
				System.out.println("sensor_functionDAOImplm:loadAboutSensor() is failed !");
				e.printStackTrace();
			} finally {
				
				//释放资源
				DBConnection.free(rs, conn, pstmt);
		   }
			return str;
		
	}
	
	
	
	public String[] searchSensor(String embededIP, String nodeIP, String channelNo){
		String[] str = null;
		// 获得连接对象
		Connection conn = DBConnection.getConnection(); 
		String selectSQL="select sensorID, unitName, embededIP, nodeIP, portType, channelNo,"+
				"b.sensorTypeName, e.sensorDataTypeName, minValue1, maxValue1,"+
	        	"c.sensorTypeName, f.sensorDataTypeName, minValue2, maxValue2,"+
	        	"d.sensorTypeName, g.sensorDataTypeName, minValue3, maxValue3, "+
	        	"a.x ,a.y, a.width, a.height " +
    	        	"from sensor a "+
    	        	"left join sensortype b on a.sensorTypeID1=b.sensorTypeID "+ 
    	        	"left join sensortype c on a.sensorTypeID2=c.sensorTypeID "+
    	        	"left join sensortype d on a.sensorTypeID3=d.sensorTypeID "+
    	        	"left join sensordatatype e on a.sensorDataTypeID1=e.sensorDataTypeID "+
    	        	"left join sensordatatype f on a.sensorDataTypeID2=f.sensorDataTypeID "+
    	        	"left join sensordatatype g on a.sensorDataTypeID3=g.sensorDataTypeID,"+
    	        	"unit h " +
        	        	"where a.unitID=h.unitID and " +
        	        	"embededIP like '%"+embededIP+"%' and " +
               			"nodeIP like '%"+nodeIP+"%' and " +
               			"channelNo like '%"+channelNo+"%' " +
               					"order by sensorID desc";
        		
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
			str = new String[22*(rs.getRow())];
			
			//到结果集的最前面即是第一条记录的前面
			rs.beforeFirst();
			
			//临时变量，控制循环str数组下标从零开始
			int i = 0;
			
			while(rs.next()){
				
				str[i++] = rs.getString(1);
				str[i++] = rs.getString(2);
				str[i++] = rs.getString(3);
				str[i++] = rs.getString(4);
				str[i++] = rs.getString(5);
				str[i++] = rs.getString(6);
				str[i++] = rs.getString(7);
				str[i++] = rs.getString(8);
				str[i++] = rs.getString(9);
				str[i++] = rs.getString(10);
				str[i++] = rs.getString(11);
				str[i++] = rs.getString(12);
				str[i++] = rs.getString(13);
				str[i++] = rs.getString(14);
				str[i++] = rs.getString(15);
				str[i++] = rs.getString(16);
				str[i++] = rs.getString(17);
				str[i++] = rs.getString(18);
				str[i++] = rs.getString(19);
				str[i++] = rs.getString(20);
				str[i++] = rs.getString(21);
				str[i++] = rs.getString(4);
			}
			
		} catch (SQLException e) {
			System.out.println("sensor_functionDAOImplm:searchSensor() is failed !");
			e.printStackTrace();
		} finally {
			//释放资源
			DBConnection.free(rs, conn, pstmt);
	   }
		return str;
	}
	
	/**
	 * 修改sensor数据库
	 */
	public boolean changeSensor(String sensorID, String newUnitName, String newEmbededIP, String newNodeIP, String newChannelNo, String newPortType,
					String newSensorTypeName1, String newSensorDataTypeName1, String newMinValue1, String newMaxValue1, 
					String newSensorTypeName2, String newSensorDataTypeName2, String newMinValue2, String newMaxValue2,
					String newSensorTypeName3, String newSensorDataTypeName3, String newMinValue3, String newMaxValue3,
					String newX, String newY, String newWidth, String newHeight){
		boolean flag = false;
		String newSensorTypeID1 = "select sensorTypeID from sensortype b where b.sensorTypeName = '" + newSensorTypeName1 + "'";
		String newSensorDataTypeID1 = "select sensorDataTypeID from sensordatatype b where b.sensorDataTypeName = '" + newSensorDataTypeName1 + "'";		
		String newSensorTypeID2 = "select sensorTypeID from sensortype b where b.sensorTypeName = '" + newSensorTypeName2 + "'";
		String newSensorDataTypeID2 = "select sensorDataTypeID from sensordatatype b where b.sensorDataTypeName = '" + newSensorDataTypeName2 + "'";
		String newSensorTypeID3 = "select sensorTypeID from sensortype b where b.sensorTypeName = '" + newSensorTypeName3 + "'";
		String newSensorDataTypeID3 = "select sensorDataTypeID from sensordatatype b where b.sensorDataTypeName = '" + newSensorDataTypeName3 + "'";
		String newUnitID = "select unitID from unit a where a.unitName = '" + newUnitName + "'";
		
		System.out.println("修改数据库");
		
		// 获得连接对象
		Connection conn = DBConnection.getConnection();
	
		String updateSQL =  "update sensor set unitID = ("
							+newUnitID + "),embededIP = " + newEmbededIP + ", nodeIP = " + newNodeIP + 
							",channelNo = " + newChannelNo + ",portType =" + newPortType + 
							",sensorTypeID1 = ("+ newSensorTypeID1 + "),sensorDataTypeID1 = (" + newSensorDataTypeID1 + 
							"),minValue1 = " + newMinValue1 + ",maxValue1 = " + newMaxValue1 + 
							",sensorTypeID2 = ("+ newSensorTypeID2 + "),sensorDataTypeID2 = (" + newSensorDataTypeID2 + 
							"),minValue2 = " + newMinValue2 + ",maxValue2 = " + newMaxValue2 +
							",sensorTypeID3 = ("+ newSensorTypeID3 + "),sensorDataTypeID3 = (" + newSensorDataTypeID3 + 
							"),minValue3 = " + newMinValue3 + ",maxValue3 = " + newMaxValue3 +
							",x = " + newX + " ,y = " + newY + ", width = " + newWidth + ",height = " + newHeight + 
							" where sensorID = " + sensorID;
		System.out.println(updateSQL);
		 
		// 声明预处理对象
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		PreparedStatement pstmt5 = null;
		PreparedStatement pstmt6 = null;
		PreparedStatement pstmt7 = null;
		
		try {
			
			 // 获得预处理对象并赋值
			pstmt1 = conn.prepareStatement(newSensorTypeID1);
			pstmt2 = conn.prepareStatement(newSensorDataTypeID1);
			pstmt3 = conn.prepareStatement(newSensorTypeID2);
			pstmt4 = conn.prepareStatement(newSensorDataTypeID2);
			pstmt5 = conn.prepareStatement(newSensorTypeID3);
			pstmt6 = conn.prepareStatement(newSensorDataTypeID3);
			pstmt7 = conn.prepareStatement(newUnitID);
			pstmt = conn.prepareStatement(updateSQL);
			
			// 执行查询
			pstmt1.executeQuery();
			pstmt2.executeQuery();
			pstmt3.executeQuery();
			pstmt4.executeQuery();
			pstmt5.executeQuery();
			pstmt6.executeQuery();
			pstmt7.executeQuery();
			int n = pstmt.executeUpdate(); 
			
			if(1 == n){
				flag = true;
			}
			
			
		} catch (SQLException e) {
			System.out.println("unitType_functionDAOImplm:changeSensor() is failed !");
			e.printStackTrace();
		} finally {
			
			//释放资源
			DBConnection.freeStatement(null, conn, pstmt);
	   }
	    System.out.println(flag);
		return flag;
	}

	@Override
	public String[][] dataForOption(){
		int i = 0;
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null; 
		ResultSet rs = null; 
		String[][] strSelect = new String[3][];
		String sqlstr = null;
		try{
			i = 0;
			sqlstr = "select unitName from unit";
			pstmt = conn.prepareStatement(sqlstr);
			rs = pstmt.executeQuery();
			rs.last();
			strSelect[0] = new String[rs.getRow()];
			rs.beforeFirst();
			while(rs.next()){
				strSelect[0][i] = rs.getString(1);
				i ++;
			}
		}catch(SQLException e){
			System.out.println("sensor_functionDAOImplm:dataForOption1() is failed !");
			e.printStackTrace();
		}
		try{
			i = 0;
			sqlstr = "select sensorTypeName from sensortype";
			pstmt = conn.prepareStatement(sqlstr);
			rs = pstmt.executeQuery();
			rs.last();
			strSelect[1] = new String[rs.getRow()];
			rs.beforeFirst();
			while(rs.next()){
				strSelect[1][i] = rs.getString(1);
				i ++;
			}
		}catch(SQLException e){
			System.out.println("sensor_functionDAOImplm:dataForOption2() is failed !");
			e.printStackTrace();
		}
		try{
			i = 0;
			sqlstr = "select sensorDataTypeName from sensordatatype";
			pstmt = conn.prepareStatement(sqlstr);
			rs = pstmt.executeQuery();
			rs.last();
			strSelect[2] = new String[rs.getRow()];
			rs.beforeFirst();
			while(rs.next()){
				strSelect[2][i] = rs.getString(1);
				i ++;
			}
		}catch(SQLException e){
			System.out.println("sensor_functionDAOImplm:dataForOption3() is failed !");
			e.printStackTrace();
		}finally {
			DBConnection.freeStatement(rs, conn, pstmt);
		}
		return strSelect;
	}	
	
}
