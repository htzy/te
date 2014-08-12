package com.monitor.factory;

import com.monitor.dao.UnitType_functionDAO;
import com.monitor.dao.UnitType_functionDAOImplm;

public class UnitType_functionDAOFactory {
	//抽象工厂的方式实例化，单元类
	 public static UnitType_functionDAO createUnitType_functionDAOImplm(){ 
		   return (UnitType_functionDAO)new UnitType_functionDAOImplm();
		   
	   }
}
