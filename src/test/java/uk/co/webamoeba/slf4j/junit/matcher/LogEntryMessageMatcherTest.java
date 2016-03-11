package uk.co.webamoeba.slf4j.junit.matcher;

import org.hamcrest.Description;
import org.hamcrest.StringDescription;
import org.junit.Test;
import uk.co.webamoeba.slf4j.junit.log.LogEntry;
import uk.co.webamoeba.slf4j.junit.log.LogEntry.Message;
import uk.co.webamoeba.slf4j.junit.log.LogEntry.StringMessage;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static uk.co.webamoeba.slf4j.junit.testsupport.DescriptionMatcher.describes;
import static uk.co.webamoeba.slf4j.junit.testsupport.LogEntryTestFactory.logEntryWithMessage;

/**
 * Test for {@link LogEntryMessageMatcher}
 * 
 * @author James Kennard
 */
public class LogEntryMessageMatcherTest extends LogEntryMatcherTest<LogEntryMessageMatcher> {

	@Test
	public void shouldMatch() {
		// Given
		Message message = aMessage();
		LogEntry logEntry = logEntryWithMessage(message);

		LogEntryMessageMatcher matcher = new LogEntryMessageMatcher(message);

		// When
		boolean matches = matcher.matches(logEntry);

		// Then
		assertThat(matches, is(true));
	}

	@Test
	public void shouldNotMatch() {
		// Given
		Message message = aMessage();
		LogEntryMessageMatcher matcher = new LogEntryMessageMatcher(message);

		Message differentMessage = aDifferentMessage(message);
		LogEntry logEntry = logEntryWithMessage(differentMessage);

		// When
		boolean matches = matcher.matches(logEntry);

		// Then
		assertThat(matches, is(false));
	}

	@Test
	public void shouldDescribeMismatch() {
		// Given
		Message message = aMessage();
		LogEntryMessageMatcher matcher = new LogEntryMessageMatcher(message);

		Message differentMessage = aDifferentMessage(message);
		LogEntry logEntry = logEntryWithMessage(differentMessage);

		Description mismatchDescription = new StringDescription();

		// When
		matcher.describeMismatch(logEntry, mismatchDescription);

		// Then
		assertThat(mismatchDescription, describes("wanted \"" + message.getMessageAsString() + "\" but was \"" + differentMessage.getMessageAsString() + "\""));
	}

	@Test
	public void shouldDescribe() {
		// Given
		Message message = aMessage();
		LogEntryMessageMatcher matcher = new LogEntryMessageMatcher(message);
		Description description = new StringDescription();

		// When
		matcher.describeTo(description);

		// Then
		assertThat(description, describes("LogEntry with message \"" + message.getMessageAsString() + "\""));
	}

	@Test
	public void shouldDescribeMatchingLogEntry() {
		// Given
		Message message = aMessage();
		LogEntryMessageMatcher matcher = new LogEntryMessageMatcher(message);
		Description description = new StringDescription();

		// When
		matcher.describeMatchingLogEntry(description);

		// Then
		assertThat(description, describes("with message \"" + message.getMessageAsString() + "\""));
	}

	@Override
	protected LogEntryMessageMatcher aMatcher() {
		return new LogEntryMessageMatcher(aMessage());
	}

	private static Message aMessage() {
		return new StringMessage("Some Message");
	}

	private static Message aDifferentMessage(Message message) {
		return new StringMessage("Different to: " + message.getMessageAsString());
	}
}
