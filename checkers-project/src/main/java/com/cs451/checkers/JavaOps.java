package com.cs451.checkers;

import org.mortbay.util.ajax.JSON;

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
    
    public String getPieces() {
    	String[][] pieces = new Board().getBoard();
    	System.out.println(JSON.toString(pieces));
    	System.out.println("ASDAS");
    	return JSON.toString(pieces);
    }
}
