package com.cs451.checkers;

import java.util.ArrayList;
import com.cs451.checkers.GameManager.Color;

public class Board {
    Checker[][] pieces;

    public Board() {
        pieces = new Checker[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    pieces[i][j] = null;
                } else if (i < 3) {
                    pieces[i][j] = new Checker(Color.RED);
                } else if (i >= 5) {
                    pieces[i][j] = new Checker(Color.BLACK);
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

    public ArrayList<Move> getValidMoves(Color color) {
        ArrayList<Move> moves = new ArrayList<Move>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 1) {
                    Position pos = new Position(i, j);
                    moves.addAll(getJumpMoves(pos, color));
                }
            }
        }
        if (moves.isEmpty()) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if ((i + j) % 2 == 1) {
                        Position pos = new Position(i, j);
                        moves.addAll(getRegularMoves(pos, color));
                    }
                }
            }
        }
        return moves;
    }

    public ArrayList<Move> getRegularMoves(Position pos, Color color) {
        ArrayList<Move> moves = new ArrayList<Move>();
        Checker checker = getPiece(pos);
        if (checker instanceof Checker && checker.getColor() == color) {
            int direction = 1;
            if (checker.getColor().equals(Color.BLACK)) {
                direction = -1;
            }
            Position frontLeft = pos.frontLeft(direction);
            if (isValid(frontLeft) && getPiece(frontLeft) == null) {
                Move move = new Move();
                move.getMove().add(pos);
                move.getMove().add(frontLeft);
                moves.add(move);
            }
            Position frontRight = pos.frontRight(direction);
            if (isValid(frontRight) && getPiece(frontRight) == null) {
                Move move = new Move();
                move.getMove().add(pos);
                move.getMove().add(frontRight);
                moves.add(move);
            }
            if (checker instanceof King) {
                Position backLeft = pos.backLeft(direction);
                if (isValid(backLeft) && getPiece(frontLeft) == null) {
                    Move move = new Move();
                    move.getMove().add(pos);
                    move.getMove().add(backLeft);
                    moves.add(move);
                }
                Position backRight = pos.backRight(direction);
                if (isValid(backRight) && getPiece(backRight) == null) {
                    Move move = new Move();
                    move.getMove().add(pos);
                    move.getMove().add(backRight);
                    moves.add(move);
                }
            }
        }
        return moves;
    }

    public ArrayList<Move> getJumpMoves(Position pos, Color color) {
        ArrayList<Move> jumps = new ArrayList<>();
        Checker checker = getPiece(pos);
        if (checker instanceof Checker && checker.getColor() == color) {
            int direction = 1;
            if (checker.getColor().equals(Color.BLACK)) {
                direction = -1;
            }
            Position frontRightJump = pos.frontRight(direction).frontRight(direction);
            if (isValid(frontRightJump) && getPiece(frontRightJump) == null) {
                if (checker.isOpponent(getPiece(pos.frontRight(direction)))) {
                        Move next_move = new Move();
                        next_move.add(pos);
                        next_move.add(frontRightJump);
                        jumps.add(next_move);
                }
            }
            Position frontLeftJump = pos.frontLeft(direction).frontLeft(direction);
            if (isValid(frontLeftJump) && getPiece(frontLeftJump) == null) {
                if (checker.isOpponent(getPiece(pos.frontLeft(direction)))) {
                    Move next_move = new Move();
                    next_move.add(pos);
                    next_move.add(frontLeftJump);
                    jumps.add(next_move);
                }
            }
            if (checker instanceof King) {
                Position backRightJump = pos.backRight(direction).backRight(direction);
                if (isValid(backRightJump) && getPiece(backRightJump) == null) {
                    if (checker.isOpponent(getPiece(pos.backRight(direction)))) {
                        Move next_move = new Move();
                        next_move.add(pos);
                        next_move.add(backRightJump);
                        jumps.add(next_move);
                    }
                }
                Position backLeftJump = pos.backLeft(direction).backLeft(direction);
                if (isValid(backLeftJump) && getPiece(backLeftJump) == null) {
                    if (checker.isOpponent(getPiece(pos.backLeft(direction)))) {
                        Move next_move = new Move();
                        next_move.add(pos);
                        next_move.add(backLeftJump);
                        jumps.add(next_move);
                    }
                }
            }
        }
        for (Move m : jumps) {
            m.isAttack();
        }
        return jumps;
    }

    public void addJumps(ArrayList<Move> jumps, Move move, int direction, Checker checker) {

    }

    public void movePiece(Position source, Position destination, boolean isAttack) {
        if (getPiece(source) instanceof Checker && getPiece(destination) == null) {
            Checker checker = getPiece(source);
            setPiece(source, null);
            setPiece(destination, checker);
            System.out.println("is attack? "+isAttack);
            if (isAttack) {
                killPiece(between(source, destination));
            }
            int direction = 1;
            if (checker.getColor().equals(Color.BLACK)) {
                direction = -1;
            }
            if (destination.getRow()==3.5+3.5*direction) {
                System.out.println("King me!");
                setPiece(destination, new King(checker.getColor()));
            }
        }
    }

    public Position between(Position p1, Position p2) {
        int row = Math.abs((p2.getRow() + p1.getRow())/2);
        int column = Math.abs((p2.getColumn() + p1.getColumn())/2);
        return new Position(row, column);
    }

    public void makeMove(Move move) {
        Position source = move.getMove().get(0);
        Position destination = move.getMove().get(1);
        if (getPiece(source) instanceof Checker && getPiece(destination) == null) {
            Checker checker = getPiece(source);
            setPiece(source, null);
            setPiece(destination, checker);
            System.out.println("is attack? "+move.getIsAttack());
            if (move.getIsAttack()) {
                killPiece(between(source, destination));
            }
            int direction = 1;
            if (checker.getColor().equals(Color.BLACK)) {
                direction = -1;
            }
            if (destination.getRow()==3.5+3.5*direction) {
                System.out.println("King me!");
                move.isPromotion();
                setPiece(destination, new King(checker.getColor()));
            }
        }
    }

    public Checker getPiece(Position pos) {
        if (isValid(pos)) {
            return pieces[pos.getRow()][pos.getColumn()];
        }
        return null;
    }

    public boolean isValid(Position pos) {
        if (pos.getColumn() < 8 && pos.getColumn() >= 0 && pos.getRow() >= 0 && pos.getRow() < 8) {
            return true;
        }
        return false;
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
