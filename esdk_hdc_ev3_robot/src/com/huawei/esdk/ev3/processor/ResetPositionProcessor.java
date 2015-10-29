package com.huawei.esdk.ev3.processor;

import com.huawei.esdk.ev3.HDCIDERobot;
import com.huawei.esdk.ev3.common.ResetPositionCmd;
import com.huawei.esdk.ev3.server.Communicator;

import lejos.hardware.Sound;

public class ResetPositionProcessor implements Processor<ResetPositionCmd> {

	private HDCIDERobot robot;

	public ResetPositionProcessor(HDCIDERobot robot) {
		this.robot = robot;
	}

	@Override
	public void process(ResetPositionCmd commandMessage, Communicator communicator) {
		//Suppose 0 is the starting position
		robot.findPosition(0);
		Sound.beepSequence();
	}
}
