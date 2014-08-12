package com.monitor.factory;

import com.monitor.dao.Unit_functionDAO;
import com.monitor.dao.Unit_functionDAOImplm;

public class Unit_functionDAOFactory {
  
   public static Unit_functionDAO createUnit_functionDAOImplm(){ //实例化，单元类
	   return (Unit_functionDAO)new Unit_functionDAOImplm();
	   
   }

	
}
