package uk.co.webamoeba.slf4j.junit.logger;

import java.util.List;
import org.junit.Test;
import org.slf4j.Marker;
import org.slf4j.helpers.BasicMarkerFactory;
import uk.co.webamoeba.slf4j.junit.log.Level;
import uk.co.webamoeba.slf4j.junit.log.LogEntry;
import uk.co.webamoeba.slf4j.junit.log.LogRegistry;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

public abstract class RecordingLoggerLevelTest {

	private final Level level;

	public RecordingLoggerLevelTest(Level level) {
		this.level = level;
	}
	
	protected Level expectedLevel() {
		return level;
	}
	
	@Test
	public void shouldDetermineIfEnabled() {
		// Given
		RecordingLogger recordingLogger = new RecordingLogger("a recording logger", new LogRegistry());

		// When
		boolean isTraceEnabled = isEnabled(recordingLogger);

		// Then
		assertThat(isTraceEnabled, is(true));
	}

	protected abstract boolean isEnabled(RecordingLogger recordingLogger);

	@Test
	public void shouldDetermineIfEnabledGivenMarker() {
		// Given
		RecordingLogger recordingLogger = new RecordingLogger("a recording logger", new LogRegistry());
		Marker marker = new BasicMarkerFactory().getMarker("some marker");

		// When
		boolean isTraceEnabled = isEnabled(recordingLogger, marker);

		// Then
		assertThat(isTraceEnabled, is(true));
	}

	protected abstract boolean isEnabled(RecordingLogger recordingLogger, Marker marker);

	@Test
	public void shouldLogGivenMessage() {
		// Given
		String name = "a recording logger";
		LogRegistry registry = new LogRegistry();
		RecordingLogger recordingLogger = new RecordingLogger(name, registry);
		String message = "expected message";

		// When
		log(recordingLogger, message);

		// Then
		assertThat(logEntries(registry, name).size(), is(1));
		assertThat(logEntries(registry, name).get(0).getMessageAsString(), is(message));
		assertThat(logEntries(registry, name).get(0).getLevel(), is(expectedLevel()));
	}

	protected abstract void log(RecordingLogger recordingLogger, String message);

	@Test
	public void shouldLogGivenFormatAndArgument() {
		// Given
		String format = "Format {}";
		Object arg = "Argument";
		String expectedMessage = "Format Argument";
		String name = "a recording logger";
		LogRegistry registry = new LogRegistry();
		RecordingLogger recordingLogger = new RecordingLogger(name, registry);

		// When
		log(format, arg, recordingLogger);

		// Then
		assertThat(logEntries(registry, name).size(), is(1));
		assertThat(logEntries(registry, name).get(0).getMessageAsString(), is(expectedMessage));
		assertThat(logEntries(registry, name).get(0).getLevel(), is(expectedLevel()));
	}

	protected abstract void log(String format, Object argument, RecordingLogger recordingLogger);

	@Test
	public void shouldLogGivenFormatAndTwoArguments() {
		// Given
		String format = "Format {} {}";
		Object arg1 = "Argument One";
		Object arg2 = "Argument Two";
		String expectedMessage = "Format Argument One Argument Two";
		String name = "a recording logger";
		LogRegistry registry = new LogRegistry();
		RecordingLogger recordingLogger = new RecordingLogger(name, registry);

		// When
		log(format, arg1, arg2, recordingLogger);

		// Then
		assertThat(logEntries(registry, name).size(), is(1));
		assertThat(logEntries(registry, name).get(0).getMessageAsString(), is(expectedMessage));
		assertThat(logEntries(registry, name).get(0).getLevel(), is(expectedLevel()));
	}

	protected abstract void log(String format, Object argument1, Object argument2, RecordingLogger recordingLogger);

	@Test
	public void shouldLogGivenFormatAndVarArgs() {
		// Given
		String format = "Format {} {} {}";
		Object arg1 = "Argument One";
		Object arg2 = "Argument Two";
		Object arg3 = "Argument Three";
		String expectedMessage = "Format Argument One Argument Two Argument Three";
		String name = "a recording logger";
		LogRegistry registry = new LogRegistry();
		RecordingLogger recordingLogger = new RecordingLogger(name, registry);

		// When
		log(format, arg1, arg2, arg3, recordingLogger);

		// Then
		assertThat(logEntries(registry, name).size(), is(1));
		assertThat(logEntries(registry, name).get(0).getMessageAsString(), is(expectedMessage));
		assertThat(logEntries(registry, name).get(0).getLevel(), is(expectedLevel()));
	}

	protected abstract void log(String format, Object argument1, Object argument2, Object argument3, RecordingLogger recordingLogger);

	@Test
	public void shouldLogGivenMessageAndThrowable() {
		// Given
		String message = "Some Message";
		Throwable throwable = new RuntimeException();
		String name = "a recording logger";
		LogRegistry registry = new LogRegistry();
		RecordingLogger recordingLogger = new RecordingLogger(name, registry);

		// When
		log(message, throwable, recordingLogger);

		// Then
		assertThat(logEntries(registry, name).size(), is(1));
		assertThat(logEntries(registry, name).get(0).getMessageAsString(), is(message));
		assertThat(logEntries(registry, name).get(0).getThrowable(), is(sameInstance(throwable)));
		assertThat(logEntries(registry, name).get(0).getLevel(), is(expectedLevel()));
	}

