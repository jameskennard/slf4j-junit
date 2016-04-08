package uk.co.webamoeba.slf4j.junit.logger;

import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Marker;
import org.slf4j.helpers.BasicMarkerFactory;
import uk.co.webamoeba.slf4j.junit.ClearLogsTestRule;
import uk.co.webamoeba.slf4j.junit.log.Level;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import static uk.co.webamoeba.slf4j.junit.testsupport.LogEntryTestFactory.logEntries;

public abstract class RecordingLoggerLevelTest {

	private final Level level;

	public RecordingLoggerLevelTest(Level level) {
		this.level = level;
	}
	
	protected Level expectedLevel() {
		return level;
	}
	
	@Rule
	public ClearLogsTestRule clearLogsTestRule = new ClearLogsTestRule();
	
	@Test
	public void shouldDetermineIfEnabled() {
		// Given
		RecordingLogger recordingLogger = new RecordingLogger("a recording logger");

		// When
		boolean isTraceEnabled = isEnabled(recordingLogger);

		// Then
		assertThat(isTraceEnabled, is(true));
	}

	protected abstract boolean isEnabled(RecordingLogger recordingLogger);

	@Test
	public void shouldDetermineIfInfoEnabledGivenMarker() {
		// Given
		RecordingLogger recordingLogger = new RecordingLogger("a recording logger");
		Marker marker = new BasicMarkerFactory().getMarker("some marker");

		// When
		boolean isTraceEnabled = isEnabled(recordingLogger, marker);

		// Then
		assertThat(isTraceEnabled, is(true));
	}

	protected abstract boolean isEnabled(RecordingLogger recordingLogger, Marker marker);

	@Test
	public void shouldLogInfoGivenMessage() {
		// Given
		String name = "a recording logger";
		RecordingLogger recordingLogger = new RecordingLogger(name);
		String message = "expected message";

		// When
		log(recordingLogger, message);

		// Then
		assertThat(logEntries(name).size(), is(1));
		assertThat(logEntries(name).get(0).getMessageAsString(), is(message));
		assertThat(logEntries(name).get(0).getLevel(), is(expectedLevel()));
	}

	protected abstract void log(RecordingLogger recordingLogger, String message);

	@Test
	public void shouldLogInfoGivenFormatAndArgument() {
		// Given
		String format = "Format {}";
		Object arg = "Argument";
		String expectedMessage = "Format Argument";
		String name = "a recording logger";
		RecordingLogger recordingLogger = new RecordingLogger(name);

		// When
		log(format, arg, recordingLogger);

		// Then
		assertThat(logEntries(name).size(), is(1));
		assertThat(logEntries(name).get(0).getMessageAsString(), is(expectedMessage));
		assertThat(logEntries(name).get(0).getLevel(), is(expectedLevel()));
	}

	protected abstract void log(String format, Object argument, RecordingLogger recordingLogger);

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
		log(format, arg1, arg2, recordingLogger);

