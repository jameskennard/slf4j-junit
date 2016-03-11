package uk.co.webamoeba.slf4j.junit.matcher;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.helpers.BasicMarkerFactory;
import uk.co.webamoeba.slf4j.junit.log.LogRegistry;
import uk.co.webamoeba.slf4j.junit.logger.RecordingLogger;

/**
 * @author James Kennard
 */
public abstract class LegacyLogEntryMatcherTest {

	@Before
	public void clearAll() {
		LogRegistry.getSingleton().clearAll();
	}

	@Test
	public void shouldImplementMatcher() {
		assertThat(matcher(), is(instanceOf(Matcher.class)));
	}

	@Test
	public void shouldNotMatchGivenUnexpectedTypeOfLogger() {
		// Given
		LegacyLogEntryMatcher matcher = matcher();
		Logger logger = mock(Logger.class);

		// When
		boolean matches = matcher.matches(logger);

		// Then
		assertFalse(matches);
	}

	@Test
	public void shouldDescribeToGivenUnexpectedTypeOfLogger() {
		// Given
		LegacyLogEntryMatcher matcher = matcher();
		Logger logger = mock(Logger.class);
		Description description = new StringDescription();

		// When
		matcher.describeMismatch(logger, description);

		// Then
		assertThat(description.toString(),
				is("the item is not a test logger, it looks like you are not using the matcher correctly "));
	}

	@Test
	public void shouldMatchGivenLoggerHasMessage() {
		// Given
		String message = "Some Message";
		LegacyLogEntryMatcher matcher = matcher(message);
		Logger logger = new RecordingLogger("A recording logger");
		log(message, logger);

		// When
		boolean matches = matcher.matches(logger);

		// Then
		assertThat(matches, is(true));
	}

	@Test
	public void shouldMatchGivenLoggerHasMarkerAndMessage() {
		// Given
		Marker marker = new BasicMarkerFactory().getMarker("Some Marker");
		String message = "Some Message";
		LegacyLogEntryMatcher matcher = matcher(marker, message);
		Logger logger = new RecordingLogger("A recording logger");
		log(marker, message, logger);

		// When
		boolean matches = matcher.matches(logger);

		// Then
		assertThat(matches, is(true));
	}

	@Test
	public void shouldMatchGivenLoggerHasMarkerAndFormattedMessage() {
		// Given
		Marker marker = new BasicMarkerFactory().getMarker("Some Marker");
		String format = "Format {}";
		String argument = "argument";
		LegacyLogEntryMatcher matcher = matcher(marker, format, argument);
		Logger logger = new RecordingLogger("A recording logger");
		log(marker, format, argument, logger);

		// When
		boolean matches = matcher.matches(logger);

		// Then
		assertThat(matches, is(true));
	}

	@Test
	public void shouldMatchGivenLoggerHasMessageAndThrowable() {
		// Given
		String message = "Some Message";
		Throwable throwable = new Throwable();
		LegacyLogEntryMatcher matcher = matcher(message, throwable);
		Logger logger = new RecordingLogger("A recording logger");
		log(message, throwable, logger);

		// When
		boolean matches = matcher.matches(logger);

		// Then
		assertThat(matches, is(true));
	}

	@Test
	public void shouldMatchGivenLoggerHasMarkerAndMessageAndThrowable() {
		// Given
		Marker marker = new BasicMarkerFactory().getMarker("Some Marker");
		String message = "Some Message";
		Throwable throwable = new Throwable();
		LegacyLogEntryMatcher matcher = matcher(marker, message, throwable);
		Logger logger = new RecordingLogger("A recording logger");
		log(marker, message, throwable, logger);

		// When
		boolean matches = matcher.matches(logger);

		// Then
		assertThat(matches, is(true));
	}

	@Test
	public void shouldNotMatchGivenDifferentLevel() {
		// Given
		String message = "Some Message";
		LegacyLogEntryMatcher matcher = matcher(message);
		Logger logger = new RecordingLogger("A recording logger");
		logDifferentLevel(message, logger);

		// When
		boolean matches = matcher.matches(logger);

		// Then
		assertThat(matches, is(false));
	}

