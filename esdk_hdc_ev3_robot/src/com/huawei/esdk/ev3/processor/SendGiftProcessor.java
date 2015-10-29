package com.huawei.esdk.ev3.processor;

import com.huawei.esdk.ev3.HDCIDERobot;
import com.huawei.esdk.ev3.common.SendGiftCmd;
import com.huawei.esdk.ev3.server.Communicator;

public class SendGiftProcessor implements Processor<SendGiftCmd> {

	private HDCIDERobot robot;

	public SendGiftProcessor(HDCIDERobot robot) {
		this.robot = robot;
	}

	@Override
	public void process(SendGiftCmd commandMessage, Communicator communicator) {
		robot.sendGift();
	}
	

}
