/*
 * Test.java
 *
 * <Purpose of the class>
 *
 * @author Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 * @version 1.0 
 */
package org.nexsof.web.thread;


import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

/**
 * It does the basic protocol handling test
 *
 * @author Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 * @version 1.0 
 */
public class LoadTest extends TestCase{

	public void testInThreads() throws Exception{
		final int NTHREADS = 1000;
		
		List<Thread> threads = new ArrayList<Thread>();
		for (int i = 0; i < NTHREADS ; i++){
			Runnable task = new MyWebClient();
			Thread worker = new Thread(task);
			worker.start();
			threads.add(worker);
		}
		int running = 0;
		do{
			running = 0;
			for (Thread thread: threads) {
				if (thread.isAlive()){
					running++;
				}
			}
			Thread.sleep(1000);
			System.out.println("We have "+ running + " running threads. ");
		}while (running > 0);
	}
}
