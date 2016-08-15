package com.cs451.checkers;

import java.io.Serializable;

/**
 * Message
 *
 * @brief Abstract class to represent the data sent over the network
 * Created by chris on 7/26/16.
 */
public abstract class NetworkMessage implements Serializable {
    public NetworkMessage() {
    }

    public abstract void set(Object data);

    public abstract Object get();

    public abstract String toString();

    public abstract Class getType();
}
