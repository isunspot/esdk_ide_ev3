package com.huawei.esdk.ev3;

import java.io.File;
import java.util.concurrent.TimeUnit;

import com.huawei.esdk.ev3.bean.RGBBean;
import com.huawei.esdk.ev3.service.ColorService;

import lejos.hardware.Sound;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;

public class HDCIDERobot extends RobotConstantsConf {
	private static HDCIDERobot instance;

	private BaseRegulatedMotor motorA;
	
	private BaseRegulatedMotor motorB;
	
	private BaseRegulatedMotor motorC;
	
	//服务端程序是否保持运行
	private boolean robotRunning;

	//机器人是否保持移动
	private volatile boolean keepMoving;
	
	private EV3ColorSensor colorSensor;
	
	private EV3ColorSensor colorSensorRGB;
	
	private boolean manuallyFoundPos = false;
	
	//光强度
	private int light;
	
	//颜色RGB值
	private float[] colorRGB;

	public static HDCIDERobot getInstance() {
		if (null == instance) {
			instance = new HDCIDERobot();
		}

		return instance;
	}

	public void init(boolean isClockwiseMove) {
		this.robotRunning = true;
		
		//Init Motor
		motorA = new EV3MediumRegulatedMotor(MotorPort.A);
		motorB = new EV3LargeRegulatedMotor(MotorPort.B);
		motorC = new EV3LargeRegulatedMotor(MotorPort.C);
		
		// init sensors
		this.colorSensor = new EV3ColorSensor(SensorPort.S3);
		//For reading color only
		try {
			this.colorSensorRGB = new EV3ColorSensor(SensorPort.S2);
		} catch(Exception e) {
			this.colorSensorRGB = null;
		}
		new Thread(new ColorIdentifyTask()).start();
		new Thread(new LightIdentifyTask()).start();
	}

	public void moving() {
		motorB.setSpeed(PID_MODE_TP);
		motorC.setSpeed(PID_MODE_TP);		
		// 巡线车前进
		motorB.forward();
		motorC.forward();
		
		keepMoving = true;
		
		// 动力补偿
		int turn = 0;
		int error = 0;
		//通常情况下，integral（积分）只能趋近于0
		while (robotRunning) {
			synchronized(this) {
				if (!keepMoving) {
					sleepInMillSeconds(10);
					//Whether need to stop?
					continue;
				}
			}
			error = PID_MODE_OFFSET - light;
			//integral（积分）= integral（积分）+ error（误差）
	//				控制器里有了比例控制部分，用于纠正当前的误差；有了积分控制部分，用于纠正过去的误差;预测未来，对还没发生的误差进行纠正
	//				Turn （转向）= Kp*error(误差) + Ki*integral(积分)+ Kd*derivative(导数)
	//				“比例控制部分”+“积分控制部分”+“导数控制部分”
			turn = (int) (PID_MODE_KP * error);
			
			//TODO: 需要测试 
			if (turn > MAX_TURN) {
				turn = MAX_TURN;
			} else if (turn < -MAX_TURN) {
				turn = -MAX_TURN;
			}
			
			motorB.setSpeed(PID_MODE_TP - turn);
			motorC.setSpeed(PID_MODE_TP + turn);
			motorB.forward();
			motorC.forward();
		}
	}

	public void setRobotMovingFlag() {
		synchronized(this) {
			this.keepMoving = true;
		}
	}

	public void turnLeft() {
		turn(360, - (90 * 5 + 20));
	}

	public void turnRight() {
		turn(360, 90 * 5 + 40);
	}
	
