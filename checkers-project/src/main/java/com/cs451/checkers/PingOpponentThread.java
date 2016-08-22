package com.cs451.checkers;

import java.util.logging.Logger;

public class PingOpponentThread extends Thread  {
	int port = 5501;
	int turn = 0;
	
	public void run() {
		Logger.getGlobal().info("Ping Thread Running...");
		NetworkMessage nm = new PingNetworkMessage();
		
		while(true){
			if(turn == 0){
				Logger.getGlobal().info("Ping Receiving...");
				try{
					nm = PingNetworkManager.getInstance().receiveMessage();
				}
				catch (NullPointerException e){
				}
				if (nm == null){
					return;
				}
				turn = 1;
			}
			else{

				try {
					sleep(500);
					Logger.getGlobal().info("Ping Sending...");
					PingNetworkManager.getInstance().sendMessage(new PingNetworkMessage());
					sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				turn = 0;
			}
		}
	}
	
	public PingOpponentThread(int port, int turn) {
		this.port = port;
		this.turn = turn;
	}
}
