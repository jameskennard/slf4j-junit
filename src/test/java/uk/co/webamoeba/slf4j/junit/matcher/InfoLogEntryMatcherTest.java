package uk.co.webamoeba.slf4j.junit.matcher;

import org.slf4j.Logger;
import org.slf4j.Marker;

/**
 * Test for {@link InfoLogEntryMatcher}
 * 
 * @author James Kennard
 */
public class InfoLogEntryMatcherTest extends LegacyLogEntryMatcherTest {

	@Override
	protected InfoLogEntryMatcher matcher() {
		return new InfoLogEntryMatcher("Message");
	}

	@Override
	protected InfoLogEntryMatcher matcher(String message) {
		return new InfoLogEntryMatcher(message);
	}

	@Override
	protected LegacyLogEntryMatcher matcher(String format, String argument) {
		return new InfoLogEntryMatcher(format, argument);
	}

	@Override
	protected InfoLogEntryMatcher matcher(Marker marker, String message) {
		return new InfoLogEntryMatcher(marker, message);
	}

	@Override
	protected InfoLogEntryMatcher matcher(String message, Throwable throwable) {
		return new InfoLogEntryMatcher(message, throwable);
	}

	@Override
	protected InfoLogEntryMatcher matcher(Marker marker, String message, Throwable throwable) {
		return new InfoLogEntryMatcher(marker, message, throwable);
	}

	@Override
	protected InfoLogEntryMatcher matcher(Marker marker, String format, String argument) {
		return new InfoLogEntryMatcher(marker, format, argument);
	}

	@Override
	protected void log(String message, Logger logger) {
		logger.info(message);
	}

	@Override
	protected void log(String format, String argument, Logger logger) {
		logger.info(format, argument);
	}

	@Override
	protected InfoLogEntryMatcher log(String format, String argument) {
		return new InfoLogEntryMatcher(format, argument);
	}

	@Override
	protected void log(Marker marker, String message, Logger logger) {
		logger.info(marker, message);
	}

	@Override
	protected void log(String message, Throwable throwable, Logger logger) {
		logger.info(message, throwable);
	}

	@Override
	protected void log(Marker marker, String message, Throwable throwable, Logger logger) {
		logger.info(marker, message, throwable);
	}

	@Override
	protected void log(Marker marker, String format, String argument, Logger logger) {
		logger.info(marker, format, argument);
	}

	@Override
	protected String expectedDescribeLevel() {
		return "info";
	}

	@Override
	protected String expectedDescribeFunction() {
		return "loggedInfo";
	}

	@Override
	protected void logDifferentLevel(String message, Logger logger) {
		logger.warn(message);
	}

}
