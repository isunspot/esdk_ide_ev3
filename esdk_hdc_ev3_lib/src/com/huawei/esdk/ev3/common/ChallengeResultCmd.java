package com.huawei.esdk.ev3.common;

public class ChallengeResultCmd extends BaseNetMessage {
	
	/**
	 * UID
	 */
	private static final long serialVersionUID = -2496047731788983690L;

	/*
	 * ��ս������λ�ã� ��1, 2
	 */
	private String position;
	
	/*
	 * ���񼶱�, ��0��ʼ��Խ���ʾ�Ѷ�Խ��
	 */
	private String taskLevel;
	
	/*
	 * ������ս��� Y - �ɹ�, N - ʧ��
	 */
	private String result;

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getTaskLevel() {
		return taskLevel;
	}

	public void setTaskLevel(String taskLevel) {
		this.taskLevel = taskLevel;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
