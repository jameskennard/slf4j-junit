package uk.co.webamoeba.slf4j.junit.event;

import static org.junit.Assert.fail;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Collections;

import org.junit.Test;

/**
 * A register containing a history of {@link LogEvent LogEvents}.
 * 
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
		LogEvent logEvent = new LogEvent();

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

}
