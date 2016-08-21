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

    public boolean getIsPromotion() {
        return isPromotion;
    }

    public void isAttack() {
        this.isAttack = true;
    }

    public void isPromotion() {
        this.isPromotion = true;
    }

    private ArrayList<Position> move;
    private boolean isAttack = false;
    private boolean isPromotion = false;

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
        return move.toString();
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Move.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final Move other = (Move) obj;
        for (int i = 0; i < move.size(); i++) {
            if (!move.get(i).equals(other.getMove().get(i))) {
                return false;
            }
        }
        return true;
    }
}
