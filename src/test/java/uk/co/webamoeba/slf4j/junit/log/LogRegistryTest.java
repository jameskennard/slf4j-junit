package uk.co.webamoeba.slf4j.junit.log;

import java.util.Collections;

import static org.junit.Assert.assertNotNull;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static uk.co.webamoeba.slf4j.junit.LoggingMatchers.enableLogging;
import static uk.co.webamoeba.slf4j.junit.log.Level.INFO;

import org.junit.Rule;
import org.junit.Test;
import uk.co.webamoeba.slf4j.junit.DisableLogging;
import uk.co.webamoeba.slf4j.junit.EnableLogging;
import uk.co.webamoeba.slf4j.junit.log.Log;
import uk.co.webamoeba.slf4j.junit.log.LogEntry;
import uk.co.webamoeba.slf4j.junit.log.LogRegistry;

/**
 * Test for {@link LogRegistry}
 * 
 * @author James Kennard
 */
public class LogRegistryTest {

	@Rule
	public EnableLogging enableLogging = enableLogging();

	@Test
	public void shouldGetSingleton() {
		assertNotNull(LogRegistry.getSingleton());
	}

	@Test
	public void shouldGetSingletonGivenCalledTwice() {
		assertThat(LogRegistry.getSingleton(), is(sameInstance(LogRegistry.getSingleton())));
	}

	@Test
	public void shouldGetLog() {
		// Given
		LogRegistry registry = LogRegistry.getSingleton();
		String name = "Some Log";

		// When
		Log register = registry.getLog(name);

		// Then
		assertThat(register, is(notNullValue()));
	}

	@Test
	public void shouldGetLogGivenSameName() {
		// Given
		LogRegistry registry = LogRegistry.getSingleton();
		String name = "Some Log";
		Log expectedRegister = registry.getLog(name);

		// When
		Log register = registry.getLog(name);

		// Then
		assertThat(register, is(sameInstance(expectedRegister)));
	}

	@Test
	public void shouldGetlofGivenDifferentName() {
		// Given
		LogRegistry registry = LogRegistry.getSingleton();
		String name = "Some Log";
		String differentName = "Some Different Log";
		Log expectedRegister = registry.getLog(name);

		// When
		Log register = registry.getLog(differentName);

		// Then
		assertThat(register, is(not(sameInstance(expectedRegister))));
	}

	@Test
	public void shouldClearAll() {
		// Given
		LogRegistry registry = LogRegistry.getSingleton();
		Log register = registry.getLog("Some Register");
		register.register(new LogEntry(INFO, "Some Log Entry"));

		// When
		registry.clearAll();

		// Then
		assertThat(register.getEntries(), is(Collections.<LogEntry> emptyList()));
	}

	@Test
	@DisableLogging
	public void shouldFailToGetRegisterGivenLoggingIsNotEnabled() {
		LogRegistry registry = LogRegistry.getSingleton();
		String name = "Some Log";

		try {
			// When
			registry.getLog(name);

			// Then
			fail("Should throw exceptionClass");
		} catch (IllegalStateException e) {
			assertThat(e.getMessage(), is("Logging is not enabled, have you used the EnableLogging @Rule?"));
		}
	}
}
