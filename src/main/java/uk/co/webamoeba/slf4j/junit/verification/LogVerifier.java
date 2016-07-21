package uk.co.webamoeba.slf4j.junit.verification;

import org.slf4j.Marker;
import uk.co.webamoeba.slf4j.junit.log.Level;
import uk.co.webamoeba.slf4j.junit.log.Log;
import uk.co.webamoeba.slf4j.junit.log.LogEntry;
import uk.co.webamoeba.slf4j.junit.logger.RecordingLogger;
import uk.co.webamoeba.slf4j.junit.specification.GroupLogEntrySpecificationFactory;
import uk.co.webamoeba.slf4j.junit.specification.LogEntrySpecification;

/**
 * Verifier which will verify a {@link Log} (from a {@link RecordingLogger}) contains log entries. And which provides on going/chained method calls.
 * 
 * @author James Kennard
 */
public class LogVerifier {

	private final GroupLogEntrySpecificationFactory factory;

	private final RecordingLogger logger;

	private final RecordingLoggerSpecificationVerifier recordingLoggerSpecificationVerifier;

	public LogVerifier(GroupLogEntrySpecificationFactory factory, RecordingLogger logger, RecordingLoggerSpecificationVerifier recordingLoggerSpecificationVerifier) {
		this.factory = factory;
		this.logger = logger;
		this.recordingLoggerSpecificationVerifier = recordingLoggerSpecificationVerifier;
	}

	/**
	 * @param level {@link Level} of the log entry we want
	 * @param message message of the log entry we want
	 */
	public void logged(Level level, String message) {
		LogEntrySpecification specification = factory.createGroupLogEntrySpecification(level, new LogEntry.StringMessage(message), null, null);
		verifyRecordingLoggerSatisfiesSpecification(specification);
	}

	/**
	 * @param level {@link Level} of the log entry we want
	 * @param message message of the log entry we want
	 * @param arguments arguments in the message of the log entry we want
	 */
	public void logged(Level level, String message, Object... arguments) {
		LogEntrySpecification specification = factory.createGroupLogEntrySpecification(level, new LogEntry.FormattedMessage(message, arguments), null, null);
		verifyRecordingLoggerSatisfiesSpecification(specification);
	}

	/**
	 * @param level {@link Level} of the log entry we want
	 * @param message message of the log entry we want
	 * @param throwable {@link Throwable} of the log entry we want
	 */
	public void logged(Level level, String message, Throwable throwable) {
		LogEntrySpecification specification = factory.createGroupLogEntrySpecification(level, new LogEntry.StringMessage(message), throwable, null);
		verifyRecordingLoggerSatisfiesSpecification(specification);
	}

	/**
	 * @param level {@link Level} of the log entry we want
	 * @param marker {@link Marker} of the log entry we want
	 * @param message message of the log entry we want
	 */
	public void logged(Level level, Marker marker, String message) {
		LogEntrySpecification specification = factory.createGroupLogEntrySpecification(level, new LogEntry.StringMessage(message), null, marker);
		verifyRecordingLoggerSatisfiesSpecification(specification);
	}

	/**
	 * @param level {@link Level} of the log entry we want
	 * @param marker {@link Marker} of the log entry we want
	 * @param message message of the log entry we want
	 * @param arguments arguments in the message of the log entry we want
	 */
	public void logged(Level level, Marker marker, String message, Object... arguments) {
		LogEntrySpecification specification = factory.createGroupLogEntrySpecification(level, new LogEntry.FormattedMessage(message, arguments), null, marker);
		verifyRecordingLoggerSatisfiesSpecification(specification);
	}

	/**
	 * @param level {@link Level} of the log entry we want
	 * @param marker {@link Marker} of the log entry we want
	 * @param message message of the log entry we want
	 * @param throwable {@link Throwable} of the log entry we want
	 */
	public void logged(Level level, Marker marker, String message, Throwable throwable) {
		LogEntrySpecification specification = factory.createGroupLogEntrySpecification(level, new LogEntry.StringMessage(message), throwable, marker);
		verifyRecordingLoggerSatisfiesSpecification(specification);
	}

	/**
	 * @param message message of the log entry we want
	 */
	public void loggedError(String message) {
		logged(Level.ERROR, message);
	}

	/**
	 * @param message message of the log entry we want
	 * @param arguments arguments in the message of the log entry we want
	 */
	public void loggedError(String message, Object... arguments) {
		logged(Level.ERROR, message, arguments);
	}

	/**
	 * @param message message of the log entry we want
	 * @param throwable {@link Throwable} of the log entry we want
	 */
	public void loggedError(String message, Throwable throwable) {
		logged(Level.ERROR, message, throwable);
	}

	/**
	 * @param marker {@link Marker} of the log entry we want
	 * @param message message of the log entry we want
	 */
	public void loggedError(Marker marker, String message) {
		logged(Level.ERROR, marker, message);
	}

	/**
	 * @param marker {@link Marker} of the log entry we want
	 * @param message message of the log entry we want
	 * @param arguments arguments in the message of the log entry we want
	 */
	public void loggedError(Marker marker, String message, Object... arguments) {
		logged(Level.ERROR, marker, message, arguments);
	}

	/**
	 * @param marker {@link Marker} of the log entry we want
	 * @param message message of the log entry we want
	 * @param throwable {@link Throwable} of the log entry we want
	 */
	public void loggedError(Marker marker, String message, Throwable throwable) {
		logged(Level.ERROR, marker, message, throwable);
	}

	/**
	 * @param message message of the log entry we want
	 */
	public void loggedWarn(String message) {
		logged(Level.WARN, message);
	}

