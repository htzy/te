package com.monitor.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface SensorDataType_functionDAO {
	
	//查询数据库中SensorDataType表，并将表中所有数据表记录返回
	public abstract String[] findSensorDataType();
	
	//通过参数传感器 数据类型SensorDataTypeName和 SensorDataTypeID，访问数据库表返回符合条件的记录
	public abstract String[] searchSensorDataType(String SensorDataTypeID, String SensorDataTypeName);
	

	//通过参数数传感器 数据类型ID号SensorDataTypeID，修改数据库中符合条件的记录相应字段改为SensorDataTypeName
	public abstract boolean changeSensorDataType(String SensorDataTypeID, String newSensorDataTypeName);
	
	//通过参数数传感器 数据类型ID号SensorDataTypeID，删除数据库中符合条件的记录
	public abstract boolean delSensorDataType(String SensorDataTypeID);
	
    //特殊标记SensorDataTypeNameS在add和change已经乱了
	
	//通过参数数传感器 数据类型名称SensorDataTypeNameS，向数据库newSensorDataType表中增加一条记录
	public abstract boolean addSensorDataType(String SensorDataTypeNameS);
}
