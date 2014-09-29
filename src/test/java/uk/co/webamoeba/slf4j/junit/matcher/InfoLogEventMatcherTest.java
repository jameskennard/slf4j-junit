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

import uk.co.webamoeba.slf4j.junit.RecordingLogger;
import uk.co.webamoeba.slf4j.junit.event.LogEventRegistry;

/**
 * @author James Kennard
 */
public class InfoLogEventMatcherTest {

	@Before
	public void clearAll() {
		LogEventRegistry.getSingleton().clearAll();
	}

	@Test
	public void shouldImplementMatcher() {
		assertThat(new InfoLogEventMatcher("Message"), is(instanceOf(Matcher.class)));
	}

	@Test
	public void shouldNotMatchGivenUnexpectedTypeOfLogger() {
		// Given
		InfoLogEventMatcher matcher = new InfoLogEventMatcher("Message");
		Logger logger = mock(Logger.class);

		// When
		boolean matches = matcher.matches(logger);

		// Then
		assertFalse(matches);
	}

	@Test
	public void shouldDescribeToGivenUnexpectedTypeOfLogger() {
		// Given
		InfoLogEventMatcher matcher = new InfoLogEventMatcher("Message");
		Logger logger = mock(Logger.class);
		Description description = new StringDescription();

		// When
		matcher.describeMismatch(logger, description);

		// Then
		assertThat(description.toString(), is("the item is not a test logger, it looks like you are not using the matcher correctly "));
	}

	@Test
	public void shouldMatchGivenLoggerHasMessage() {
		// Given
		String message = "Some Message";
		InfoLogEventMatcher matcher = new InfoLogEventMatcher(message);
		Logger logger = new RecordingLogger("A recording logger");
		logger.info(message);

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
		InfoLogEventMatcher matcher = new InfoLogEventMatcher(marker, message);
		Logger logger = new RecordingLogger("A recording logger");
		logger.info(marker, message);

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
		InfoLogEventMatcher matcher = new InfoLogEventMatcher(marker, format, argument);
		Logger logger = new RecordingLogger("A recording logger");
		logger.info(marker, format, argument);

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
		InfoLogEventMatcher matcher = new InfoLogEventMatcher(message, throwable);
		Logger logger = new RecordingLogger("A recording logger");
		logger.info(message, throwable);

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
		InfoLogEventMatcher matcher = new InfoLogEventMatcher(marker, message, throwable);
		Logger logger = new RecordingLogger("A recording logger");
		logger.info(marker, message, throwable);

		// When
		boolean matches = matcher.matches(logger);

		// Then
		assertThat(matches, is(true));
	}
	
	@Test
	public void shouldNotMatchGivenLoggerHasMessageButNoMarker() {
		// Given
		Marker marker = new BasicMarkerFactory().getMarker("Some Marker");
		String message = "Some Message";
		InfoLogEventMatcher matcher = new InfoLogEventMatcher(marker, message);
		Logger logger = new RecordingLogger("A recording logger");
		logger.info(message);

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
		InfoLogEventMatcher matcher = new InfoLogEventMatcher(marker, message, throwable);
		Logger logger = new RecordingLogger("A recording logger");
		logger.info(marker, message);

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
		InfoLogEventMatcher matcher = new InfoLogEventMatcher(message, throwable);
		Logger logger = new RecordingLogger("A recording logger");
		logger.info(message);

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
		InfoLogEventMatcher matcher = new InfoLogEventMatcher(message);
		Logger logger = new RecordingLogger("A recording logger");
		logger.info(message, throwable);

		// When
		boolean matches = matcher.matches(logger);

		// Then
		assertThat(matches, is(false));
	}

	@Test
	public void shouldMatchGivenLoggerHasStringMessageAndMatcherHasFormatedMessage() {
		// Given
		String message = "Some Message";
		InfoLogEventMatcher matcher = new InfoLogEventMatcher("{}", message);
		Logger logger = new RecordingLogger("A recording logger");
		logger.info(message);

		// When
		boolean matches = matcher.matches(logger);

		// Then
		assertThat(matches, is(true));
	}

	@Test
	public void shouldMatchGivenLoggerHasFormattedMessageAndMatcherHasStringMessage() {
		// Given
		String message = "Some Message";
		InfoLogEventMatcher matcher = new InfoLogEventMatcher(message);
		Logger logger = new RecordingLogger("A recording logger");
		logger.info("{}", message);

		// When
		boolean matches = matcher.matches(logger);

		// Then
		assertThat(matches, is(true));
	}

