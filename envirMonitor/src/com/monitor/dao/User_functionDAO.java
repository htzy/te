package com.monitor.dao;

import java.util.ArrayList;

public interface User_functionDAO { //定义用户接口

	
	public abstract int findUser(String username,String password); //根据用户的名称和密码，查找该用户，如果存在该用户则返回该用户的ID号,如果为-1，则说明不存在该用户
	public abstract int getUserRole(int UserID);//根据用户的ID号，得到该用户的角色ID号,如果该用户角色不存在，返回-1
}
