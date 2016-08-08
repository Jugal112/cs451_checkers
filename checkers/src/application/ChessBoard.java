package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ChessBoard{
	public Scene getScene(){
		GridPane gp = new GridPane();
		gp.setAlignment(Pos.CENTER);
		gp.setPadding(new Insets(25, 25, 25, 25));
		Scene scene = new Scene(gp,800,600);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		for(int i=0; i < 8; i++ ){
			for(int j=0; j < 8; j++ ){
				Rectangle rectangle;
				if((i+j)%2 == 0){
					rectangle = new Rectangle(50,50,Color.WHITE);
				}
				else{
					rectangle = new Rectangle(50,50,Color.RED);
				}
				gp.add(rectangle, j, i);
			}
		}
		
		return scene;
	}
}
