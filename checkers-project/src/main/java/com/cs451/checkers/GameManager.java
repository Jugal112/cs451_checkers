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
	Color opponentPlayerColor;
	Board board;
	Position lastAttack;
	
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
		if (lastAttack!=null) {
			return board.getJumpMoves(lastAttack, currentPlayerColor);
		}
		return board.getValidMoves(currentPlayerColor);
	}

	public void makeMove(Move move){
		System.out.println("making move "+move.toString());
		System.out.println(board.getValidMoves(currentPlayerColor).contains(move));
		int moveIndex;
		if (lastAttack == null) {
			moveIndex = board.getValidMoves(currentPlayerColor).indexOf(move);
		} else {
			moveIndex = board.getJumpMoves(lastAttack, currentPlayerColor).indexOf(move);
		}
		if (moveIndex >= 0) {
			Move theRealMove = board.getValidMoves(currentPlayerColor).get(moveIndex);
			board.makeMove(theRealMove);
			NormalNetworkManager.getInstance().sendMessage(new MoveNetworkMessage(theRealMove));
			if (theRealMove.getIsAttack()) {
				lastAttack = theRealMove.getLastPosition();
			}
			if (!theRealMove.getIsAttack() || !(board.getJumpMoves(lastAttack, currentPlayerColor).size() > 0) || theRealMove.getIsPromotion()) {
				//you can't make any more moves. end turn
				lastAttack = null;
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						Main.browser.webEngine.executeScript("switchTurn()");
					}
				});
			}
			else {
				//you can make a move but we need a ping back
				System.out.println("Waiting for ping back");
				Function<NetworkMessage, Integer> after = new Function<NetworkMessage, Integer>() {
					@Override
					public Integer apply(NetworkMessage t) {
						// TODO Auto-generated method stub
						if(t.getType() == PingNetworkMessage.class) {
							System.out.println("Received Ping");
						}
						return 1;
					}
				};
				ReceiveMessageThread t = new ReceiveMessageThread(port, after);
				t.start();
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						Main.browser.webEngine.executeScript("whoseTurn()");
					}
				});
			}
		}
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Main.browser.webEngine.executeScript("putPiecesOnBoard()");
			}
		});
	}
	
	public void waitForOpponent(){
		Function<NetworkMessage, Integer> after = new Function<NetworkMessage, Integer>() {
			@Override
				public Integer apply(NetworkMessage t) {
				// TODO Auto-generated method stub
					if(t.getType() == MoveNetworkMessage.class) {
						Move move = (Move)t.get();
						board.makeMove(move);
						if (!move.getIsAttack() || !(board.getJumpMoves(move.getLastPosition(), opponentPlayerColor).size() > 0) || move.getIsPromotion()) {
							//opponent cannot move
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									Main.browser.webEngine.executeScript("switchTurn()");
								}
							});
						}
						else {
							//opponent can still move;
							System.out.println("Sending ping because opponent can still go");
							NormalNetworkManager.getInstance().sendMessage(new PingNetworkMessage());
							ReceiveMessageThread lt = new ReceiveMessageThread(port, this);
					 		lt.start();
						}
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								Main.browser.webEngine.executeScript("putPiecesOnBoard()");
							}
						});

					}
					else if(t.getType() == EndNetworkMessage.class){
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								Main.browser.webEngine.executeScript("setWinner('"+currentPlayerColor+"')");
							}
						});

					}
					return 1;
				}
 			};
 		ReceiveMessageThread t = new ReceiveMessageThread(port, after);
 		t.start();
	}
	
	public void endGame(){
		Function<Integer, Integer> after = new Function<Integer, Integer>() {
			@Override
			public Integer apply(Integer i) {
				// TODO Auto-generated method stub
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						Main.browser.webEngine.executeScript("setWinner('"+opponentPlayerColor+"')");
					}
				});
				return 1;
			}
		};
		EndNetworkMessage enm = new EndNetworkMessage();
		
		SendMessageThread t = new SendMessageThread(port, enm, after);
		t.start();
	}
	
	public Color checkGameState(){
		if(board.getValidMoves(this.currentPlayerColor).isEmpty()){
			return this.player2;
		}
		return null;
	}
	
	public void setCurrentPlayer(Player player) {
		this.currentPlayerNum = player;
		if (player == Player.PLAYER1) {
			this.currentPlayerColor = player1;
			this.opponentPlayerColor = player2;
		}
		else {
			this.currentPlayerColor = player2;
			this.opponentPlayerColor = player1;
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