	@Test
	public void shouldNotMatchGivenLoggerHasNoLogEvents() {
		// Given
		String message = "Some Message";
		InfoLogEventMatcher matcher = new InfoLogEventMatcher(message);
		Logger logger = new RecordingLogger("A recording logger");

		// When
		boolean matches = matcher.matches(logger);

		// Then
		assertThat(matches, is(false));
	}

	@Test
	public void shouldNotMatchGivenLoggerHasLogEventWithDifferentMessage() {
		// Given
		InfoLogEventMatcher matcher = new InfoLogEventMatcher("Some Message");
		Logger logger = new RecordingLogger("A recording logger");
		logger.info("Some Different Message");

		// When
		boolean matches = matcher.matches(logger);

		// Then
		assertThat(matches, is(false));
	}

	@Test
	public void shouldDescribeMismatchGivenMessage() {
		// Given
		String message = "Some Message";
		InfoLogEventMatcher matcher = new InfoLogEventMatcher(message);
		String loggerName = "A recording logger";
		Logger logger = new RecordingLogger(loggerName);
		Description description = new StringDescription();

		// When
		matcher.describeMismatch(logger, description);

		// Then
		assertThat(description.toString(), is("info to \"" + loggerName + "\" with message \"" + message
				+ "\" was not logged "));
	}
	
	@Test
	public void shouldDescribeMismatchGivenMessageAndThrowable() {
		// Given
		String message = "Some Message";
		Throwable throwable = new Throwable();
		InfoLogEventMatcher matcher = new InfoLogEventMatcher(message, throwable);
		String loggerName = "A recording logger";
		Logger logger = new RecordingLogger(loggerName);
		Description description = new StringDescription();

		// When
		matcher.describeMismatch(logger, description);

		// Then
		assertThat(description.toString(), is("info to \"" + loggerName + "\" with message \"" + message
				+ "\" and throwable <" + throwable + "> was not logged "));
	}
	
	@Test
	public void shouldDescribeMismatchGivenSomethingElseWasLogged() {
		// Given
		String message = "Some Message";
		String somethingElse = "Something Else";
		InfoLogEventMatcher matcher = new InfoLogEventMatcher(message);
		String loggerName = "A recording logger";
		Logger logger = new RecordingLogger(loggerName);
		logger.info(somethingElse);
		Description description = new StringDescription();

		// When
		matcher.describeMismatch(logger, description);

		// Then
		assertThat(description.toString(), is("info to \"" + loggerName + "\" with message \"" + message
				+ "\" was not logged; But these were logged info(\"" + somethingElse + "\") "));
	}
	
	@Test
	public void shouldDescribeMismatchGivenOtherThingsWasLogged() {
		// Given
		String message = "Some Message";
		String anotherThing = "Another Thing";
		String yetAnotherThing = "Yet Another Thing";
		InfoLogEventMatcher matcher = new InfoLogEventMatcher(message);
		String loggerName = "A recording logger";
		Logger logger = new RecordingLogger(loggerName);
		logger.info(anotherThing);
		logger.info(yetAnotherThing);
		Description description = new StringDescription();

		// When
		matcher.describeMismatch(logger, description);

		// Then
		assertThat(description.toString(), is("info to \"" + loggerName + "\" with message \"" + message
				+ "\" was not logged; But these were logged info(\"" + anotherThing + "\") info(\"" + yetAnotherThing + "\") "));
	}

	@Test
	public void shouldDescribeToGivenStringMessage() {
		// Given
		InfoLogEventMatcher matcher = new InfoLogEventMatcher("Some Message");
		Description description = new StringDescription();

		// When
		matcher.describeTo(description);

		// Then
		assertThat(description.toString(), is("loggedInfo(\"Some Message\")"));
	}
	
	@Test
	public void shouldDescribeToGivenStringMessageAndThrowable() {
		// Given
		String message = "Some Message";
		Throwable throwable = new Throwable();
		InfoLogEventMatcher matcher = new InfoLogEventMatcher(message, throwable);
		Description description = new StringDescription();

		// When
		matcher.describeTo(description);

		// Then
		assertThat(description.toString(), is("loggedInfo(\"Some Message\", <" + throwable + ">)"));
	}
	
	@Test
	public void shouldDescribeToGivenFormattedMessage() {
		// Given
		InfoLogEventMatcher matcher = new InfoLogEventMatcher("format", "argument");
		Description description = new StringDescription();

		// When
		matcher.describeTo(description);

		// Then
		assertThat(description.toString(), is("loggedInfo(\"format\", \"argument\")"));
	}
}
