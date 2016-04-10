package uk.co.webamoeba.slf4j.junit;

import org.hamcrest.Matcher;
import org.slf4j.Logger;
import org.slf4j.Marker;
import uk.co.webamoeba.slf4j.junit.log.Level;

/**
 * @author James Kennard
 */
public class LoggingMatchersLoggedErrorIntegrationTest extends LoggingMatchersLoggedLevelIntegrationTest {

	public LoggingMatchersLoggedErrorIntegrationTest() {
		super(Level.ERROR);
	}

	@Override
	protected Matcher<Logger> loggedLevel(String message) {
		return LoggingMatchers.loggedError(message);
	}

	@Override
	protected void logAtLevel(String message, Logger logger) {
		logger.error(message);
	}

	@Override
	protected void logAtDifferentLevel(String message, Logger logger) {
		logger.warn(message);
	}

	@Override
	protected Matcher<Logger> loggedLevel(String format, Object... arguments) {
		return LoggingMatchers.loggedError(format, arguments);
	}

	@Override
	protected void logAtLevel(String format, Object argument, Logger logger) {
		logger.error(format, argument);
	}

	@Override
	protected void logAtDifferentLevel(String format, Object argument, Logger logger) {
		logger.warn(format, argument);
	}

	@Override
	protected Matcher<Logger> loggedLevel(String message, Throwable throwable) {
		return LoggingMatchers.loggedError(message, throwable);
	}

	@Override
	protected void logAtLevel(String message, Throwable throwable, Logger logger) {
		logger.error(message, throwable);
	}

	@Override
	protected void logAtDifferentLevel(String message, Throwable throwable, Logger logger) {
		logger.warn(message, throwable);
	}

	@Override
	protected Matcher<Logger> loggedLevel(Marker marker, String message) {
		return LoggingMatchers.loggedError(marker, message);
	}

	@Override
	protected void logAtLevel(Marker marker, String message, Logger logger) {
		logger.error(marker, message);
	}

	@Override
	protected void logAtDifferentLevel(String message, Marker marker, Logger logger) {
		logger.warn(marker, message);
	}

	@Override
	protected Matcher<Logger> loggedLevel(Marker marker, String message, Throwable throwable) {
		return LoggingMatchers.loggedError(marker, message, throwable);
	}

	@Override
	protected void logAtLevel(Marker marker, String message, Throwable throwable, Logger logger) {
		logger.error(marker, message, throwable);
	}

	protected void logAtDifferentLevel(Marker marker, String message, Throwable throwable, Logger logger) {
		logger.warn(marker, message, throwable);
	}

	@Override
	protected Matcher<Logger> loggedLevel(Marker marker, String format, Object... arguments) {
		return LoggingMatchers.loggedError(marker, format, arguments);
	}

	@Override
	protected void logAtLevel(Marker marker, String format, Object argument, Logger logger) {
		logger.error(marker, format, argument);
	}

	@Override
	protected void logAtDifferentLevel(Marker marker, String format, Object argument, Logger logger) {
		logger.warn(marker, format, argument);
	}

}
