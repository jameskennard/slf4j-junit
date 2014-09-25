package uk.co.webamoeba.slf4j.junit.event;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.hamcrest.Description;
import org.hamcrest.SelfDescribing;
import org.hamcrest.StringDescription;
import org.junit.Test;

import uk.co.webamoeba.slf4j.junit.event.LogEvent.FormattedMessage;
import uk.co.webamoeba.slf4j.junit.event.LogEvent.StringMessage;

public class LogEventTest {

	@Test
	public void shouldGetMessageGivenStringMessage() {
		// Given
		String expectedMessage = "some message";
		LogEvent logEvent = new LogEvent(expectedMessage);
		
		// When
		String message = logEvent.getMessageAsString();
		
		// Then
		assertThat(message, is(expectedMessage));
	}
	
	@Test
	public void shouldGetMessageGivenFormattedMessage() {
		// Given
		String format = "some {}";
		Object[] arguments = new Object[] {"message"};
		String expectedMessage = "some message";
		LogEvent logEvent = new LogEvent(format, arguments);
		
		// When
		String message = logEvent.getMessageAsString();
		
		// Then
		assertThat(message, is(expectedMessage));
	}
	
	@Test
	public void shouldEqualGivenLogEventMessageAndMessageIsEqual() {
		// Given
		String messageAsString = "Some Message";
		StringMessage stringMessage = new LogEvent.StringMessage(messageAsString);
		FormattedMessage formattedMessage = new LogEvent.FormattedMessage("{}", new Object[] {messageAsString});
		
		// When
		boolean equals = stringMessage.equals(formattedMessage);
		
		// Then
		assertThat(equals, is(true));
		assertThat(stringMessage.hashCode(), is(formattedMessage.hashCode()));
	}
	
	@Test
	public void shouldEqualGivenLogEventMessageAndMessageIsNotEqual() {
		// Given
		FormattedMessage formattedMessage = new LogEvent.FormattedMessage("{}", new Object[] {"Some Message"});
		StringMessage stringMessage = new LogEvent.StringMessage("Some Other Message");
		
		// When
		boolean equals = stringMessage.equals(formattedMessage);
		
		// Then
		assertThat(equals, is(false));
		assertThat(stringMessage.hashCode(), is(not(formattedMessage.hashCode())));
	}
	
	@Test
	public void shouldEqualGivenLogEventStringMessageAndObjectIsNotMessage() {
		// Given
		StringMessage stringMessage = new LogEvent.StringMessage("Some Other Message");
		Object object = "Not a message";
		
		// When
		boolean equals = stringMessage.equals(object);
		
		// Then
		assertThat(equals, is(false));
	}
	
	@Test
	public void shouldEqualGivenLogEventFormattedMessageAndObjectIsNotMessage() {
		// Given
		FormattedMessage formattedMessage = new LogEvent.FormattedMessage("{}", new Object[] {"Some Message"});
		Object object = "Not a message";
		
		// When
		boolean equals = formattedMessage.equals(object);
		
		// Then
		assertThat(equals, is(false));
		
	}
	
	@Test
	public void shouldEqualGivenLogEventStringMessageAndObjectIsNull() {
		// Given
		StringMessage stringMessage = new LogEvent.StringMessage("Some Other Message");
		Object object = null;
		
		// When
		boolean equals = stringMessage.equals(object);
		
		// Then
		assertThat(equals, is(false));
	}
	
	@Test
	public void shouldEqualGivenLogEventFormattedMessageAndObjectIsNull() {
		// Given
		FormattedMessage formattedMessage = new LogEvent.FormattedMessage("{}", new Object[] {"Some Message"});
		Object object = null;
		
		// When
		boolean equals = formattedMessage.equals(object);
		
		// Then
		assertThat(equals, is(false));
	}
	
	@Test
	public void shouldToStringGivenFormattedMessage() {
		// Given
		FormattedMessage formattedMessage = new LogEvent.FormattedMessage("{}", new Object[] {"Some Message"});
		
		// When
		String string = formattedMessage.toString();
		
		// Then
		assertThat(string, is("Some Message"));
	}
	
	@Test
	public void shouldToStringGivenStringMessage() {
		// Given
		StringMessage stringMessage = new LogEvent.StringMessage("Some Message");
		
		// When
		String string = stringMessage.toString();
		
		// Then
		assertThat(string, is("Some Message"));
	}
	
	@Test
	public void shouldBeSelfDescribing() {
		assertThat(new LogEvent("Message"), is(instanceOf(SelfDescribing.class)));
	}
	
	@Test
	public void shouldDescribeGivenMessage() {
		// Given
		LogEvent logEvent = new LogEvent("Some Message");
		Description description = new StringDescription();
		
		// When
		logEvent.describeTo(description);
		
		// Then
		assertThat(description.toString(), is("info(\"Some Message\")"));
	}
	
	@Test
	public void shouldDescribeGivenMessageAndThrowable() {
		// Given
		String message = "Some Message";
		Throwable throwable = new Throwable();
		LogEvent logEvent = new LogEvent(message, throwable);
		Description description = new StringDescription();
		
		// When
		logEvent.describeTo(description);
		
		// Then
		assertThat(description.toString(), is("info(\"Some Message\", <" + throwable + ">)"));
	}
	
}
