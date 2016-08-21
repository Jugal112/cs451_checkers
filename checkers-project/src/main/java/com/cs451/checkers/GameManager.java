package com.cs451.checkers;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Function;

import javafx.application.Platform;

public class GameManager {
	enum Color {RED, BLACK};
	enum Player {PLAYER1, PLAYER2};
	public static final int port = 5500;
	
	Color player1; //host
	Color player2; //client
	Player currentPlayerNum;
	Color currentPlayerColor;
	Board board;
	
	public GameManager() {
		board = new Board();
	}
	
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

	public ArrayList<Move> getValidMoves() {
		return board.getValidMoves(this.currentPlayerColor);
	}

	public void makeMove(Move move){
		System.out.println("making move "+move.toString());
		System.out.println(getValidMoves().contains(move));
		if (getValidMoves().contains(move)) {
			Move theRealMove = getValidMoves().get(getValidMoves().indexOf(move));
			board.makeMove(theRealMove);
			NormalNetworkManager.getInstance().sendMessage(new MoveNetworkMessage(theRealMove));
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					Main.browser.webEngine.executeScript("switchTurn()");
					Main.browser.webEngine.executeScript("putPiecesOnBoard()");
				}
			});
		}
		else {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					Main.browser.webEngine.executeScript("putPiecesOnBoard()");
				}
			});
		}
	}
	
	public void waitForOpponent(){
		Function<NetworkMessage, Integer> after = new Function<NetworkMessage, Integer>() {
			@Override
				public Integer apply(NetworkMessage t) {
				// TODO Auto-generated method stub
					if(t.getType() == MoveNetworkMessage.class) {
						Move move = (Move)t.get();
						board.makeMove(move);
						Platform.runLater(new Runnable() {
					      @Override
						  public void run() {
						     	Main.browser.webEngine.executeScript("putPiecesOnBoard()");
						     	Main.browser.webEngine.executeScript("switchTurn()");
						  }
						});
					}
					return 1;
				}
 			};
 		ReceiveMessageThread t = new ReceiveMessageThread(port, after);
 		t.start();
	}
	
	public void checkGameState(){
		//check if all black or all red pieces are missing
	}
	
	public void setCurrentPlayer(Player player) {
		this.currentPlayerNum = player;
		if (player == Player.PLAYER1) {
			this.currentPlayerColor = player1;
		}
		else {
			this.currentPlayerColor = player2;
		}
	}
	
	public Player getCurrentPlayer() {
		return this.currentPlayerNum;
	}

	private void swapColors(){
		Color temp = player1;
		player1 = player2;
		player2 = temp;
	}
}
