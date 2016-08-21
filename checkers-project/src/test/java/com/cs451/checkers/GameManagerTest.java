package com.cs451.checkers;

import com.cs451.checkers.GameManager.Color;
import com.cs451.checkers.GameManager.Player;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import junit.framework.TestCase;

public class GameManagerTest extends TestCase {

	public void testInitGame() {
		GameManager gm = new GameManager();
		assertTrue(gm.player1 == null);
		assertTrue(gm.player2 == null);
		gm.initGame();
		gm.initGame(); // hit swap branch
		assertTrue(gm.board != null);
		assertTrue(gm.player1 != null);
		assertTrue(gm.player2 != null);
	}

	public void testInitGameColor() {
		GameManager gm = new GameManager();
		assertTrue(gm.player1 == null);
		assertTrue(gm.player2 == null);
		gm.initGame(Color.BLACK);
		assertTrue(gm.board != null);
		assertTrue(gm.player1 == Color.BLACK);
		assertTrue(gm.player2 == Color.RED);
		
		gm = new GameManager();
		assertTrue(gm.player1 == null);
		assertTrue(gm.player2 == null);
		gm.initGame(Color.RED);
		assertTrue(gm.board != null);
		assertTrue(gm.player1 == Color.RED);
		assertTrue(gm.player2 == Color.BLACK);
	}

	public void testSendInitializationData() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				new JFXPanel();
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						new Main().start(new Stage());
						assertTrue(new GameManager().sendInitializationData(Color.BLACK) == 0);
					}
				});
			}
		});
		thread.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void testWaitForOpponent(){
		GameManager gm = new GameManager();
		gm.waitForOpponent();
	}
	
	public void testCheckGameState(){
		GameManager gm = new GameManager();
		gm.checkGameState();
	}
	
	public void testSetGetCurrentPlayer(){
		GameManager gm = new GameManager();
		gm.setCurrentPlayer(Player.PLAYER1);
		assertTrue(gm.getCurrentPlayer() == Player.PLAYER1);
		gm.setCurrentPlayer(Player.PLAYER2);
		assertTrue(gm.getCurrentPlayer() == Player.PLAYER2);
	}
}
