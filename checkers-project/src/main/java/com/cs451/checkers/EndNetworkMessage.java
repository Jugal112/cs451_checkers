package com.cs451.checkers;

public class EndNetworkMessage extends NetworkMessage {
	private String message = "Game is kill";

	@Override
	public void set(Object data) {
		// TODO Auto-generated method stub
		message = (String)data;
	}

	@Override
	public Object get() {
		// TODO Auto-generated method stub
		return message;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return message.toString();
	}

	@Override
	public Class getType() {
		// TODO Auto-generated method stub
		return EndNetworkMessage.class;
	}
}
