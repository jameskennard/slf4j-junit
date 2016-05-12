package uk.co.webamoeba.slf4j.junit.matcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.slf4j.Logger;
import uk.co.webamoeba.slf4j.junit.DisableLogging;
import uk.co.webamoeba.slf4j.junit.EnableLogging;
import uk.co.webamoeba.slf4j.junit.context.LoggingContext;
import uk.co.webamoeba.slf4j.junit.log.LogEntry;
import uk.co.webamoeba.slf4j.junit.log.LogEntry.StringMessage;
import uk.co.webamoeba.slf4j.junit.logger.RecordingLogger;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.willAnswer;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static uk.co.webamoeba.slf4j.junit.LoggingMatchers.enableLogging;
import static uk.co.webamoeba.slf4j.junit.testsupport.DescriptionMatcher.describes;

/**
 * Test for {@link RecordingLoggerMatcher}
 * 
 * @author James Kennard
 */
public class RecordingLoggerMatcherTest {

	@Rule
	public EnableLogging enableLogging = enableLogging();

	@Test
	public void shouldMatch() {
		// Given
		Logger logger =  new RecordingLogger("A Logger", LoggingContext.getRegistry());
		String message = "some message";
		logger.info(message);

		Matcher<LogEntry> logEntryMatcher = new LogEntryMessageMatcher(new StringMessage(message));
		RecordingLoggerMatcher matcher = new RecordingLoggerMatcher(logEntryMatcher);

		// When
		boolean matches = matcher.matches(logger);

		// Then
		assertThat(matches, is(true));
	}

	@Test
	public void shouldNotMatchGivenNoMatchingLogEntries() {
		// Given
		Logger logger = new RecordingLogger("A Logger", LoggingContext.getRegistry());
		String message = "some message";
		logger.info(message);

		String differentMessage = "some different message";
		Matcher<LogEntry> logEntryMatcher = new LogEntryMessageMatcher(new StringMessage(differentMessage));
		RecordingLoggerMatcher matcher = new RecordingLoggerMatcher(logEntryMatcher);

		// When
		boolean matches = matcher.matches(logger);

		// Then
		assertThat(matches, is(false));
	}

	@Test
	public void shouldNotMatchGivenNoLogEntries() {
		// Given
		Logger logger = new RecordingLogger("A Logger", LoggingContext.getRegistry());

		Matcher<LogEntry> logEntryMatcher = new LogEntryMessageMatcher(new StringMessage("some message"));
		RecordingLoggerMatcher matcher = new RecordingLoggerMatcher(logEntryMatcher);

		// When
		boolean matches = matcher.matches(logger);

		// Then
		assertThat(matches, is(false));
	}

	@Test
	public void shouldDescribeMismatchGivenNoMatchingLogEntries() {
		// Given
		Logger logger = new RecordingLogger("A Logger", LoggingContext.getRegistry());
		String differentMessage = "some different message";
		logger.info(differentMessage);

		String message = "some message";
		LogEntryMessageMatcher logEntryMessageMatcher = new LogEntryMessageMatcher(new StringMessage(message));
		RecordingLoggerMatcher matcher = new RecordingLoggerMatcher(logEntryMessageMatcher);

		Description description = new StringDescription();

		// When
		matcher.describeMismatch(logger, description);

		// Then
		assertThat(description, describes("There are LogEntries, but no LogEntry with message \"some message\" was logged to \"A Logger\""));
	}

	@Test
	public void shouldDescribeMismatchGivenNoLogEntries() {
		// Given
		Logger logger = new RecordingLogger("A Logger", LoggingContext.getRegistry());

		String message = "some message";
		LogEntryMessageMatcher logEntryMessageMatcher = new LogEntryMessageMatcher(new StringMessage(message));
		RecordingLoggerMatcher matcher = new RecordingLoggerMatcher(logEntryMessageMatcher);

		Description description = new StringDescription();

		// When
		matcher.describeMismatch(logger, description);

		// Then
		assertThat(description, describes("No LogEntries were logged to \"A Logger\"\nWanted LogEntry with message \"some message\""));
	}

	@Test
	public void shouldNotMatchGivenIsNotARecordingLogger() {
		// Given
		Logger logger = mock(Logger.class, "Not a RecordingLogger");
		RecordingLoggerMatcher matcher = new RecordingLoggerMatcher(aLogEntryMatcher());

		// When
		boolean matches = matcher.matches(logger);

		// Then
		assertThat(matches, is(false));
	}

	@Test
	public void shouldDescribeMismatchGivenIsNotARecordingLogger() {
		// Given
		RecordingLoggerMatcher matcher = new RecordingLoggerMatcher(aLogEntryMatcher());

		Logger logger = mock(Logger.class, "Not a RecordingLogger");
		Description description = new StringDescription();

		// When
		matcher.describeMismatch(logger, description);

		// Then
		assertThat(description, describes("The logger is not a RecordingLogger, are you using the matcher correctly?"));
	}

	@Test
	@DisableLogging
	public void shouldDescribeMismatchGivenIsNotARecordingLoggerAndLoggingIsNotEnabled() {
		// Given
		RecordingLoggerMatcher matcher = new RecordingLoggerMatcher(aLogEntryMatcher());

		Logger logger = mock(Logger.class, "Not a RecordingLogger");
		Description description = new StringDescription();

		// When
		matcher.describeMismatch(logger, description);

		// Then
		assertThat(description, describes("The logger is not a RecordingLogger, are you using the matcher correctly?" +
				"\nLogging is not enabled, have you used the EnableLogging @Rule?"));
	}

	@Test
	public void shouldDescribe() {
		// Given
		Matcher<LogEntry> logEntryMatcher = logEntryMatcherDescribedAs("something");

		RecordingLoggerMatcher matcher = new RecordingLoggerMatcher(logEntryMatcher);

		Description description = new StringDescription();

		// When
		matcher.describeTo(description);

		// Then
		assertThat(description, describes("Logged something"));
	}

	@SuppressWarnings("unchecked")
	private static Matcher<LogEntry> logEntryMatcherDescribedAs(final String text) {
		Matcher<LogEntry> logEntryMatcher = mock(Matcher.class);
		willAnswer(new Answer<Void>() {

			public Void answer(InvocationOnMock invocation) throws Throwable {
				Description description = (Description) invocation.getArguments()[0];
				description.appendText(text);
				return null;
			}
		}).given(logEntryMatcher).describeTo(any(Description.class));
		return logEntryMatcher;
	}

	private static Matcher<LogEntry> aLogEntryMatcher() {
		return mock(LogEntryMatcher.class);
	}

}
