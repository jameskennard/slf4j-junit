package uk.co.webamoeba.slf4j.junit.specification;

import uk.co.webamoeba.slf4j.junit.assertion.CheckArgument;
import uk.co.webamoeba.slf4j.junit.log.LogEntry;
import uk.co.webamoeba.slf4j.junit.log.LogEntry.Message;

/**
 * Specification that can be satisfied by a {@link LogEntry} that has a specific {@link Message}
 * 
 * @author James Kennard
 */
public class MessageLogEntrySpecification implements LogEntrySpecification {

	private final Message message;

	/**
	 * @param message {@link Message} a {@link LogEntry} must have to {@link #isSatisfiedBy(LogEntry) satisfy} this {@link LogEntrySpecification}
	 */
	public MessageLogEntrySpecification(Message message) {
		CheckArgument.isNotNull(message, "message must not be null");
		this.message = message;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isSatisfiedBy(LogEntry logEntry) {
		return message.equals(logEntry.getMessage());
	}

	/**
	 * {@inheritDoc}
	 */
	public String describeSatisfiedLogEntry() {
		return "with message <" + message.getMessageAsString() + ">";
	}

}
