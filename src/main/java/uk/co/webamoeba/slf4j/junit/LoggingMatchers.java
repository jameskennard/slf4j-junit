package uk.co.webamoeba.slf4j.junit;

import org.hamcrest.Matcher;
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

	private static final LogEntryLevelMatcher ERROR_MATCHER = new LogEntryLevelMatcher(Level.ERROR);
	
	private static final LogEntryLevelMatcher WARN_MATCHER = new LogEntryLevelMatcher(Level.WARN);
	
	private static final LogEntryLevelMatcher INFO_MATCHER = new LogEntryLevelMatcher(Level.INFO);
	
	private static final LogEntryLevelMatcher DEBUG_MATCHER = new LogEntryLevelMatcher(Level.DEBUG);
	
	private static final LogEntryLevelMatcher TRACE_MATCHER = new LogEntryLevelMatcher(Level.TRACE);

	public static EnableLogging enableLogging() { 
		return EnableLogging.enableLogging();
	}
	
	public static Logger logger(Class<?> clazz) {
		return LoggerFactory.getLogger(clazz);
	}

	public static Logger logger(String loggerName) {
		return LoggerFactory.getLogger(loggerName);
	}

	public static Matcher<Logger> loggedError(String message) {
		return logged(ERROR_MATCHER, message);
	}

	public static Matcher<Logger> loggedError(String format, Object... arguments) {
		return logged(ERROR_MATCHER, format, arguments);
	}

	public static Matcher<Logger> loggedError(String message, Throwable throwable) {
		return logged(ERROR_MATCHER, message, throwable);
	}

	public static Matcher<Logger> loggedError(Marker marker, String message) {
		return logged(ERROR_MATCHER, marker, message);
	}

	public static Matcher<Logger> loggedError(Marker marker, String format, Object... arguments) {
		return logged(ERROR_MATCHER, marker, format, arguments);
	}

	public static Matcher<Logger> loggedError(Marker marker, String message, Throwable throwable) {
		return logged(ERROR_MATCHER, marker, message, throwable);
	}
	
	public static Matcher<Logger> loggedWarn(String message) {
		return logged(WARN_MATCHER, message);
	}

	public static Matcher<Logger> loggedWarn(String format, Object... arguments) {
		return logged(WARN_MATCHER, format, arguments);
	}

	public static Matcher<Logger> loggedWarn(String message, Throwable throwable) {
		return logged(WARN_MATCHER, message, throwable);
	}

	public static Matcher<Logger> loggedWarn(Marker marker, String message) {
		return logged(WARN_MATCHER, marker, message);
	}

	public static Matcher<Logger> loggedWarn(Marker marker, String format, Object... arguments) {
		return logged(WARN_MATCHER, marker, format, arguments);
	}

	public static Matcher<Logger> loggedWarn(Marker marker, String message, Throwable throwable) {
		return logged(WARN_MATCHER, marker, message, throwable);
	}

	public static Matcher<Logger> loggedInfo(String message) {
		return logged(INFO_MATCHER, message);
	}

	public static Matcher<Logger> loggedInfo(String format, Object... arguments) {
		return logged(INFO_MATCHER, format, arguments);
	}

	public static Matcher<Logger> loggedInfo(String message, Throwable throwable) {
		return logged(INFO_MATCHER, message, throwable);
	}

	public static Matcher<Logger> loggedInfo(Marker marker, String message) {
		return logged(INFO_MATCHER, marker, message);
	}

	public static Matcher<Logger> loggedInfo(Marker marker, String format, Object... arguments) {
		return logged(INFO_MATCHER, marker, format, arguments);
	}

	public static Matcher<Logger> loggedInfo(Marker marker, String message, Throwable throwable) {
		return logged(INFO_MATCHER, marker, message, throwable);
	}

	public static Matcher<Logger> loggedDebug(String message) {
		return logged(DEBUG_MATCHER, message);
	}

	public static Matcher<Logger> loggedDebug(String format, Object... arguments) {
		return logged(DEBUG_MATCHER, format, arguments);
	}

	public static Matcher<Logger> loggedDebug(String message, Throwable throwable) {
		return logged(DEBUG_MATCHER, message, throwable);
	}

	public static Matcher<Logger> loggedDebug(Marker marker, String message) {
		return logged(DEBUG_MATCHER, marker, message);
	}

	public static Matcher<Logger> loggedDebug(Marker marker, String format, Object... arguments) {
		return logged(DEBUG_MATCHER, marker, format, arguments);
	}

	public static Matcher<Logger> loggedDebug(Marker marker, String message, Throwable throwable) {
		return logged(DEBUG_MATCHER, marker, message, throwable);
	}
	
	public static Matcher<Logger> loggedTrace(String message) {
		return logged(TRACE_MATCHER, message);
	}

	public static Matcher<Logger> loggedTrace(String format, Object... arguments) {
		return logged(TRACE_MATCHER, format, arguments);
	}

	public static Matcher<Logger> loggedTrace(String message, Throwable throwable) {
		return logged(TRACE_MATCHER, message, throwable);
	}

	public static Matcher<Logger> loggedTrace(Marker marker, String message) {
		return logged(TRACE_MATCHER, marker, message);
	}

	public static Matcher<Logger> loggedTrace(Marker marker, String format, Object... arguments) {
		return logged(TRACE_MATCHER, marker, format, arguments);
	}

	public static Matcher<Logger> loggedTrace(Marker marker, String message, Throwable throwable) {
		return logged(TRACE_MATCHER, marker, message, throwable);
	}
	
	private static Matcher<Logger> logged(LogEntryMatcher levelMatcher, String message) {
		LogEntryMatcher messageMatcher = new LogEntryMessageMatcher(new LogEntry.StringMessage(message));
		return new RecordingLoggerMatcher(new CompositeLogEntryMatcher(levelMatcher, messageMatcher));
	}

	private static Matcher<Logger> logged(LogEntryMatcher levelMatcher, String format, Object[] arguments) {
		LogEntryMatcher messageMatcher = new LogEntryMessageMatcher(new LogEntry.FormattedMessage(format, arguments));
		return new RecordingLoggerMatcher(new CompositeLogEntryMatcher(levelMatcher, messageMatcher));
	}

	private static Matcher<Logger> logged(LogEntryMatcher levelMatcher, String message, Throwable throwable) {
		LogEntryMatcher messageMatcher = new LogEntryMessageMatcher(new LogEntry.StringMessage(message));
		LogEntryMatcher throwableMatcher = new LogEntryThrowableMatcher(throwable);
		return new RecordingLoggerMatcher(new CompositeLogEntryMatcher(levelMatcher, messageMatcher, throwableMatcher));
	}

	private static Matcher<Logger> logged(LogEntryMatcher levelMatcher, Marker marker, String message) {
		LogEntryMatcher messageMatcher = new LogEntryMessageMatcher(new LogEntry.StringMessage(message));
		LogEntryMatcher markerMatcher = new LogEntryMarkerMatcher(marker);
		return new RecordingLoggerMatcher(new CompositeLogEntryMatcher(levelMatcher, messageMatcher, markerMatcher));
	}

	private static Matcher<Logger> logged(LogEntryMatcher levelMatcher, Marker marker, String format, Object[] arguments) {
		LogEntryMatcher messageMatcher = new LogEntryMessageMatcher(new LogEntry.FormattedMessage(format, arguments));
		LogEntryMatcher markerMatcher = new LogEntryMarkerMatcher(marker);
		return new RecordingLoggerMatcher(new CompositeLogEntryMatcher(levelMatcher, messageMatcher, markerMatcher));
	}

	private static Matcher<Logger> logged(LogEntryMatcher levelMatcher, Marker marker, String message, Throwable throwable) {
		LogEntryMatcher messageMatcher = new LogEntryMessageMatcher(new LogEntry.StringMessage(message));
		LogEntryMatcher throwableMatcher = new LogEntryThrowableMatcher(throwable);
		LogEntryMatcher markerMatcher = new LogEntryMarkerMatcher(marker);
		return new RecordingLoggerMatcher(new CompositeLogEntryMatcher(levelMatcher, messageMatcher, throwableMatcher, markerMatcher));
	}
}
