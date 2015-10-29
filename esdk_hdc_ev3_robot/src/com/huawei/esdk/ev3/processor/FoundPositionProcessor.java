package com.huawei.esdk.ev3.processor;

import com.huawei.esdk.ev3.HDCIDERobot;
import com.huawei.esdk.ev3.common.FoundPositionCmd;
import com.huawei.esdk.ev3.server.Communicator;

public class FoundPositionProcessor implements Processor<FoundPositionCmd> {

	private HDCIDERobot robot;

	public FoundPositionProcessor(HDCIDERobot robot) {
		this.robot = robot;
	}

	@Override
	public void process(FoundPositionCmd commandMessage, Communicator communicator) {
		robot.setManuallyFoundPos(true);
	}

}
