package uk.co.webamoeba.slf4j.junit.matcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import uk.co.webamoeba.slf4j.junit.ClearLogsTestRule;
import uk.co.webamoeba.slf4j.junit.log.LogEntry;
import uk.co.webamoeba.slf4j.junit.log.LogEntry.StringMessage;
import uk.co.webamoeba.slf4j.junit.logger.RecordingLogger;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static uk.co.webamoeba.slf4j.junit.testsupport.DescriptionMatcher.describes;

/**
 * Test for {@link RecordingLoggerMatcher}
 * 
 * @author James Kennard
 */
public class RecordingLoggerMatcherTest {
	
	@Rule
	public ClearLogsTestRule rule = new ClearLogsTestRule();

	@Test
	public void shouldMatch() {
		// Given
		Logger logger = new RecordingLogger("A Logger");
		String message = "some message";
		logger.info(message);
		LogEntryMessageMatcher logEntryMessageMatcher = new LogEntryMessageMatcher(new StringMessage(message));
		
		RecordingLoggerMatcher matcher = new RecordingLoggerMatcher(logEntryMessageMatcher);

		// When
		boolean matches = matcher.matches(logger);

		// Then
		assertThat(matches, is(true));
	}
	
	@Test
	public void shouldNotMatch() {
		// Given
		Logger logger = new RecordingLogger("A Logger");
		String message = "some message";
		String differentMessage = "some different message";
		logger.info(message);
		LogEntryMessageMatcher logEntryMessageMatcher = new LogEntryMessageMatcher(new StringMessage(differentMessage));
		
		RecordingLoggerMatcher matcher = new RecordingLoggerMatcher(logEntryMessageMatcher);

		// When
		boolean matches = matcher.matches(logger);

		// Then
		assertThat(matches, is(false));
	}
	
	@Test
	public void shouldDescribeMismatch() {
		// Given
		Logger logger = new RecordingLogger("A Logger");
		String differentMessage = "some different message";
		String message = "some message";
		logger.info(differentMessage);
		LogEntryMessageMatcher logEntryMessageMatcher = new LogEntryMessageMatcher(new StringMessage(message));
		
		RecordingLoggerMatcher matcher = new RecordingLoggerMatcher(logEntryMessageMatcher);

		Description description = new StringDescription();
		
		// When
		matcher.describeMismatch(logger, description);

		// Then
		assertThat(description, describes("No LogEntry with message \"some message\" was logged to \"A Logger\""));
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
	
	private static Matcher<LogEntry> aLogEntryMatcher() {
		return mock(LogEntryMatcher.class);
	}
	
}
