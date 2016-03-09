package uk.co.webamoeba.slf4j.junit.matcher;

import org.hamcrest.Description;
import org.hamcrest.StringDescription;
import org.junit.Test;
import uk.co.webamoeba.slf4j.junit.event.LogEvent;
import uk.co.webamoeba.slf4j.junit.event.LogEvent.Message;
import uk.co.webamoeba.slf4j.junit.event.LogEvent.StringMessage;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static uk.co.webamoeba.slf4j.junit.testsupport.DescriptionMatcher.describes;
import static uk.co.webamoeba.slf4j.junit.testsupport.LogEventTestFactory.logEventWithMessage;

/**
 * Test for {@link LogEventMessageMatcher}
 * 
 * @author James Kennard
 */
public class LogEventMessageMatcherTest {

	@Test
	public void shouldMatch() {
		// Given
		Message message = aMessage();
		LogEvent logEvent = logEventWithMessage(message);

		LogEventMessageMatcher matcher = new LogEventMessageMatcher(message);

		// When
		boolean matches = matcher.matches(logEvent);

		// Then
		assertThat(matches, is(true));
	}

	@Test
	public void shouldNotMatch() {
		// Given
		Message message = aMessage();
		LogEventMessageMatcher matcher = new LogEventMessageMatcher(message);

		Message differentMessage = aDifferentMessage(message);
		LogEvent logEvent = logEventWithMessage(differentMessage);

		// When
		boolean matches = matcher.matches(logEvent);

		// Then
		assertThat(matches, is(false));
	}

	@Test
	public void shouldDescribeMismatch() {
		// Given
		Message message = aMessage();
		LogEventMessageMatcher matcher = new LogEventMessageMatcher(message);

		Message differentMessage = aDifferentMessage(message);
		LogEvent logEvent = logEventWithMessage(differentMessage);

		Description mismatchDescription = new StringDescription();

		// When
		matcher.describeMismatch(logEvent, mismatchDescription);

		// Then
		assertThat(mismatchDescription, describes("wanted \"" + message.getMessageAsString() + "\" but was \"" + differentMessage.getMessageAsString() + "\""));
	}

	@Test
	public void shouldDescribe() {
		// Given
		Message message = aMessage();
		LogEventMessageMatcher matcher = new LogEventMessageMatcher(message);
		Description description = new StringDescription();

		// When
		matcher.describeTo(description);

		// Then
		assertThat(description, describes("LogEvent with message \"" + message.getMessageAsString() + "\""));
	}

	private static Message aMessage() {
		return new StringMessage("Some Message");
	}

	private Message aDifferentMessage(Message message) {
		return new StringMessage("Different to: " + message.getMessageAsString());
	}
}
