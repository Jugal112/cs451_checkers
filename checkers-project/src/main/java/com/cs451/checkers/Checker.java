package com.cs451.checkers;

/**
 * Created by jugal on 8/15/2016.
 */
public class Checker {
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    private String color;

    public Checker (String color) {
        this.color = color;
    }

    public String toString() {
        return this.color;
    }

}
