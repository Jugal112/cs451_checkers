package com.cs451.checkers;

import junit.framework.TestCase;
import com.cs451.checkers.GameManager.Color;
public class KingTest extends TestCase {

	King redKing;
	King blackKing;
	public void testGetColor() {
		redKing = new King(Color.RED);
		blackKing = new King(Color.RED);

		assertTrue("RED".equals(redKing.getColor()));
		assertTrue("BLACK".equals(blackKing.getColor()));
	}

	public void testSetColor() {
		redKing = new King(Color.RED);
		blackKing = new King(Color.BLACK);

		assertTrue("RED".equals(redKing.getColor()));
		assertTrue("BLACK".equals(blackKing.getColor()));

		redKing.setColor(Color.BLACK);
		blackKing.setColor(Color.RED);

		assertTrue("BLACK".equals(redKing.getColor()));
		assertTrue("RED".equals(blackKing.getColor()));
	}

	public void testKing() {
		redKing = null;
		assertTrue(redKing==null);
		redKing = new King(Color.RED);
		assertTrue(redKing != null);
	}

	public void testToString() {
		redKing = new King(Color.RED);
		blackKing = new King(Color.BLACK);
		assertTrue("RED_KING".equals(redKing.toString()));
		assertTrue("BLACK_KING".equals(blackKing.toString()));
	}
}