package com.monitor.factory;

import com.monitor.dao.SensorData_functionDAO;
import com.monitor.dao.SensorData_functionDAOImplm;

public class SensorData_functionDAOFactory {
	
	//实例化一个单元类
	public static SensorData_functionDAO createSensorData_functionDAOImplm(){
		return (SensorData_functionDAO)new SensorData_functionDAOImplm();
	}
}
