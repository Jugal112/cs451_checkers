package com.cs451.checkers;

import junit.framework.TestCase;

public class PositionTest extends TestCase {

	private Position p;

	public void testGetRow() {
		p = new Position(1, 2);
		assertTrue(p.getRow() == 1);
	}

	public void testSetRow() {
		p = new Position(1, 2);
		assertTrue(p.getRow() == 1);
		p.setRow(3);
		assertTrue(p.getRow() == 3);
	}

	public void testGetColumn() {
		p = new Position(1, 2);
		assertTrue(p.getColumn() == 2);
	}

	public void testSetColumn() {
		p = new Position(1, 2);
		assertTrue(p.getColumn() == 2);
		p.setColumn(3);
		assertTrue(p.getColumn() == 3);
	}

	public void testPosition() {
		p = null;
		assertTrue(p == null);
		p = new Position(0, 0);
		assertTrue(p != null);
	}

	public void testFrontLeft() {
		p = new Position(2,2);
		assertTrue(p.frontLeft(1).equals(new Position(3,1)));
	}

	public void testFrontRight() {
		p = new Position(2,2);
		assertTrue(p.frontRight(1).equals(new Position(3,3)));
	}

	public void testBackLeft() {
		p = new Position(2,2);
		assertTrue(p.backLeft(1).equals(new Position(1,1)));
	}

	public void testBackRight() {
		p = new Position(2,2);
		assertTrue(p.backRight(1).equals(new Position(1,3)));
	}

	public void testEquals() {
		assertTrue(new Position(2,2).equals(new Position(2,2)) && !new Position(2,2).equals(new Position(2,1)));
		assertFalse(new Position(2,2).equals(null));
		assertFalse(new Position(2,2).equals(""));
	}

	public void testToString() {
		assertTrue(new Position(2,2).toString().equals("(2, 2)"));
	}
}
