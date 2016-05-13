package uk.co.webamoeba.slf4j.junit.specification;

import org.junit.Test;
import uk.co.webamoeba.slf4j.junit.log.LogEntry;
import uk.co.webamoeba.slf4j.junit.log.LogEntry.Message;
import uk.co.webamoeba.slf4j.junit.log.LogEntry.StringMessage;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static uk.co.webamoeba.slf4j.junit.testsupport.LogEntryTestFactory.logEntryWithMessage;

/**
 * Test for {@link MarkerLogEntrySpecification}
 * 
 * @author James Kennard
 */
public class MessageLogEntrySpecificationTest {

	@Test
	public void shouldSatisfy() {
		// Given
		Message message = aMessage();
		LogEntry logEntry = logEntryWithMessage(message);

		MessageLogEntrySpecification specification = new MessageLogEntrySpecification(message);

		// When
		boolean isSatisfied = specification.isSatisfiedBy(logEntry);

		// Then
		assertThat(isSatisfied, is(true));
	}

	@Test
	public void shouldNotSatisfy() {
		// Given
		Message message = aMessage();
		LogEntry logEntry = logEntryWithMessage(message);

		Message differentMessage = aDifferentMessage();
		MessageLogEntrySpecification specification = new MessageLogEntrySpecification(differentMessage);

		// When
		boolean isSatisfied = specification.isSatisfiedBy(logEntry);

		// Then
		assertThat(isSatisfied, is(false));
	}

	@Test
	public void shouldDescribeSatisfiedLogEntry() {
		// Given
		String messageText = "Some Message";
		Message message = new StringMessage(messageText);
		MessageLogEntrySpecification specification = new MessageLogEntrySpecification(message);

		// When
		String description = specification.describeSatisfiedLogEntry();

		// Then
		assertThat(description, is("with message <" + messageText + ">"));
	}

	@Test
	public void shouldFailToConstructGivenNull() {
		// Given
		Message message = null;

		try {
			// When
			new MessageLogEntrySpecification(message);

			// Then
			fail("Should throw IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("message must not be null"));
		}
	}

	private static Message aMessage() {
		return new StringMessage("Some Message");
	}

	private static Message aDifferentMessage() {
		return new StringMessage("Some Different Message");
	}

}
