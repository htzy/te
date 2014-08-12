package com.monitor.factory;

import com.monitor.dao.SensorDataType_functionDAO;
import com.monitor.dao.SensorDataType_functionDAOImplm;

public class SensorDataType_functionDAOFactory {
	//抽象工厂的方式实例化，单元类
	 public static SensorDataType_functionDAO createSensorDataType_functionDAOImplm(){ 
		   return (SensorDataType_functionDAO)new SensorDataType_functionDAOImplm();
		   
	   }
}
