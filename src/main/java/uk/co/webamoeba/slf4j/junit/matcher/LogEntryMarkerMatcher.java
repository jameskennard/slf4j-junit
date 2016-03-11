package uk.co.webamoeba.slf4j.junit.matcher;

import org.hamcrest.Description;
import org.slf4j.Marker;
import uk.co.webamoeba.slf4j.junit.log.LogEntry;

/**
 * Matcher capable of matching {@link LogEntry LogEntries} based on their {@link Marker}
 * 
 * @author James Kennard
 */
public class LogEntryMarkerMatcher extends LogEntryMatcher {

	private final Marker marker;

	/**
	 * @param marker {@link Marker} we want matched {@link LogEntry LogEntries} to have
	 */
	public LogEntryMarkerMatcher(Marker marker) {
		this.marker = marker;
	}
	
	@Override
	public void describeMatchingLogEntry(Description description) {
		description.appendText("with marker ").appendValue(marker);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean matchesSafely(LogEntry logEntry, Description mismatchDescription) {
		if (!marker.equals(logEntry.getMarker())) {
			mismatchDescription
					.appendText("wanted ").appendValue(marker)
					.appendText(" but was ").appendValue(logEntry.getMarker());
			return false;
		}
		return true;
	}

}
