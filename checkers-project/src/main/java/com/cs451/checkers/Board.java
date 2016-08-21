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
        ArrayList<Move> jumps = new ArrayList<Move>();
        Checker checker = getPiece(pos);
        if (checker instanceof Checker && checker.getColor() == color) {
            int direction = 1;
            if (checker.getColor().equals(Color.BLACK)) {
                direction = -1;
            }
            Move move = new Move();
            move.add(pos);
            addJumps(jumps, move, direction, checker);
        }
        return jumps;
    }

    public void addJumps(ArrayList<Move> jumps, Move move, int direction, Checker checker) {
        Position pos = move.getLastPosition();
        boolean endMove = true;
        Position frontRightJump = pos.frontRight(direction).frontRight(direction);
        if (isValid(frontRightJump) && getPiece(frontRightJump) == null) {
            if (checker.isOpponent(getPiece(pos.frontRight(direction)))) {
                if (!move.getMove().contains(frontRightJump)) {
                    endMove = false;
                    Move next_move = new Move(move);
                    next_move.add(frontRightJump);
                    addJumps(jumps, next_move, direction, checker);
                }
            }
        }
        Position frontLeftJump = pos.frontLeft(direction).frontLeft(direction);
        if (isValid(frontLeftJump) && getPiece(frontLeftJump) == null) {
            if (checker.isOpponent(getPiece(pos.frontLeft(direction)))) {
                if (!move.getMove().contains(frontLeftJump)) {
                    endMove = false;
                    Move next_move = new Move(move);
                    next_move.add(frontLeftJump);
                    addJumps(jumps, next_move, direction, checker);
                }
            }
        }
        if (checker instanceof King) {
            Position backRightJump = pos.backRight(direction).backRight(direction);
            if (isValid(backRightJump) && getPiece(backRightJump) == null) {
                if (checker.isOpponent(getPiece(pos.backRight(direction)))) {
                    if (!move.getMove().contains(backRightJump)) {
                        endMove = false;
                        Move next_move = new Move(move);
                        next_move.add(backRightJump);
                        addJumps(jumps, next_move, direction, checker);
                    }
                }
            }
            Position backLeftJump = pos.backLeft(direction).backLeft(direction);
            if (isValid(backLeftJump) && getPiece(backLeftJump) == null) {
                if (checker.isOpponent(getPiece(pos.backLeft(direction)))) {
                    if (!move.getMove().contains(backLeftJump)) {
                        endMove = false;
                        Move next_move = new Move(move);
                        next_move.add(backLeftJump);
                        addJumps(jumps, next_move, direction, checker);
                    }
                }
            }
        }
        if (endMove && move.getMove().size()>1) {
            move.isAttack();
            jumps.add(move);
        }
    }

    public void movePiece(Position source, Position destination, boolean isAttack) {
        if (getPiece(source) instanceof Checker && getPiece(destination) == null) {
            Checker checker = getPiece(source);
            setPiece(source, null);
            setPiece(destination, checker);
            System.out.println("is attack? "+isAttack);
            if (isAttack) {
                System.out.println("trying to kill");
                killPiece(between(source, destination));
            }
            int direction = 1;
            if (checker.getColor().equals(Color.BLACK)) {
                direction = -1;
            }
            System.out.println(String.format("%s %s", destination.getRow(), (4+4*direction)));
            System.out.println("tryna king me "+(destination.getRow()==(4+4*direction)));
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
        for (int i = 0; i < move.getMove().size()-1; i++) {
            movePiece(move.getMove().get(i), move.getMove().get(i+1), move.getIsAttack());
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
