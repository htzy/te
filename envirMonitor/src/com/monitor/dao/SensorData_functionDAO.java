package com.monitor.dao;

import java.util.ArrayList;

public interface SensorData_functionDAO {
	
	//根据参数开始时间startTime和结束时间endTime查询表，并将结果返回
	public abstract String[] searchSensorData(String startTime,String endTime);
	
	//根据参数sensorDataID，value1，value2，value3修改数据库中符合条件的记录。
	public abstract boolean changeSensorData(String sensorDataID,String samplingTime, String value1, String value2,String value3);
	
	//根据参数sensorDataID，删除数据库中符合条件的记录
	public abstract boolean delSeneorData(String sensorDataID);
}
