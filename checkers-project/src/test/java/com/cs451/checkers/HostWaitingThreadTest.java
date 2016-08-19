package com.cs451.checkers;

import junit.framework.TestCase;

public class HostWaitingThreadTest extends TestCase {

	public void testRun() {
		HostWaitingThread thread = new HostWaitingThread(5500);
		thread.start();
		try{
		Thread.sleep(2000);
		} catch (InterruptedException e){
			
		}
		
	}

	public void testHostWaitingThread() {
		HostWaitingThread thread = new HostWaitingThread(5500);
		assertTrue(thread.port == 5500);
	}

}
