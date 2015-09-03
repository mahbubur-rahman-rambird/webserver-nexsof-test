/*
 * CompleteTestSuite.java
 *
 * <Purpose of the class>
 *
 * @author Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 * @version 1.0 
 */
package org.nexsof.web.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.nexsof.web.basic.BadRequestTest;
import org.nexsof.web.basic.BasicTest;
import org.nexsof.web.exceptions.ExceptionTest;
import org.nexsof.web.thread.LoadTest;

/**
 * CompleteTestSuite.java
 *
 * <Purpose of the class>
 *
 * @author Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 * @version 1.0 
 */
@RunWith(Suite.class)
@SuiteClasses({ BasicTest.class, ExceptionTest.class, BadRequestTest.class, LoadTest.class })
public class CompleteTestSuite {

}
