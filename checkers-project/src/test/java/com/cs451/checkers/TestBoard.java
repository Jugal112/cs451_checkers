package com.cs451.checkers;

import junit.framework.TestCase;

public class TestBoard extends TestCase {

	private Board testBoard = new Board();

	public String displayBoardArr(String [][] b){
		String s = "\n";
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[i].length; j++) {
				if(b[i][j] != null && !"inv".equals(b[i][j])){
					//System.out.print(b[i][j]);
					s+=b[i][j];
				} else {
					//System.out.print("-");
					s+="-";
				}
				if(j == 7){
					//System.out.println();
					s+="\n";
				}
			}
		}
		return s;
	}

	public void testBoard() {
		testBoard = null;
		testBoard = new Board();
		assertEquals(testBoard == null, false);
	}

	public void testToStringArray() {
		String[][] strArr = testBoard.toStringArray();
		assertEquals(strArr != null, true);
		for (int i = 0; i < strArr.length; i++) {
			for (int j = 0; j < strArr[i].length; j++) {
				//assertTrue("inv".equals(strArr[i][j]) || "r".equals(strArr[i][j]) || "b".equals(strArr[i][j]));
			}
		}
	}

	public void testMovePiece() {
		testBoard = new Board();
		Position source = new Position(2, 1);
		Position destination = new Position(3,0);
		Move m = new Move("");
		assertTrue(testBoard.getPiece(destination) == null);
		testBoard.movePiece(source, destination, m.getType());
		assertTrue(testBoard.getPiece(destination) != null);
		assertTrue(testBoard.getPiece(destination).getColor().equals("r"));
		assertTrue(testBoard.getPiece(source) == null);

	}

	public void testBetween() {
		testBoard = new Board();
		Position p1 = new Position(2, 1);
		Position p2 = new Position(4,3);
		Position between = testBoard.between(p1, p2);
		Position expected = new Position(3, 2);
		String a[][] = testBoard.toStringArray();
		a[2][1] = "S"; //source on board
		a[4][3] = "D"; //destination on board
		a[3][2] = "E"; //expected result
		a[between.getRow()][between.getColumn()] = "B"; //actual result
		displayBoardArr(a);
		assertTrue("Expected: 3,2 but result was "+ between.getRow()+","+ between.getColumn() + displayBoardArr(a), expected.getRow() == between.getRow() && expected.getColumn() == between.getColumn());
	}

	public void testMakeMove() {
		//fail("Not yet implemented");
	}

	public void testGetPiecePosition() {
		testBoard = new Board();
		Position redPiece = new Position(2, 1);
		Position blackPiece = new Position(5,0);
		Position nullPiece = new Position(3,3);
		assertTrue(testBoard.getPiece(redPiece) instanceof Checker);
		assertTrue(testBoard.getPiece(blackPiece) instanceof Checker);

		assertTrue("Expected black piece", "b".equals(testBoard.getPiece(blackPiece).getColor()));
		assertTrue("Expected red piece", "r".equals(testBoard.getPiece(redPiece).getColor()));
		assertTrue("Expected null", testBoard.getPiece(nullPiece) == null);
	}

	public void testSetPiece() {
		testBoard = new Board();
		Position pos = new Position(3, 2);
		Checker checker = new Checker("r");
		assertTrue(testBoard.getPiece(pos) == null);
		testBoard.setPiece(pos, checker);
		assertTrue(testBoard.getPiece(pos) == checker);
	}

	public void testKillPiece() {
		testBoard = new Board();
		Position pos = new Position(3, 2);
		Checker checker = new Checker("r");
		testBoard.setPiece(pos, checker);
		assertTrue(testBoard.getPiece(pos) == checker);
		testBoard.killPiece(pos);
		assertTrue(testBoard.getPiece(pos) == null);
	}

	public void testGetPieceIntInt() {
		testBoard = new Board();
		assertTrue(testBoard.getPiece(2,1) instanceof Checker);
		assertTrue(testBoard.getPiece(5,0) instanceof Checker);

		assertTrue("Expected black piece", "b".equals(testBoard.getPiece(5,0).getColor()));
		assertTrue("Expected red piece", "r".equals(testBoard.getPiece(2,1).getColor()));
		assertTrue("Expected null", testBoard.getPiece(3,3) == null);
	}

	public void testGetBoard() {
		testBoard = new Board();
		Checker[][] b = testBoard.getBoard();
		assertTrue(b.length == 8);
		for(int i = 0; i < b.length; i++){
			assertTrue(b[i].length == 8);
		}
	}

}
