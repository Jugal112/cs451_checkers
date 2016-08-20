package com.cs451.checkers;

import java.net.MalformedURLException;
import java.net.URL;

import junit.framework.TestCase;

public class NormalNetworkManagerTest extends TestCase {
	NormalNetworkManager nm;

	public void testGetInstance() {
		nm = NormalNetworkManager.getInstance();
		assertTrue(nm != null);
	}

	public void testHostAndConnect() throws InterruptedException {
		nm = NormalNetworkManager.getInstance();
		Thread t = new Thread() {
			public void run() {
				nm.host(8080);
			}
		};
		Thread t2 = new Thread(){
			public void run(){
				try {
					assertTrue(nm.connect(new URL("http://127.0.0.1:8080")) == 0);
				} catch (MalformedURLException e) {
					fail("Malformed exception");
					e.printStackTrace();
				}
			}
		};
		t.start();
		t2.start();
		Thread.sleep(1000);
	}

	public void testSendMessage() throws InterruptedException {
		nm = NormalNetworkManager.getInstance();
		Thread t = new Thread() {
			public void run() {
				nm.host(8080);
			}
		};
		Thread t2 = new Thread(){
			public void run(){
				assertTrue(nm.sendMessage(new TestNetworkMessage("string")) == 0);
			}
		};
		t.start();
		t2.start();
		Thread.sleep(1000);
	}

	public void testReceiveMessage() {
		nm = NormalNetworkManager.getInstance();
		// fail("Not yet implemented");
	}

	public void testIsConnected() throws InterruptedException {
		nm = NormalNetworkManager.getInstance();
		Thread t = new Thread() {
			public void run() {
				nm.host(8080);
			}
		};
		Thread t2 = new Thread() {
			public void run() {
				try {
					assertTrue(nm.connect(new URL("http://127.0.0.1:8080")) == 0);
					assertTrue(nm.isConnected());
				} catch (MalformedURLException e) {
					fail("Malformed exception");
					e.printStackTrace();
				}
			}
		};
		t.start();
		t2.start();
		Thread.sleep(1000);
	}

	public void testClose() throws InterruptedException {
		nm = NormalNetworkManager.getInstance();
		Thread t = new Thread() {
			public void run() {
				nm.host(8080);
			}
		};
		Thread t2 = new Thread() {
			public void run() {
				try {
					assertTrue(nm.connect(new URL("http://127.0.0.1:8080")) == 0);
					assertTrue(nm.isConnected());
					assertTrue(nm.close() == 0);
				} catch (MalformedURLException e) {
					fail("Malformed exception");
					e.printStackTrace();
				}
			}
		};
		t.start();
		t2.start();
		Thread.sleep(1000);
	}

}
