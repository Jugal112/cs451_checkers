package com.cs451.checkers;

public class HostPingWaitingThread extends Thread {
	int port = 5501;
	
	public void run() {
		PingNetworkManager.getInstance().host(port);
    	PingOpponentThread pt = new PingOpponentThread(5501, 0);
    	pt.start();
	}

	public HostPingWaitingThread(int port) {
		this.port = port;
	}
}