	/**
	 * @param message message of the log entry we want
	 * @param arguments arguments in the message of the log entry we want
	 */
	public void loggedWarn(String message, Object... arguments) {
		logged(Level.WARN, message, arguments);
	}

	/**
	 * @param message message of the log entry we want
	 * @param throwable {@link Throwable} of the log entry we want
	 */
	public void loggedWarn(String message, Throwable throwable) {
		logged(Level.WARN, message, throwable);
	}

	/**
	 * @param marker {@link Marker} of the log entry we want
	 * @param message message of the log entry we want
	 */
	public void loggedWarn(Marker marker, String message) {
		logged(Level.WARN, marker, message);
	}

	/**
	 * @param marker {@link Marker} of the log entry we want
	 * @param message message of the log entry we want
	 * @param arguments arguments in the message of the log entry we want
	 */
	public void loggedWarn(Marker marker, String message, Object... arguments) {
		logged(Level.WARN, marker, message, arguments);
	}

	/**
	 * @param marker {@link Marker} of the log entry we want
	 * @param message message of the log entry we want
	 * @param throwable {@link Throwable} of the log entry we want
	 */
	public void loggedWarn(Marker marker, String message, Throwable throwable) {
		logged(Level.WARN, marker, message, throwable);
	}

	/**
	 * @param message message of the log entry we want
	 */
	public void loggedInfo(String message) {
		logged(Level.INFO, message);
	}

	/**
	 * @param message message of the log entry we want
	 * @param arguments arguments in the message of the log entry we want
	 */
	public void loggedInfo(String message, Object... arguments) {
		logged(Level.INFO, message, arguments);
	}

	/**
	 * @param message message of the log entry we want
	 * @param throwable {@link Throwable} of the log entry we want
	 */
	public void loggedInfo(String message, Throwable throwable) {
		logged(Level.INFO, message, throwable);
	}

	/**
	 * @param marker {@link Marker} of the log entry we want
	 * @param message message of the log entry we want
	 */
	public void loggedInfo(Marker marker, String message) {
		logged(Level.INFO, marker, message);
	}

	/**
	 * @param marker {@link Marker} of the log entry we want
	 * @param message message of the log entry we want
	 * @param arguments arguments in the message of the log entry we want
	 */
	public void loggedInfo(Marker marker, String message, Object... arguments) {
		logged(Level.INFO, marker, message, arguments);
	}

	/**
	 * @param marker {@link Marker} of the log entry we want
	 * @param message message of the log entry we want
	 * @param throwable {@link Throwable} of the log entry we want
	 */
	public void loggedInfo(Marker marker, String message, Throwable throwable) {
		logged(Level.INFO, marker, message, throwable);
	}

	/**
	 * @param message message of the log entry we want
	 */
	public void loggedDebug(String message) {
		logged(Level.DEBUG, message);
	}

	/**
	 * @param message message of the log entry we want
	 * @param arguments arguments in the message of the log entry we want
	 */
	public void loggedDebug(String message, Object... arguments) {
		logged(Level.DEBUG, message, arguments);
	}

	/**
	 * @param message message of the log entry we want
	 * @param throwable {@link Throwable} of the log entry we want
	 */
	public void loggedDebug(String message, Throwable throwable) {
		logged(Level.DEBUG, message, throwable);
	}

	/**
	 * @param marker {@link Marker} of the log entry we want
	 * @param message message of the log entry we want
	 */
	public void loggedDebug(Marker marker, String message) {
		logged(Level.DEBUG, marker, message);
	}

	/**
	 * @param marker {@link Marker} of the log entry we want
	 * @param message message of the log entry we want
	 * @param arguments arguments in the message of the log entry we want
	 */
	public void loggedDebug(Marker marker, String message, Object... arguments) {
		logged(Level.DEBUG, marker, message, arguments);
	}

	/**
	 * @param marker {@link Marker} of the log entry we want
	 * @param message message of the log entry we want
	 * @param throwable {@link Throwable} of the log entry we want
	 */
	public void loggedDebug(Marker marker, String message, Throwable throwable) {
		logged(Level.DEBUG, marker, message, throwable);
	}

	/**
	 * @param message message of the log entry we want
	 */
	public void loggedTrace(String message) {
		logged(Level.TRACE, message);
	}

	/**
	 * @param message message of the log entry we want
	 * @param arguments arguments in the message of the log entry we want
	 */
	public void loggedTrace(String message, Object... arguments) {
		logged(Level.TRACE, message, arguments);
	}

	/**
	 * @param message message of the log entry we want
	 * @param throwable {@link Throwable} of the log entry we want
	 */
	public void loggedTrace(String message, Throwable throwable) {
		logged(Level.TRACE, message, throwable);
	}

	/**
	 * @param marker {@link Marker} of the log entry we want
	 * @param message message of the log entry we want
	 */
	public void loggedTrace(Marker marker, String message) {
		logged(Level.TRACE, marker, message);
	}

	/**
	 * @param marker {@link Marker} of the log entry we want
	 * @param message message of the log entry we want
	 * @param arguments arguments in the message of the log entry we want
	 */
	public void loggedTrace(Marker marker, String message, Object... arguments) {
		logged(Level.TRACE, marker, message, arguments);
	}

	/**
	 * @param marker {@link Marker} of the log entry we want
	 * @param message message of the log entry we want
	 * @param throwable {@link Throwable} of the log entry we want
	 */
	public void loggedTrace(Marker marker, String message, Throwable throwable) {
		logged(Level.TRACE, marker, message, throwable);
	}

	private void verifyRecordingLoggerSatisfiesSpecification(LogEntrySpecification specification) throws AssertionError {
		recordingLoggerSpecificationVerifier.verifyRecordingLoggerSatisfiesSpecification(logger, specification);
	}

}
