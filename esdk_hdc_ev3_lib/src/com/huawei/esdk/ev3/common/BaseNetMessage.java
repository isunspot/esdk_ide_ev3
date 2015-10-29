package com.huawei.esdk.ev3.common;

public class BaseNetMessage implements NetMessage {

//	/**
//	 * UID
//	 */
//	private static final long serialVersionUID = -7367746853167081529L;

	//Is the message from hand phone
	private String handPhoneCmd;

	public String getHandPhoneCmd() {
		return handPhoneCmd;
	}

	public void setHandPhoneCmd(String handPhoneCmd) {
		this.handPhoneCmd = handPhoneCmd;
	}
}
