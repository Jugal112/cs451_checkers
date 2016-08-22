package com.cs451.checkers;

import junit.framework.TestCase;

public class PingNetworkMessageTest extends TestCase {

	public void testGetAndSet() {
		PingNetworkMessage m = new PingNetworkMessage();
		Object s = new Object();
		m.set(s);
		assertTrue(m.get() != null && m.get() == s); //not implemented
	}

	public void testToString() {
		PingNetworkMessage m = new PingNetworkMessage();
		Move move = new Move();
		m.set(move);
		assertTrue(m.toString().equals(move.toString())); //not implemented
	}

	public void testGetType() {
		PingNetworkMessage m = new PingNetworkMessage();
		assertTrue(m.getType() == PingNetworkMessage.class);
	}

	public void testMoveNetworkMessage() {
		PingNetworkMessage m = new PingNetworkMessage();
		assertTrue(m.get() == null);
		
		m = new PingNetworkMessage();
		Move move = new Move();
		m.set(move);
		assertTrue(m.get() != null && m.get() == move);
	}

}
