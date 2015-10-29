package com.huawei.esdk.ev3.common;

/**
 * 巡考消息命令
 */
public class PatrolCmd implements NetMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3389833812236618464L;

	private String startFlag;

	public String getStartFlag() {
		return startFlag;
	}

	public void setStartFlag(String startFlag) {
		this.startFlag = startFlag;
	}

	@Override
	public String toString() {
		return "PantrolCmd [startFlag=" + startFlag + "]";
	}
}