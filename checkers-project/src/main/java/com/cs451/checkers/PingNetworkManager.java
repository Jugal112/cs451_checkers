package com.cs451.checkers;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PingNetworkManager{

    private static PingNetworkManager instance;
    private static final Logger log = Logger.getGlobal();
    private static DataOutputStream out;
    private static InputStream in;
    private static Socket sock;
    private static ServerSocket s_sock;

    public static PingNetworkManager getInstance() {
        if (instance == null) instance = new PingNetworkManager();
        return instance;
    }

    public int connect(URL url) {
        if (sock == null) {
            log.info("Trying to connect to " + url.toString());
            try {
                sock = new Socket(url.getHost(), url.getPort());
            } catch (IOException e) {
                log.severe("Error connecting socket");
                return -1;
            }
        }
        try {
            log.info("Trying to get input stream from current socket");
            in = sock.getInputStream();
        } catch (IOException e) {
            log.severe("Error getting input stream");
            return -1;
        }
        try {
            log.info("Trying to get output stream from current socket");
            out = new DataOutputStream(sock.getOutputStream());
        } catch (IOException e) {
            log.severe("Error getting output data stream from current socket");
            return -1;
        }
        return 0;
    }

    public int host(int port) {
        if (s_sock == null) {
            log.info("Trying to host a server on port " + port);
            try {
                s_sock = new ServerSocket(port);
            } catch (IOException e) {
                log.severe("Error creating ServerSocket");
                return -1;
            }
        }
        log.info("Server socket created successfully");
        if (sock == null) {
            try {
            	s_sock.setSoTimeout(2000); // Set accept timeout
            	while(true) {
            		try {
            		sock = s_sock.accept();
            		break;
            		} catch(IOException e) {
            			log.info("Still waiting for client to connect");
            		}
            	}
            } catch (IOException e) {
                log.severe("Error connecting socket");
                return -1;
            }
        }
        try {
            log.info("Trying to get input stream from current socket");
            in = sock.getInputStream();
        } catch (IOException e) {
            log.severe("Error getting input stream");
            return -1;
        }
        try {
            log.info("Trying to get output stream from current socket");
            out = new DataOutputStream(sock.getOutputStream());
        } catch (IOException e) {
            log.severe("Error getting output data stream from current socket");
            return -1;
        }

        return 0;
    }

    public int sendMessage(NetworkMessage msg) {
        try {
            log.info("Trying to send message over network");
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(msg);
        } catch (IOException e) {
            log.severe("Error sending message over network");
            return -1;
        }
        return 0;
    }

    public NetworkMessage receiveMessage() {
        NetworkMessage ret;
        try {
            log.info("Trying to receive message over the network");
            ObjectInputStream ois = new ObjectInputStream(in);
            ret = (NetworkMessage) ois.readObject();
        } catch (IOException e) {
            log.severe("Error receiving message over network");
            errorPopUp();
            ret = null;
        } catch (ClassNotFoundException c) {
            log.severe("ClassNotFoundException error");
            ret = null;
        }
        return ret;
    }
    
    public void errorPopUp(){
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
		    	final Stage dialog = new Stage();
		        dialog.initModality(Modality.APPLICATION_MODAL);
		        dialog.initOwner(Main.stage);
		        dialog.setTitle("Error!");
		        VBox dialogVbox = new VBox(20);
		        dialogVbox.getChildren().add(new Text("Error, disconnect!"));
		        Scene dialogScene = new Scene(dialogVbox, 300, 200);
		        dialog.setScene(dialogScene);
		        dialog.show();
		    }
		});
    }

    public boolean isConnected() {
        if (sock.isConnected()) return true;
        else return false;
    }

    public int close() {
        try {
            sock.close();
        } catch (IOException e) {
            log.severe("Error cannot close socket");
            return -1;
        }
        return 0;
    }
}