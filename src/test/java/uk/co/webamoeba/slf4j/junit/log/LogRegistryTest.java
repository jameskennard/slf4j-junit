package uk.co.webamoeba.slf4j.junit.log;

import java.util.Collections;

import static org.junit.Assert.assertNotNull;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static uk.co.webamoeba.slf4j.junit.log.Level.INFO;

import org.junit.Test;
import uk.co.webamoeba.slf4j.junit.log.Log;
import uk.co.webamoeba.slf4j.junit.log.LogEntry;
import uk.co.webamoeba.slf4j.junit.log.LogRegistry;

/**
 * Test for {@link LogRegistry}
 * 
 * @author James Kennard
 */
public class LogRegistryTest {

	@Test
	public void shouldGetSingleton() {
		assertNotNull(LogRegistry.getSingleton());
	}

	@Test
	public void shouldGetSingletonGivenCalledTwice() {
		assertThat(LogRegistry.getSingleton(), is(sameInstance(LogRegistry.getSingleton())));
	}

	@Test
	public void shouldGetRegister() {
		// Given
		LogRegistry registry = LogRegistry.getSingleton();
		String name = "Some Register";

		// When
		Log register = registry.getRegister(name);

		// Then
		assertThat(register, is(notNullValue()));
	}

	@Test
	public void shouldGetRegisterGivenSameName() {
		// Given
		LogRegistry registry = LogRegistry.getSingleton();
		String name = "Some Register";
		Log expectedRegister = registry.getRegister(name);

		// When
		Log register = registry.getRegister(name);

		// Then
		assertThat(register, is(sameInstance(expectedRegister)));
	}

	@Test
	public void shouldGetRegisterGivenDifferentName() {
		// Given
		LogRegistry registry = LogRegistry.getSingleton();
		String name = "Some Register";
		String differentName = "Some Different Register";
		Log expectedRegister = registry.getRegister(name);

		// When
		Log register = registry.getRegister(differentName);

		// Then
		assertThat(register, is(not(sameInstance(expectedRegister))));
	}

	@Test
	public void shouldClearAll() {
		// Given
		LogRegistry registry = LogRegistry.getSingleton();
		Log register = registry.getRegister("Some Register");
		register.register(new LogEntry(INFO, "Some Log Entry"));

		// When
		registry.clearAll();

		// Then
		assertThat(register.getEntries(), is(Collections.<LogEntry> emptyList()));
	}
}
