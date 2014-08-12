package com.monitor.dao;

import java.util.ArrayList;

public interface Sensor_functionDAO {

	public abstract ArrayList findSensorByUnitID(int unitID);//根据节点的ID号，来查找其对应的传感器

	public abstract ArrayList findSensorDataBySensorID(int sensorID,String time,String endTime);//根据传感器的ID号、开始时间和结束时间来提取其对应的记录，调取其对应的记录
	
	
	//public abstract ArrayList findSensorByUnitID(int unitID);//根据节点的ID号，来查找其对应的传感器

	//public abstract ArrayList findSensorDataBySensorID(int sensorID,String time,String endTime);//根据传感器的ID号、开始时间和结束时间来提取其对应的记录，调取其对应的记录
	
	//查询所有的传感器的unitTypeName,端口类型,通道号，其中unitTypeName是通过查询unitType表得到的
	public abstract String[] findSensor();
	
	//批量增加新的传感器
	//public abstract boolean addSensor();
	
	//删除相应记录
	public abstract boolean delSensor(String sensorID);
	//加载相应的外键到下拉选框
	public abstract String[][] loadAboutSensor();
	//搜索符合条件的传感器
	public abstract String[] searchSensor(String embededIP, String nodeIP, String channelNo);
	//批量增加传感器
	//public abstract boolean addSensor(String unitNames, String embededIPs, String nodeIPs, String channelNos, String portTypes,
	//		String sensorTypeName1s, String sensorDataTypeName1s, String minValue1s, String maxValue1s, String sensorTypeName2s, 
	//		String sensorDataTypeName2s, String minValue2s, String maxValue2s, String sensorTypeName3s, String sensorDataTypeName3s,
	//		String minValue3s, String maxValue3s, String xs, String ys, String widths, String heights);
	
	public abstract boolean addSensor(String[] sensorAllArray);
	
	// 修改传感器内容
	public abstract boolean changeSensor(String sensorID, String newUnitName, String newEmbededIP, String newNodeIP, String newChannelNo, String newPortType,
			String newSensorTypeName1, String newSensorDataTypeName1, String newMinValue1, String newMaxValue1, 
			String newSensorTypeName2, String newSensorDataTypeName2, String newMinValue2, String newMaxValue2,
			String newSensorTypeName3, String newSensorDataTypeName3, String newMinValue3, String newMaxValue3,
			String newX, String newY, String newWidth, String newHeight);
	
	//查询传感器类型方法
	public abstract String[][] dataForOption();
}