	public boolean findPosition(int position) {
		// First need make sure the robot is moving for seeking position
		synchronized(this) {
			keepMoving = true;
		}
		
		manuallyFoundPos = false;
		RGBBean[] posColorValues = ColorService.retrievePosColorsRGB(position);
		boolean firstFound = false, secondFound = false;
		int beginPos = 0;
		
		while(robotRunning && keepMoving) {
			synchronized(this) {
				if (!keepMoving) {
					break;
				}
			}
			if (ColorService.colorMatchRGB(colorRGB, posColorValues[0])) {
//				System.out.println("1st true");
				firstFound = true;
				beginPos = motorB.getTachoCount();
			}
			
			if (firstFound) {//需要寻找第二个
//				System.out.println(colorRGB[0] + ", " + colorRGB[1] + ", " + colorRGB[2]);
				if (ColorService.colorMatchRGB(colorRGB, posColorValues[1])) {
					secondFound = true;
				}
				
				if(firstFound && !secondFound) {//找到了第一个颜色或者
					//看之后是否碰到了轨道的颜色，如果是则该点颜色寻找结束
					if (motorB.getTachoCount() - beginPos > 180){
//						System.out.println("1st reset");
						//Reset flag here
						beginPos = 0;
						firstFound = false;
						secondFound = false;
					}
				}
			}
			
			//找到
			if((firstFound && secondFound) || manuallyFoundPos) {
//				System.out.println("--->Matched " + position);
				synchronized(this){
					this.keepMoving = false;
				}
				stop();
				beginPos = 0;
				firstFound = false;
				secondFound = false;
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Send a gift to when the challenge succeed
	 */
	public void sendGift() {
		motorA.rotate(70);
		sleepInSeconds(1);
		motorA.rotate(-70);
	}

	/**
	 * Fire a ball if the challenge failed
	 */
	public void fire() {
	}

	public void playMusic(String file) {
		new Thread(new PlayMusicTask(file)).start();
	}

	/**
	 * For reading color information
	 * 
	 */
	class LightIdentifyTask implements Runnable {
		public void run() {
			if (null == colorSensor) {
				return;
			}
			while (robotRunning) {
				// Red Mode
				try {
					float[] sample = new float[colorSensor.getRedMode().sampleSize()];
					colorSensor.getRedMode().fetchSample(sample, 0);
					light = (int) (100 * sample[0]);
				} catch(Exception e) {
					//Ignore
					e.printStackTrace();
				}
			}
		}
	}
	
	class ColorIdentifyTask implements Runnable {
		public void run() {
			if (null == colorSensorRGB){
				return;
			}
			while (robotRunning) {
				try {
					synchronized (this) {
						colorRGB = new float[colorSensorRGB.getRGBMode().sampleSize()];
						colorSensorRGB.getRGBMode().fetchSample(colorRGB, 0);
					}
				} catch(Exception e) {
					//Ignore
					e.printStackTrace();
				}
			}
		}
	}
	
	class PlayMusicTask implements Runnable {
		private String musicFilePath;

		PlayMusicTask(String musicFile) {
			musicFilePath = musicFile;
		}

		public void run() {
			File musicFile = new File(musicFilePath);
			if (musicFile.exists()) {
				Sound.playSample(musicFile, 95);
			}
		}
	}

	// Following method may not needed
	public void forward(int speed, int angle) {
		if (0 == speed) {
			speed = 360;
		}
		if (0 == angle) {
			angle = 360;
		}
		motorB.setSpeed(speed);
		motorC.setSpeed(speed);
		motorB.forward();
		motorC.forward();
	}

	public void backword(int speed, int angle) {
		if (0 == speed) {
			speed = 360;
		}
		motorB.setSpeed(speed);
		motorC.setSpeed(speed);
		motorB.backward();
		motorC.backward();
	}

	public void left(int speed, int angle) {
		if (0 == angle) {
			angle = 120;
		}
		if (0 == speed) {
			speed = 180;
		}

		turn(speed, -angle);
	}

	public void right(int speed, int angle) {
		if (0 == angle) {
			angle = 120;
		}
		if (0 == speed) {
			speed = 180;
		}

		turn(speed, angle);
	}

	public void stop() {
		synchronized(this) {
			this.keepMoving = false;
		}
		motorB.stop(true);
		motorC.stop(true);
		motorB.stop();
		motorC.stop();
		waitForStop();
	}
	
	private void turn(int speed, int angle) {
		motorB.setSpeed(speed);
		motorC.setSpeed(speed);
		motorB.rotate(angle, true);
		motorC.rotate(-angle, true);
	}
	
	public void setManuallyFoundPos(boolean found){
		this.manuallyFoundPos = true;
	}
	
	public void waitForStop(){
		while (true) {
			if (!motorB.isMoving() && !motorC.isMoving()) {
				return;
			}
			sleepInMillSeconds(100);
		}		
	}
	
	//Common methods
	public void sleepInSeconds(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void sleepInMillSeconds(int millSeconds) {
		try {
			TimeUnit.MILLISECONDS.sleep(millSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
