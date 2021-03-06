package uk.co.webamoeba.slf4j.junit.log;

import org.junit.Test;
import uk.co.webamoeba.slf4j.junit.log.LogEntry.FormattedMessage;
import uk.co.webamoeba.slf4j.junit.log.LogEntry.StringMessage;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static uk.co.webamoeba.slf4j.junit.testsupport.LevelTestFactory.aLevel;

/**
 * Test for {@link LogEntry}
 * 
 * @author James Kennard
 */
public class LogEntryTest {

	@Test
	public void shouldGetMessageGivenStringMessage() {
		// Given
		Level level = aLevel();
		String expectedMessage = "some message";
		LogEntry logEntry = new LogEntry(level, expectedMessage);

		// When
		String message = logEntry.getMessageAsString();

		// Then
		assertThat(message, is(expectedMessage));
	}

	@Test
	public void shouldGetMessageGivenFormattedMessage() {
		// Given
		Level level = aLevel();
		String format = "some {}";
		Object[] arguments = new Object[] { "message" };
		String expectedMessage = "some message";
		LogEntry logEntry = new LogEntry(level, format, arguments);

		// When
		String message = logEntry.getMessageAsString();

		// Then
		assertThat(message, is(expectedMessage));
	}

	@Test
	public void shouldEqualGivenLogEntryMessageAndMessageIsEqual() {
		// Given
		String messageAsString = "Some Message";
		StringMessage stringMessage = new LogEntry.StringMessage(messageAsString);
		FormattedMessage formattedMessage = new LogEntry.FormattedMessage("{}", new Object[] { messageAsString });

		// When
		boolean equals = stringMessage.equals(formattedMessage);

		// Then
		assertThat(equals, is(true));
		assertThat(stringMessage.hashCode(), is(formattedMessage.hashCode()));
	}

	@Test
	public void shouldEqualGivenLogEntryMessageAndMessageIsNotEqual() {
		// Given
		FormattedMessage formattedMessage = new LogEntry.FormattedMessage("{}", new Object[] { "Some Message" });
		StringMessage stringMessage = new LogEntry.StringMessage("Some Other Message");

		// When
		boolean equals = stringMessage.equals(formattedMessage);

		// Then
		assertThat(equals, is(false));
		assertThat(stringMessage.hashCode(), is(not(formattedMessage.hashCode())));
	}

	@Test
	public void shouldEqualGivenLogEntryStringMessageAndObjectIsNotMessage() {
		// Given
		StringMessage stringMessage = new LogEntry.StringMessage("Some Other Message");
		Object object = "Not a message";

		// When
		boolean equals = stringMessage.equals(object);

		// Then
		assertThat(equals, is(false));
	}

	@Test
	public void shouldEqualGivenLogEntryFormattedMessageAndObjectIsNotMessage() {
		// Given
		FormattedMessage formattedMessage = new LogEntry.FormattedMessage("{}", new Object[] { "Some Message" });
		Object object = "Not a message";

		// When
		boolean equals = formattedMessage.equals(object);

		// Then
		assertThat(equals, is(false));

	}

	@Test
	public void shouldEqualGivenLogEntryStringMessageAndObjectIsNull() {
		// Given
		StringMessage stringMessage = new LogEntry.StringMessage("Some Other Message");
		Object object = null;

		// When
		boolean equals = stringMessage.equals(object);

		// Then
		assertThat(equals, is(false));
	}

	@Test
	public void shouldEqualGivenLogEntryFormattedMessageAndObjectIsNull() {
		// Given
		FormattedMessage formattedMessage = new LogEntry.FormattedMessage("{}", new Object[] { "Some Message" });
		Object object = null;

		// When
		boolean equals = formattedMessage.equals(object);

		// Then
		assertThat(equals, is(false));
	}

	@Test
	public void shouldToStringGivenFormattedMessage() {
		// Given
		FormattedMessage formattedMessage = new LogEntry.FormattedMessage("{}", new Object[] { "Some Message" });

		// When
		String string = formattedMessage.toString();

		// Then
		assertThat(string, is("Some Message"));
	}

	@Test
	public void shouldToStringGivenStringMessage() {
		// Given
		StringMessage stringMessage = new LogEntry.StringMessage("Some Message");

		// When
		String string = stringMessage.toString();

		// Then
		assertThat(string, is("Some Message"));
	}
}
