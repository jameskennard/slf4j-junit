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
public class OnGoingLogVerifier {

	private final GroupLogEntrySpecificationFactory factory;

	private final RecordingLogger logger;

	private final RecordingLoggerSpecificationVerifier recordingLoggerSpecificationVerifier;

	public OnGoingLogVerifier(GroupLogEntrySpecificationFactory factory, RecordingLogger logger, RecordingLoggerSpecificationVerifier recordingLoggerSpecificationVerifier) {
		this.factory = factory;
		this.logger = logger;
		this.recordingLoggerSpecificationVerifier = recordingLoggerSpecificationVerifier;
	}

	/**
	 * @param level {@link Level} of the log entry we want
	 * @param message message of the log entry we want
	 * @return {@link OnGoingLogVerifier}
	 */
	public OnGoingLogVerifier logged(Level level, String message) {
		LogEntrySpecification specification = factory.createGroupLogEntrySpecification(level, new LogEntry.StringMessage(message), null, null);
		verifyRecordingLoggerSatisfiesSpecification(specification);
		return this;
	}

	/**
	 * @param level {@link Level} of the log entry we want
	 * @param message message of the log entry we want
	 * @param arguments arguments in the message of the log entry we want
	 * @return {@link OnGoingLogVerifier}
	 */
	public OnGoingLogVerifier logged(Level level, String message, Object... arguments) {
		LogEntrySpecification specification = factory.createGroupLogEntrySpecification(level, new LogEntry.FormattedMessage(message, arguments), null, null);
		verifyRecordingLoggerSatisfiesSpecification(specification);
		return this;
	}

	/**
	 * @param level {@link Level} of the log entry we want
	 * @param message message of the log entry we want
	 * @param throwable {@link Throwable} of the log entry we want
	 * @return {@link OnGoingLogVerifier}
	 */
	public OnGoingLogVerifier logged(Level level, String message, Throwable throwable) {
		LogEntrySpecification specification = factory.createGroupLogEntrySpecification(level, new LogEntry.StringMessage(message), throwable, null);
		verifyRecordingLoggerSatisfiesSpecification(specification);
		return this;
	}

	/**
	 * @param level {@link Level} of the log entry we want
	 * @param marker {@link Marker} of the log entry we want
	 * @param message message of the log entry we want
	 * @return {@link OnGoingLogVerifier}
	 */
	public OnGoingLogVerifier logged(Level level, Marker marker, String message) {
		LogEntrySpecification specification = factory.createGroupLogEntrySpecification(level, new LogEntry.StringMessage(message), null, marker);
		verifyRecordingLoggerSatisfiesSpecification(specification);
		return this;
	}

	/**
	 * @param level {@link Level} of the log entry we want
	 * @param marker {@link Marker} of the log entry we want
	 * @param message message of the log entry we want
	 * @param arguments arguments in the message of the log entry we want
	 * @return {@link OnGoingLogVerifier}
	 */
	public OnGoingLogVerifier logged(Level level, Marker marker, String message, Object... arguments) {
		LogEntrySpecification specification = factory.createGroupLogEntrySpecification(level, new LogEntry.FormattedMessage(message, arguments), null, marker);
		verifyRecordingLoggerSatisfiesSpecification(specification);
		return this;
	}

	/**
	 * @param level {@link Level} of the log entry we want
	 * @param marker {@link Marker} of the log entry we want
	 * @param message message of the log entry we want
	 * @param throwable {@link Throwable} of the log entry we want
	 * @return {@link OnGoingLogVerifier}
	 */
	public OnGoingLogVerifier logged(Level level, Marker marker, String message, Throwable throwable) {
		LogEntrySpecification specification = factory.createGroupLogEntrySpecification(level, new LogEntry.StringMessage(message), throwable, marker);
		verifyRecordingLoggerSatisfiesSpecification(specification);
		return this;
	}

	private void verifyRecordingLoggerSatisfiesSpecification(LogEntrySpecification specification) throws AssertionError {
		recordingLoggerSpecificationVerifier.verifyRecordingLoggerSatisfiesSpecification(logger, specification);
	}

}
