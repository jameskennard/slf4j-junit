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
		assertThat(description.toString(),
				is("the item is not a test logger, it looks like you are not using the matcher correctly "));
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
	public void shouldDescribeMismatchGivenMessageNotLogged() {
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
