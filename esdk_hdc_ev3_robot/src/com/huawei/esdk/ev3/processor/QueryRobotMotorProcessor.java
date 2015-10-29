package com.huawei.esdk.ev3.processor;

import com.huawei.esdk.ev3.HDCIDERobot;
import com.huawei.esdk.ev3.common.QueryRobotMotorCmd;
import com.huawei.esdk.ev3.common.QueryRobotMotorResponse;
import com.huawei.esdk.ev3.server.Communicator;

import lejos.hardware.motor.Motor;

public class QueryRobotMotorProcessor implements Processor<QueryRobotMotorCmd> {

	private HDCIDERobot robot;

	public QueryRobotMotorProcessor(HDCIDERobot robot) {
		this.robot = robot;
	}

	@Override
	public void process(QueryRobotMotorCmd commandMessage, final Communicator communicator) {
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					QueryRobotMotorResponse msg = new QueryRobotMotorResponse();
					msg.setMotorBMoving(Motor.B.isMoving());
					msg.setMotorBSpeed(Motor.B.getSpeed());
					msg.setMotorCMoving(Motor.C.isMoving());
					msg.setMotorCSpeed(Motor.C.getSpeed());
					
					communicator.send(msg);
					
					robot.sleepInMillSeconds(500);
				}
			}
		}).start();
	}
}
