package uk.co.webamoeba.slf4j.junit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.helpers.BasicMarkerFactory;

import uk.co.webamoeba.slf4j.junit.event.LogEvent;
import uk.co.webamoeba.slf4j.junit.event.LogEventRegistry;

/**
 * @author James Kennard
 */
public class RecordingLoggerTest {

	@Before
	public void clearLogs() {
		LogEventRegistry.getSingleton().clearAll();
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
	public void shouldDetermineIfInfoEnabled() {
		// Given
		RecordingLogger recordingLogger = new RecordingLogger("a recording logger");

		// When
		boolean isTraceEnabled = recordingLogger.isInfoEnabled();

		// Then
		assertThat(isTraceEnabled, is(true));
	}

	@Test
	public void shouldDetermineIfInfoEnabledGivenMarker() {
		// Given
		RecordingLogger recordingLogger = new RecordingLogger("a recording logger");
		Marker marker = new BasicMarkerFactory().getMarker("some marker");

		// When
		boolean isTraceEnabled = recordingLogger.isInfoEnabled(marker);

		// Then
		assertThat(isTraceEnabled, is(true));
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

	@Test
	public void shouldLogInfoGivenMessage() {
		// Given
		String name = "a recording logger";
		RecordingLogger recordingLogger = new RecordingLogger(name);
		String message = "expected message";

		// When
		recordingLogger.info(message);

		// Then
		assertThat(logEvents(name).size(), is(1));
		assertThat(logEvents(name).get(0).getMessageAsString(), is(message));
	}

	@Test
	public void shouldLogInfoGivenFormatAndArgument() {
		// Given
		String format = "Format {}";
		Object arg = "Argument";
		String expectedMessage = "Format Argument";
		String name = "a recording logger";
		RecordingLogger recordingLogger = new RecordingLogger(name);

		// When
		recordingLogger.info(format, arg);

		// Then
		assertThat(logEvents(name).size(), is(1));
		assertThat(logEvents(name).get(0).getMessageAsString(), is(expectedMessage));
	}

	@Test
	public void shouldLogInfoGivenFormatAndTwoArguments() {
		// Given
		String format = "Format {} {}";
		Object arg1 = "Argument One";
		Object arg2 = "Argument Two";
		String expectedMessage = "Format Argument One Argument Two";
		String name = "a recording logger";
		RecordingLogger recordingLogger = new RecordingLogger(name);

		// When
		recordingLogger.info(format, arg1, arg2);

		// Then
		assertThat(logEvents(name).size(), is(1));
		assertThat(logEvents(name).get(0).getMessageAsString(), is(expectedMessage));
	}

	@Test
	public void shouldLogInfoGivenFormatAndVarArgs() {
		// Given
		String format = "Format {} {} {}";
		Object arg1 = "Argument One";
		Object arg2 = "Argument Two";
		Object arg3 = "Argument Three";
		String expectedMessage = "Format Argument One Argument Two Argument Three";
		String name = "a recording logger";
		RecordingLogger recordingLogger = new RecordingLogger(name);

		// When
		recordingLogger.info(format, arg1, arg2, arg3);

		// Then
		assertThat(logEvents(name).size(), is(1));
		assertThat(logEvents(name).get(0).getMessageAsString(), is(expectedMessage));
	}

	@Test
	public void shouldLogInfoGivenMessageAndThrowable() {
		// Given
		String message = "Some Message";
		Throwable throwable = new RuntimeException();
		String name = "a recording logger";
		RecordingLogger recordingLogger = new RecordingLogger(name);

		// When
		recordingLogger.info(message, throwable);

		// Then
		assertThat(logEvents(name).size(), is(1));
		assertThat(logEvents(name).get(0).getMessageAsString(), is(message));
		assertThat(logEvents(name).get(0).getThrowable(), is(sameInstance(throwable)));
	}

	@Test
	public void shouldLogInfoGivenMarkerAndMessage() {
		// Given
		Marker marker = new BasicMarkerFactory().getMarker("Some marker");
		String message = "Some Message";
		String name = "a recording logger";
		RecordingLogger recordingLogger = new RecordingLogger(name);

		// When
		recordingLogger.info(marker, message);

		// Then
		assertThat(logEvents(name).size(), is(1));
		assertThat(logEvents(name).get(0).getMessageAsString(), is(message));
		assertThat(logEvents(name).get(0).getMarker(), is(sameInstance(marker)));
	}

	@Test
	public void shouldFailToLogInfoGivenMarkerFormatAndArgument() {
		// Given
		Marker marker = new BasicMarkerFactory().getMarker("Some marker");
		String format = "Format";
		Object arg = "Argument";
		String name = "a recording logger";
		RecordingLogger recordingLogger = new RecordingLogger(name);

		try {
			// When
			recordingLogger.info(marker, format, arg);

			// Then
			fail("Should throw UnsupportedOperationException");
		} catch (UnsupportedOperationException e) {
		}
	}

	@Test
	public void shouldFailToLogInfoGivenMarkerFormatAndTwoArguments() {
		// Given
		Marker marker = new BasicMarkerFactory().getMarker("Some marker");
		String format = "Format";
		Object arg1 = "Argument";
		Object arg2 = "Argument";
		String name = "a recording logger";
		RecordingLogger recordingLogger = new RecordingLogger(name);

		try {
			// When
			recordingLogger.info(marker, format, arg1, arg2);

			// Then
			fail("Should throw UnsupportedOperationException");
		} catch (UnsupportedOperationException e) {
		}
	}

	@Test
	public void shouldFailToLogInfoGivenMarkerFormatAndVarArgs() {
		// Given
		Marker marker = new BasicMarkerFactory().getMarker("Some marker");
		String format = "Format";
		Object arg1 = "Argument";
		Object arg2 = "Argument";
		Object arg3 = "Argument";
		String name = "a recording logger";
		RecordingLogger recordingLogger = new RecordingLogger(name);

		try {
			// When
			recordingLogger.info(marker, format, arg1, arg2, arg3);

			// Then
			fail("Should throw UnsupportedOperationException");
		} catch (UnsupportedOperationException e) {
		}
	}

	@Test
	public void shouldFailToLogInfoGivenMarkerMessageAndThrowable() {
		// Given
		Marker marker = new BasicMarkerFactory().getMarker("Some marker");
		String message = "Message";
		Throwable thowable = new RuntimeException();
		String name = "a recording logger";
		RecordingLogger recordingLogger = new RecordingLogger(name);

		try {
			// When
			recordingLogger.info(marker, message, thowable);

			// Then
			fail("Should throw UnsupportedOperationException");
		} catch (UnsupportedOperationException e) {
		}
	}

	private List<LogEvent> logEvents(String name) {
		return LogEventRegistry.getSingleton().getRegister(name).getLogEvents();
	}

}