		// Then
		assertThat(logEntries(name).size(), is(1));
		assertThat(logEntries(name).get(0).getMessageAsString(), is(expectedMessage));
		assertThat(logEntries(name).get(0).getLevel(), is(expectedLevel()));
	}

	protected abstract void log(String format, Object argument1, Object argument2, RecordingLogger recordingLogger);

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
		log(format, arg1, arg2, arg3, recordingLogger);

		// Then
		assertThat(logEntries(name).size(), is(1));
		assertThat(logEntries(name).get(0).getMessageAsString(), is(expectedMessage));
		assertThat(logEntries(name).get(0).getLevel(), is(expectedLevel()));
	}

	protected abstract void log(String format, Object argument1, Object argument2, Object argument3, RecordingLogger recordingLogger);

	@Test
	public void shouldLogInfoGivenMessageAndThrowable() {
		// Given
		String message = "Some Message";
		Throwable throwable = new RuntimeException();
		String name = "a recording logger";
		RecordingLogger recordingLogger = new RecordingLogger(name);

		// When
		log(message, throwable, recordingLogger);

		// Then
		assertThat(logEntries(name).size(), is(1));
		assertThat(logEntries(name).get(0).getMessageAsString(), is(message));
		assertThat(logEntries(name).get(0).getThrowable(), is(sameInstance(throwable)));
		assertThat(logEntries(name).get(0).getLevel(), is(expectedLevel()));
	}

	protected abstract void log(String message, Throwable throwable, RecordingLogger recordingLogger);

	@Test
	public void shouldLogInfoGivenMarkerAndMessage() {
		// Given
		Marker marker = new BasicMarkerFactory().getMarker("Some marker");
		String message = "Some Message";
		String name = "a recording logger";
		RecordingLogger recordingLogger = new RecordingLogger(name);

		// When
		log(marker, message, recordingLogger);

		// Then
		assertThat(logEntries(name).size(), is(1));
		assertThat(logEntries(name).get(0).getMessageAsString(), is(message));
		assertThat(logEntries(name).get(0).getMarker(), is(sameInstance(marker)));
		assertThat(logEntries(name).get(0).getLevel(), is(expectedLevel()));
	}

	protected abstract void log(Marker marker, String message, RecordingLogger recordingLogger);

	@Test
	public void shouldLogInfoGivenMarkerFormatAndArgument() {
		// Given
		Marker marker = new BasicMarkerFactory().getMarker("Some marker");
		String format = "Format {}";
		Object arg = "Argument";
		String name = "a recording logger";
		RecordingLogger recordingLogger = new RecordingLogger(name);

		// When
		log(marker, format, arg, recordingLogger);

		// Then
		assertThat(logEntries(name).size(), is(1));
		assertThat(logEntries(name).get(0).getMessageAsString(), is("Format Argument"));
		assertThat(logEntries(name).get(0).getMarker(), is(sameInstance(marker)));
		assertThat(logEntries(name).get(0).getLevel(), is(expectedLevel()));
	}

	protected abstract void log(Marker marker, String format, Object argument, RecordingLogger recordingLogger);

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
		log(marker, format, arg1, arg2, recordingLogger);

		// Then
		assertThat(logEntries(name).size(), is(1));
		assertThat(logEntries(name).get(0).getMessageAsString(), is("Format Argument Argument"));
		assertThat(logEntries(name).get(0).getMarker(), is(sameInstance(marker)));
		assertThat(logEntries(name).get(0).getLevel(), is(expectedLevel()));
	}

	protected abstract void log(Marker marker, String format, Object argument1, Object argument2, RecordingLogger recordingLogger);

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
		log(marker, format, arg1, arg2, arg3, recordingLogger);

		// Then
		assertThat(logEntries(name).size(), is(1));
		assertThat(logEntries(name).get(0).getMessageAsString(), is("Format Argument Argument Argument"));
		assertThat(logEntries(name).get(0).getMarker(), is(sameInstance(marker)));
		assertThat(logEntries(name).get(0).getLevel(), is(expectedLevel()));
	}

	protected abstract void log(Marker marker, String format, Object argument1, Object argument2, Object argument3, RecordingLogger recordingLogger);

	@Test
	public void shouldFailToLogInfoGivenMarkerMessageAndThrowable() {
		// Given
		Marker marker = new BasicMarkerFactory().getMarker("Some marker");
		String message = "Message";
		Throwable throwable = new RuntimeException();
		String name = "a recording logger";
		RecordingLogger recordingLogger = new RecordingLogger(name);

		// When
		log(marker, message, throwable, recordingLogger);

		// Then
		assertThat(logEntries(name).size(), is(1));
		assertThat(logEntries(name).get(0).getMessageAsString(), is(message));
		assertThat(logEntries(name).get(0).getMarker(), is(sameInstance(marker)));
		assertThat(logEntries(name).get(0).getThrowable(), is(sameInstance(throwable)));
		assertThat(logEntries(name).get(0).getLevel(), is(expectedLevel()));
	}

	protected abstract void log(Marker marker, String message, Throwable throwable, RecordingLogger recordingLogger);

}