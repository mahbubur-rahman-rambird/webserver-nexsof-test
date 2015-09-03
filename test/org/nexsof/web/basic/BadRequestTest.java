/*
 * BadRequestTest.java
 *
 * <Purpose of the class>
 *
 * @author Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 * @version 1.0 
 */
package org.nexsof.web.basic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static org.nexsof.web.settings.TestSetup.*;

import junit.framework.TestCase;

/**
 * BadRequestTest.java
 *
 * <Purpose of the class>
 *
 * @author Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 * @version 1.0 
 */
public class BadRequestTest extends TestCase{
	
	private final String BAD_REQUEST_CPDE = "400";
	
	private final String[] BAD_REQUESTS = {
								// URI does not Start with "/"
								"GET index.html HTTP/1.0",
								
								// Lower case protocol name
								"GET /index.html http/1.0",
								
								//Does not have three parts of request line
								"GET HTTP/1.0",
								
								// wrong protocol name instead of HTTP
								"GET /index.html PTTP/1.0",

								// OPTIONS but url is not *
								"OPTIONS /index.html PTTP/1.0"

	};
	
	public void testBadRequest() throws Exception{
		for(String reqLine:BAD_REQUESTS){
			System.out.println("Request:"+ reqLine);
			String status = sendBadRequest(reqLine);
			assertNotNull(status);
			assertEquals(BAD_REQUEST_CPDE, status);
		}
	}
	public void testWrongHttpVersion() throws Exception{
			String status = sendBadRequest("GET /index.html HTTP/1.9");
			assertNotNull(status);
			assertEquals("505", status);
	}

	public void testLowerCaseMethod() throws Exception{
		String status = sendBadRequest("get index.html HTTP/1.1");
		assertNotNull(status);
		assertEquals("405", status);
	}

	public void testOptionsMethod() throws Exception{
		String status = sendBadRequest("OPTIONS * HTTP/1.1");
		assertNotNull(status);
		assertEquals("200", status);
	}
	
	public String sendBadRequest(String requestLine) throws Exception{
		 // The host and port to be connected.
	      // Create a TCP socket and connect to the host:port.
	      Socket socket = new Socket(TEST_HOST, TEST_PORT);
	      // Create the input and output streams for the network socket.
	      BufferedReader in
	         = new BufferedReader(
	              new InputStreamReader(socket.getInputStream()));
	      PrintWriter out
	         = new PrintWriter(socket.getOutputStream(), true);
	      // Send request to the HTTP server.
	      out.println(requestLine);
	      out.println("Connection: close");
	      out.println();   // blank line separating header & body
	      out.flush();
	      // Read the response and display on console.
	      String line = null;
	      String statusLine = null ;
	      // readLine() returns null if server close the network socket.
	      int lineCount = 0;
	      while((line = in.readLine()) != null) {
	         lineCount++;
	    	 System.out.println(line);
	    	 if (lineCount ==1){
	    		 statusLine = line;
	    	 }
	      }
	      // Close the I/O streams.
	      in.close();
	      out.close();
	      socket.close();
	      if (statusLine != null){
		      final int SP = ' ';
		      int spPos = statusLine.indexOf(SP);
		      
		      // parse the status 400 
		      // HTTP/1.1 400 Bad Request
		      return  statusLine.substring(spPos+1, statusLine.indexOf(SP, spPos + 1));
	      }else{
	    	  return null;
	      }
	}

}
