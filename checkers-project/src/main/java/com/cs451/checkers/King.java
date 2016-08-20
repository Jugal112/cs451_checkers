package com.cs451.checkers;

/**
 * Created by jugal on 8/15/2016.
 */
public class King extends Checker{
    //private String color;

    public King(String color) {
        super(color);
    }

    public String toString() {
        return this.color+"K";
    }
}