	@Test
	public void shouldNotMatchGivenLoggerHasMessageButNoMarker() {
		// Given
		Marker marker = new BasicMarkerFactory().getMarker("Some Marker");
		String message = "Some Message";
		LegacyLogEntryMatcher matcher = matcher(marker, message);
		Logger logger = new RecordingLogger("A recording logger");
		log(message, logger);

		// When
		boolean matches = matcher.matches(logger);

		// Then
		assertThat(matches, is(false));
	}

	@Test
	public void shouldNotMatchGivenLoggerHasMarkerAndMessageButNoThrowable() {
		// Given
		Marker marker = new BasicMarkerFactory().getMarker("Some Marker");
		String message = "Some Message";
		Throwable throwable = new Throwable();
		LegacyLogEntryMatcher matcher = matcher(marker, message, throwable);
		Logger logger = new RecordingLogger("A recording logger");
		log(marker, message, logger);

		// When
		boolean matches = matcher.matches(logger);

		// Then
		assertThat(matches, is(false));
	}

	@Test
	public void shouldNotMatchGivenLoggerHasMessageButNoThrowable() {
		// Given
		String message = "Some Message";
		Throwable throwable = new Throwable();
		LegacyLogEntryMatcher matcher = matcher(message, throwable);
		Logger logger = new RecordingLogger("A recording logger");
		log(message, logger);

		// When
		boolean matches = matcher.matches(logger);

		// Then
		assertThat(matches, is(false));
	}

	@Test
	public void shouldNotMatchGivenLoggerHasMessageAndUnexpectedThrowable() {
		// Given
		String message = "Some Message";
		Throwable throwable = new Throwable();
		LegacyLogEntryMatcher matcher = matcher(message);
		Logger logger = new RecordingLogger("A recording logger");
		log(message, throwable, logger);

		// When
		boolean matches = matcher.matches(logger);

		// Then
		assertThat(matches, is(false));
	}

	@Test
	public void shouldMatchGivenLoggerHasStringMessageAndMatcherHasFormatedMessage() {
		// Given
		String message = "Some Message";
		String format = "{}";
		LegacyLogEntryMatcher matcher = log(format, message);
		Logger logger = new RecordingLogger("A recording logger");
		log(message, logger);

		// When
		boolean matches = matcher.matches(logger);

		// Then
		assertThat(matches, is(true));
	}

	@Test
	public void shouldMatchGivenLoggerHasFormattedMessageAndMatcherHasStringMessage() {
		// Given
		String message = "Some Message";
		LegacyLogEntryMatcher matcher = matcher(message);
		Logger logger = new RecordingLogger("A recording logger");
		String format = "{}";
		log(format, message, logger);

		// When
		boolean matches = matcher.matches(logger);

		// Then
		assertThat(matches, is(true));
	}

	@Test
	public void shouldNotMatchGivenLoggerHasNoLogEntries() {
		// Given
		String message = "Some Message";
		LegacyLogEntryMatcher matcher = matcher(message);
		Logger logger = new RecordingLogger("A recording logger");

		// When
		boolean matches = matcher.matches(logger);

		// Then
		assertThat(matches, is(false));
	}

	@Test
	public void shouldNotMatchGivenLoggerHasLogEntryWithDifferentMessage() {
		// Given
		LegacyLogEntryMatcher matcher = matcher("Some Message");
		Logger logger = new RecordingLogger("A recording logger");
		log("Some Different Message", logger);

		// When
		boolean matches = matcher.matches(logger);

		// Then
		assertThat(matches, is(false));
	}

	@Test
	public void shouldDescribeMismatchGivenMessage() {
		// Given
		String message = "Some Message";
		LegacyLogEntryMatcher matcher = matcher(message);
		String loggerName = "A recording logger";
		Logger logger = new RecordingLogger(loggerName);
		Description description = new StringDescription();

		// When
		matcher.describeMismatch(logger, description);

		// Then
		assertThat(description.toString(), is(expectedDescribeLevel() + " to \"" + loggerName + "\" with message \""
				+ message + "\" was not logged "));
	}

	@Test
	public void shouldDescribeMismatchGivenMessageAndThrowable() {
		// Given
		String message = "Some Message";
		Throwable throwable = new Throwable();
		LegacyLogEntryMatcher matcher = matcher(message, throwable);
		String loggerName = "A recording logger";
		Logger logger = new RecordingLogger(loggerName);
		Description description = new StringDescription();

		// When
		matcher.describeMismatch(logger, description);

		// Then
		assertThat(description.toString(), is(expectedDescribeLevel() + " to \"" + loggerName + "\" with message \""
				+ message + "\" and throwable <" + throwable + "> was not logged "));
	}

