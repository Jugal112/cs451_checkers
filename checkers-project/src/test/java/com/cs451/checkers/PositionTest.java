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
		assertTrue(p.getColumn() == 3);	}

	public void testPosition() {
		p = null;
		assertTrue(p == null);
		p = new Position(0, 0);
		assertTrue(p != null);
	}

}
