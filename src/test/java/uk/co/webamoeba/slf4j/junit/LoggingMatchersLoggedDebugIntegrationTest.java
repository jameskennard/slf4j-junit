package uk.co.webamoeba.slf4j.junit;

import org.hamcrest.Matcher;
import org.slf4j.Logger;
import org.slf4j.Marker;
import uk.co.webamoeba.slf4j.junit.log.Level;

/**
 * @author James Kennard
 */
public class LoggingMatchersLoggedDebugIntegrationTest extends LoggingMatchersLoggedLevelIntegrationTest {

	public LoggingMatchersLoggedDebugIntegrationTest() {
		super(Level.DEBUG);
	}

	@Override
	protected Matcher<Logger> loggedLevel(String message) {
		return LoggingMatchers.loggedDebug(message);
	}

	@Override
	protected void logAtLevel(String message, Logger logger) {
		logger.debug(message);
	}

	@Override
	protected void logAtDifferentLevel(String message, Logger logger) {
		logger.error(message);
	}

	@Override
	protected Matcher<Logger> loggedLevel(String format, Object... arguments) {
		return LoggingMatchers.loggedDebug(format, arguments);
	}

	@Override
	protected void logAtLevel(String format, Object argument, Logger logger) {
		logger.debug(format, argument);
	}

	@Override
	protected void logAtDifferentLevel(String format, Object argument, Logger logger) {
		logger.error(format, argument);
	}

	@Override
	protected Matcher<Logger> loggedLevel(String message, Throwable throwable) {
		return LoggingMatchers.loggedDebug(message, throwable);
	}

	@Override
	protected void logAtLevel(String message, Throwable throwable, Logger logger) {
		logger.debug(message, throwable);
	}

	@Override
	protected void logAtDifferentLevel(String message, Throwable throwable, Logger logger) {
		logger.error(message, throwable);
	}

	@Override
	protected Matcher<Logger> loggedLevel(Marker marker, String message) {
		return LoggingMatchers.loggedDebug(marker, message);
	}

	@Override
	protected void logAtLevel(Marker marker, String message, Logger logger) {
		logger.debug(marker, message);
	}

	@Override
	protected void logAtDifferentLevel(String message, Marker marker, Logger logger) {
		logger.error(marker, message);
	}

	@Override
	protected Matcher<Logger> loggedLevel(Marker marker, String message, Throwable throwable) {
		return LoggingMatchers.loggedDebug(marker, message, throwable);
	}

	@Override
	protected void logAtLevel(Marker marker, String message, Throwable throwable, Logger logger) {
		logger.debug(marker, message, throwable);
	}

	protected void logAtDifferentLevel(Marker marker, String message, Throwable throwable, Logger logger) {
		logger.error(marker, message, throwable);
	}

	@Override
	protected Matcher<Logger> loggedLevel(Marker marker, String format, Object... arguments) {
		return LoggingMatchers.loggedDebug(marker, format, arguments);
	}

	@Override
	protected void logAtLevel(Marker marker, String format, Object argument, Logger logger) {
		logger.debug(marker, format, argument);
	}

	@Override
	protected void logAtDifferentLevel(Marker marker, String format, Object argument, Logger logger) {
		logger.error(marker, format, argument);
	}

}
