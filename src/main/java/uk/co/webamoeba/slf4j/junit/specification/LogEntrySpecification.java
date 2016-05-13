package uk.co.webamoeba.slf4j.junit.specification;

import uk.co.webamoeba.slf4j.junit.log.LogEntry;

/**
 * Specification that a {@link LogEntry} can satisfy.
 * 
 * @author James Kennard
 */
public interface LogEntrySpecification {

	/**
	 * @param logEntry {@link LogEntry} we want to know satisfies the {@link LogEntrySpecification}
	 * @return {@code true} if the {@link LogEntry} satisfies the {@link LogEntrySpecification}
	 */
	boolean isSatisfiedBy(LogEntry logEntry);

	/**
	 * @return Description of the Specification
	 */
	String describeSatisfiedLogEntry();

}
