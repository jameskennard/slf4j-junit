package uk.co.webamoeba.slf4j.junit.log;

import org.slf4j.Marker;
import org.slf4j.helpers.MessageFormatter;

/**
 * Describes a Log in terms of {@link LogEntry LogEntries} that have been made.
 * 
 * @author James Kennard
 */
public class LogEntry {

	private final Level level;

	private final Message message;

	private final Throwable throwable;

	private final Marker marker;

	public LogEntry(Level level, String message) {
		this(level, null, new StringMessage(message), null);
	}

	public LogEntry(Level level, String format, Object[] arguments) {
		this(level, null, new FormattedMessage(format, arguments), null);
	}

	public LogEntry(Level level, String message, Throwable throwable) {
		this(level, null, new StringMessage(message), throwable);
	}

	public LogEntry(Level level, Marker marker, String message) {
		this(level, marker, new StringMessage(message), null);
	}

	public LogEntry(Level level, Marker marker, String message, Throwable throwable) {
		this(level, marker, new StringMessage(message), throwable);
	}

	public LogEntry(Level level, Marker marker, String format, Object[] arguments) {
		this(level, marker, new FormattedMessage(format, arguments), null);
	}

	private LogEntry(Level level, Marker marker, Message message, Throwable throwable) {
		this.level = level;
		this.marker = marker;
		this.message = message;
		this.throwable = throwable;
	}

	public Level getLevel() {
		return level;
	}

	public Message getMessage() {
		return message;
	}

	public String getMessageAsString() {
		return message.getMessageAsString();
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public Marker getMarker() {
		return marker;
	}

	/**
	 * Describes a message for a {@link LogEntry}
	 * 
	 * @author James Kennard
	 */
	public static abstract class Message {

		public abstract String getMessageAsString();

		@Override
		public int hashCode() {
			return 31 + ((getMessageAsString() == null) ? 0 : getMessageAsString().hashCode());
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null || !Message.class.isInstance(obj)) {
				return false;
			}
			return this == obj || getMessageAsString().equals(((Message) obj).getMessageAsString());
		}

	}

	/**
	 * Describes a message for a {@link LogEntry} in terms of a {@link String}.
	 * 
	 * @author James Kennard
	 */
	public static class StringMessage extends Message {

		private String message;

		public StringMessage(String message) {
			this.message = message;
		}

		@Override
		public String getMessageAsString() {
			return message;
		}

		@Override
		public String toString() {
			return getMessageAsString();
		}

	}

	/**
	 * Describes a message for a {@link LogEntry} in terms of a format {@link String} and an array of {@link Object Objects}.
	 * 
	 * @author James Kennard
	 */
	public static class FormattedMessage extends Message {

		private String format;

		private Object[] arguments;

		public FormattedMessage(String format, Object[] arguments) {
			this.format = format;
			this.arguments = arguments;
		}

		public String getFormat() {
			return format;
		}

		public Object[] getArguments() {
			return arguments;
		}

		@Override
		public String getMessageAsString() {
			return MessageFormatter.arrayFormat(getFormat(), getArguments()).getMessage();
		}

		@Override
		public String toString() {
			return getMessageAsString();
		}

	}

}
