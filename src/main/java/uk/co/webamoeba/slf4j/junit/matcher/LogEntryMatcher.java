package uk.co.webamoeba.slf4j.junit.matcher;

import java.util.List;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.slf4j.Logger;
import org.slf4j.Marker;
import uk.co.webamoeba.slf4j.junit.log.Level;
import uk.co.webamoeba.slf4j.junit.log.Log;
import uk.co.webamoeba.slf4j.junit.log.LogEntry;
import uk.co.webamoeba.slf4j.junit.log.LogRegistry;
import uk.co.webamoeba.slf4j.junit.log.LogEntry.FormattedMessage;
import uk.co.webamoeba.slf4j.junit.log.LogEntry.Message;
import uk.co.webamoeba.slf4j.junit.log.LogEntry.StringMessage;
import uk.co.webamoeba.slf4j.junit.logger.RecordingLogger;

/**
 * @author James Kennard
 */
public abstract class LogEntryMatcher extends BaseMatcher<Logger> {

	private final Level level;
	
	private final Marker marker;
	
	private final Message message;

	private final Throwable throwable;

	/**
	 * @param message The message we want the {@link LogEntry} to have
	 */
	protected LogEntryMatcher(Level level, String message) {
		this(level, null, new StringMessage(message), null);
	}

	/**
	 * @param format The format of the message we want the {@link LogEntry} to have
	 * @param arguments The arguments we want to use to describe the parts of the message from the format
	 */
	protected LogEntryMatcher(Level level, String format, Object... arguments) {
		this(level, null, new FormattedMessage(format, arguments), null);
	}

	/**
	 * @param message The message we want the {@link LogEntry} to have
	 * @param throwable The {@link Throwable} we are logging
	 */
	protected LogEntryMatcher(Level level, String message, Throwable throwable) {
		this(level, null, new StringMessage(message), throwable);
	}

	protected LogEntryMatcher(Level level, Marker marker, String message) {
		this(level, marker, new StringMessage(message), null);
	}
	
	protected LogEntryMatcher(Level level, Marker marker, String message, Throwable throwable) {
		this(level, marker, new StringMessage(message), throwable);
	}
	
	protected LogEntryMatcher(Level level, Marker marker, String format, Object... arguments) {
		this(level, marker, new FormattedMessage(format, arguments), null);
	}
	
	protected LogEntryMatcher(Level level, Marker marker, Message message, Throwable throwable) {
		this.level = level;
		this.marker = marker;
		this.message = message;
		this.throwable = throwable;
	}

	public boolean matches(Object item) {
		if (!RecordingLogger.class.isAssignableFrom(item.getClass())) {
			return false;
		}
		return matches((RecordingLogger) item, new Description.NullDescription());
	}

	public void describeTo(Description description) {
		description.appendText(describeFunction() + "(");
		if (message.getClass() == FormattedMessage.class) {
			FormattedMessage formattedMessage = (FormattedMessage) message;
			description.appendValue(formattedMessage.getFormat());
			for (Object argument : formattedMessage.getArguments()) {
				description.appendText(", ").appendValue(argument);
			}
		} else {
			description.appendValue(message.getMessageAsString());
		}
		if (throwable != null) {
			description.appendText(", ") .appendValue(throwable);
		}
		description.appendText(")");
	}

	@Override
	public void describeMismatch(Object item, Description description) {
		matches(item, description);
	}

	private boolean matches(Object item, Description mismatchDescription) {
		if (!RecordingLogger.class.isAssignableFrom(item.getClass())) {
			mismatchDescription
					.appendText("the item is not a test logger, it looks like you are not using the matcher correctly ");
			return false;
		}
		return matches((RecordingLogger) item, mismatchDescription);
	}

	private boolean matches(RecordingLogger logger, Description mismatchDescription) {
		Log register = LogRegistry.getSingleton().getRegister(logger.getName());
		for (LogEntry logEntry : register.getEntries()) {
			if (logEntryMatches(logEntry)) {
				return true;
			}
		}
		describeMismatch(logger, mismatchDescription);
		return false;
	}

	private boolean logEntryMatches(LogEntry logEntry) {
		if (notEqual(level, logEntry.getLevel())) {
			return false;
		}
		if (notEqual(message, logEntry.getMessage())) {
			return false;
		}
		if (notEqual(throwable, logEntry.getThrowable())) {
			return false;
		}
		if (notEqual(marker, logEntry.getMarker())) {
			return false;
		}
		return true;
	}

	/**
	 * Describes why the matcher did not match by appending the description to the provided mismatchDescription.
	 * 
	 * @param logger The {@link RecordingLogger} that did not contain a matching {@link LogEntry}
	 * @param mismatchDescription The description in which we want to describe the mismatch 
	 */
	private void describeMismatch(RecordingLogger logger, Description mismatchDescription) {
		mismatchDescription.appendText(describeLevel() + " to ").appendValue(logger.getName()).appendText(" with message ")
				.appendValue(message.getMessageAsString());
		if (throwable != null) {
			mismatchDescription.appendText(" and throwable ").appendValue(throwable);
		}
		mismatchDescription.appendText(" was not logged");
		Log register = LogRegistry.getSingleton().getRegister(logger.getName());
		List<LogEntry> logEntries = register.getEntries();
		if (logEntries.isEmpty()) {
			mismatchDescription.appendText(" ");
		} else {
			mismatchDescription.appendText("; But these were logged ");
			for (LogEntry logEntry : logEntries) {
				mismatchDescription.appendDescriptionOf(logEntry).appendText(" ");
			}
		}
	}

	/**
	 * <code>null</code> safe method used to determine if two {@link Object Objects} are not equal. This method relies
	 * on the {@link Object Objects} equals() method to determine equality for non <code>null</code> values.
	 * 
	 * @param object1 object to check for non equality
	 * @param object2 object to check for non equality
	 * @return Returns <code>false</code> if the objects are equal
	 */
	private static boolean notEqual(Object object1, Object object2) {
		return object1 == null && object2 != null || (object1 != null && !object1.equals(object2));
	}
	
	/**
	 * @return The name of the function to use when describing the matcher, for example "loggedInfo"
	 */
	protected abstract String describeFunction();

	/**
	 * @return The name of the level to use when describing a mismatch, for example "info"
	 */
	protected abstract String describeLevel();
	
}
