package com.huawei.esdk.ev3.processor;

import com.huawei.esdk.ev3.HDCIDERobot;
import com.huawei.esdk.ev3.common.PatrolCmd;
import com.huawei.esdk.ev3.server.Communicator;

public class PatrolProcessor implements Processor<PatrolCmd> {

	private HDCIDERobot robot;

	public PatrolProcessor(HDCIDERobot robot) {
		this.robot = robot;
	}

	@Override
	public void process(PatrolCmd commandMessage, Communicator communicator) {
		if ("Y".equalsIgnoreCase(commandMessage.getStartFlag()) ||
				"Yes".equalsIgnoreCase(commandMessage.getStartFlag())){
			robot.setRobotMovingFlag();
		} else {
			robot.stop();
		}
	}
}
