package uk.co.webamoeba.slf4j.junit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static uk.co.webamoeba.slf4j.junit.LoggingMatchers.loggedInfo;
import static uk.co.webamoeba.slf4j.junit.LoggingMatchers.logger;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.helpers.BasicMarkerFactory;
import uk.co.webamoeba.slf4j.junit.log.LogRegistry;

/**
 * @author James Kennard
 */
public class LoggingMatchersLoggedInfoIntegrationTest {

	@Before
	public void clearLogs() {
		LogRegistry.getSingleton().clearAll();
	}

	@Test
	public void shouldMatchGivenInfoLogged() {
		// Given
		Logger logger = logger(LoggingMatchersLoggedInfoIntegrationTest.class);
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
		Logger logger = logger(LoggingMatchersLoggedInfoIntegrationTest.class);
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
		Logger logger = logger(LoggingMatchersLoggedInfoIntegrationTest.class);
		String message = "Some Message";
		Throwable throwable = new Throwable();
		logger.info(message, throwable);

		// When
		boolean matches = loggedInfo(message, throwable).matches(logger);

		// Then
		assertThat(matches, is(true));
	}

	@Test
	public void shouldMatchGivenInfoLoggedWithMarker() {
		// Given
		Logger logger = logger(LoggingMatchersLoggedInfoIntegrationTest.class);
		String message = "Some Message";
		Marker marker = new BasicMarkerFactory().getMarker("Some Marker");
		logger.info(marker, message);

		// When
		boolean matches = loggedInfo(marker, message).matches(logger);

		// Then
		assertThat(matches, is(true));
	}

	@Test
	public void shouldMatchGivenInfoLoggedWithMarkerAndThrowable() {
		// Given
		Logger logger = logger(LoggingMatchersLoggedInfoIntegrationTest.class);
		String message = "Some Message";
		Marker marker = new BasicMarkerFactory().getMarker("Some Marker");
		Throwable throwable = new Throwable();
		logger.info(marker, message, throwable);

		// When
		boolean matches = loggedInfo(marker, message, throwable).matches(logger);

		// Then
		assertThat(matches, is(true));
	}

	@Test
	public void shouldMatchGivenInfoLoggedWithMarkerAndFormat() {
		// Given
		Marker marker = new BasicMarkerFactory().getMarker("Some Marker");
		Logger logger = logger(LoggingMatchersLoggedInfoIntegrationTest.class);
		String format = "Format {}";
		Object argument = "Argument";
		logger.info(marker, format, argument);

		// When
		boolean matches = loggedInfo(marker, format, argument).matches(logger);

		// Then
		assertThat(matches, is(true));
	}

	@Test
	public void shouldNotMatchGivenInfoLoggedWithDifferentMessage() {
		// Given
		Logger logger = logger(LoggingMatchersLoggedInfoIntegrationTest.class);
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
		Logger logger = logger(LoggingMatchersLoggedInfoIntegrationTest.class);
		String message = "Some Message";

		// When
		boolean matches = loggedInfo(message).matches(logger);

		// Then
		assertThat(matches, is(false));
	}

	@Test
	public void shouldNotMatchGivenLoggedAtDifferentLevel() {
		// Given
		Logger logger = logger(LoggingMatchersLoggedInfoIntegrationTest.class);
		String message = "Some Message";
		logger.warn(message);

		// When
		boolean matches = loggedInfo(message).matches(logger);

		// Then
		assertThat(matches, is(false));
	}

}
