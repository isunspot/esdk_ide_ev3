package com.huawei.esdk.ev3.processor;

import com.huawei.esdk.ev3.HDCIDERobot;
import com.huawei.esdk.ev3.common.ClockwiseCmd;
import com.huawei.esdk.ev3.server.Communicator;

public class ClockwiseProcessor implements Processor<ClockwiseCmd> {

	private HDCIDERobot robot;

	public ClockwiseProcessor(HDCIDERobot robot) {
		this.robot = robot;
	}

	@Override
	public void process(ClockwiseCmd commandMessage, Communicator communicator) {
		if ("Y".equalsIgnoreCase(commandMessage.getClockwiseFlag())
				|| "Yes".equalsIgnoreCase(commandMessage.getClockwiseFlag())) {
//			robot.setClockwise(true);
		} else {
//			robot.setClockwise(false);
		}
	}

}
