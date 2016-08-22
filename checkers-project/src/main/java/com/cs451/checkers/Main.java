package com.cs451.checkers;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class Main extends Application {
	
	public static Stage stage;
	public static Browser browser;
	public static GameManager gm;
	
    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.setTitle("Checkers");
            browser = new Browser();
            gm = new GameManager();
            primaryStage.setScene(new Scene(browser, 800, 600));
            primaryStage.show();
            
            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent t) {
                    Platform.exit();
                    System.exit(0);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        this.stage = primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    
}
