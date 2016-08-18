package com.cs451.checkers;

public class HostWaitingThread extends Thread {
	int port = 5500;
	
	
	public void run() {
		NormalNetworkManager.getInstance().host(port);
		Main.browser.hostContinue();
	}

	public HostWaitingThread(int port) {
		this.port = port;
	}
	
}