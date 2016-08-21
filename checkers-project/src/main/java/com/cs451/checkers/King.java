package com.cs451.checkers;

/**
 * Created by jugal on 8/15/2016.
 */

import com.cs451.checkers.GameManager.Color;

public class King extends Checker{
    //private String color;

    public King(Color color) {
        super(color);
    }

    public String toString() {
        return this.color+"_KING";
    }
}
