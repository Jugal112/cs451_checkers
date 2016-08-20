package com.cs451.checkers;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.function.Function;

import com.cs451.checkers.GameManager.Color;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

class Browser extends Region {

    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();

    public Browser() throws URISyntaxException {
        //add the web view to the scene
        getChildren().add(browser);

        //set up java code on js side
        JSObject window = (JSObject) webEngine.executeScript("window");
        window.setMember("javaOp", new JavaOps());

        //load the web page
        //webEngine.load("file:/" + path + "/src/resources/index.html");
        URL url = getClass().getResource("/index.html");
        webEngine.load(url.toExternalForm());
    }

    public Object executeJavascript(String js) {
        // Whatever is returned from the javascript call is returned and cast to the Java equivalent
        return webEngine.executeScript(js);
    }

    //Deprecated for now
    public void addListenerAfterLoad() {
        //On the JS side it is in the form of JavaOps.method(parameter)

        webEngine.getLoadWorker().stateProperty().addListener(
                new ChangeListener<State>() {

                    @Override
                    public void changed(ObservableValue<? extends State> ov, State oldState, State newState) {
                        if (newState == State.SUCCEEDED) {
                            JSObject window = (JSObject) webEngine.executeScript("window");
                            window.setMember("javaOp", new JavaOps());
                        }
                    }
                });
    }

    @Override
    protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        layoutInArea(browser, 0, 0, w, h, 0, HPos.CENTER, VPos.CENTER);
    }
    
    public void hostContinue(){
		Function<Object, Integer> hc = new Function<Object, Integer>() {
			@Override
			public Integer apply(Object t) {
				// TODO Auto-generated method stub
				return (Integer) webEngine.executeScript("hostContinue()");
			}
		};
			
		runTask(hc, null);
    }
    
    public int setInitialization(Color color){
    	final String c;
    	
    	if(color == Color.BLACK){
    		c = "BLACK";
    	}
    	else{
    		c = "RED";
    	}
    	
		Function<Object, Integer> hc = new Function<Object, Integer>() {
			@Override
			public Integer apply(Object t) {
				// TODO Auto-generated method stub
				return (Integer) webEngine.executeScript("startGame('" + c + "')");
			}
		};
			
		runTask(hc, null);
    	return 0;
    }
    
    public void runTask(final Function<Object, Integer> task, final Object param){
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
				task.apply(param);
		    }
		});
    }
}
