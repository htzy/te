package com.monitor.factory;

import com.monitor.dao.SensorType_functionDAO;
import com.monitor.dao.SensorType_functionDAOImplm;

public class SensorType_functionDAOFactory {
	//抽象工厂的方式实例化，传感器类型类
	 public static SensorType_functionDAO createSensorType_functionDAOImplm(){ 
		   return (SensorType_functionDAO)new SensorType_functionDAOImplm();
		   
	   }
}
