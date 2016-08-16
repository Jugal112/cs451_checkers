package com.cs451.checkers;

import java.util.ArrayList;

public class Board {
    Checker[][] pieces;

    public Board() {
        pieces = new Checker[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    pieces[i][j] = null;
                } else if (i < 3) {
                    pieces[i][j] = new Checker("r");
                } else if (i >= 5) {
                    pieces[i][j] = new Checker("b");
                } else {
                    pieces[i][j] = null;
                }
            }
        }
    }

    public String[][] toStringArray() {
        String[][] pieces_str = new String[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    pieces_str[i][j] = "inv";
                } else if (getPiece(i, j) instanceof Checker){
                    pieces_str[i][j] = pieces[i][j].toString();
                }
            }
        }
        return pieces_str;
    }

    public ArrayList<Move> getValidMoves(Position pos) {
        ArrayList<Move> moves = new ArrayList<Move>();
        Checker checker = getPiece(pos);
        if (getPiece(pos) instanceof Checker) {
            int direction = 1;
            if (checker.getColor().equals("b")) {
                direction = -1;
            }
            if (getPiece(pos.frontLeft(direction)) == null) {

            }
        } else {
            return null;
        }
        return moves;
    }

    public ArrayList<Position> getJumpMoves(Position pos) {
        ArrayList<Position> jumps = new ArrayList<Position>();

        return jumps;
    }

    public void movePiece(Position source, Position destination, boolean isAttack) {
        if (getPiece(source) instanceof Checker && getPiece(destination) == null) {
            Checker checker = getPiece(source);
            setPiece(source, null);
            setPiece(destination, checker);
            if (isAttack) {
                killPiece(between(source, destination));
            }
        }
    }

    public Position between(Position p1, Position p2) {
        int row = Math.abs((p2.getRow() + p1.getRow())/2);
        int column = Math.abs((p2.getColumn() + p1.getColumn())/2);
        return new Position(row, column);
    }

    public void makeMove(Move move) {
        for (int i = 0; i < move.getMove().size(); i++) {
            movePiece(move.getMove().get(i), move.getMove().get(i+1), move.getIsAttack());
        }
    }

    public Checker getPiece(Position pos) {
        if (pos == null) {
            return null;
        }
        return pieces[pos.getRow()][pos.getColumn()];
    }

    public void setPiece(Position pos, Checker checker) {
        pieces[pos.getRow()][pos.getColumn()] = checker;
    }

    public void killPiece(Position pos) {
        setPiece(pos, null);
    }

    public Checker getPiece(int row, int column) {
        return pieces[row][column];
    }

    public Checker[][] getBoard() {
        return pieces;
    }
}
