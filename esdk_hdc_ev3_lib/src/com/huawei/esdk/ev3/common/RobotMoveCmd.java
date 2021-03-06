package com.huawei.esdk.ev3.common;

/**
 * 控制机器人移动的命令
 *
 */
public class RobotMoveCmd implements NetMessage {
	/**
	 * 命令种类枚举
	 */
	public enum Command {
		/** 前进 */
		Forward,
		/** 后退 */
		Backward,
		/** 左转 */
		Left,
		/** 右转 */
		Right,
		/** 切断动力、惯性滑行 */
		Float,
		/** 停止，禁止转向 */
		Stop,
	}

	private static final long serialVersionUID = -7523347542695340161L;

	private Command command;
	/** 机器人行进时的引擎转速，单位:度/s */
	private short speed;
	/** 机器人转向角度，单位：度 */
	private short rotation;

	public Command getCommand() {
		return command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}

	public short getSpeed() {
		return speed;
	}

	public void setSpeed(short speed) {
		this.speed = speed;
	}

	public short getRotation() {
		return rotation;
	}

	public void setRotation(short rotation) {
		this.rotation = rotation;
	}

	@Override
	public String toString() {
		return "RobotMoveCommand [command=" + command + ", speed=" + speed + ", rotation=" + rotation + "]";
	}
}
