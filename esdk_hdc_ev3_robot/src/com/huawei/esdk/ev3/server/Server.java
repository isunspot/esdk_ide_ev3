package com.huawei.esdk.ev3.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private static Server instance = new Server();

	private ServerSocket serverSocket;
	
	private Server() {
	}

	public static Server getInstance() {
		return instance;
	}

	public void start() {
		try {
			serverSocket = new ServerSocket(9058);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Socket waitSocket() {
		try {
			Socket socket = serverSocket.accept();
			return socket;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void stop() {
		if (null != serverSocket) {
			try {
				serverSocket.close();
				System.out.println("ServerSocket Closed");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean isStarted() {
		return null != serverSocket && !serverSocket.isClosed();
	}
}
