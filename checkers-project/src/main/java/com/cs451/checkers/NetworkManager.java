package com.cs451.checkers;

import java.net.URL;

/**
 * NetworkManager
 *
 * @brief The general interface for a NetworkManager. The NetworkManager is responsible for opening, hosting, sending
 * messages and closing connections.
 * Created by chris on 7/26/16.
 */
public interface NetworkManager {
    public abstract int connect(URL url);

    public abstract int host(int port);

    public abstract int sendMessage(NetworkMessage msg);

    public abstract NetworkMessage receiveMessage();

    public abstract boolean isConnected();

    public abstract int close();
}
