package com.cs451.checkers;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by jugal on 8/15/2016.
 */
public class Move {
    public ArrayList<Position> getMove() {
        return move;
    }

    public void setMove(ArrayList<Position> moves) {
        this.move = moves;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private ArrayList<Position> move;
    private String type = null;

    public Move(String type) {
        move = new ArrayList<Position>();
        this.type = type;
    }

}
