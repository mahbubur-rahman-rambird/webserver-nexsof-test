/*
 * ExceptionTest.java
 *
 * <Purpose of the class>
 *
 * @author Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 * @version 1.0 
 */
package org.nexsof.web.exceptions;

import static org.nexsof.web.settings.TestSetup.TEST_SERVER_URL;

import java.net.URL;

import junit.framework.TestCase;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;

/**
 * ExceptionTest.java
 *
 * <Purpose of the class>
 *
 * @author Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 * @version 1.0 
 */
public class ExceptionTest extends TestCase {


	/*
	 *  Trace method request should return "Not implemented" status 
	 */
	public void testTraceNotImplemented() throws Exception{ 
		final int NOT_IMPLEMENTED=405;
		final WebClient webClient = new WebClient();

		try {
			WebRequest webRequest = new WebRequest(new URL(TEST_SERVER_URL), HttpMethod.TRACE);
			assertEquals(NOT_IMPLEMENTED, webClient.getPage(webRequest).getWebResponse().getStatusCode());
		}catch (FailingHttpStatusCodeException e) {
			assertEquals(NOT_IMPLEMENTED, e.getStatusCode());
		}
	    webClient.closeAllWindows();
	}
	
	/*
	 *  It tries to access a page is not available, so it should return "not found" error
	 * 
	 */
	public void testNotFound() throws Exception{
		
		final int NOT_FOUND=404;

		final String PAGE_URI = "dfjghdfjghxvgfdsfdsfds.html";

		final WebRequest webRequest = new WebRequest(new URL(TEST_SERVER_URL + PAGE_URI), HttpMethod.GET);

	    testException(webRequest, NOT_FOUND);
	    
	}

	/*
	 *  Request URI too long
	 *  Maximum 1024 allowed
	 */
	public void testURITooLong() throws Exception{
		
		final int URI_TOO_LONG=414;
		String PAGE_URI = "asdcfg";
		
		for(int i = 5 ; i<1020;i++){
			PAGE_URI += "a";
		}
		PAGE_URI += ".html";

		final WebRequest webRequest = new WebRequest(new URL(TEST_SERVER_URL + PAGE_URI), HttpMethod.GET);

		testException(webRequest, URI_TOO_LONG);
	}
	/*
	 *  Forbidden file access
	 */
	public void testForbidden() throws Exception{
		
		final int FORBIDDEN=403;

		String PAGE_URI = "forbidden.html";
		
		final WebRequest webRequest = new WebRequest(new URL(TEST_SERVER_URL + PAGE_URI), HttpMethod.GET);

	    testException(webRequest, FORBIDDEN);
	    
	}
	/*
	 *  Forbidden directory access
	 */
	public void testForbiddenDir() throws Exception{
		
		final int FORBIDDEN=403;

		String PAGE_URI = "forbiddendir";
		
		final WebRequest webRequest = new WebRequest(new URL(TEST_SERVER_URL + PAGE_URI), HttpMethod.GET);

	    testException(webRequest, FORBIDDEN);
	    
	}

	private void testException(final WebRequest webRequest, final int code) throws Exception{
		final WebClient webClient = new WebClient();

		try{
			assertEquals(code, webClient.getPage(webRequest).getWebResponse().getStatusCode());
		}catch (FailingHttpStatusCodeException e) {
			assertEquals(code, e.getStatusCode());
		}
	    webClient.closeAllWindows();
		
	}

	
}
