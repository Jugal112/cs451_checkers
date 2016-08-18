package com.cs451.checkers;

import java.util.function.Function;

public class SendMessageThread extends Thread{
	int port = 5500;
	Function<Integer, Integer> runAfter;
	NetworkMessage message;
	
	public void run() {
		int result = NormalNetworkManager.getInstance().sendMessage(message);		
    	if(runAfter != null)
			runAfter.apply(result);
	}

	public SendMessageThread(int port, NetworkMessage message, Function<Integer, Integer> runAfter) {
		this.port = port;
		this.runAfter = runAfter;
		this.message = message;
	}
}