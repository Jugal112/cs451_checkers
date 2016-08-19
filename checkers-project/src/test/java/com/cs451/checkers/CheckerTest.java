package com.cs451.checkers;

import junit.framework.TestCase;

public class CheckerTest extends TestCase {

	private Checker redChecker;
	private Checker blackChecker;

	public void testGetColor() {
		redChecker = new Checker("r");
		blackChecker = new Checker("b");

		assertTrue("r".equals(redChecker.getColor()));
		assertTrue("b".equals(blackChecker.getColor()));
	}

	public void testSetColor() {
		redChecker = new Checker("r");
		blackChecker = new Checker("b");

		assertTrue("r".equals(redChecker.getColor()));
		assertTrue("b".equals(blackChecker.getColor()));

		redChecker.setColor("b");
		blackChecker.setColor("r");

		assertTrue("b".equals(redChecker.getColor()));
		assertTrue("r".equals(blackChecker.getColor()));
	}

	public void testChecker() {
		redChecker = null;
		assertTrue(redChecker==null);
		redChecker = new Checker("r");
		assertTrue(redChecker != null);
	}

	public void testToString() {
		redChecker = new Checker("r");
		blackChecker = new Checker("b");

		assertTrue("r".equals(redChecker.getColor()));
		assertTrue("b".equals(blackChecker.getColor()));
	}
	
	public void testIsOpponent(){
		assertTrue(new Checker("r").isOpponent(new Checker("b")));
		assertTrue(new Checker("b").isOpponent(new Checker("r")));
		assertFalse(new Checker("r").isOpponent(new Checker("r")));
		assertFalse(new Checker("b").isOpponent(new Checker("b")));
	}

}
