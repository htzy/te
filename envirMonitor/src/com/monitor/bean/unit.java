package com.monitor.bean;

public class unit { // 单元
	private int unitID;
	private int unitTypeID;
	private String unitName;
	private int fatherUnitID;
	private int x;
	private int y;
	private int width;
	private int height;
	private String pictureName;
	private String thumbnailsName;

	public int getUnitID() {
		return unitID;
	}

	public void setUnitID(int unitID) {
		this.unitID = unitID;
	}

	public int getUnitTypeID() {
		return unitTypeID;
	}

	public void setUnitTypeID(int unitTypeID) {
		this.unitTypeID = unitTypeID;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public int getFatherUnitID() {
		return fatherUnitID;
	}

	public void setFatherUnitID(int fatherUnitID) {
		this.fatherUnitID = fatherUnitID;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getPictureName() {
		return pictureName;
	}

	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}

	public String getThumbnailsName() {
		return thumbnailsName;
	}

	public void setThumbnailsName(String thumbnailsName) {
		this.thumbnailsName = thumbnailsName;
	}

}
