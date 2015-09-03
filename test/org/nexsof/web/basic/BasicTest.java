/*
 * Test.java
 *
 * <Purpose of the class>
 *
 * @author Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 * @version 1.0 
 */
package org.nexsof.web.basic;


import static org.nexsof.web.settings.TestSetup.TEST_SERVER_URL;
import static org.nexsof.web.settings.TestSetup.TEST_HOST;
import static org.nexsof.web.settings.TestSetup.TEST_PORT;

import java.net.URL;
import java.util.List;

import junit.framework.TestCase;

import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.NameValuePair;

/**
 * It does the basic protocol handling test
 *
 * @author Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 * @version 1.0 
 */
public class BasicTest extends TestCase{

	public void testGetOKStat() throws Exception{
		testOKStat(HttpMethod.GET);
	}
	public void testPostOKStat() throws Exception{
		testOKStat(HttpMethod.POST);
	}

	public void testGetDirOKStat() throws Exception{
		testOKStat(HttpMethod.GET, "images");
	}
	
	public void testOKStat(HttpMethod method) throws Exception{
		testOKStat(method, "index.html");
	}

	public void testOKStat(HttpMethod method, String target) throws Exception{
		
		final int OK=200;
		final WebClient webClient = new WebClient();


		final WebRequest webRequest = new WebRequest(new URL(TEST_SERVER_URL + target), method);

		final HtmlPage currentPage = webClient.getPage(webRequest);

	    List<NameValuePair> response =currentPage.getWebResponse().getResponseHeaders();
	    for (NameValuePair header : response) {
	         System.out.println(header.getName() + " = " + header.getValue());
	    }
	    assertEquals(OK, currentPage.getWebResponse().getStatusCode());
	    assertNotSame("", webClient.getPage(webRequest).getWebResponse().getContentAsString());	    
	    
	    System.out.println(currentPage.asText());
	    webClient.closeAllWindows();
	}
	
	public void testHeadMethod() throws Exception{
		
		final int OK=200;

		final WebClient webClient = new WebClient();
		
		final WebRequest webRequest = new WebRequest(new URL(TEST_SERVER_URL + "index.html"), HttpMethod.HEAD);

		final HtmlPage currentPage = webClient.getPage(webRequest);

	    List<NameValuePair> response =currentPage.getWebResponse().getResponseHeaders();
	    for (NameValuePair header : response) {
	         System.out.println(header.getName() + " = " + header.getValue());
	    }
	    assertEquals(OK, webClient.getPage(webRequest).getWebResponse().getStatusCode());
	    assertEquals("", webClient.getPage(webRequest).getWebResponse().getContentAsString());
	    
	    
	    System.out.println(currentPage.asText());
	    webClient.closeAllWindows();
	}
	
	public void testIfModifiedSinceNo() throws Exception{
		
		final int NOT_MODIFIED=304;
		final WebClient webClient = new WebClient();
		
		final WebRequest webRequest = new WebRequest(new URL(TEST_SERVER_URL + "index.html"), HttpMethod.GET);

		webRequest.setAdditionalHeader("if-modified-since", "Sat, 25 May 2013 16:23:22 GMT");
		final HtmlPage currentPage = webClient.getPage(webRequest);

	    List<NameValuePair> response =currentPage.getWebResponse().getResponseHeaders();
	    for (NameValuePair header : response) {
	         System.out.println(header.getName() + " = " + header.getValue());
	    }
	    assertEquals(NOT_MODIFIED, webClient.getPage(webRequest).getWebResponse().getStatusCode());
	    assertEquals("", webClient.getPage(webRequest).getWebResponse().getContentAsString());
	    
	    
	    System.out.println(currentPage.asText());
	    webClient.closeAllWindows();
	}

	public void testIfModifiedSinceYes() throws Exception{
		
		final int OK=200;
		final WebClient webClient = new WebClient();

		final WebRequest webRequest = new WebRequest(new URL(TEST_SERVER_URL + "index.html"), HttpMethod.GET);

		webRequest.setAdditionalHeader("if-modified-since", "Sun, 18 May 2013 20:22:18 GMT");
		final HtmlPage currentPage = webClient.getPage(webRequest);

	    List<NameValuePair> response =currentPage.getWebResponse().getResponseHeaders();
	    for (NameValuePair header : response) {
	         System.out.println(header.getName() + " = " + header.getValue());
	    }
	    assertEquals(OK, webClient.getPage(webRequest).getWebResponse().getStatusCode());
	    assertNotSame("", webClient.getPage(webRequest).getWebResponse().getContentAsString());
	    
	    System.out.println(currentPage.asText());
	    webClient.closeAllWindows();
	}
	
}
