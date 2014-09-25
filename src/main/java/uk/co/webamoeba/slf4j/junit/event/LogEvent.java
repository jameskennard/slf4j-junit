package uk.co.webamoeba.slf4j.junit.event;

import org.hamcrest.Description;
import org.hamcrest.SelfDescribing;
import org.slf4j.helpers.MessageFormatter;

/**
 * Describes a Log in terms of an event that has occurred.
 * 
 * @author James Kennard
 */
public class LogEvent implements SelfDescribing {

	private final Message message;
	
	private Throwable throwable;

	public LogEvent(String message) {
		this.message = new StringMessage(message);
	}

	public LogEvent(String format, Object[] arguments) {
		this.message = new FormattedMessage(format, arguments);
	}

	public LogEvent(String message, Throwable throwable) {
		this.message = new StringMessage(message);
		this.throwable = throwable;
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
	
	public void describeTo(Description description) {
		description.appendText("info(").appendValue(message.getMessageAsString());
		if (throwable != null) {
			description.appendText(", ").appendValue(throwable);
		}
		description.appendText(")");		
	}

	/**
	 * Describes a message for a {@link LogEvent}
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
	 * Describes a message for a {@link LogEvent} in terms of a {@link String}.
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

		public String getFormat() {
			return format;
		}
		
		public Object[] getArguments() {
			return arguments;
		}
		
		@Override
		public String getMessageAsString() {
			return MessageFormatter.arrayFormat(format, arguments).getMessage();
		}
		
		@Override
		public String toString() {
			return getMessageAsString();
		}

	}

}
