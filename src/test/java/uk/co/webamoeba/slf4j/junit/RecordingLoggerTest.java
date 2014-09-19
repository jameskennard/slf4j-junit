package uk.co.webamoeba.slf4j.junit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.slf4j.Marker;
import org.slf4j.helpers.BasicMarkerFactory;

/**
 * @author James Kennard
 */
public class RecordingLoggerTest {
	
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
	public void shouldDetermineIfInfoEnabled() {
		// Given
		RecordingLogger recordingLogger = new RecordingLogger("a recording logger");
		
		// When
		boolean isTraceEnabled = recordingLogger.isInfoEnabled();
		
		// Then
		assertThat(isTraceEnabled, is(false));
	}
	
	@Test
	public void shouldDetermineIfInfoEnabledGivenMarker() {
		// Given
		RecordingLogger recordingLogger = new RecordingLogger("a recording logger");
		Marker marker = new BasicMarkerFactory().getMarker("some marker");
		
		// When
		boolean isTraceEnabled = recordingLogger.isInfoEnabled(marker);
		
		// Then
		assertThat(isTraceEnabled, is(false));
	}
	
	@Test
	public void shouldDetermineIfWarnEnabled() {
		// Given
		RecordingLogger recordingLogger = new RecordingLogger("a recording logger");
		
		// When
		boolean isTraceEnabled = recordingLogger.isWarnEnabled();
		
		// Then
		assertThat(isTraceEnabled, is(false));
	}
	
	@Test
	public void shouldDetermineIfWarnEnabledGivenMarker() {
		// Given
		RecordingLogger recordingLogger = new RecordingLogger("a recording logger");
		Marker marker = new BasicMarkerFactory().getMarker("some marker");
		
		// When
		boolean isTraceEnabled = recordingLogger.isWarnEnabled(marker);
		
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
	
	// TODO make RecordingLogger implement org.slf4j.Logger
	
}
