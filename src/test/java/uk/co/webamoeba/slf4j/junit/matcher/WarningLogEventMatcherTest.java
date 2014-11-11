package uk.co.webamoeba.slf4j.junit.matcher;

import org.slf4j.Logger;
import org.slf4j.Marker;

/**
 * @author James Kennard
 */
public class WarningLogEventMatcherTest extends LogEventMatcherTest {

	@Override
	protected LogEventMatcher matcher() {
		return new WarningLogEventMatcher("Message");
	}

	@Override
	protected LogEventMatcher matcher(String message) {
		return new WarningLogEventMatcher(message);
	}

	@Override
	protected LogEventMatcher matcher(String format, String argument) {
		return new WarningLogEventMatcher(format, argument);
	}

	@Override
	protected LogEventMatcher matcher(Marker marker, String message) {
		return new WarningLogEventMatcher(marker, message);
	}

	@Override
	protected LogEventMatcher matcher(String message, Throwable throwable) {
		return new WarningLogEventMatcher(message, throwable);
	}

	@Override
	protected LogEventMatcher matcher(Marker marker, String message, Throwable throwable) {
		return new WarningLogEventMatcher(marker, message, throwable);
	}

	@Override
	protected LogEventMatcher matcher(Marker marker, String format, String argument) {
		return new WarningLogEventMatcher(marker, format, argument);
	}

	@Override
	protected void log(String message, Logger logger) {
		logger.warn(message);
	}

	@Override
	protected void log(String format, String argument, Logger logger) {
		logger.warn(format, argument);
	}

	@Override
	protected void log(Marker marker, String message, Logger logger) {
		logger.warn(marker, message);
	}

	@Override
	protected LogEventMatcher log(String format, String argument) {
		return new WarningLogEventMatcher(format, argument);
	}

	@Override
	protected void log(String message, Throwable throwable, Logger logger) {
		logger.warn(message, throwable);
	}

	@Override
	protected void log(Marker marker, String message, Throwable throwable, Logger logger) {
		logger.warn(marker, message, throwable);
	}

	@Override
	protected void log(Marker marker, String format, String argument, Logger logger) {
		logger.warn(marker, format, argument);
	}

	@Override
	protected String expectedDescribeLevel() {
		return "warn";
	}

	@Override
	protected String expectedDescribeFunction() {
		return "loggedWarning";
	}

	@Override
	protected void logDifferentLevel(String message, Logger logger) {
		logger.info(message);
	}

}
