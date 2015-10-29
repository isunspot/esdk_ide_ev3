package com.huawei.esdk.ev3.processor;

import com.huawei.esdk.ev3.HDCIDERobot;
import com.huawei.esdk.ev3.common.ExitSignalCmd;
import com.huawei.esdk.ev3.server.Communicator;

public class ExitSignalProcessor implements Processor<ExitSignalCmd> {

	private HDCIDERobot robot;

	public ExitSignalProcessor(HDCIDERobot robot) {
		this.robot = robot;
	}

	@Override
	public void process(ExitSignalCmd commandMessage, Communicator communicator) {
		//Client side exit should not trigger server to stop running
//		robot.release();
	}

}
