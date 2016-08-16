package com.cs451.checkers;

import javafx.application.Platform;
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
        NetworkManager net = NormalNetworkManager.getInstance();
        net.host(port);
    }

    public void startClient(String url) {
        if (!url.contains("http://")) {
            url = "http://" + url;
        }
        try {
            URL u = new URL(url + ":" + port);
            NetworkManager net = NormalNetworkManager.getInstance();
            net.connect(u);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            log.severe("Error the URL entered was invalid");
        }
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