	protected abstract void log(String message, Throwable throwable, RecordingLogger recordingLogger);

	@Test
	public void shouldLogGivenMarkerAndMessage() {
		// Given
		Marker marker = new BasicMarkerFactory().getMarker("Some marker");
		String message = "Some Message";
		String name = "a recording logger";
		LogRegistry registry = new LogRegistry();
		RecordingLogger recordingLogger = new RecordingLogger(name, registry);

		// When
		log(marker, message, recordingLogger);

		// Then
		assertThat(logEntries(registry, name).size(), is(1));
		assertThat(logEntries(registry, name).get(0).getMessageAsString(), is(message));
		assertThat(logEntries(registry, name).get(0).getMarker(), is(sameInstance(marker)));
		assertThat(logEntries(registry, name).get(0).getLevel(), is(expectedLevel()));
	}

	protected abstract void log(Marker marker, String message, RecordingLogger recordingLogger);

	@Test
	public void shouldLogGivenMarkerFormatAndArgument() {
		// Given
		Marker marker = new BasicMarkerFactory().getMarker("Some marker");
		String format = "Format {}";
		Object arg = "Argument";
		String name = "a recording logger";
		LogRegistry registry = new LogRegistry();
		RecordingLogger recordingLogger = new RecordingLogger(name, registry);

		// When
		log(marker, format, arg, recordingLogger);

		// Then
		assertThat(logEntries(registry, name).size(), is(1));
		assertThat(logEntries(registry, name).get(0).getMessageAsString(), is("Format Argument"));
		assertThat(logEntries(registry, name).get(0).getMarker(), is(sameInstance(marker)));
		assertThat(logEntries(registry, name).get(0).getLevel(), is(expectedLevel()));
	}

	protected abstract void log(Marker marker, String format, Object argument, RecordingLogger recordingLogger);

	@Test
	public void shouldFailToLogGivenMarkerFormatAndTwoArguments() {
		// Given
		Marker marker = new BasicMarkerFactory().getMarker("Some marker");
		String format = "Format {} {}";
		Object arg1 = "Argument";
		Object arg2 = "Argument";
		String name = "a recording logger";
		LogRegistry registry = new LogRegistry();
		RecordingLogger recordingLogger = new RecordingLogger(name, registry);

		// When
		log(marker, format, arg1, arg2, recordingLogger);

		// Then
		assertThat(logEntries(registry, name).size(), is(1));
		assertThat(logEntries(registry, name).get(0).getMessageAsString(), is("Format Argument Argument"));
		assertThat(logEntries(registry, name).get(0).getMarker(), is(sameInstance(marker)));
		assertThat(logEntries(registry, name).get(0).getLevel(), is(expectedLevel()));
	}

	protected abstract void log(Marker marker, String format, Object argument1, Object argument2, RecordingLogger recordingLogger);

	@Test
	public void shouldFailToLogGivenMarkerFormatAndVarArgs() {
		// Given
		Marker marker = new BasicMarkerFactory().getMarker("Some marker");
		String format = "Format {} {} {}";
		Object arg1 = "Argument";
		Object arg2 = "Argument";
		Object arg3 = "Argument";
		String name = "a recording logger";
		LogRegistry registry = new LogRegistry();
		RecordingLogger recordingLogger = new RecordingLogger(name, registry);

		// When
		log(marker, format, arg1, arg2, arg3, recordingLogger);

		// Then
		assertThat(logEntries(registry, name).size(), is(1));
		assertThat(logEntries(registry, name).get(0).getMessageAsString(), is("Format Argument Argument Argument"));
		assertThat(logEntries(registry, name).get(0).getMarker(), is(sameInstance(marker)));
		assertThat(logEntries(registry, name).get(0).getLevel(), is(expectedLevel()));
	}

	protected abstract void log(Marker marker, String format, Object argument1, Object argument2, Object argument3, RecordingLogger recordingLogger);

	@Test
	public void shouldFailToLogGivenMarkerMessageAndThrowable() {
		// Given
		Marker marker = new BasicMarkerFactory().getMarker("Some marker");
		String message = "Message";
		Throwable throwable = new RuntimeException();
		String name = "a recording logger";
		LogRegistry registry = new LogRegistry();
		RecordingLogger recordingLogger = new RecordingLogger(name, registry);

		// When
		log(marker, message, throwable, recordingLogger);

		// Then
		assertThat(logEntries(registry, name).size(), is(1));
		assertThat(logEntries(registry, name).get(0).getMessageAsString(), is(message));
		assertThat(logEntries(registry, name).get(0).getMarker(), is(sameInstance(marker)));
		assertThat(logEntries(registry, name).get(0).getThrowable(), is(sameInstance(throwable)));
		assertThat(logEntries(registry, name).get(0).getLevel(), is(expectedLevel()));
	}

	private List<LogEntry> logEntries(LogRegistry registry, String name) {
		return registry.getLog(name).getEntries();
	}

	protected abstract void log(Marker marker, String message, Throwable throwable, RecordingLogger recordingLogger);

}
