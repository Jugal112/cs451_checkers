package com.cs451.checkers;

import junit.framework.TestCase;

public class InitializationMessageTest extends TestCase {
	InitializationMessage m = new InitializationMessage();
	public void testGetAndSet() {
		m.set(GameManager.Color.BLACK);
		assertTrue(m.get() == GameManager.Color.BLACK);
	}

	public void testToString() {
		fail("Not yet implemented");
	}

	public void testGetType() {
		fail("Not yet implemented");
	}

}
