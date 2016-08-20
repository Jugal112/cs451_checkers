package com.cs451.checkers;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by jugal on 8/15/2016.
 */
public class Move implements Serializable{
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

    public void add(Position pos) {
        move.add(pos);
    }

    public Position getLastPosition() {
        return move.get(move.size()-1);
    }

    public Move() {
        move = new ArrayList<Position>();

    }

    public Move(Move other) {
        move = new ArrayList<Position>();
        for (Position p: other.getMove()) {
            add(p);
        }
    }

    public String toString() {
        String s = "";
        for (Position p: getMove()) {
            s += p.toString() + " ";
        }
        String result = String.format("[ %s ] ", s);
        if (isAttack) {
            result += "ATTACK";
        }
        return result;
    }
}
