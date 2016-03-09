package uk.co.webamoeba.slf4j.junit.log;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static uk.co.webamoeba.slf4j.junit.log.Level.INFO;

import java.util.Collections;

import org.junit.Test;
import uk.co.webamoeba.slf4j.junit.log.Log;
import uk.co.webamoeba.slf4j.junit.log.LogEntry;

import static java.util.Collections.singletonList;

/**
 * Test for {@link Log}
 * 
 * @author James Kennard
 */
public class LogTest {

	@Test
	public void shouldInstantiate() {
		new Log();
	}

	@Test
	public void shouldRegister() {
		// Given
		Log log = new Log();
		LogEntry logEntry = new LogEntry(INFO, "A Log Entry");

		// When
		log.register(logEntry);

		// Then
		assertThat(log.getEntries(), is(singletonList(logEntry)));
	}

	@Test
	public void shouldFailToRegisterGivenNull() {
		// Given
		Log log = new Log();
		LogEntry logEntry = null;

		try {
			// When
			log.register(logEntry);

			// Then
			fail("Should throw IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("logEntry must not be null"));
		}
	}
	
	@Test
	public void shouldClear() {
		// Given
		Log log = new Log();
		log.register(new LogEntry(INFO, "A Log Entry"));
		
		// When
		log.clear();

		// Then
		assertThat(log.getEntries(), is(Collections.<LogEntry> emptyList()));
	}

}
