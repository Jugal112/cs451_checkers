package application;

import java.net.URL;
import java.util.logging.Logger;

/**
 * NetworkManager
 * @brief The general interface for a NetworkManager. The NetworkManager is responsible for opening, hosting, sending
 * messages and closing connections.
 * Created by chris on 7/26/16.
 */
public abstract class NetworkManager {
    private static NetworkManager  instance;
    private static final Logger    log = Logger.getGlobal();

    protected NetworkManager() {}

    public abstract NetworkManager getInstance();
    public abstract int            connect(URL url);
    public abstract int            host(int port);
    public abstract int            sendMessage(NetworkMessage msg);
    public abstract NetworkMessage receiveMessage();
    public abstract boolean        isConnected();
    public abstract int            close();
}
