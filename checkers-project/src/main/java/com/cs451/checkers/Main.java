package com.cs451.checkers;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Checkers");
	        primaryStage.setScene(new Menu().getScene(primaryStage));
	        primaryStage.show();
	        
	    } catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		launch(args);
	}
}
