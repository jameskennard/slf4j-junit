package uk.co.webamoeba.slf4j.junit.specification;

import org.junit.Test;
import org.slf4j.Marker;
import org.slf4j.helpers.BasicMarkerFactory;
import uk.co.webamoeba.slf4j.junit.log.LogEntry;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static uk.co.webamoeba.slf4j.junit.testsupport.LogEntryTestFactory.logEntryWithMarker;

/**
 * Test for {@link MarkerLogEntrySpecification}
 * 
 * @author James Kennard
 */
public class MarkerLogEntrySpecificationTest {

	private static final BasicMarkerFactory MARKER_FACTORY = new BasicMarkerFactory();

	@Test
	public void shouldSatisfy() {
		// Given
		Marker marker = aMarker();
		LogEntry logEntry = logEntryWithMarker(marker);

		MarkerLogEntrySpecification specification = new MarkerLogEntrySpecification(marker);

		// When
		boolean isSatisfied = specification.isSatisfiedBy(logEntry);

		// Then
		assertThat(isSatisfied, is(true));
	}

	@Test
	public void shouldNotSatisfy() {
		// Given
		Marker marker = aMarker();
		LogEntry logEntry = logEntryWithMarker(marker);

		Marker differentMarker = aDifferentMarker();
		MarkerLogEntrySpecification specification = new MarkerLogEntrySpecification(differentMarker);

		// When
		boolean isSatisfied = specification.isSatisfiedBy(logEntry);

		// Then
		assertThat(isSatisfied, is(false));
	}

	@Test
	public void shouldDescribeSatisfiedLogEntry() {
		// Given
		String markerName = "Some Marker";
		Marker marker = mock(Marker.class, markerName);
		MarkerLogEntrySpecification specification = new MarkerLogEntrySpecification(marker);

		// When
		String description = specification.describeSatisfiedLogEntry();

		// Then
		assertThat(description, is("with marker <" + markerName + ">"));
	}

	@Test
	public void shouldFailToConstructGivenNull() {
		// Given
		Marker marker = null;

		// When
		try {
			new MarkerLogEntrySpecification(marker);

			// Then
			fail("Should throw IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("marker must not be null"));
		}
	}

	private static Marker aMarker() {
		return MARKER_FACTORY.getMarker("A Marker");
	}

	private static Marker aDifferentMarker() {
		return MARKER_FACTORY.getMarker("A Different Marker");
	}

}
