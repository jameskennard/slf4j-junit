package uk.co.webamoeba.slf4j.junit.specification;

import org.slf4j.Marker;
import uk.co.webamoeba.slf4j.junit.assertion.CheckArgument;
import uk.co.webamoeba.slf4j.junit.log.LogEntry;

/**
 * Specification that can be satisfied by a {@link LogEntry} that has a specific {@link Marker}
 * 
 * @author James Kennard
 */
public class MarkerLogEntrySpecification implements LogEntrySpecification {

	private final Marker marker;

	/**
	 * @param marker {@link Marker} a {@link LogEntry} must have to {@link #isSatisfiedBy(LogEntry) satisfy} this {@link LogEntrySpecification}
	 */
	public MarkerLogEntrySpecification(Marker marker) {
		CheckArgument.isNotNull(marker, "marker must not be null");
		this.marker = marker;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isSatisfiedBy(LogEntry logEntry) {
		return marker.equals(logEntry.getMarker());
	}

	/**
	 * {@inheritDoc}
	 */
	public String describeSatisfiedLogEntry() {
		return "with marker <" + marker + ">";
	}

}
