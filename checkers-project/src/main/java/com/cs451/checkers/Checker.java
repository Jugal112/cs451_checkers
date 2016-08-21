package com.cs451.checkers;

/**
 * Created by jugal on 8/15/2016.
 */
import com.cs451.checkers.GameManager.Color;

public class Checker {
	protected Color color;
	
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }    

    public Checker (Color color) {
        this.color = color;
    }

    public boolean isOpponent (Checker checker) {
        if (checker == null) {
            return false;
        }
        else if (this.color.equals(checker.getColor())) {
            return false;
        }
        return true;
    }

    public String toString() {
        return this.color.toString();
    }

}
