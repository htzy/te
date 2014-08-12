package com.monitor.dao;

/**
 * 传感器类型管理，包括查询所有信息findSensorType,批量增加addSensorTypes,
 * 删除delSensorType,修改changeSensorType,按条件查询searchSensorType
 * 
 * @author htzy
 */
public interface SensorType_functionDAO{

	/**
	 * 查询数据库中SensorType表，并将表中所有数据表记录返回
	 * @return ResultSet(结果集) String[]
	 */
	public abstract String[] findSensorType();

	/**
	 * 通过参数传感器类型SensorTypeName查询数据，访问数据库表返回符合条件的记录
	 * @param sensorTypeName
	 * @return ResultSet(结果集) String[]
	 */
	public abstract String[] searchSensorType(String sensorTypeName);

	/**
	 * 通过参数传感器类型ID号SensorTypeID，修改数据库中符合条件的记录相应字段改为newSensorTypeName
	 * @param sensorTypeID
	 * @param newSensorTypeName
	 * @return 修改成功为true，修改失败为false
	 */
	public abstract boolean
			changeSensorType(String sensorTypeID, String newSensorTypeName);

	/**
	 * 通过参数传感器类型ID号SensorTypeID，删除数据库中符合条件的记录
	 * @param sensorTypeID
	 * @return 删除成功为true，删除失败为false
	 */
	public abstract boolean delSensorType(String sensorTypeID);

	/**
	 * 通过参数传感器类型名称SensorTypeNameS，向数据库SensorType表中增加记录，可能是多条
	 * @param sensorTypeNameS
	 * @return 增加成功为true，增加失败为false
	 */
	public abstract boolean addSensorTypes(String sensorTypeNameS);
}
