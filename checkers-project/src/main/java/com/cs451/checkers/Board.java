package com.cs451.checkers;

public class Board {
    String[][] pieces;

    public Board() {
        pieces = new String[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    pieces[i][j] = "inv";
                } else if (i < 3) {
                    pieces[i][j] = "r";
                } else if (i >= 5) {
                    pieces[i][j] = "b";
                } else {
                    pieces[i][j] = "";
                }
            }
        }
    }

    public String[][] getBoard() {
        return pieces;
    }
}
