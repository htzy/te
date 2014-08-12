package com.monitor.factory;

import com.monitor.dao.User_functionDAO;
import com.monitor.dao.User_functionDAOImplm;

public class User_functionDAOFactory {
  public static User_functionDAO createUser_functionDAOImplm(){ //实例化该类的方法
	  return (User_functionDAO)new User_functionDAOImplm();
  }
}
