package uk.co.webamoeba.slf4j.junit.acceptanetest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static uk.co.webamoeba.slf4j.junit.LoggingMatchers.loggedInfo;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.webamoeba.slf4j.junit.event.LogEventRegistry;

/**
 * @author James Kennard
 */
public class LogInfoAcceptanceTest {
	
	@Before
	public void clearLogs() {
		LogEventRegistry.getSingleton().clearAll();
	}

	@Test
	public void shouldMatchGivenInfoLogged() {
		// Given
		Logger logger = LoggerFactory.getLogger(LogInfoAcceptanceTest.class);
		String message = "Some Message";
		logger.info(message);
		
		// When
		boolean matches = loggedInfo(message).matches(logger);
		
		// Then
		assertThat(matches, is(true));
	}
	
	@Test
	public void shouldMatchGivenInfoLoggedWithFormat() {
		// Given
		Logger logger = LoggerFactory.getLogger(LogInfoAcceptanceTest.class);
		String format = "Format {}";
		Object argument = "Argument";
		logger.info(format, argument);
		
		// When
		boolean matches = loggedInfo(format, argument).matches(logger);
		
		// Then
		assertThat(matches, is(true));
	}
	
	@Test
	public void shouldMatchGivenInfoLoggedWithThrowable() {
		// Given
		Logger logger = LoggerFactory.getLogger(LogInfoAcceptanceTest.class);
		String message = "Some Message";
		Throwable throwable = new Throwable();
		logger.info(message, throwable);
		
		// When
		boolean matches = loggedInfo(message, throwable).matches(logger);
		
		// Then
		assertThat(matches, is(true));
	}
	
	@Test
	public void shouldNotMatchGivenInfoLoggedWithDifferentMessage() {
		// Given
		Logger logger = LoggerFactory.getLogger(LogInfoAcceptanceTest.class);
		String message = "Some Message";
		logger.info("Some Different Message");
		
		// When
		boolean matches = loggedInfo(message).matches(logger);
		
		// Then
		assertThat(matches, is(false));
	}
	
	@Test
	public void shouldNotMatchGivenNoInfoLogged() {
		// Given
		Logger logger = LoggerFactory.getLogger(LogInfoAcceptanceTest.class);
		String message = "Some Message";
		
		// When
		boolean matches = loggedInfo(message).matches(logger);
		
		// Then
		assertThat(matches, is(false));
	}
	
	@Test
	public void shouldNotMatchGivenLoggedAtDifferentLevel() {
		// Given
		Logger logger = LoggerFactory.getLogger(LogInfoAcceptanceTest.class);
		String message = "Some Message";
		logger.warn(message);
		
		// When
		boolean matches = loggedInfo(message).matches(logger);
		
		// Then
		assertThat(matches, is(false));
	}
	
}
