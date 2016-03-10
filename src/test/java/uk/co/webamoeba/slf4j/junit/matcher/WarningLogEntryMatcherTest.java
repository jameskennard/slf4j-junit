package uk.co.webamoeba.slf4j.junit.matcher;

import org.slf4j.Logger;
import org.slf4j.Marker;

/**
 * @author James Kennard
 */
public class WarningLogEntryMatcherTest extends LegacyLogEntryMatcherTest {

	@Override
	protected LegacyLogEntryMatcher matcher() {
		return new WarningLogEntryMatcher("Message");
	}

	@Override
	protected LegacyLogEntryMatcher matcher(String message) {
		return new WarningLogEntryMatcher(message);
	}

	@Override
	protected LegacyLogEntryMatcher matcher(String format, String argument) {
		return new WarningLogEntryMatcher(format, argument);
	}

	@Override
	protected LegacyLogEntryMatcher matcher(Marker marker, String message) {
		return new WarningLogEntryMatcher(marker, message);
	}

	@Override
	protected LegacyLogEntryMatcher matcher(String message, Throwable throwable) {
		return new WarningLogEntryMatcher(message, throwable);
	}

	@Override
	protected LegacyLogEntryMatcher matcher(Marker marker, String message, Throwable throwable) {
		return new WarningLogEntryMatcher(marker, message, throwable);
	}

	@Override
	protected LegacyLogEntryMatcher matcher(Marker marker, String format, String argument) {
		return new WarningLogEntryMatcher(marker, format, argument);
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
	protected LegacyLogEntryMatcher log(String format, String argument) {
		return new WarningLogEntryMatcher(format, argument);
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
