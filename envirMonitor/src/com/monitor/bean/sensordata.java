package com.monitor.bean;



public class sensordata {
	private int sensorDataID;
	private int sensorID;
	private String samplingTime;
	private double value1;
	private double value2;
	private double value3;
	
	
	public String getSamplingTime() {
		return samplingTime;
	}
	public void setSamplingTime(String samplingTime) {
		this.samplingTime = samplingTime;
	}
	public int getSensorDataID() {
		return sensorDataID;
	}
	public void setSensorDataID(int sensorDataID) {
		this.sensorDataID = sensorDataID;
	}
	public int getSensorID() {
		return sensorID;
	}
	public void setSensorID(int sensorID) {
		this.sensorID = sensorID;
	}
	
	public double getValue1() {
		return value1;
	}
	public void setValue1(double value1) {
		this.value1 = value1;
	}
	public double getValue2() {
		return value2;
	}
	public void setValue2(double value2) {
		this.value2 = value2;
	}
	public double getValue3() {
		return value3;
	}
	public void setValue3(double value3) {
		this.value3 = value3;
	}
	

}
