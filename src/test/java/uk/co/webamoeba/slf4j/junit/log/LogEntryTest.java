package uk.co.webamoeba.slf4j.junit.log;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static uk.co.webamoeba.slf4j.junit.log.Level.INFO;
import static uk.co.webamoeba.slf4j.junit.log.Level.WARN;

import org.hamcrest.Description;
import org.hamcrest.SelfDescribing;
import org.hamcrest.StringDescription;
import org.junit.Test;
import org.slf4j.Marker;
import org.slf4j.helpers.BasicMarkerFactory;
import uk.co.webamoeba.slf4j.junit.log.Level;
import uk.co.webamoeba.slf4j.junit.log.LogEntry;
import uk.co.webamoeba.slf4j.junit.log.LogEntry.FormattedMessage;
import uk.co.webamoeba.slf4j.junit.log.LogEntry.StringMessage;

/**
 * Test for {@link LogEntry}
 * 
 * @author James Kennard
 */
public class LogEntryTest {

	@Test
	public void shouldGetMessageGivenStringMessage() {
		// Given
		Level level = validLevel();
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
		Level level = validLevel();
		String format = "some {}";
		Object[] arguments = new Object[] {"message"};
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
		FormattedMessage formattedMessage = new LogEntry.FormattedMessage("{}", new Object[] {messageAsString});
		
		// When
		boolean equals = stringMessage.equals(formattedMessage);
		
		// Then
		assertThat(equals, is(true));
		assertThat(stringMessage.hashCode(), is(formattedMessage.hashCode()));
	}
	
	@Test
	public void shouldEqualGivenLogEntryMessageAndMessageIsNotEqual() {
		// Given
		FormattedMessage formattedMessage = new LogEntry.FormattedMessage("{}", new Object[] {"Some Message"});
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
		FormattedMessage formattedMessage = new LogEntry.FormattedMessage("{}", new Object[] {"Some Message"});
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
		FormattedMessage formattedMessage = new LogEntry.FormattedMessage("{}", new Object[] {"Some Message"});
		Object object = null;
		
		// When
		boolean equals = formattedMessage.equals(object);
		
		// Then
		assertThat(equals, is(false));
	}
	
	@Test
	public void shouldToStringGivenFormattedMessage() {
		// Given
		FormattedMessage formattedMessage = new LogEntry.FormattedMessage("{}", new Object[] {"Some Message"});
		
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
	
	@Test
	public void shouldBeSelfDescribing() {
		assertThat(new LogEntry(validLevel(), "Message"), is(instanceOf(SelfDescribing.class)));
	}
	
	@Test
	public void shouldDescribeGivenMessage() {
		// Given
		Level level = validLevel();
		LogEntry logEntry = new LogEntry(level, "Some Message");
		Description description = new StringDescription();
		
		// When
		logEntry.describeTo(description);
		
		// Then
		assertThat(description.toString(), is("info(\"Some Message\")"));
	}
	
	@Test
	public void shouldDescribeGivenMarkerAndMessage() {
		// Given
		Level level = INFO;
		Marker marker = new BasicMarkerFactory().getMarker("Some Marker");
		LogEntry logEntry = new LogEntry(level, marker, "Some Message");
		Description description = new StringDescription();
		
		// When
		logEntry.describeTo(description);
		
		// Then
		assertThat(description.toString(), is("info(<Some Marker>, \"Some Message\")"));
	}
	
	@Test
	public void shouldDescribeGivenMessageAndThrowable() {
		// Given
		Level level = WARN;
		String message = "Some Message";
		Throwable throwable = new Throwable();
		LogEntry logEntry = new LogEntry(level, message, throwable);
		Description description = new StringDescription();
		
		// When
		logEntry.describeTo(description);
		
		// Then
		assertThat(description.toString(), is("warn(\"Some Message\", <" + throwable + ">)"));
	}

	private Level validLevel() {
		return INFO;
	}
}
