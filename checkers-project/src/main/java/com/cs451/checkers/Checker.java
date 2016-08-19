package com.cs451.checkers;

/**
 * Created by jugal on 8/15/2016.
 */
public class Checker {
	protected String color;
	
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }    

    public Checker (String color) {
        this.color = color;
    }

    public boolean isOpponent (Checker checker) {
        if (this.color.equals(checker.getColor())) {
            return false;
        }
        return true;
    }

    public String toString() {
        return this.color;
    }

}
