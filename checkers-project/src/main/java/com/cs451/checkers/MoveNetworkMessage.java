package com.cs451.checkers;

public class MoveNetworkMessage extends NetworkMessage {
	private Move move;

	@Override
	public void set(Object data) {
		// TODO Auto-generated method stub
		move = (Move)data;
	}

	@Override
	public Object get() {
		// TODO Auto-generated method stub
		return move;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return move.toString();
	}

	@Override
	public Class getType() {
		// TODO Auto-generated method stub
		return MoveNetworkMessage.class;
	}
	
	public MoveNetworkMessage(Move move) {
		this.move = move;
	}

}
