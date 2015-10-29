package com.huawei.esdk.ev3;

import java.io.IOException;
import java.net.Socket;

import com.huawei.esdk.ev3.common.ChallengeResultCmd;
import com.huawei.esdk.ev3.common.ClockwiseCmd;
import com.huawei.esdk.ev3.common.ExitSignalCmd;
import com.huawei.esdk.ev3.common.FoundPositionCmd;
import com.huawei.esdk.ev3.common.PatrolCmd;
import com.huawei.esdk.ev3.common.QueryRobotMotorCmd;
import com.huawei.esdk.ev3.common.RobotMoveCmd;
import com.huawei.esdk.ev3.common.SendGiftCmd;
import com.huawei.esdk.ev3.processor.ChallengeResultProcessor;
import com.huawei.esdk.ev3.processor.ClockwiseProcessor;
import com.huawei.esdk.ev3.processor.ExitSignalProcessor;
import com.huawei.esdk.ev3.processor.FoundPositionProcessor;
import com.huawei.esdk.ev3.processor.PatrolProcessor;
import com.huawei.esdk.ev3.processor.QueryRobotMotorProcessor;
import com.huawei.esdk.ev3.processor.RobotMoveProcessor;
import com.huawei.esdk.ev3.processor.SendGiftProcessor;
import com.huawei.esdk.ev3.server.Communicator;
import com.huawei.esdk.ev3.server.Server;

import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.KeyListener;
import lejos.hardware.LED;
import lejos.hardware.Sound;
import lejos.hardware.ev3.EV3;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.Font;
import lejos.hardware.lcd.GraphicsLCD;

public class HDCRobotServer {

	private static EV3 ev3 = LocalEV3.get();

	private static GraphicsLCD g = ev3.getGraphicsLCD();

	private static LED led = ev3.getLED();

	static {
		g.setFont(Font.getSmallFont());
	}

	private static void promptWait() {
		g.clear();
		g.drawString("Waiting for connection...", 0, 0, GraphicsLCD.LEFT | GraphicsLCD.TOP);
		led.setPattern(6);
	}

	private static void promptConnected(Socket socket) {
		g.clear();
		g.drawString(socket.getPort() + " Connected!", 0, 0, GraphicsLCD.LEFT | GraphicsLCD.TOP);
		led.setPattern(1);
	}

	public static void main(String[] args) {
		// 取得服务器对象
		final Server server = Server.getInstance();
		
		Button.ESCAPE.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(Key k) {
				server.stop();
				System.exit(0);
			}

			@Override
			public void keyReleased(Key k) {
			}
		});
		
		//机器人对象
		HDCIDERobot robot = HDCIDERobot.getInstance();
		//顺时针还是逆时针
		robot.init(true);
		
		// 提示服务器等待连接
		promptWait();
		// 启动服务器，等待连接；没有Socket连接请求时会处于等待状态中
		server.start();
		
		Socket socket;
		while(true) {
			socket = server.waitSocket();
			//已建立了一个Socket链接， 通知已连接
			promptConnected(socket);
			try {
				// 取得通讯员对象
				Communicator communicator = getCommunicator(socket);
				registryProcessor(robot, communicator);
			} catch (IOException e) {
				Sound.buzz();
				e.printStackTrace();
			}
		}
	}
	
	private static Communicator getCommunicator(Socket socket) throws IOException {
		Communicator communicator = new Communicator(socket.getInputStream(), socket.getOutputStream());
		return communicator;
	}
	
	private static void registryProcessor(HDCIDERobot robot, Communicator communicator) {
		// 设置机器人运行方向
		communicator.addProcessor(ClockwiseCmd.class, new ClockwiseProcessor(robot));
		//
		communicator.addProcessor(ChallengeResultCmd.class, new ChallengeResultProcessor(robot));
		//巡逻
		communicator.addProcessor(PatrolCmd.class, new PatrolProcessor(robot));
		// 追加机器人移动命令处理员
		communicator.addProcessor(RobotMoveCmd.class, new RobotMoveProcessor(robot));
		// 追加退出命令处理员
		communicator.addProcessor(ExitSignalCmd.class, new ExitSignalProcessor(robot));
		//机器人马达状态查询命令
		communicator.addProcessor(QueryRobotMotorCmd.class, new QueryRobotMotorProcessor(robot));
		
		communicator.addProcessor(SendGiftCmd.class, new SendGiftProcessor(robot));
		
		communicator.addProcessor(FoundPositionCmd.class, new FoundPositionProcessor(robot));
	}
}
