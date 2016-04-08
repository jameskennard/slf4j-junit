package uk.co.webamoeba.slf4j.junit.logger;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.helpers.BasicMarkerFactory;
import uk.co.webamoeba.slf4j.junit.log.LogRegistry;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Test for {@link RecordingLogger}, also refer to {@link RecordingLoggerLevelTest}
 * 
 * @author James Kennard
 */
public class RecordingLoggerTest {

	@Before
	public void clearLogs() {
		LogRegistry.getSingleton().clearAll();
	}

	@Test
	public void shouldGetName() {
		// Given
		String expectedName = "expected name";
		RecordingLogger recordingLogger = new RecordingLogger(expectedName);

		// When
		String name = recordingLogger.getName();

		// Then
		assertThat(name, is(expectedName));
	}

	@Test
	public void shouldImplementLogger() {
		// Given
		RecordingLogger recordingLogger = new RecordingLogger("a recording logger");

		// When
		boolean isLogger = Logger.class.isInstance(recordingLogger);

		// Then
		assertThat(isLogger, is(true));
	}

	@Test
	public void shouldDetermineIfTraceEnabled() {
		// Given
		RecordingLogger recordingLogger = new RecordingLogger("a recording logger");

		// When
		boolean isTraceEnabled = recordingLogger.isTraceEnabled();

		// Then
		assertThat(isTraceEnabled, is(false));
	}

	@Test
	public void shouldDetermineIfTraceEnabledGivenMarker() {
		// Given
		RecordingLogger recordingLogger = new RecordingLogger("a recording logger");
		Marker marker = new BasicMarkerFactory().getMarker("some marker");

		// When
		boolean isTraceEnabled = recordingLogger.isTraceEnabled(marker);

		// Then
		assertThat(isTraceEnabled, is(false));
	}

	@Test
	public void shouldDetermineIfDebugEnabled() {
		// Given
		RecordingLogger recordingLogger = new RecordingLogger("a recording logger");

		// When
		boolean isTraceEnabled = recordingLogger.isDebugEnabled();

		// Then
		assertThat(isTraceEnabled, is(false));
	}

	@Test
	public void shouldDetermineIfDebugEnabledGivenMarker() {
		// Given
		RecordingLogger recordingLogger = new RecordingLogger("a recording logger");
		Marker marker = new BasicMarkerFactory().getMarker("some marker");

		// When
		boolean isTraceEnabled = recordingLogger.isDebugEnabled(marker);

		// Then
		assertThat(isTraceEnabled, is(false));
	}

	@Test
	public void shouldDetermineIfErrorEnabled() {
		// Given
		RecordingLogger recordingLogger = new RecordingLogger("a recording logger");

		// When
		boolean isTraceEnabled = recordingLogger.isErrorEnabled();

		// Then
		assertThat(isTraceEnabled, is(false));
	}

	@Test
	public void shouldDetermineIfErrorEnabledGivenMarker() {
		// Given
		RecordingLogger recordingLogger = new RecordingLogger("a recording logger");
		Marker marker = new BasicMarkerFactory().getMarker("some marker");

		// When
		boolean isTraceEnabled = recordingLogger.isErrorEnabled(marker);

		// Then
		assertThat(isTraceEnabled, is(false));
	}

}
