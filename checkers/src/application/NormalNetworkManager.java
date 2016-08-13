package application;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.logging.Logger;

/**
 * Created by chris on 7/31/16.
 */
public class NormalNetworkManager extends NetworkManager{

    private static NetworkManager  instance;
    private static final Logger log = Logger.getGlobal();
    private static DataOutputStream out;
    private static InputStream in;
    private static Socket sock;
    private static ServerSocket s_sock;


    @Override
    public NetworkManager getInstance() {
        if(instance == null) instance = new NormalNetworkManager();
        return instance;
    }

    @Override
    public int connect(URL url) {
        if(sock == null) {
            log.info("Trying to connect to " + url.toString());
            try {
                sock = new Socket(url.getHost(), url.getPort());
            }
            catch(IOException e){
                log.severe("Error connecting socket");
                return -1;
            }
        }
        try {
            log.info("Trying to get input stream from current socket");
            in = sock.getInputStream();
        }
        catch(IOException e) {
            log.severe("Error getting input stream");
            return -1;
        }
        try {
            log.info("Trying to get output stream from current socket");
            out = new DataOutputStream(sock.getOutputStream());
        }
        catch(IOException e)
        {
            log.severe("Error getting output data stream from current socket");
            return -1;
        }
        return 0;
    }

    @Override
    public int host(int port) {
        if(s_sock == null) {
            log.info("Trying to host a server on port " + port);
            try {
                s_sock = new ServerSocket(port);
            } catch (IOException e) {
                log.severe("Error creating ServerSocket");
                return -1;
            }
        }
        if(sock == null) {
            try {
                sock = s_sock.accept();
            }
            catch(IOException e){
                log.severe("Error connecting socket");
                return -1;
            }
        }
        try {
            log.info("Trying to get input stream from current socket");
            in = sock.getInputStream();
        }
        catch(IOException e) {
            log.severe("Error getting input stream");
            return -1;
        }
        try {
            log.info("Trying to get output stream from current socket");
            out = new DataOutputStream(sock.getOutputStream());
        }
        catch(IOException e)
        {
            log.severe("Error getting output data stream from current socket");
            return -1;
        }

        return 0;
    }

    @Override
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

    @Override
    public NetworkMessage receiveMessage() {
        NetworkMessage ret;
        try {
            log.info("Trying to receive message over the network");
            ObjectInputStream ois = new ObjectInputStream(in);
            ret = (NetworkMessage) ois.readObject();
        }catch(IOException e) {
            log.severe("Error receiving message over network");
            ret = null;
        }catch (ClassNotFoundException c) {
            log.severe("ClassNotFoundException error");
            ret = null;
        }
        return ret;
    }

    @Override
    public boolean isConnected() {
        if(sock.isConnected()) return true;
        else return false;
    }

    @Override
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
