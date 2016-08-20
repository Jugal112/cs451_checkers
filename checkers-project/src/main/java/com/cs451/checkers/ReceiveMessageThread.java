package com.cs451.checkers;

import java.util.function.Function;
import java.util.logging.Logger;

public class ReceiveMessageThread extends Thread {
	int port = 5500;
	Function<NetworkMessage, Integer> runAfter;
	
	public void run() {
		Logger.getGlobal().info("Receive Message Thread Running...");
		NetworkMessage m = NormalNetworkManager.getInstance().receiveMessage();
    	if(runAfter != null)
    		runAfter.apply(m);
	}

	public ReceiveMessageThread(int port, Function<NetworkMessage, Integer> after) {
		this.port = port;
		this.runAfter = after;
	}
}
