package uk.co.webamoeba.slf4j.junit.matcher;

import org.hamcrest.Description;
import org.hamcrest.StringDescription;
import org.junit.Test;
import org.slf4j.Marker;
import org.slf4j.helpers.BasicMarkerFactory;
import uk.co.webamoeba.slf4j.junit.event.LogEvent;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static uk.co.webamoeba.slf4j.junit.testsupport.DescriptionMatcher.describes;
import static uk.co.webamoeba.slf4j.junit.testsupport.LogEventTestFactory.logEventWithMarker;

/**
 * Test for {@link LogEventMarkerMatcher}
 * 
 * @author James Kennard
 */
public class LogEventMarkerMatcherTest {

	private static final BasicMarkerFactory MARKER_FACTORY = new BasicMarkerFactory();

	@Test
	public void shouldMatch() {
		// Given
		Marker marker = aMarker();
		LogEvent logEvent = logEventWithMarker(marker);

		LogEventMarkerMatcher matcher = new LogEventMarkerMatcher(marker);

		// When
		boolean matches = matcher.matches(logEvent);

		// Then
		assertThat(matches, is(true));
	}

	@Test
	public void shouldNotMatch() {
		// Given
		Marker marker = aMarker();
		LogEventMarkerMatcher matcher = new LogEventMarkerMatcher(marker);

		Marker differentMarker = aDifferentMarker(marker);
		LogEvent logEvent = logEventWithMarker(differentMarker);

		// When
		boolean matches = matcher.matches(logEvent);

		// Then
		assertThat(matches, is(false));
	}

	@Test
	public void shouldDescribeMismatch() {
		// Given
		Marker marker = aMarker();
		LogEventMarkerMatcher matcher = new LogEventMarkerMatcher(marker);

		Marker differentMarker = aDifferentMarker(marker);
		LogEvent logEvent = logEventWithMarker(differentMarker);

		Description mismatchDescription = new StringDescription();

		// When
		matcher.describeMismatch(logEvent, mismatchDescription);

		// Then
		assertThat(mismatchDescription, describes("wanted <" + marker + "> but was <" + differentMarker + ">"));
	}

	@Test
	public void shouldDescribe() {
		// Given
		Marker marker = aMarker();
		LogEventMarkerMatcher matcher = new LogEventMarkerMatcher(marker);
		Description description = new StringDescription();

		// When
		matcher.describeTo(description);

		// Then
		assertThat(description, describes("LogEvent with marker <" + marker + ">"));
	}

	private static Marker aMarker() {
		return MARKER_FACTORY.getMarker("A Marker");
	}

	private Marker aDifferentMarker(Marker marker) {
		return MARKER_FACTORY.getMarker("Different to: " + marker.getName());
	}
}
