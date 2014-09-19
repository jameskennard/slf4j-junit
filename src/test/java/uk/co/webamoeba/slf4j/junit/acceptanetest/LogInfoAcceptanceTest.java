package uk.co.webamoeba.slf4j.junit.acceptanetest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static uk.co.webamoeba.slf4j.junit.LoggingMatchers.loggedInfo;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author James Kennard
 */
public class LogInfoAcceptanceTest {

	@Test
	@Ignore("Not ready for acceptance")
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
	
}
