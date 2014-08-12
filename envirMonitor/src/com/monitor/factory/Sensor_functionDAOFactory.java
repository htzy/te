package com.monitor.factory;

import com.monitor.dao.Sensor_functionDAO;
import com.monitor.dao.Sensor_functionDAOImplm;


public class Sensor_functionDAOFactory {

	
	 public static Sensor_functionDAO createSensor_functionDAOImplm(){ //实例化，单元类
		   return (Sensor_functionDAO)new Sensor_functionDAOImplm();
		   
	   }
}
