package com.cs451.checkers;

import java.io.IOException;
import java.util.function.Function;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PingOpponentThread extends Thread  {
	int port = 5501;
	
	public void run() {
		PingNetworkManager pnm = PingNetworkManager.getInstance();
		Logger.getGlobal().info("Ping Thread Running...");
		
		while(true){
			pnm.sendMessage(new PingNetworkMessage());
			try {
				sleep(1000);
				pnm.receiveMessage();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				errorPopUp();
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
    public void errorPopUp(){
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
		    	final Stage dialog = new Stage();
		        dialog.initModality(Modality.APPLICATION_MODAL);
		        dialog.initOwner(Main.stage);
		        dialog.setTitle("Error!");
		        VBox dialogVbox = new VBox(20);
		        dialogVbox.getChildren().add(new Text("Error, disconnect!"));
		        Scene dialogScene = new Scene(dialogVbox, 300, 200);
		        dialog.setScene(dialogScene);
		        dialog.show();
		    }
		});
    }

	public PingOpponentThread(int port) {
		this.port = port;
	}
}
