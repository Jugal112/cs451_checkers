package com.cs451.checkers;

import junit.framework.TestCase;
import com.cs451.checkers.GameManager.Color;
import java.util.ArrayList;

import com.cs451.checkers.Board;

public class TestBoard extends TestCase {

	private Board testBoard = new Board();

	public String displayBoardArr(String[][] b) {
		String s = "\n";
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[i].length; j++) {
				if (b[i][j] != null && !"inv".equals(b[i][j])) {
					// System.out.print(b[i][j]);
					s += b[i][j];
				} else {
					// System.out.print("-");
					s += "-";
					if (b[i][j] == null) {
						// System.out.println("Null @ "+ i + "," + j);
					}
				}
				if (j == 7) {
					// System.out.println();
					s += "\n";
				}
			}
		}
		return s;
	}

	public void testBoard() {
		testBoard = null;
		testBoard = new Board();
		assertEquals(testBoard == null, false);
		for(int i = 0; i < 7; i++){
			assertTrue(testBoard.getPiece(3,i) == null);
			assertTrue(testBoard.getPiece(4,i) == null);
		}
	}

	public void testToStringArray() {
		testBoard = new Board();
		String[][] strArr = testBoard.toStringArray();
		assertEquals(strArr != null, true);
		for (int i = 0; i < strArr.length; i++) {
			for (int j = 0; j < strArr[i].length; j++) {
				if(i < 3){
					assertTrue("Value at " + i + "," + j, "inv".equals(strArr[i][j]) || Color.RED.equals(strArr[i][j]));
				} else if(i == 3 || i == 4){
					assertTrue("Value at " + i + "," + j, "inv".equals(strArr[i][j]) || strArr[i][j] == null);
				} else if(i > 5){
					assertTrue("Value at " + i + "," + j, "inv".equals(strArr[i][j]) || Color.BLACK.equals(strArr[i][j]));
				}
			}
		}
	}

	public void testMovePiece() {
		testBoard = new Board();
		Position source = new Position(2, 1);
		Position destination = new Position(3, 0);
		assertTrue(testBoard.getPiece(destination) == null);
		testBoard.movePiece(source, destination, false);
		assertTrue(testBoard.getPiece(destination) != null);
		assertTrue(testBoard.getPiece(destination).getColor().equals(Color.RED));
		assertTrue(testBoard.getPiece(source) == null);

		testBoard = new Board();
		source = new Position(2, 1);
		destination = new Position(3, 0);
		assertTrue(testBoard.getPiece(destination) == null);
		testBoard.movePiece(source, destination, false);
		assertTrue(testBoard.getPiece(destination) != null);
		assertTrue(testBoard.getPiece(destination).getColor().equals(Color.RED));
		assertTrue(testBoard.getPiece(source) == null);

	}

	public void testBetween() {
		testBoard = new Board();
		Position p1 = new Position(2, 1);
		Position p2 = new Position(4, 3);
		Position between = testBoard.between(p1, p2);
		Position expected = new Position(3, 2);
		String a[][] = testBoard.toStringArray();
		a[2][1] = "S"; // source on board
		a[4][3] = "D"; // destination on board
		a[3][2] = "E"; // expected result
		a[between.getRow()][between.getColumn()] = "BLACK"; // actual result
		displayBoardArr(a);
		assertTrue("Expected: 3,2 but result was " + between.getRow() + "," + between.getColumn() + displayBoardArr(a),
				expected.getRow() == between.getRow() && expected.getColumn() == between.getColumn());
	}

	public void testMakeMove() {
		// fail("Not yet implemented");
	}

	public void testGetPiecePosition() {
		testBoard = new Board();
		Position redPiece = new Position(2, 1);
		Position blackPiece = new Position(5, 0);
		Position nullPiece = new Position(3, 3);
		assertTrue(testBoard.getPiece(redPiece) instanceof Checker);
		assertTrue(testBoard.getPiece(blackPiece) instanceof Checker);

		assertTrue("Expected black piece", Color.BLACK.equals(testBoard.getPiece(blackPiece).getColor()));
		assertTrue("Expected red piece", Color.RED.equals(testBoard.getPiece(redPiece).getColor()));
		assertTrue("Expected null", testBoard.getPiece(nullPiece) == null);
	}

	public void testSetPiece() {
		testBoard = new Board();
		Position pos = new Position(3, 2);
		Checker checker = new Checker(Color.RED);
		assertTrue(testBoard.getPiece(pos) == null);
		testBoard.setPiece(pos, checker);
		assertTrue(testBoard.getPiece(pos) == checker);
	}

	public void testKillPiece() {
		testBoard = new Board();
		Position pos = new Position(3, 2);
		Checker checker = new Checker(Color.RED);
		testBoard.setPiece(pos, checker);
		assertTrue(testBoard.getPiece(pos) == checker);
		testBoard.killPiece(pos);
		assertTrue(testBoard.getPiece(pos) == null);
	}

	public void testGetPieceIntInt() {
		testBoard = new Board();
		assertTrue(testBoard.getPiece(2, 1) instanceof Checker);
		assertTrue(testBoard.getPiece(5, 0) instanceof Checker);

		assertTrue("Expected black piece", Color.BLACK.equals(testBoard.getPiece(5, 0).getColor()));
		assertTrue("Expected red piece", Color.RED.equals(testBoard.getPiece(2, 1).getColor()));
		assertTrue("Expected null", testBoard.getPiece(3, 3) == null);
	}

	public void testGetBoard() {
		testBoard = new Board();
		Checker[][] b = testBoard.getBoard();
		assertTrue(b.length == 8);
		for (int i = 0; i < b.length; i++) {
			assertTrue(b[i].length == 8);
		}
	}
	
	public void testGetRegularMoves(){
		testBoard = new Board();
		testBoard.movePiece(new Position(2,1), new Position(3,2), false);
		testBoard.killPiece(new Position(3,2));
		testBoard.killPiece(new Position(2,3));
		testBoard.setPiece(new Position(3, 2), new King(Color.BLACK));
		//System.out.println(displayBoardArr(testBoard.toStringArray()));
		ArrayList<Move> m = testBoard.getRegularMoves(new Position(3,2), Color.RED);
		assertTrue(m.size() == 4);
	}
	
	public void testAddJumps(){
		testBoard = new Board();
		testBoard.setPiece(new Position(3, 2), new King(Color.RED));
		testBoard.movePiece(new Position(5,4), new Position(4,3), false);
		testBoard.movePiece(new Position(5,2), new Position(4,1), false);
		testBoard.killPiece(new Position(5,0));
		testBoard.killPiece(new Position(2,5));
		for(int i = 0; i < testBoard.toStringArray()[1].length; i++){
			testBoard.killPiece(new Position(1,i));
			testBoard.killPiece(new Position(2,i));
		}
		testBoard.setPiece(new Position(2, 1), new Checker(Color.BLACK));
		testBoard.setPiece(new Position(2, 3), new Checker(Color.BLACK));
		testBoard.setPiece(new Position(3, 2), new King(Color.RED));
		System.out.println(displayBoardArr(testBoard.toStringArray()));
		testBoard.addJumps(testBoard.getJumpMoves(new Position(3,2), Color.RED), testBoard.getJumpMoves(new Position(3,2), Color.RED).get(0), 1, null);
	}

}