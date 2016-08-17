package com.cs451.checkers;

import javafx.application.Platform;
import javafx.scene.web.WebEngine;

public class HostWaitingThread extends Thread {
	int port = 5500;
	
	
	public void run() {
		NormalNetworkManager.getInstance().host(port);
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
		    	Main.browser.webEngine.executeScript("hostContinue()");
		    }
		});
	}

	public HostWaitingThread(int port) {
		this.port = port;
	}
	
}