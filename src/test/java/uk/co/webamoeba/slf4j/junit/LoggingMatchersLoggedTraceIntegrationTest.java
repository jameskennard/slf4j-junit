package uk.co.webamoeba.slf4j.junit;

import org.hamcrest.Matcher;
import org.slf4j.Logger;
import org.slf4j.Marker;
import uk.co.webamoeba.slf4j.junit.log.Level;

/**
 * @author James Kennard
 */
public class LoggingMatchersLoggedTraceIntegrationTest extends LoggingMatchersLoggedLevelIntegrationTest {

	public LoggingMatchersLoggedTraceIntegrationTest() {
		super(Level.TRACE);
	}

	@Override
	protected Matcher<Logger> loggedLevel(String message) {
		return LoggingMatchers.loggedTrace(message);
	}

	@Override
	protected void logAtLevel(String message, Logger logger) {
		logger.trace(message);
	}

	@Override
	protected void logAtDifferentLevel(String message, Logger logger) {
		logger.error(message);
	}

	@Override
	protected Matcher<Logger> loggedLevel(String format, Object... arguments) {
		return LoggingMatchers.loggedTrace(format, arguments);
	}

	@Override
	protected void logAtLevel(String format, Object argument, Logger logger) {
		logger.trace(format, argument);
	}

	@Override
	protected void logAtDifferentLevel(String format, Object argument, Logger logger) {
		logger.error(format, argument);
	}

	@Override
	protected Matcher<Logger> loggedLevel(String message, Throwable throwable) {
		return LoggingMatchers.loggedTrace(message, throwable);
	}

	@Override
	protected void logAtLevel(String message, Throwable throwable, Logger logger) {
		logger.trace(message, throwable);
	}

	@Override
	protected void logAtDifferentLevel(String message, Throwable throwable, Logger logger) {
		logger.error(message, throwable);
	}

	@Override
	protected Matcher<Logger> loggedLevel(Marker marker, String message) {
		return LoggingMatchers.loggedTrace(marker, message);
	}

	@Override
	protected void logAtLevel(Marker marker, String message, Logger logger) {
		logger.trace(marker, message);
	}

	@Override
	protected void logAtDifferentLevel(String message, Marker marker, Logger logger) {
		logger.error(marker, message);
	}

	@Override
	protected Matcher<Logger> loggedLevel(Marker marker, String message, Throwable throwable) {
		return LoggingMatchers.loggedTrace(marker, message, throwable);
	}

	@Override
	protected void logAtLevel(Marker marker, String message, Throwable throwable, Logger logger) {
		logger.trace(marker, message, throwable);
	}

	protected void logAtDifferentLevel(Marker marker, String message, Throwable throwable, Logger logger) {
		logger.error(marker, message, throwable);
	}

	@Override
	protected Matcher<Logger> loggedLevel(Marker marker, String format, Object... arguments) {
		return LoggingMatchers.loggedTrace(marker, format, arguments);
	}

	@Override
	protected void logAtLevel(Marker marker, String format, Object argument, Logger logger) {
		logger.trace(marker, format, argument);
	}

	@Override
	protected void logAtDifferentLevel(Marker marker, String format, Object argument, Logger logger) {
		logger.error(marker, format, argument);
	}

}
