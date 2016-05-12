package uk.co.webamoeba.slf4j.junit.log;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

/**
 * Test for {@link LogRegistry}
 * 
 * @author James Kennard
 */
public class LogRegistryTest {

	@Test
	public void shouldGetLog() {
		// Given
		LogRegistry registry = new LogRegistry();
		String name = "Some Log";

		// When
		Log register = registry.getLog(name);

		// Then
		assertThat(register, is(notNullValue()));
	}

	@Test
	public void shouldGetLogGivenSameName() {
		// Given
		LogRegistry registry = new LogRegistry();
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
		LogRegistry registry = new LogRegistry();
		String name = "Some Log";
		String differentName = "Some Different Log";
		Log expectedRegister = registry.getLog(name);

		// When
		Log register = registry.getLog(differentName);

		// Then
		assertThat(register, is(not(sameInstance(expectedRegister))));
	}

}
