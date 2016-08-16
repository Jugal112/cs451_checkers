package com.cs451.checkers;

import junit.framework.TestCase;

public class KingTest extends TestCase {

	King redKing;
	King blackKing;
	public void testGetColor() {
		redKing = new King("r");
		blackKing = new King("b");

		assertTrue("r".equals(redKing.getColor()));
		assertTrue("b".equals(blackKing.getColor()));
	}

	public void testSetColor() {
		redKing = new King("r");
		blackKing = new King("b");

		assertTrue("r".equals(redKing.getColor()));
		assertTrue("b".equals(blackKing.getColor()));

		redKing.setColor("b");
		blackKing.setColor("r");

		assertTrue("b".equals(redKing.getColor()));
		assertTrue("r".equals(blackKing.getColor()));
	}

	public void testKing() {
		redKing = null;
		assertTrue(redKing==null);
		redKing = new King("r");
		assertTrue(redKing != null);
	}

	public void testToString() {
		redKing = new King("r");
		blackKing = new King("b");

		assertTrue("rk".equals(redKing.getColor()));
		assertTrue("bk".equals(blackKing.getColor()));
	}
}