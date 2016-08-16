package com.cs451.checkers;

/**
 * Created by jugal on 8/15/2016.
 */
public class Position {
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

    public Position frontLeft(int direction) {
        if (getColumn()-1 >= 0 && getRow()+direction >= 0 && getRow()+direction <= 8) {
            return new Position (this.getRow()+direction, this.getColumn()-1);
        }
        return null;
    }

    public Position frontRight(int direction) {
        if (getColumn()+1 <= 8 && getRow()+direction >= 0 && getRow()+direction <= 8) {
            return new Position (this.getRow()+direction, this.getColumn()+1);
        }
        return null;
    }

    public Position backLeft(int direction) {
        if (getColumn()-1 >= 0 && getRow()-direction >= 0 && getRow()-direction <= 8) {
            return new Position (this.getRow()-direction, this.getColumn()-1);
        }
        return null;
    }

    public Position backRight(int direction) {
        if (getColumn()+1 <= 8 && getRow()-direction >= 0 && getRow()-direction <= 8) {
            return new Position (this.getRow()-direction, this.getColumn()+1);
        }
        return null;
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
}
