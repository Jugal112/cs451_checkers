package com.cs451.checkers;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	
	public static Stage stage;
	public static Browser browser;
	
    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.setTitle("Checkers");
            browser = new Browser();
            primaryStage.setScene(new Scene(browser, 800, 600));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        this.stage = primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
