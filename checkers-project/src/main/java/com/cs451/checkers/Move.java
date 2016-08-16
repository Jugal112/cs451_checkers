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

    public boolean getIsAttack() {
        return isAttack;
    }

    public Move isAttack() {
        this.isAttack = true;
        return this;
    }

    private ArrayList<Position> move;
    private boolean isAttack = false;

    public Move() {
        move = new ArrayList<Position>();
    }

}