	@Test
	public void shouldDescribeMismatchGivenSomethingElseWasLogged() {
		// Given
		String message = "Some Message";
		String somethingElse = "Something Else";
		LegacyLogEntryMatcher matcher = matcher(message);
		String loggerName = "A recording logger";
		Logger logger = new RecordingLogger(loggerName);
		log(somethingElse, logger);
		Description description = new StringDescription();

		// When
		matcher.describeMismatch(logger, description);

		// Then
		assertThat(description.toString(), is(expectedDescribeLevel() + " to \"" + loggerName + "\" with message \""
				+ message + "\" was not logged; But these were logged " + expectedDescribeLevel() + "(\""
				+ somethingElse + "\") "));
	}

	@Test
	public void shouldDescribeMismatchGivenOtherThingsWasLogged() {
		// Given
		String message = "Some Message";
		String anotherThing = "Another Thing";
		String yetAnotherThing = "Yet Another Thing";
		LegacyLogEntryMatcher matcher = matcher(message);
		String loggerName = "A recording logger";
		Logger logger = new RecordingLogger(loggerName);
		log(anotherThing, logger);
		log(yetAnotherThing, logger);
		Description description = new StringDescription();

		// When
		matcher.describeMismatch(logger, description);

		// Then
		assertThat(description.toString(), is(expectedDescribeLevel() + " to \"" + loggerName + "\" with message \""
				+ message + "\" was not logged; But these were logged " + expectedDescribeLevel() + "(\""
				+ anotherThing + "\") " + expectedDescribeLevel() + "(\"" + yetAnotherThing + "\") "));
	}

	@Test
	public void shouldDescribeToGivenStringMessage() {
		// Given
		LegacyLogEntryMatcher matcher = matcher("Some Message");
		Description description = new StringDescription();

		// When
		matcher.describeTo(description);

		// Then
		assertThat(description.toString(), is(expectedDescribeFunction() + "(\"Some Message\")"));
	}

	@Test
	public void shouldDescribeToGivenStringMessageAndThrowable() {
		// Given
		String message = "Some Message";
		Throwable throwable = new Throwable();
		LegacyLogEntryMatcher matcher = matcher(message, throwable);
		Description description = new StringDescription();

		// When
		matcher.describeTo(description);

		// Then
		assertThat(description.toString(), is(expectedDescribeFunction() + "(\"Some Message\", <" + throwable + ">)"));
	}

	@Test
	public void shouldDescribeToGivenFormattedMessage() {
		// Given
		LegacyLogEntryMatcher matcher = matcher("format", "argument");
		Description description = new StringDescription();

		// When
		matcher.describeTo(description);

		// Then
		assertThat(description.toString(), is(expectedDescribeFunction() + "(\"format\", \"argument\")"));
	}

	protected abstract LegacyLogEntryMatcher matcher();

	protected abstract LegacyLogEntryMatcher matcher(String message);

	protected abstract LegacyLogEntryMatcher matcher(String format, String argument);

	protected abstract LegacyLogEntryMatcher matcher(Marker marker, String message);

	protected abstract LegacyLogEntryMatcher matcher(String message, Throwable throwable);

	protected abstract LegacyLogEntryMatcher matcher(Marker marker, String message, Throwable throwable);

	protected abstract LegacyLogEntryMatcher matcher(Marker marker, String format, String argument);

	protected abstract void log(String message, Logger logger);

	protected abstract void log(String format, String argument, Logger logger);

	protected abstract void log(Marker marker, String message, Logger logger);

	protected abstract LegacyLogEntryMatcher log(String format, String argument);

	protected abstract void log(String message, Throwable throwable, Logger logger);

	protected abstract void log(Marker marker, String message, Throwable throwable, Logger logger);

	protected abstract void log(Marker marker, String format, String argument, Logger logger);

	protected abstract void logDifferentLevel(String message, Logger logger);

	protected abstract String expectedDescribeLevel();

	protected abstract String expectedDescribeFunction();
}
