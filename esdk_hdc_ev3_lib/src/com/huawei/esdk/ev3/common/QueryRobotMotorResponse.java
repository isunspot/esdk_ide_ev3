package com.huawei.esdk.ev3.common;

public class QueryRobotMotorResponse implements NetMessage {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 2844764683033534488L;

	private boolean isMotorBMoving;
	
	private int motorBSpeed;
	
	private boolean isMotorCMoving;
	
	private int motorCSpeed;

	public boolean isMotorBMoving() {
		return isMotorBMoving;
	}

	public void setMotorBMoving(boolean isMotorBMoving) {
		this.isMotorBMoving = isMotorBMoving;
	}

	public int getMotorBSpeed() {
		return motorBSpeed;
	}

	public void setMotorBSpeed(int motorBSpeed) {
		this.motorBSpeed = motorBSpeed;
	}

	public boolean isMotorCMoving() {
		return isMotorCMoving;
	}

	public void setMotorCMoving(boolean isMotorCMoving) {
		this.isMotorCMoving = isMotorCMoving;
	}

	public int getMotorCSpeed() {
		return motorCSpeed;
	}

	public void setMotorCSpeed(int motorCSpeed) {
		this.motorCSpeed = motorCSpeed;
	}
}
