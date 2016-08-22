package com.cs451.checkers;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import org.mortbay.util.ajax.JSON;

import com.cs451.checkers.GameManager.Color;
import com.cs451.checkers.GameManager.Player;

import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.function.Function;
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

    public void initializeGame(String role){
		GameManager gm = Main.gm;
		System.out.println(role);
		
    	if(role.equals("HOST")){
    		gm.initGame();
			gm.setCurrentPlayer(Player.PLAYER1);
    		
    		Function<NetworkMessage, Integer> after = new Function<NetworkMessage, Integer>() {
			@Override
				public Integer apply(NetworkMessage t) {
				// TODO Auto-generated method stub
			        return gm.sendInitializationData(gm.player1);
				}
 			};
 			
 			InitializationMessage im = new InitializationMessage();
 			im.set(gm.player1);
 			
 			SendMessageThread mt = new SendMessageThread(port, im, null);
 	    	mt.start();
 	    	ReceiveMessageThread rt = new ReceiveMessageThread(port, after);
 	    	rt.start();
    	}
    	else{
  			
  			InitializationMessage im = new InitializationMessage();
  			im.set(gm.player1);
    		Function<NetworkMessage, Integer> after = new Function<NetworkMessage, Integer>() {
			@Override
				public Integer apply(NetworkMessage t) {
				// TODO Auto-generated method stub
				    log.info("Initialized message received over network! WOOHOO!");
					InitializationMessage im = (InitializationMessage)t;
					gm.initGame((Color) im.get());
					gm.setCurrentPlayer(Player.PLAYER2);
					NormalNetworkManager.getInstance().sendMessage(new PingNetworkMessage());

					return gm.sendInitializationData(gm.player2);
				}
 			};
 			
 			ReceiveMessageThread mt = new ReceiveMessageThread(port, after);
  	    	mt.start();
    	}
    }
    
    public void sendMove(String m){
    	GameManager gm = Main.gm;
    	String[] smove = m.split(" ");
    	Move move = new Move();
    	move.add(new Position(smove[0]));
    	move.add(new Position(smove[1]));
    	gm.makeMove(move);
    }
    
    public void checkGameStatus(){
    	GameManager gm = Main.gm;
    	Color color = gm.checkGameState();
    	if(color != null){
    		gm.endGame();
    	}
    }
    
    public void endGame(){
    	GameManager gm = Main.gm;
    	gm.endGame();
    }
    
    public void waitForOpponent(){
    	log.info("Waiting for opponent move!");
    	GameManager gm = Main.gm;
    	gm.waitForOpponent();
    }

    public void startHost() {
    	PingOpponentThread pt = new PingOpponentThread(5501);
    	pt.start();
    	
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
            
            PingNetworkManager pet = PingNetworkManager.getInstance();
            URL u2 = new URL(url + ":" + 5501);
            pet.connect(u2);
        	PingOpponentThread pt = new PingOpponentThread(5501);
        	pt.start();
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

    public String getValidMoves() {
        return JSON.toString(Main.gm.getValidMoves());
    }

    public String getPieces() {
        String[][] pieces = Main.gm.board.toStringArray();
        return JSON.toString(pieces);
    }
}
