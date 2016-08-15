package com.cs451.checkers;

/**
 * Created by chris on 8/7/16.
 */
public class TestNetworkMessage extends NetworkMessage {
    String message;

    public TestNetworkMessage(String message) {
        this.message = message;
    }

    @Override
    public void set(Object data) {
        message = (String) data;
    }

    @Override
    public String get() {
        return null;
    }

    @Override
    public String toString() {
        return message;
    }

    @Override
    public Class getType() {
        return TestNetworkMessage.class;
    }
}
