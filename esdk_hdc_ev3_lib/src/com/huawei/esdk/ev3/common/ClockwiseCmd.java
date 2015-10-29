package com.huawei.esdk.ev3.common;

/**
 * 设置顺时针运行
 */
public class ClockwiseCmd implements NetMessage {

	/**
	 * UID
	 */
	private static final long serialVersionUID = -5671940685385081352L;
	
	private static final ClockwiseCmd instance = new ClockwiseCmd();

	private String clockwiseFlag;
	
	private ClockwiseCmd() {
	}

	public static ClockwiseCmd getInstance() {
		return instance;
	}

	public String getClockwiseFlag() {
		return clockwiseFlag;
	}

	public void setClockwiseFlag(String clockwiseFlag) {
		this.clockwiseFlag = clockwiseFlag;
	}

	@Override
	public String toString() {
		return "ClockwiseCmd [clockwiseFlag=" + clockwiseFlag + "]";
	}
}