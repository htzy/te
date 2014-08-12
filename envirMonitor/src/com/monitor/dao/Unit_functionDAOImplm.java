package com.monitor.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.monitor.bean.unit;
import com.monitor.util.DBConnection;

public class Unit_functionDAOImplm implements Unit_functionDAO{

	@Override
	public ArrayList findTopUnit(int userID, int fatherUnitID){
		ArrayList list = new ArrayList();// 初始化链表
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		String selectSQL = "select * from unit where fatherUnitID="
				+ fatherUnitID;
		PreparedStatement pstmt = null; // 声明预处理对象
		ResultSet rs = null; // 声明结果集对象
		try{
			pstmt = conn.prepareStatement(selectSQL); // 获得预处理对象并赋值
			rs = pstmt.executeQuery(); // 执行查询
			while(rs.next()){
				unit un = new unit();
				un.setUnitID(rs.getInt("unitID"));
				un.setUnitTypeID(rs.getInt("unitTypeID"));
				un.setUnitName(rs.getString("unitName"));
				un.setFatherUnitID(rs.getInt("fatherUnitID"));
				un.setX(rs.getInt("x"));
				un.setY(rs.getInt("y"));
				un.setWidth(rs.getInt("width"));
				un.setHeight(rs.getInt("height"));
				un.setPictureName(rs.getString("pictureName"));
				un.setThumbnailsName(rs.getString("thumbnailsName"));
				list.add(un);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			DBConnection.free(rs, conn, pstmt);// 释放资源
		}
		return list;
	}

	/*
	 * public static void main(String[] args) { Unit_functionDAOImplm ts=new
	 * Unit_functionDAOImplm(); ts.findTopUnit(1); }
	 */

	@Override
	/**
	 * 方法功能解释：访问数据库，unit,unittype进行连接取出数据
	 * @param 
	 * @return String[]
	 */
	public String[] findUnit(){
		System.out.println("进入单元管理");

		// 获得连接对象
		Connection conn = DBConnection.getConnection();
		// 将unit表自连接（左连接），用于将表中的fatherUnitID转化为对应的unitName；
		// 并与unitType连接，用于将unitTypeID转化为对应的unitTypeName。
		String selectSQL = "select a.unitID,a.unitName,unittype.unitTypeName,b.unitName,a.x,a.y,"
				+ "a.width,a.height,a.pictureName,a.thumbnailsName from unittype,"
				+ " unit a left join unit b on a.fatherUnitID = b.unitID"
				+ " where a.unitTypeID = unittype.unitTypeID";

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
			str = new String[10 * (rs.getRow())];

			// 到结果集的最前面即是第一条记录的前面
			rs.beforeFirst();

			// 临时变量，控制循环str数组下标从零开始
			int i = 0;

			// 临时变量，用来指定一条记录中的不同属性
			int j;
			File file = null;
			while(rs.next()){
				for(j = 1; j <= 10; j++){
					// 当j == 4时，即fatherUnitName字段，该字段可能为空，则重新赋值
					if(j == 4 && rs.getString(j) == null){
						str[i++] = "没有父节点";
					}else{
						str[i++] = rs.getString(j);
					}
					if(j == 9){
						file = new File("http:\\\\localhost:8080\\envirMonitor\\"+rs.getString(9));
						boolean fileIsExist = file.canRead();
						System.out.println(rs.getString(9)+"文件存在否："+fileIsExist);
						
						
					}
					
					
				}

			}

		}catch(SQLException e){
			System.out.println("unit_functionDAOImplm:findUnit() is failed !");
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
	 * 方法功能解释：访问数据库，查询所有的unitID,unitName,
	 * 用于通过父节点ID找到父节点的名称
	 * 当参数为1时，返回json型数据；当参数为2时，返回option型数据
	 * @param i
	 * @return String
	 */
	public String findUnitID_Name(int type){
		System.out.println("进入单元管理");

		// JSONObject j = new JSONStringer();
		// 获得连接对象
		Connection conn = DBConnection.getConnection();

		String selectSQL = "select unitID,unitName from unit";

		System.out.println(selectSQL);

		// 声明预处理对象
		PreparedStatement pstmt = null;

		// 声明结果集对象
		ResultSet rs = null;
		String str = null;

		String json = "{\"list\":[";
		String options = "";
		String result = "";
		try{

			// 获得预处理对象并赋值
			pstmt = conn.prepareStatement(selectSQL);

			// 执行查询
			rs = pstmt.executeQuery();

			// 先到最后一行，获得结果集的总行数
			// 到结果集的最后一行
			rs.last();
			// str = new String[2*(rs.getRow())];

			// 到结果集的最前面即是第一条记录的前面
			rs.beforeFirst();
			
			//type==1,返回的类型为json
			if(type == 1){
				while(rs.next()){
					str = "{\"value\":\"" + rs.getString(1) + "\",\"key\":\""
						+ rs.getString(2) + "\"},";
					// 拼接json型数据
					json += str;
				}
				// 将最后一个多余的“,”去掉
				json = json.substring(0, json.length() - 1);
				json = json + "]}";
				result = json;
			}
			//type==2,返回的类型为option
			else if(type == 2){
				while(rs.next()){
					str = " <option value='" + rs.getString(1) + "'>" + rs.getString(2) + "</option>";
					//拼接option型数据
					options += str;
				}
				result = options;
				//<option value=''>请选择功能</option>
			}
		}catch(SQLException e){
			System.out.println("unit_functionDAOImplm:findUnitID_Name() is failed !");
			e.printStackTrace();
		}
		finally{
			// 释放资源
			DBConnection.free(rs, conn, pstmt);
		}
		return result;
		
	}

	@Override
	/**
	 * 方法功能解释：通过一系列参数查询unit表中的记录（暂不支持通过图片查找
	 * @param String unitTypeID,String unitName,String fatherUnitID,String x,String y,String width,String height
	 * @return String[]
	 */
	public String[]
			searchUnit(String unitName,String unitTypeID, String fatherUnitID, String x, String y, String width, String height){
		System.out.println("进入单元管理---搜索");

		// 获得连接对象
		Connection conn = DBConnection.getConnection();

		// 判断是否接受到的数据为空，若为空则赋值成 ""
		if(unitTypeID == null){
			unitTypeID = "";
		}
		if(unitName == null){
			unitName = "";
		}
		if(fatherUnitID == null){
			fatherUnitID = "";
		}
		if(x == null){
			x = "";
		}
		if(y == null){
			y = "";
		}
		if(width == null){
			width = "";
		}
		if(height == null){
			height = "";
		}

		// 将unit表自连接（左连接），用于将表中的fatherUnitID转化为对应的unitName；
		// 并与unitType连接，用于将unitTypeID转化为对应的unitTypeName。
		String selectSQL = "select a.unitID,a.unitName,unittype.unitTypeName,b.unitName,a.x,a.y,"
				+ "a.width,a.height,a.pictureName,a.thumbnailsName from unittype,"
				+ " unit a left join unit b on a.fatherUnitID = b.unitID"
				+ " where a.unitTypeID = unittype.unitTypeID and a.unitID in(select unitID from unit where"
				+ " unitTypeID like '%"
				+ unitTypeID
				+ "%' and unitName like '%"
				+ unitName
				+ "%'"
				+ " and fatherUnitID like '%"
				+ fatherUnitID
				+ "%' and x like '%"
				+ x
				+ "%'"
				+ " and y like '%"
				+ y
				+ "%' and width like '%"
				+ width
				+ "%' and height like '%"
				+ height + "%')";

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
			str = new String[10 * (rs.getRow())];

			// 到结果集的最前面即是第一条记录的前面
			rs.beforeFirst();

			// 临时变量，控制循环str数组下标从零开始
			int i = 0;

			// 临时变量，用来指定一条记录中的不同属性
			int j;

			while(rs.next()){
				for(j = 1; j <= 10; j++){
					// 当j == 4时，即fatherUnitName字段，该字段可能为空，则重新赋值
					if(j == 4 && rs.getString(j) == null){
						str[i++] = "没有父节点";
					}else{
						str[i++] = rs.getString(j);
					}
				}
			}
		}catch(SQLException e){
			System.out.println("unit_functionDAOImplm:searchUnit() is failed !");
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
	 * 方法功能解释：通过参数单元类型名unitID，访问数据库
	 * 			 表项unit,删除符合条件的记录，返回的删除记录是否成功
	 * @param unitID
	 * @return boolean
	 */
	public boolean delUnit(String unitID){
		boolean flag = true;
		System.out.println("删除unit数据记录");

		// 获得连接对象
		Connection conn = DBConnection.getConnection();
		ResultSet rs = null;
		
		//查询待删的unitID是否为其他unit的父节点
		String selectSQL = "select unitID from unit where fatherUnitID = " + unitID;
		
		String deleteSQL = "delete from unit where unitID = " + unitID;
		System.out.println(deleteSQL);

		// 声明预处理对象
		PreparedStatement pstmt = null;

		try{
			pstmt = conn.prepareStatement(selectSQL);
			rs = pstmt.executeQuery();
			while(rs.next()){
				//如果取到值则说明待删的单元有父节点，不能删除。
				if(rs.getString(1) != null){
					flag = false;
				}
			}
		}catch(SQLException e){
			System.out.println("unitType_functionDAOImplm:del_selectUnit() is failed !");
			e.printStackTrace();
		}
		if(flag == true ){
			try{
			
				// 获得预处理对象并赋值
				pstmt = conn.prepareStatement(deleteSQL);

				// 执行查询
				int n = pstmt.executeUpdate();

				if(1 == n){
					flag = true;
				}
			}catch(SQLException e){
				System.out.println("unitType_functionDAOImplm:delUnit() is failed !");
				e.printStackTrace();
			}
			finally{

				// 	释放资源
				DBConnection.freeStatement(rs, conn, pstmt);
			}
		}
		System.out.println("刪除成功？"+flag);
		return flag;
	}
	
	@Override
	/**
	 * 方法功能解释：通过参数单元类型名unitTypeID，访问数据库
	 * 			 表项unitType,找到符合条件的记录,将相应字段改为newUnitTypeName，
	 *          返回修改结果
	 * @param String unitID,String unitName,String unitTypeID,String fatherUnitName,String x,String y,String width,String height
	 * @return boolean
	 */
	public boolean changeUnit(String unitID,String unitName,String unitTypeID,String fatherUnitID,String x,String y,String width,String height){
		boolean flag = false;
		System.out.println("修改unit表中的记录");
		String selectSQL="";
		
		//width,height均可为空，所以这里做判断，当为空时，值置为"null"
		if(width == ""){
			width = "null";
		}
		if(height == ""){
			height = "null";
		}
		
		//如果unitTypeID和fatherUnitID都没有改变，则执行下面的sql语句
		if((unitTypeID == null || unitTypeID =="")&&(fatherUnitID == null || fatherUnitID == "")){
			selectSQL =  "update unit set unitName = '"
				+unitName+ "', x="+x+" , y="+y+" , width="+width+" , height="+height+" where unitID="
				+ unitID;
		}
		//如果unitTypeID没有改变，则执行下面的sql语句
		else if(unitTypeID == null || unitTypeID == ""){
			selectSQL =  "update unit set unitName = '"+unitName
			+ "', fatherUnitID="+fatherUnitID+" , x="+x+" , y="+y+" , width="+width+" , height="+height+" where unitID="
				+ unitID;
		}
		//如果fatherUnitID没有改变，则执行下面的sql语句
		else if(fatherUnitID == null || fatherUnitID == ""){
			selectSQL =  "update unit set unitName = '"+unitName
			+ "', unitTypeID="+unitTypeID+" , x="+x+" , y="+y+" , width="+width+" , height="+height+" where unitID="
				+ unitID;
		}
		//如果unitTypeID和fatherUnitID均改变，则执行下面的sql语句
		else{
			selectSQL =  "update unit set unitName = '"
				+unitName+ "' , unitTypeID = "+unitTypeID+" , fatherUnitID="+fatherUnitID
				+" , x="+x+" , y="+y+" , width="+width+" , height="+height+" where unitID="
				+ unitID;
		}
		
		// 获得连接对象
		Connection conn = DBConnection.getConnection(); 
		
		System.out.println(selectSQL);
		 
		// 声明预处理对象
		PreparedStatement pstmt = null;
		try{
			// 获得预处理对象并赋值
			pstmt = conn.prepareStatement(selectSQL);

			// 执行查询
			int n = pstmt.executeUpdate();
			if(1 == n){
				flag = true;
			}
		}catch(SQLException e){
			System.out.println("unit_functionDAOImplm:changeUnit() is failed !");
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
	 * 方法功能解释：向unit表中增加多条记录（批量）
	 * ，但是暂时不包括图片！！！
	 * 
	 * @param units
	 * @return boolean
	 */
	public boolean addUnit(String[][] units, int many){
		System.out.println("向unit数据表增加记录");

		Connection conn = null;
		Statement stmt=null; 
		// 声明结果集对象
		ResultSet rs = null; 
		// 获得连接对象
		conn=DBConnection.getConnection(); 
		
		// 声明预处理对象
		PreparedStatement pstmt = null;
		
		int max = 0;
		
		try{
			String selectSQL = "select max(unitID) from unit";
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
		try{
			
			//创建Statement对象
			stmt = conn.createStatement();

			//设置JDBC事务的默认提交方式，不自动提交
			conn.setAutoCommit(false);		
			
			for(int i=0; i<many; i ++){
				//此时的max为下一次需要的unitID
				max = max + 1;
				stmt.addBatch("insert into unit(unitID,unitName,unitTypeID,fatherUnitID,x,y,width,height,pictureName,thumbnailsName)"
						+"value("+max+", '"+units[i][0]+"',"+units[i][1]+","+units[i][2]+","+units[i][3]+","+units[i][4]+","
						+units[i][5]+","+units[i][6]+",'"+units[i][7]+"','"+units[i][8]+"')"); 
			}
			stmt.executeBatch();
			//事务提交
			//commit与rollback只能执行一个，不能同时执行
			conn.commit();
			
			//设置回其自动提交模式
			conn.setAutoCommit(true);
			return true;
		}catch(SQLException e){
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

}
