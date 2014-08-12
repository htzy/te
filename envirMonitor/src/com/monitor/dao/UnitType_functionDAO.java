package com.monitor.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface UnitType_functionDAO {
	
	//查询数据库中unitType表，并将表中所有数据表记录返回
	public abstract String[] findUnitType();
	
	//通过参数单元类型unitTypeName和int unitTypeID，访问数据库表返回符合条件的记录
	public abstract String[] searchUnitType(String unitTypeID, String unitTypeName);
	

	//通过参数单元类型ID号unitTypeID，修改数据库中符合条件的记录相应字段改为newUnitTypeName
	public abstract boolean changeUnitType(String unitTypeID, String newUnitTypeName);
	
	//通过参数单元类型ID号unitTypeID，删除数据库中符合条件的记录
	public abstract boolean delUnitType(String unitTypeID);

	//通过参数单元类型名称unitTypeNameS，向数据库unitType表中增加一条记录
	public abstract boolean addUnitType(String unitTypeNameS);
}
