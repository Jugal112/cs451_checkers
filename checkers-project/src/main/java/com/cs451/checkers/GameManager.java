package com.cs451.checkers;

import java.util.Random;
import java.util.function.Function;

import com.cs451.checkers.GameManager.Color;

public class GameManager {
	enum Color {RED, BLACK};
	enum Player {PLAYER1, PLAYER2};
	
	Player playerNum;
	Color player1; //host
	Color player2; //client
	Color currentPlayer;
	Board board;
	
	public void initGame(){
		board = new Board();
		if(player1 == null){
			Random rand = new Random();
			int  n = rand.nextInt(2);
			if(n == 1){
				player1 = Color.BLACK;
				player2 = Color.RED;
			}
			else{
				player2 = Color.BLACK;
				player1 = Color.RED;
			}
		}
		else{
			swapColors();
		}
	}
	
	public void initGame(Color color){
		board = new Board();
		player1 = color;
		if(color == Color.BLACK){
			player2 = Color.RED;
		}
		else{
			player2 = Color.BLACK;
		}
	}
	
	public int sendInitializationData(Color color){
		Main.browser.setInitialization(color);
		return 0;
	}
	
	public void makeMove(Move move){
		NormalNetworkManager.getInstance().sendMessage(new MoveNetworkMessage(move));	
	}
	
	public void waitForOpponent(){
		
	}
	
	public void checkGameState(){
		//check if all black or all red pieces are missing
	}
	
	
	private void swapColors(){
		Color temp = player1;
		player1 = player2;
		player2 = temp;
	}
}
