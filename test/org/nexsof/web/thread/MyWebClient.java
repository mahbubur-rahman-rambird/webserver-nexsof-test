/*
 * MyWebClient.java
 *
 * <Purpose of the class>
 *
 * @author Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 * @version 1.0 
 */
package org.nexsof.web.thread;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.nexsof.web.settings.TestSetup.TEST_SERVER_URL;

import java.net.URL;
import java.util.List;

import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.NameValuePair;

/**
 * MyWebClient.java
 *
 * <Purpose of the class>
 *
 * @author Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 * @version 1.0 
 */
public class MyWebClient  implements Runnable{
	@Override
	public void run() {
		final int OK=200;
		final WebClient webClient = new WebClient();
		try{
			final WebRequest webRequest = new WebRequest(new URL(TEST_SERVER_URL + "index.html"), HttpMethod.GET);
	
			final HtmlPage currentPage = webClient.getPage(webRequest);
	
		    List<NameValuePair> response =currentPage.getWebResponse().getResponseHeaders();
		    for (NameValuePair header : response) {
		         System.out.println(header.getName() + " = " + header.getValue());
		    }
		    assertEquals(OK, currentPage.getWebResponse().getStatusCode());
		    
		    System.out.println(currentPage.asText());
		}catch (Exception e) {
			assertFalse(true);
		}finally{
			webClient.closeAllWindows();
		}
	}
}
