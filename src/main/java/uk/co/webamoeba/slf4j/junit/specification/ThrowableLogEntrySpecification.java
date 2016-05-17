package uk.co.webamoeba.slf4j.junit.specification;

import uk.co.webamoeba.slf4j.junit.assertion.CheckArgument;
import uk.co.webamoeba.slf4j.junit.log.LogEntry;

/**
 * Specification that can be satisfied by a {@link LogEntry} that has a specific {@link Throwable}
 * 
 * @author James Kennard
 */
public class ThrowableLogEntrySpecification implements LogEntrySpecification {

	private final Throwable throwable;

	/**
	 * @param throwable {@link Throwable} a {@link LogEntry} must have to {@link #isSatisfiedBy(LogEntry) satisfy} this {@link LogEntrySpecification}
	 */
	public ThrowableLogEntrySpecification(Throwable throwable) {
		CheckArgument.isNotNull(throwable, "throwable must not be null");
		this.throwable = throwable;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isSatisfiedBy(LogEntry logEntry) {
		return throwable.equals(logEntry.getThrowable());
	}

	/**
	 * {@inheritDoc}
	 */
	public String describeSatisfiedLogEntry() {
		return "with throwable <" + throwable.getMessage() + ">";
	}

}
