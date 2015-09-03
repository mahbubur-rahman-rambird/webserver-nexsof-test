Nexsof v1.0 web server testing module
=====================================
Author: Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)


This is an independent integration testing project based on Junit/HTML Unit to test web server for the following key features:
* Basic protocol handling for GET, POST, HEAD and OPTIONS
* Bad request testing using a HTML client that allows to send bad request to a web server
* HTTP protocol error handling for forbidden, method not implemented, HTTP not supported
* Load testing to run 1000 concurrent request to the web server

How to run?
===========
* The eclipse export test application (attached: webserver-nexsof-test.zip) needs to be imported to eclipse
* Edit the org.nexsof.web.settings.TestSetup for web server running host and port number
* Copy the nexsof-www directory to the webserver running server and use that as a document root
* Revoke permission from forbidden.html and forbiddendir
* Run the org.nexsof.web.suite.CompleteTestSuite as JUnit test; One test may fail for if-modified-since as coding has hardcoded timestamp
* Please note minimal care was give to the test coding, but it covers most of the integration and load testing
* JDK is also required for this project  
