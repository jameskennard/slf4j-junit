package uk.co.webamoeba.slf4j.junit.log;

import org.junit.Rule;
import org.junit.Test;
import uk.co.webamoeba.slf4j.junit.EnableLogging;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static uk.co.webamoeba.slf4j.junit.LoggingMatchers.enableLogging;

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
	public void shouldGetLogGivenDifferentName() {
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

}
