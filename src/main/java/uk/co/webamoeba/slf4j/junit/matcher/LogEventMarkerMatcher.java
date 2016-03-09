package uk.co.webamoeba.slf4j.junit.matcher;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.slf4j.Marker;
import uk.co.webamoeba.slf4j.junit.event.LogEvent;

/**
 * Matcher capable of matching {@link LogEvent LogEvents} based on their {@link Marker}
 * 
 * @author James Kennard
 */
public class LogEventMarkerMatcher extends TypeSafeDiagnosingMatcher<LogEvent> {

	private final Marker marker;

	/**
	 * @param marker {@link Marker} we want matched {@link LogEvent LogEvents} to have
	 */
	public LogEventMarkerMatcher(Marker marker) {
		this.marker = marker;
	}

	/**
	 * {@inheritDoc}
	 */
	public void describeTo(Description description) {
		description.appendText(LogEvent.class.getSimpleName()).appendText(" with marker ").appendValue(marker);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean matchesSafely(LogEvent logEvent, Description mismatchDescription) {
		if (!marker.equals(logEvent.getMarker())) {
			mismatchDescription
					.appendText("wanted ").appendValue(marker)
					.appendText(" but was ").appendValue(logEvent.getMarker());
			return false;
		}
		return true;
	}

}
