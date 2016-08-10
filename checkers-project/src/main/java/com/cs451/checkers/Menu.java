package com.cs451.checkers;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Menu {
	public Scene getScene(final Stage primaryStage){
		final GridPane vbox = new GridPane();
		vbox.setGridLinesVisible(true);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(0, 0, 25, 25));
		Scene scene = new Scene(vbox,800,600);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		Text title = new Text("CHECKERS");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 50));
		title.setFill(Color.WHITE);
		vbox.add(title, 0, 0);
	
		Text db = new Text("Developed by:");
		db.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
		db.setFill(Color.WHITE);
	
		Text names = new Text("Chris Buchter\nAaron Campbell\nDaniel Laikhter\nJugal Lodaya");
		names.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
		names.setFill(Color.WHITE);
		VBox nameVbox = new VBox();
		nameVbox.getChildren().addAll(db, names);
		nameVbox.setAlignment(Pos.CENTER);
		vbox.add(nameVbox, 0, 1);
		
	
		FadeTransition ft = new FadeTransition(Duration.millis(2000), nameVbox);
		ft.setOnFinished(new EventHandler<ActionEvent>(){
	        public void handle(ActionEvent arg0) {
	        	GridPane hbox = new GridPane();
				Button host = new Button("Host");
				Button join = new Button("Join");
				host.setAlignment(Pos.CENTER_RIGHT);
				join.setAlignment(Pos.CENTER_LEFT);
				hbox.setAlignment(Pos.CENTER);
				hbox.setHgap(30);
				hbox.add(host, 0, 1);
				hbox.add(join, 1, 1);
				vbox.add(hbox, 0, 1);
	 			
				host.setOnAction(new EventHandler<ActionEvent>(){
		            public void handle(ActionEvent t){
		            	Scene p = new ChessBoard().getScene();
		    			primaryStage.setScene(p);
		    			primaryStage.show();
		            }
				});
			}
	    });
		ft.setFromValue(1.0);
		ft.setToValue(0);
		ft.setCycleCount(0);
		ft.setAutoReverse(true);
		ft.play();
		return scene;
	}
}
