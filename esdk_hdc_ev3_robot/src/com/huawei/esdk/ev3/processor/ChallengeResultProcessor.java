package com.huawei.esdk.ev3.processor;

import com.huawei.esdk.ev3.HDCIDERobot;
import com.huawei.esdk.ev3.common.ChallengeResultCmd;
import com.huawei.esdk.ev3.common.ChallengeResultResponseCmd;
import com.huawei.esdk.ev3.server.Communicator;

import lejos.hardware.motor.Motor;

public class ChallengeResultProcessor implements Processor<ChallengeResultCmd> {

	private HDCIDERobot robot;

	public ChallengeResultProcessor(HDCIDERobot robot) {
		this.robot = robot;
	}

	@Override
	public void process(ChallengeResultCmd commandMessage, Communicator communicator) {
		int position = Integer.parseInt(commandMessage.getPosition());
		
		boolean successful = "Y".equalsIgnoreCase(commandMessage.getResult()) ||
				"Yes".equalsIgnoreCase(commandMessage.getResult());
		//寻找位置
		if (!robot.findPosition(position)) {
			return;
		}
		//找到位置后播放成功或者失败
		String f;
		if(successful) {
			f =  "./succ.wav";
		} else {
			f = "./failed1.wav";
		}
		robot.playMusic(f);
		//等待停止后转弯
		robot.sleepInSeconds(2);
		//转向开发者
		robot.turnLeft();
		robot.waitForStop();
		robot.stop();
		//面向开发者后等待一段时间
		robot.sleepInSeconds(2);
		
		//派发礼物或者发射炮弹
		if(successful) {
			robot.sendGift();
		} else {
			robot.fire();
		}
		
		robot.sleepInSeconds(1);
		//转回轨道
		robot.turnRight();
		
		robot.waitForStop();
		
		robot.stop();
		//等待转弯完成
//		robot.sleepInSeconds(3);
		
		//继续移动
//		new Thread(new Runnable(){
//			public void run() {
//				robot.movingInCircle();
//			}
//		}).start();
		
		ChallengeResultResponseCmd msg = new ChallengeResultResponseCmd();
		msg.setPosition(commandMessage.getPosition());
		communicator.send(msg);
	}
	
	
}
