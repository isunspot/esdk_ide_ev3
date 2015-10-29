package com.huawei.esdk.ev3.processor;

import com.huawei.esdk.ev3.common.NetMessage;
import com.huawei.esdk.ev3.server.Communicator;

/**
 * 操作员接口，所有操作员类必须实现此接口， 用以操作通讯员传来的消息。
 * 
 * @param <T>
 *            操作员可处理的消息类型
 */
public interface Processor<T extends NetMessage> {
	/**
	 * Process the command
	 * @param commandMessage
	 * @param communicator
	 */
	void process(T commandMessage, Communicator communicator);
}
