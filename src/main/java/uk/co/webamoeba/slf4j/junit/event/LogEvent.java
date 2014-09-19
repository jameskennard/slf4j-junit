package uk.co.webamoeba.slf4j.junit.event;

import org.slf4j.helpers.MessageFormatter;

/**
 * Describes a Log in terms of an event that has occurred.
 * 
 * @author James Kennard
 */
public class LogEvent {

	private Message message;

	public LogEvent(String message) {
		this.message = new StringMessage(message);
	}

	public LogEvent(String format, Object[] arguments) {
		this.message = new FormattedMessage(format, arguments);
	}

	public String getMessage() {
		return message.getMessage();
	}

	/**
	 * Describes a message for a {@link LogEvent}
	 * 
	 * @author James Kennard
	 */
	public static abstract class Message {

		public abstract String getMessage();

		@Override
		public int hashCode() {
			return 31 + ((getMessage() == null) ? 0 : getMessage().hashCode());
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null || !Message.class.isInstance(obj)) {
				return false;
			}
			return this == obj || getMessage().equals(((Message) obj).getMessage());
		}

	}

	/**
	 * Describes a message for a {@link LogEvent} in terms of a {@link String}.
	 * 
	 * @author James Kennard
	 */
	public static class StringMessage extends Message {

		private String message;

		public StringMessage(String message) {
			this.message = message;
		}

		public String getMessage() {
			return message;
		}

	}

	/**
	 * Describes a message for a {@link LogEvent} in terms of a format {@link String} and an array of {@link Object Objects}.
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

		public String getMessage() {
			return MessageFormatter.arrayFormat(format, arguments).getMessage();
		}

	}

}
