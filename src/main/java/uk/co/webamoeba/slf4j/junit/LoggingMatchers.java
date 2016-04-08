package uk.co.webamoeba.slf4j.junit;

import org.hamcrest.Matcher;
import org.junit.rules.TestRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import uk.co.webamoeba.slf4j.junit.log.Level;
import uk.co.webamoeba.slf4j.junit.log.LogEntry;
import uk.co.webamoeba.slf4j.junit.matcher.CompositeLogEntryMatcher;
import uk.co.webamoeba.slf4j.junit.matcher.LogEntryLevelMatcher;
import uk.co.webamoeba.slf4j.junit.matcher.LogEntryMarkerMatcher;
import uk.co.webamoeba.slf4j.junit.matcher.LogEntryMatcher;
import uk.co.webamoeba.slf4j.junit.matcher.LogEntryMessageMatcher;
import uk.co.webamoeba.slf4j.junit.matcher.LogEntryThrowableMatcher;
import uk.co.webamoeba.slf4j.junit.matcher.RecordingLoggerMatcher;

/**
 * @author James Kennard
 */
public class LoggingMatchers {

	private static final LogEntryLevelMatcher INFO_MATCHER = new LogEntryLevelMatcher(Level.INFO);

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
		LogEntryMatcher messageMatcher = new LogEntryMessageMatcher(new LogEntry.StringMessage(message));
		return new RecordingLoggerMatcher(new CompositeLogEntryMatcher(INFO_MATCHER, messageMatcher));
	}

	public static Matcher<Logger> loggedInfo(String format, Object... arguments) {
		LogEntryMatcher messageMatcher = new LogEntryMessageMatcher(new LogEntry.FormattedMessage(format, arguments));
		return new RecordingLoggerMatcher(new CompositeLogEntryMatcher(INFO_MATCHER, messageMatcher));
	}

	public static Matcher<Logger> loggedInfo(String message, Throwable throwable) {
		LogEntryMatcher messageMatcher = new LogEntryMessageMatcher(new LogEntry.StringMessage(message));
		LogEntryMatcher throwableMatcher = new LogEntryThrowableMatcher(throwable);
		return new RecordingLoggerMatcher(new CompositeLogEntryMatcher(INFO_MATCHER, messageMatcher, throwableMatcher));
	}

	public static Matcher<Logger> loggedInfo(Marker marker, String message) {
		LogEntryMatcher messageMatcher = new LogEntryMessageMatcher(new LogEntry.StringMessage(message));
		LogEntryMatcher markerMatcher = new LogEntryMarkerMatcher(marker);
		return new RecordingLoggerMatcher(new CompositeLogEntryMatcher(INFO_MATCHER, messageMatcher, markerMatcher));
	}

	public static Matcher<Logger> loggedInfo(Marker marker, String format, Object... arguments) {
		LogEntryMatcher messageMatcher = new LogEntryMessageMatcher(new LogEntry.FormattedMessage(format, arguments));
		LogEntryMatcher markerMatcher = new LogEntryMarkerMatcher(marker);
		return new RecordingLoggerMatcher(new CompositeLogEntryMatcher(INFO_MATCHER, messageMatcher, markerMatcher));
	}

	public static Matcher<Logger> loggedInfo(Marker marker, String message, Throwable throwable) {
		LogEntryMatcher messageMatcher = new LogEntryMessageMatcher(new LogEntry.StringMessage(message));
		LogEntryMatcher throwableMatcher = new LogEntryThrowableMatcher(throwable);
		LogEntryMatcher markerMatcher = new LogEntryMarkerMatcher(marker);
		return new RecordingLoggerMatcher(new CompositeLogEntryMatcher(INFO_MATCHER, messageMatcher, throwableMatcher, markerMatcher));
	}

}
