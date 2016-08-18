package com.cs451.checkers;

import com.cs451.checkers.GameManager.Color;

public class InitializationMessage extends NetworkMessage {
	Color color;
	
	@Override
	public void set(Object data) {
		// TODO Auto-generated method stub
		color = (Color) data;
	}

	@Override
	public Object get() {
		// TODO Auto-generated method stub
		return (Object) color;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class getType() {
		// TODO Auto-generated method stub
		return null;
	}

}
