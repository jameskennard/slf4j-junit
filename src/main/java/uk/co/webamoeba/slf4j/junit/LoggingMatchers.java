package uk.co.webamoeba.slf4j.junit;

import org.hamcrest.Matcher;
import org.junit.rules.TestRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

import uk.co.webamoeba.slf4j.junit.matcher.InfoLogEntryMatcher;

/**
 * @author James Kennard
 */
public class LoggingMatchers {

	public static Logger logger(Class<?> clazz) {
		return LoggerFactory.getLogger(clazz);
	}

	public static Logger logger(String loggerName) {
		return LoggerFactory.getLogger(loggerName);
	}

	public static TestRule clearLogs() {
		return new ClearLogsTestRule();
	}

	public static TestRule clearLogs(Class<?> loggerClass) {
		return new ClearLogsTestRule(loggerClass);
	}

	public static Matcher<Logger> loggedInfo(String message) {
		return new InfoLogEntryMatcher(message);
	}

	public static Matcher<Logger> loggedInfo(String format, Object... arguments) {
		return new InfoLogEntryMatcher(format, arguments);
	}

	public static Matcher<Logger> loggedInfo(String message, Throwable throwable) {
		return new InfoLogEntryMatcher(message, throwable);
	}

	public static Matcher<Logger> loggedInfo(Marker marker, String message) {
		return new InfoLogEntryMatcher(marker, message);
	}

	public static Matcher<Logger> loggedInfo(Marker marker, String format, Object... arguments) {
		return new InfoLogEntryMatcher(marker, format, arguments);
	}

	public static Matcher<Logger> loggedInfo(Marker marker, String message, Throwable throwable) {
		return new InfoLogEntryMatcher(marker, message, throwable);
	}

	public static Matcher<Logger> loggedError(String message) {
		return null;
	}

	public static Matcher<Logger> loggedError(String format, Object... arguments) {
		return null;
	}

	public static Matcher<Logger> loggedError(String message, Throwable throwable) {
		return null;
	}

	public static Matcher<Logger> loggedError(Marker marker, String message) {
		return null;
	}

	public static Matcher<Logger> loggedError(Marker marker, String format, Object... arguments) {
		return null;
	}

	public static Matcher<Logger> loggedError(Marker marker, String message, Throwable throwable) {
		return null;
	}

}
