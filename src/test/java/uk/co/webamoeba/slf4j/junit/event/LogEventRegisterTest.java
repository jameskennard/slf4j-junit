package uk.co.webamoeba.slf4j.junit.event;

import static uk.co.webamoeba.slf4j.junit.event.Level.INFO;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Collections;

import org.junit.Test;

/**
 * @author James Kennard
 */
public class LogEventRegisterTest {

	@Test
	public void shouldInstantiate() {
		new LogEventRegister();
	}

	@Test
	public void shouldRegister() {
		// Given
		LogEventRegister logEventRegister = new LogEventRegister();
		LogEvent logEvent = new LogEvent(INFO, "A Log Event");

		// When
		logEventRegister.register(logEvent);

		// Then
		assertThat(logEventRegister.getLogEvents(), is(Collections.singletonList(logEvent)));
	}

	@Test
	public void shouldFailToRegisterGivenNull() {
		// Given
		LogEventRegister logEventRegister = new LogEventRegister();
		LogEvent logEvent = null;

		try {
			// When
			logEventRegister.register(logEvent);

			// Then
			fail("Should throw IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("logEvent must not be null"));
		}
	}
	
	@Test
	public void shouldClear() {
		// Given
		LogEventRegister logEventRegister = new LogEventRegister();
		logEventRegister.register(new LogEvent(INFO, "A Log Event"));
		
		// When
		logEventRegister.clear();

		// Then
		assertThat(logEventRegister.getLogEvents(), is(Collections.<LogEvent> emptyList()));
	}

}
