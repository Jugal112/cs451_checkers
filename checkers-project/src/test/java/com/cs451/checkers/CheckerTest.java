package com.cs451.checkers;

import junit.framework.TestCase;
import com.cs451.checkers.GameManager.Color;
public class CheckerTest extends TestCase {

	private Checker redChecker;
	private Checker blackChecker;

	public void testGetColor() {
		redChecker = new Checker(Color.RED);
		blackChecker = new Checker(Color.BLACK);

		assertTrue(Color.RED.equals(redChecker.getColor()));
		assertTrue(Color.BLACK.equals(blackChecker.getColor()));
	}

	public void testSetColor() {
		redChecker = new Checker(Color.RED);
		blackChecker = new Checker(Color.BLACK);

		assertTrue(Color.RED.equals(redChecker.getColor()));
		assertTrue(Color.BLACK.equals(blackChecker.getColor()));

		redChecker.setColor(Color.BLACK);
		blackChecker.setColor(Color.RED);

		assertTrue(Color.BLACK.equals(redChecker.getColor()));
		assertTrue(Color.RED.equals(blackChecker.getColor()));
	}

	public void testChecker() {
		redChecker = null;
		assertTrue(redChecker==null);
		redChecker = new Checker(Color.RED);
		assertTrue(redChecker != null);
	}

	public void testToString() {
		redChecker = new Checker(Color.RED);
		blackChecker = new Checker(Color.BLACK);

		assertTrue(Color.RED.equals(redChecker.getColor()));
		assertTrue(Color.BLACK.equals(blackChecker.getColor()));
	}
	
	public void testIsOpponent(){
		assertTrue(new Checker(Color.RED).isOpponent(new Checker(Color.BLACK)));
		assertTrue(new Checker(Color.BLACK).isOpponent(new Checker(Color.RED)));
		assertFalse(new Checker(Color.RED).isOpponent(new Checker(Color.RED)));
		assertFalse(new Checker(Color.BLACK).isOpponent(new Checker(Color.BLACK)));
	}

}
