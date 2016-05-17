package uk.co.webamoeba.slf4j.junit.specification;

import uk.co.webamoeba.slf4j.junit.assertion.CheckArgument;
import uk.co.webamoeba.slf4j.junit.log.LogEntry;

/**
 * Group of {@link LogEntrySpecification} which can be used together.
 * 
 * @author James Kennard
 */
public class GroupLogEntrySpecification implements LogEntrySpecification {

	private final LogEntrySpecification[] specifications;

	/**
	 * @param specifications {@link LogEntrySpecification specifications} we are grouping together
	 */
	public GroupLogEntrySpecification(LogEntrySpecification... specifications) {
		CheckArgument.hasNoNullElements(specifications, "specifications must not be null");
		CheckArgument.hasElements(specifications, "at least one specification must be provided");
		this.specifications = specifications;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isSatisfiedBy(LogEntry logEntry) {
		for (int specificationIndex = 0; specificationIndex < specifications.length; specificationIndex++) {
			if (!specifications[specificationIndex].isSatisfiedBy(logEntry)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	public String describeSatisfiedLogEntry() {
		String description = "";
		for (int specificationIndex = 0; specificationIndex < specifications.length; specificationIndex++) {
			if (specificationIndex > 0) {
				description += " ";
			}
			description += specifications[specificationIndex].describeSatisfiedLogEntry();
		}
		return description;
	}

}
