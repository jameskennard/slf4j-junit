package uk.co.webamoeba.slf4j.junit.logger;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.helpers.BasicMarkerFactory;

import uk.co.webamoeba.slf4j.junit.event.Level;
import uk.co.webamoeba.slf4j.junit.event.LogEvent;
import uk.co.webamoeba.slf4j.junit.event.LogEventRegistry;
import uk.co.webamoeba.slf4j.junit.logger.RecordingLogger;

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
		assertThat(isTraceEnabled, is(true));
	}

	@Test
	public void shouldDetermineIfWarnEnabledGivenMarker() {
		// Given
		RecordingLogger recordingLogger = new RecordingLogger("a recording logger");
		Marker marker = new BasicMarkerFactory().getMarker("some marker");

		// When
		boolean isTraceEnabled = recordingLogger.isWarnEnabled(marker);

		// Then
		assertThat(isTraceEnabled, is(true));
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
		assertThat(logEvents(name).get(0).getLevel(), is(Level.INFO));
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
		assertThat(logEvents(name).get(0).getLevel(), is(Level.INFO));
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
		assertThat(logEvents(name).get(0).getLevel(), is(Level.INFO));
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
		assertThat(logEvents(name).get(0).getLevel(), is(Level.INFO));
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
		assertThat(logEvents(name).get(0).getLevel(), is(Level.INFO));
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
		assertThat(logEvents(name).get(0).getLevel(), is(Level.INFO));
	}

	@Test
	public void shouldLogInfoGivenMarkerFormatAndArgument() {
		// Given
		Marker marker = new BasicMarkerFactory().getMarker("Some marker");
		String format = "Format {}";
		Object arg = "Argument";
		String name = "a recording logger";
		RecordingLogger recordingLogger = new RecordingLogger(name);

		// When
		recordingLogger.info(marker, format, arg);

		// Then
		assertThat(logEvents(name).size(), is(1));
		assertThat(logEvents(name).get(0).getMessageAsString(), is("Format Argument"));
		assertThat(logEvents(name).get(0).getMarker(), is(sameInstance(marker)));
		assertThat(logEvents(name).get(0).getLevel(), is(Level.INFO));
	}

	@Test
	public void shouldFailToLogInfoGivenMarkerFormatAndTwoArguments() {
		// Given
		Marker marker = new BasicMarkerFactory().getMarker("Some marker");
		String format = "Format {} {}";
		Object arg1 = "Argument";
		Object arg2 = "Argument";
		String name = "a recording logger";
		RecordingLogger recordingLogger = new RecordingLogger(name);

		// When
		recordingLogger.info(marker, format, arg1, arg2);

		// Then
		assertThat(logEvents(name).size(), is(1));
		assertThat(logEvents(name).get(0).getMessageAsString(), is("Format Argument Argument"));
		assertThat(logEvents(name).get(0).getMarker(), is(sameInstance(marker)));
		assertThat(logEvents(name).get(0).getLevel(), is(Level.INFO));
	}

	@Test
	public void shouldFailToLogInfoGivenMarkerFormatAndVarArgs() {
		// Given
		Marker marker = new BasicMarkerFactory().getMarker("Some marker");
		String format = "Format {} {} {}";
		Object arg1 = "Argument";
		Object arg2 = "Argument";
		Object arg3 = "Argument";
		String name = "a recording logger";
		RecordingLogger recordingLogger = new RecordingLogger(name);

		// When
		recordingLogger.info(marker, format, arg1, arg2, arg3);

		// Then
		assertThat(logEvents(name).size(), is(1));
		assertThat(logEvents(name).get(0).getMessageAsString(), is("Format Argument Argument Argument"));
		assertThat(logEvents(name).get(0).getMarker(), is(sameInstance(marker)));
		assertThat(logEvents(name).get(0).getLevel(), is(Level.INFO));
	}

	@Test
	public void shouldFailToLogInfoGivenMarkerMessageAndThrowable() {
		// Given
		Marker marker = new BasicMarkerFactory().getMarker("Some marker");
		String message = "Message";
		Throwable throwable = new RuntimeException();
		String name = "a recording logger";
		RecordingLogger recordingLogger = new RecordingLogger(name);

		// When
		recordingLogger.info(marker, message, throwable);

		// Then
		assertThat(logEvents(name).size(), is(1));
		assertThat(logEvents(name).get(0).getMessageAsString(), is(message));
		assertThat(logEvents(name).get(0).getMarker(), is(sameInstance(marker)));
		assertThat(logEvents(name).get(0).getThrowable(), is(sameInstance(throwable)));
		assertThat(logEvents(name).get(0).getLevel(), is(Level.INFO));
	}
	
	@Test
	public void shouldLogWarningGivenMessage() {
		// Given
		String name = "a recording logger";
		RecordingLogger recordingLogger = new RecordingLogger(name);
		String message = "expected message";

		// When
		recordingLogger.warn(message);

		// Then
		assertThat(logEvents(name).size(), is(1));
		assertThat(logEvents(name).get(0).getMessageAsString(), is(message));
		assertThat(logEvents(name).get(0).getLevel(), is(Level.WARN));
	}

	@Test
	public void shouldLogWarningGivenFormatAndArgument() {
		// Given
		String format = "Format {}";
		Object arg = "Argument";
		String expectedMessage = "Format Argument";
		String name = "a recording logger";
		RecordingLogger recordingLogger = new RecordingLogger(name);

		// When
		recordingLogger.warn(format, arg);

		// Then
		assertThat(logEvents(name).size(), is(1));
		assertThat(logEvents(name).get(0).getMessageAsString(), is(expectedMessage));
		assertThat(logEvents(name).get(0).getLevel(), is(Level.WARN));
	}

	@Test
	public void shouldLogWarningGivenFormatAndTwoArguments() {
		// Given
		String format = "Format {} {}";
		Object arg1 = "Argument One";
		Object arg2 = "Argument Two";
		String expectedMessage = "Format Argument One Argument Two";
		String name = "a recording logger";
		RecordingLogger recordingLogger = new RecordingLogger(name);

		// When
		recordingLogger.warn(format, arg1, arg2);

		// Then
		assertThat(logEvents(name).size(), is(1));
		assertThat(logEvents(name).get(0).getMessageAsString(), is(expectedMessage));
		assertThat(logEvents(name).get(0).getLevel(), is(Level.WARN));
	}

	@Test
	public void shouldLogWarningGivenFormatAndVarArgs() {
		// Given
		String format = "Format {} {} {}";
		Object arg1 = "Argument One";
		Object arg2 = "Argument Two";
		Object arg3 = "Argument Three";
		String expectedMessage = "Format Argument One Argument Two Argument Three";
		String name = "a recording logger";
		RecordingLogger recordingLogger = new RecordingLogger(name);

		// When
		recordingLogger.warn(format, arg1, arg2, arg3);

		// Then
		assertThat(logEvents(name).size(), is(1));
		assertThat(logEvents(name).get(0).getMessageAsString(), is(expectedMessage));
		assertThat(logEvents(name).get(0).getLevel(), is(Level.WARN));
	}

	@Test
	public void shouldLogWarningGivenMessageAndThrowable() {
		// Given
		String message = "Some Message";
		Throwable throwable = new RuntimeException();
		String name = "a recording logger";
		RecordingLogger recordingLogger = new RecordingLogger(name);

		// When
		recordingLogger.warn(message, throwable);

		// Then
		assertThat(logEvents(name).size(), is(1));
		assertThat(logEvents(name).get(0).getMessageAsString(), is(message));
		assertThat(logEvents(name).get(0).getThrowable(), is(sameInstance(throwable)));
		assertThat(logEvents(name).get(0).getLevel(), is(Level.WARN));
	}

	@Test
	public void shouldLogWarningGivenMarkerAndMessage() {
		// Given
		Marker marker = new BasicMarkerFactory().getMarker("Some marker");
		String message = "Some Message";
		String name = "a recording logger";
		RecordingLogger recordingLogger = new RecordingLogger(name);

		// When
		recordingLogger.warn(marker, message);

		// Then
		assertThat(logEvents(name).size(), is(1));
		assertThat(logEvents(name).get(0).getMessageAsString(), is(message));
		assertThat(logEvents(name).get(0).getMarker(), is(sameInstance(marker)));
		assertThat(logEvents(name).get(0).getLevel(), is(Level.WARN));
	}

	@Test
	public void shouldLogWarningGivenMarkerFormatAndArgument() {
		// Given
		Marker marker = new BasicMarkerFactory().getMarker("Some marker");
		String format = "Format {}";
		Object arg = "Argument";
		String name = "a recording logger";
		RecordingLogger recordingLogger = new RecordingLogger(name);

		// When
		recordingLogger.warn(marker, format, arg);

		// Then
		assertThat(logEvents(name).size(), is(1));
		assertThat(logEvents(name).get(0).getMessageAsString(), is("Format Argument"));
		assertThat(logEvents(name).get(0).getMarker(), is(sameInstance(marker)));
		assertThat(logEvents(name).get(0).getLevel(), is(Level.WARN));
	}

	@Test
	public void shouldFailToLogWarningGivenMarkerFormatAndTwoArguments() {
		// Given
		Marker marker = new BasicMarkerFactory().getMarker("Some marker");
		String format = "Format {} {}";
		Object arg1 = "Argument";
		Object arg2 = "Argument";
		String name = "a recording logger";
		RecordingLogger recordingLogger = new RecordingLogger(name);

		// When
		recordingLogger.warn(marker, format, arg1, arg2);

		// Then
		assertThat(logEvents(name).size(), is(1));
		assertThat(logEvents(name).get(0).getMessageAsString(), is("Format Argument Argument"));
		assertThat(logEvents(name).get(0).getMarker(), is(sameInstance(marker)));
		assertThat(logEvents(name).get(0).getLevel(), is(Level.WARN));
	}

	@Test
	public void shouldFailToLogWarningGivenMarkerFormatAndVarArgs() {
		// Given
		Marker marker = new BasicMarkerFactory().getMarker("Some marker");
		String format = "Format {} {} {}";
		Object arg1 = "Argument";
		Object arg2 = "Argument";
		Object arg3 = "Argument";
		String name = "a recording logger";
		RecordingLogger recordingLogger = new RecordingLogger(name);

		// When
		recordingLogger.warn(marker, format, arg1, arg2, arg3);

		// Then
		assertThat(logEvents(name).size(), is(1));
		assertThat(logEvents(name).get(0).getMessageAsString(), is("Format Argument Argument Argument"));
		assertThat(logEvents(name).get(0).getMarker(), is(sameInstance(marker)));
		assertThat(logEvents(name).get(0).getLevel(), is(Level.WARN));
	}

	@Test
	public void shouldFailToLogWarningGivenMarkerMessageAndThrowable() {
		// Given
		Marker marker = new BasicMarkerFactory().getMarker("Some marker");
		String message = "Message";
		Throwable throwable = new RuntimeException();
		String name = "a recording logger";
		RecordingLogger recordingLogger = new RecordingLogger(name);

		// When
		recordingLogger.warn(marker, message, throwable);

		// Then
		assertThat(logEvents(name).size(), is(1));
		assertThat(logEvents(name).get(0).getMessageAsString(), is(message));
		assertThat(logEvents(name).get(0).getMarker(), is(sameInstance(marker)));
		assertThat(logEvents(name).get(0).getThrowable(), is(sameInstance(throwable)));
		assertThat(logEvents(name).get(0).getLevel(), is(Level.WARN));
	}

	private List<LogEvent> logEvents(String name) {
		return LogEventRegistry.getSingleton().getRegister(name).getLogEvents();
	}

}
