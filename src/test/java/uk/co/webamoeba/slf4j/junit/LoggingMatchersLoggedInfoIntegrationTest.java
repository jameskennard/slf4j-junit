package uk.co.webamoeba.slf4j.junit;

import org.hamcrest.Matcher;
import org.slf4j.Logger;
import org.slf4j.Marker;
import uk.co.webamoeba.slf4j.junit.log.Level;

/**
 * @author James Kennard
 */
public class LoggingMatchersLoggedInfoIntegrationTest extends LoggingMatchersLoggedLevelIntegrationTest {

	public LoggingMatchersLoggedInfoIntegrationTest() {
		super(Level.INFO);
	}

	@Override
	protected Matcher<Logger> loggedLevel(String message) {
		return LoggingMatchers.loggedInfo(message);
	}

	@Override
	protected void logAtLevel(String message, Logger logger) {
		logger.info(message);
	}

	@Override
	protected void logAtDifferentLevel(String message, Logger logger) {
		logger.error(message);
	}

	@Override
	protected Matcher<Logger> loggedLevel(String format, Object... arguments) {
		return LoggingMatchers.loggedInfo(format, arguments);
	}

	@Override
	protected void logAtLevel(String format, Object argument, Logger logger) {
		logger.info(format, argument);
	}

	@Override
	protected void logAtDifferentLevel(String format, Object argument, Logger logger) {
		logger.error(format, argument);
	}

	@Override
	protected Matcher<Logger> loggedLevel(String message, Throwable throwable) {
		return LoggingMatchers.loggedInfo(message, throwable);
	}

	@Override
	protected void logAtLevel(String message, Throwable throwable, Logger logger) {
		logger.info(message, throwable);
	}

	@Override
	protected void logAtDifferentLevel(String message, Throwable throwable, Logger logger) {
		logger.error(message, throwable);
	}

	@Override
	protected Matcher<Logger> loggedLevel(Marker marker, String message) {
		return LoggingMatchers.loggedInfo(marker, message);
	}

	@Override
	protected void logAtLevel(Marker marker, String message, Logger logger) {
		logger.info(marker, message);
	}

	@Override
	protected void logAtDifferentLevel(String message, Marker marker, Logger logger) {
		logger.error(marker, message);
	}

	@Override
	protected Matcher<Logger> loggedLevel(Marker marker, String message, Throwable throwable) {
		return LoggingMatchers.loggedInfo(marker, message, throwable);
	}

	@Override
	protected void logAtLevel(Marker marker, String message, Throwable throwable, Logger logger) {
		logger.info(marker, message, throwable);
	}

	protected void logAtDifferentLevel(Marker marker, String message, Throwable throwable, Logger logger) {
		logger.error(marker, message, throwable);
	}

	@Override
	protected Matcher<Logger> loggedLevel(Marker marker, String format, Object... arguments) {
		return LoggingMatchers.loggedInfo(marker, format, arguments);
	}

	@Override
	protected void logAtLevel(Marker marker, String format, Object argument, Logger logger) {
		logger.info(marker, format, argument);
	}

	@Override
	protected void logAtDifferentLevel(Marker marker, String format, Object argument, Logger logger) {
		logger.error(marker, format, argument);
	}

}
