package uk.co.webamoeba.slf4j.junit.matcher;

import org.hamcrest.Description;
import org.hamcrest.StringDescription;
import org.junit.Test;
import org.slf4j.Marker;
import org.slf4j.helpers.BasicMarkerFactory;
import uk.co.webamoeba.slf4j.junit.log.LogEntry;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static uk.co.webamoeba.slf4j.junit.testsupport.DescriptionMatcher.describes;
import static uk.co.webamoeba.slf4j.junit.testsupport.LogEntryTestFactory.logEntryWithMarker;

/**
 * Test for {@link LogEntryMarkerMatcher}
 * 
 * @author James Kennard
 */
public class LogEntryMarkerMatcherTest extends LogEntryMatcherTest<LogEntryMarkerMatcher> {

	private static final BasicMarkerFactory MARKER_FACTORY = new BasicMarkerFactory();

	@Test
	public void shouldMatch() {
		// Given
		Marker marker = aMarker();
		LogEntry logEntry = logEntryWithMarker(marker);

		LogEntryMarkerMatcher matcher = new LogEntryMarkerMatcher(marker);

		// When
		boolean matches = matcher.matches(logEntry);

		// Then
		assertThat(matches, is(true));
	}

	@Test
	public void shouldNotMatch() {
		// Given
		Marker marker = aMarker();
		LogEntryMarkerMatcher matcher = new LogEntryMarkerMatcher(marker);

		Marker differentMarker = aDifferentMarker(marker);
		LogEntry logEntry = logEntryWithMarker(differentMarker);

		// When
		boolean matches = matcher.matches(logEntry);

		// Then
		assertThat(matches, is(false));
	}

	@Test
	public void shouldDescribeMismatch() {
		// Given
		Marker marker = aMarker();
		LogEntryMarkerMatcher matcher = new LogEntryMarkerMatcher(marker);

		Marker differentMarker = aDifferentMarker(marker);
		LogEntry logEntry = logEntryWithMarker(differentMarker);

		Description mismatchDescription = new StringDescription();

		// When
		matcher.describeMismatch(logEntry, mismatchDescription);

		// Then
		assertThat(mismatchDescription, describes("wanted <" + marker + "> but was <" + differentMarker + ">"));
	}

	@Test
	public void shouldDescribe() {
		// Given
		Marker marker = aMarker();
		LogEntryMarkerMatcher matcher = new LogEntryMarkerMatcher(marker);
		Description description = new StringDescription();

		// When
		matcher.describeTo(description);

		// Then
		assertThat(description, describes("LogEntry with marker <" + marker + ">"));
	}
	
	@Test
	public void shouldDescribeMatchingLogEntry() {
		// Given
		Marker marker = aMarker();
		LogEntryMarkerMatcher matcher = new LogEntryMarkerMatcher(marker);
		Description description = new StringDescription();

		// When
		matcher.describeMatchingLogEntry(description);

		// Then
		assertThat(description, describes("with marker <" + marker + ">"));
	}

	@Override
	protected LogEntryMarkerMatcher aMatcher() {
		return new LogEntryMarkerMatcher(aMarker());
	}

	private static Marker aMarker() {
		return MARKER_FACTORY.getMarker("A Marker");
	}

	private static Marker aDifferentMarker(Marker marker) {
		return MARKER_FACTORY.getMarker("Different to: " + marker.getName());
	}

}
