package com.cs451.checkers;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import org.mortbay.util.ajax.JSON;

import java.net.*;
import java.util.Enumeration;
import java.util.logging.Logger;

public class JavaOps {
    public static final int port = 5500;
    public static final Logger log = Logger.getGlobal();

    public void debug(String p) {
        log.info(p);
    }

    public void exit() {
        Platform.exit();
    }

    public void startHost() {
    	HostWaitingThread wt = new HostWaitingThread(port);
    	wt.start();
    }

    public int startClient(String url) {
    	int ret = -1;
    	
        if (!url.contains("http://")) {
            url = "http://" + url;
        }
        try {
            URL u = new URL(url + ":" + port);
            NetworkManager net = NormalNetworkManager.getInstance();
            ret = net.connect(u);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            log.severe("Error the URL entered was invalid");
        }
        
        if(ret == -1) {
        	final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(Main.stage);
            dialog.setTitle("Error!");
            VBox dialogVbox = new VBox(20);
            dialogVbox.getChildren().add(new Text("Unable to connect to specified host!"));
            Scene dialogScene = new Scene(dialogVbox, 300, 200);
            dialog.setScene(dialogScene);
            dialog.show();
        }
        
        return ret;
    }

    public String getIPAddress() {
        log.info("Attempting to get possible IP addresses");
        String ret = "";
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                // filters out 127.0.0.1 and inactive interfaces
                if (iface.isLoopback() || !iface.isUp())
                    continue;

                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    if (!addr.getHostAddress().toString().contains(":")) {
                        ret += addr.getHostAddress() + "\n";
                        log.info(addr.getHostAddress());
                    }
                }
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        return ret;
    }

    public String getPieces() {
        Board board = new Board();
        String[][] pieces = board.toStringArray();
        return JSON.toString(pieces);
    }
}
