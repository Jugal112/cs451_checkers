package com.cs451.checkers;

import junit.framework.TestCase;

public class MoveNetworkMessageTest extends TestCase {

	public void testGetAndSet() {
		MoveNetworkMessage m = new MoveNetworkMessage(null);
		Move move = new Move();
		m.set(move);
		assertTrue(m.get() != null && m.get() == move);
	}

	public void testToString() {
		MoveNetworkMessage m = new MoveNetworkMessage(null);
		Move move = new Move();
		m.set(move);
		assertTrue(m.toString().equals(move.toString()));
	}

	public void testGetType() {
		MoveNetworkMessage m = new MoveNetworkMessage(null);
		assertTrue(m.getType() == MoveNetworkMessage.class);
	}

	public void testMoveNetworkMessage() {
		MoveNetworkMessage m = new MoveNetworkMessage(null);
		assertTrue(m.get() == null);
		
		m = new MoveNetworkMessage(null);
		Move move = new Move();
		m.set(move);
		assertTrue(m.get() != null && m.get() == move);
	}

}
