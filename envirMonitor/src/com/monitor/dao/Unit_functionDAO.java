package com.monitor.dao;

import java.util.ArrayList;

public interface Unit_functionDAO {
	//通过用户的ID号和父节点的ID号，找到对应其权限的的单元
	public abstract ArrayList findTopUnit(int userID,int fatherUnitID);
	
	//找到unit表中所有的记录
	public abstract String[] findUnit();
	
	//找到unit表中所有的unitID，unitName;type用于判断返回的值的数据类型
	public abstract String findUnitID_Name(int type);

	//查询unit表中的数据
	public abstract String[] searchUnit(String unitName,String unitTypeID,String fatherUnitID,String x,String y,String width,String height);
	
	//删除unit表中的数据
	public abstract boolean delUnit(String unitID);
	
	//修改unit表中的数据
	public abstract boolean changeUnit(String unitID,String unitName,String unitTypeID,String fatherUnitID,String x,String y,String width,String height);
	
	//增加unit表中的数据
	public abstract boolean addUnit(String[][] units, int many);

	//test
}
