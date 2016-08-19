package com.cs451.checkers;

import java.net.URISyntaxException;
import java.util.function.Function;

import com.cs451.checkers.GameManager.Color;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import junit.framework.TestCase;

public class BrowserTest extends TestCase {
	public void testLayoutChildren() {
		// fail("Not yet implemented");
	}

	public void testBrowser() {
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
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void testExecuteJavascript() throws URISyntaxException {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				new JFXPanel();
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						new Main().start(new Stage());
						assertTrue((Integer) Main.browser.executeJavascript("1+1") == 2);
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

	public void testAddListenerAfterLoad() {
		// fail("Not yet implemented");
	}
/*
	public void testHostContinue() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				new JFXPanel();
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						new Main().start(new Stage());
						int port = 5500;
						NormalNetworkManager.getInstance().host(port);
						Main.browser.hostContinue();
						//check state of page?
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
*/
	public void testSetInitialization() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				new JFXPanel();
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						new Main().start(new Stage());
						assertTrue(Main.browser.setInitialization(Color.RED) == 0);
						assertTrue(Main.browser.setInitialization(Color.BLACK) == 0);
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

	public void testRunTask() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				new JFXPanel();
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						new Main().start(new Stage());
						Function<Object, Integer> hc = new Function<Object, Integer>() {
							@Override
							public Integer apply(Object t) {
								// TODO Auto-generated method stub
								return (Integer) Main.browser.webEngine.executeScript("startGame('" + "BLACK" + "')");
							}
						};
						Main.browser.runTask(hc, null);
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

}
