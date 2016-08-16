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

}
