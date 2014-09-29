package uk.co.webamoeba.slf4j.junit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static uk.co.webamoeba.slf4j.junit.LoggingMatchers.loggedError;
import static uk.co.webamoeba.slf4j.junit.LoggingMatchers.logger;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.helpers.BasicMarkerFactory;

import uk.co.webamoeba.slf4j.junit.event.LogEventRegistry;

/**
 * @author James Kennard
 */
public class LoggingMatchersLoggedErrorIntegrationTest {

	@Before
	public void clearLogs() {
		LogEventRegistry.getSingleton().clearAll();
	}

	@Test
	@Ignore("Not Ready")
	public void shouldMatchGivenErrorLogged() {
		// Given
		Logger logger = logger(LoggingMatchersLoggedErrorIntegrationTest.class);
		String message = "Some Message";
		logger.error(message);

		// When
		boolean matches = loggedError(message).matches(logger);

		// Then
		assertThat(matches, is(true));
	}

	@Test
	@Ignore("Not Ready")
	public void shouldMatchGivenErrorLoggedWithFormat() {
		// Given
		Logger logger = logger(LoggingMatchersLoggedErrorIntegrationTest.class);
		String format = "Format {}";
		Object argument = "Argument";
		logger.error(format, argument);

		// When
		boolean matches = loggedError(format, argument).matches(logger);

		// Then
		assertThat(matches, is(true));
	}

	@Test
	@Ignore("Not Ready")
	public void shouldMatchGivenErrorLoggedWithThrowable() {
		// Given
		Logger logger = logger(LoggingMatchersLoggedErrorIntegrationTest.class);
		String message = "Some Message";
		Throwable throwable = new Throwable();
		logger.error(message, throwable);

		// When
		boolean matches = loggedError(message, throwable).matches(logger);

		// Then
		assertThat(matches, is(true));
	}

	@Test
	@Ignore("Not Ready")
	public void shouldMatchGivenErrorLoggedWithMarker() {
		// Given
		Logger logger = logger(LoggingMatchersLoggedErrorIntegrationTest.class);
		String message = "Some Message";
		Marker marker = new BasicMarkerFactory().getMarker("Some Marker");
		logger.error(marker, message);

		// When
		boolean matches = loggedError(marker, message).matches(logger);

		// Then
		assertThat(matches, is(true));
	}

	@Test
	@Ignore("Not Ready")
	public void shouldMatchGivenErrorLoggedWithMarkerAndThrowable() {
		// Given
		Logger logger = logger(LoggingMatchersLoggedErrorIntegrationTest.class);
		String message = "Some Message";
		Marker marker = new BasicMarkerFactory().getMarker("Some Marker");
		Throwable throwable = new Throwable();
		logger.error(marker, message, throwable);

		// When
		boolean matches = loggedError(marker, message, throwable).matches(logger);

		// Then
		assertThat(matches, is(true));
	}

	@Test
	@Ignore("Not Ready")
	public void shouldMatchGivenErrorLoggedWithMarkerAndFormat() {
		// Given
		Marker marker = new BasicMarkerFactory().getMarker("Some Marker");
		Logger logger = logger(LoggingMatchersLoggedErrorIntegrationTest.class);
		String format = "Format {}";
		Object argument = "Argument";
		logger.error(marker, format, argument);

		// When
		boolean matches = loggedError(marker, format, argument).matches(logger);

		// Then
		assertThat(matches, is(true));
	}

	@Test
	@Ignore("Not Ready")
	public void shouldNotMatchGivenErrorLoggedWithDifferentMessage() {
		// Given
		Logger logger = logger(LoggingMatchersLoggedErrorIntegrationTest.class);
		String message = "Some Message";
		logger.error("Some Different Message");

		// When
		boolean matches = loggedError(message).matches(logger);

		// Then
		assertThat(matches, is(false));
	}

	@Test
	@Ignore("Not Ready")
	public void shouldNotMatchGivenNoErrorLogged() {
		// Given
		Logger logger = logger(LoggingMatchersLoggedErrorIntegrationTest.class);
		String message = "Some Message";

		// When
		boolean matches = loggedError(message).matches(logger);

		// Then
		assertThat(matches, is(false));
	}

	@Test
	@Ignore("Not Ready")
	public void shouldNotMatchGivenLoggedAtDifferentLevel() {
		// Given
		Logger logger = logger(LoggingMatchersLoggedErrorIntegrationTest.class);
		String message = "Some Message";
		logger.warn(message);

		// When
		boolean matches = loggedError(message).matches(logger);

		// Then
		assertThat(matches, is(false));
	}

}
