package uk.co.webamoeba.slf4j.junit.specification;

import uk.co.webamoeba.slf4j.junit.assertion.CheckArgument;
import uk.co.webamoeba.slf4j.junit.log.Level;
import uk.co.webamoeba.slf4j.junit.log.LogEntry;

/**
 * Specification that can be satisfied by a {@link LogEntry} that has a specific {@link Level}
 * 
 * @author James Kennard
 */
public class LevelLogEntrySpecification implements LogEntrySpecification {

	private final Level level;

	/**
	 * @param level {@link Level} a {@link LogEntry} must have to {@link #isSatisfiedBy(LogEntry) satisfy} this {@link LogEntrySpecification}
	 */
	public LevelLogEntrySpecification(Level level) {
		CheckArgument.isNotNull(level, "level must not be null");
		this.level = level;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isSatisfiedBy(LogEntry logEntry) {
		if (logEntry.getLevel() != level) {
			return false;
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	public String describeSatisfiedLogEntry() {
		return "at level <" + level + ">";
	}

}
