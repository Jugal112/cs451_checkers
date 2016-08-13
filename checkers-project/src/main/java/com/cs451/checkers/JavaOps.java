package com.cs451.checkers;

import javafx.application.Platform;

public class JavaOps {
    public void debug(String p) {
        System.out.println(p);
    }
    public void exit() {
        Platform.exit();
    }
    public String getIPAddress() {
    	return "0.0.0.0";
    }
}
