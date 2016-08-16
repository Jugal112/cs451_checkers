package com.cs451.checkers;

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

    public Browser() {
        String path = System.getProperty("user.dir");
        //add the web view to the scene
        getChildren().add(browser);

        //set up java code on js side
        JSObject window = (JSObject) webEngine.executeScript("window");
        window.setMember("javaOp", new JavaOps());

        //load the web page
        //webEngine.load("file:/" + path + "/src/resources/index.html");
        webEngine.load(getClass().getResource("/index.html").toExternalForm());
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
}
