package com.cs451.checkers;

import org.w3c.dom.Document;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;
class Browser extends Region {
	 
    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();
     
    public Browser() {
        getStyleClass().add("browser");
        String path = System.getProperty("user.dir");
        // load the web page
        webEngine.load("file://"+ path + "/src/resources/index.html");
        //add the web view to the scene
        getChildren().add(browser);
        
        //This listener catches javascript that is calling to the java backend, these methods can be found in JavaOps.java
        //On the JS side it is in the form of JavaOps.method(parameter)
        webEngine.getLoadWorker().stateProperty().addListener(
                new ChangeListener<State>(){

                    @Override
                    public void changed(ObservableValue<? extends State> ov, State oldState, State newState) {
                        if(newState == State.SUCCEEDED){
                            JSObject window = (JSObject)webEngine.executeScript("window");
                            window.setMember("javaOp", new JavaOps());

                        }
                    }
                });
        
    }
    
    public Object executeJavascript(String js){
    	// Whatever is returned from the javascript call is returned and cast to the Java equivalent
    	return webEngine.executeScript(js);
    }

    @Override protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        layoutInArea(browser,0,0,w,h,0, HPos.CENTER, VPos.CENTER);
    }
}
