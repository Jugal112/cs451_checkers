package com.cs451.checkers;

import java.io.Serializable;

/**
 * Created by jugal on 8/15/2016.
 */
public class Position implements Serializable{
    private int row;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    private int column;

    public Position(int i, int j) {
        this.row = i;
        this.column = j;
    }

    public Position(String pos) {
        this.row = pos.charAt(0)-97;
        this.column = Character.getNumericValue(pos.charAt(1));
    }

    public Position frontLeft(int direction) {
        return new Position (this.getRow()+direction, this.getColumn()-1);
    }

    public Position frontRight(int direction) {
        return new Position (this.getRow()+direction, this.getColumn()+1);
    }

    public Position backLeft(int direction) {
        return new Position (this.getRow()-direction, this.getColumn()-1);
    }

    public Position backRight(int direction) {
        return new Position (this.getRow()-direction, this.getColumn()+1);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Position.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final Position other = (Position) obj;
        if (this.row == other.row && this.column == other.column) {
            return true;
        }
        return false;
    }

    public String toString() {
        return String.format("(%s, %s)", (char) row+97, column);
    }
}
