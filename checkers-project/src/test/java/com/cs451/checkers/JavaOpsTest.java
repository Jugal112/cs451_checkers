package com.cs451.checkers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import junit.framework.TestCase;

public class JavaOpsTest extends TestCase {

	public void testDebug() {
		// Nothing to test
	}

	public void testExit() {
		JavaOps j = new JavaOps();
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				new JFXPanel();
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						new Main().start(new Stage());
					}
				});
			}
		});
		thread.start();
		try {
			Thread.sleep(1000);
			j.exit();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

	}

	public void testInitializeGame() {
		final JavaOps j = new JavaOps();
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				new JFXPanel();
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						new Main().start(new Stage());
						j.initializeGame("HOST");
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

		thread = new Thread(new Runnable() {

			@Override
			public void run() {
				new JFXPanel();
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						new Main().start(new Stage());
						j.initializeGame("CLIENT");
					}
				});
			}
		});
		thread.start();
		try {
			Thread.sleep(1000);
			j.exit();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

	}

	public void testSendMove() {
		fail("Not yet implemented");
	}

	public void testWaitForOpponent() {
		fail("Not yet implemented");
	}

	public void testStartHost() {
		final JavaOps j = new JavaOps();
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				new JFXPanel();
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						new Main().start(new Stage());
						j.startHost();
					}
				});
			}
		});
		thread.start();
		try {
			Thread.sleep(2000);
			j.exit();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void testStartClient() {
		
		final JavaOps j = new JavaOps();
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				new JFXPanel();
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						new Main().start(new Stage());
						j.startClient("http://127.0.0.1");
					}
				});
			}
		});
		thread.start();
		try {
			Thread.sleep(2000);
			j.exit();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		thread = new Thread(new Runnable() {

			@Override
			public void run() {
				new JFXPanel();
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						new Main().start(new Stage());
						j.startClient("127.0.0.1");
					}
				});
			}
		});
		thread.start();
		try {
			Thread.sleep(1000);
			j.exit();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		
	}

	public void testGetIPAddress() {
		JavaOps j = new JavaOps();
		String ip = j.getIPAddress();
		assertTrue(ip != null);
		String IPADDRESS_PATTERN = 
		        "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";

		Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);
		Matcher matcher = pattern.matcher(ip);
		        if (matcher.find()) {
		            
		        }
		        else{
		            fail("IP does not match regex");
		        }
		
	}

	public void testGetPieces() {
		JavaOps j = new JavaOps();
		assertTrue(null != j.getPieces());
	}

}
