package com.cs451.checkers;

import junit.framework.TestCase;

public class EndNetworkMessageTest extends TestCase {

	public void testGetAndSet() {
		EndNetworkMessage n = new EndNetworkMessage();
		n.set("t");
		assertTrue(n.get().equals("t"));
	}

	public void testToString() {
		EndNetworkMessage n = new EndNetworkMessage();
		assertTrue(n.toString().equals("Game is kill"));
	}

	public void testGetType() {
		assertTrue(new EndNetworkMessage().getType().equals(EndNetworkMessage.class));
	}

}
