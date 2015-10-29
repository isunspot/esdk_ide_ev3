package com.huawei.esdk.ev3.bean;

public class RGBBean {
	public RGBBean(){
	}
	
	public RGBBean(float r, float g, float b) {
		this.rValue = r;
		this.gValue = g;
		this.bValue = b;
	}
	
	private float rValue;

	private float gValue;

	private float bValue;

	public float getrValue() {
		return rValue;
	}

	public void setrValue(float rValue) {
		this.rValue = rValue;
	}

	public float getgValue() {
		return gValue;
	}

	public void setgValue(float gValue) {
		this.gValue = gValue;
	}

	public float getbValue() {
		return bValue;
	}

	public void setbValue(float bValue) {
		this.bValue = bValue;
	}
}