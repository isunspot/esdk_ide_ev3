package com.huawei.esdk.ev3.processor;

import com.huawei.esdk.ev3.HDCIDERobot;
import com.huawei.esdk.ev3.common.RobotMoveCmd;
import com.huawei.esdk.ev3.server.Communicator;

public class RobotMoveProcessor implements Processor<RobotMoveCmd> {

	private HDCIDERobot robot;

	public RobotMoveProcessor(HDCIDERobot robot) {
		this.robot = robot;
	}

	@Override
	public void process(RobotMoveCmd msg, Communicator communicator) {
		short speed = msg.getSpeed();
		short angle = msg.getRotation();
		switch (msg.getCommand()) {
		case Forward:
			robot.forward(speed, angle);
			break;
		case Backward:
			robot.backword(speed, angle);
			break;
		case Left:
			robot.left(speed, angle);
			break;
		case Right:
			robot.right(speed, angle);
			break;
		case Float:
//			robot.flt();
			break;
		case Stop:
			robot.stop();
			break;
		}
	}

}
