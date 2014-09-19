package uk.co.webamoeba.slf4j.junit.event;

import static org.hamcrest.CoreMatchers.not;

import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * @author James Kennard
 */
public class LogEventRegistryTest {

	@Test
	public void shouldInstantiate() {
		new LogEventRegistry();
	}
	
	@Test
	public void shouldGetRegister() {
		// Given
		LogEventRegistry registry = new LogEventRegistry();
		String name = "Some Register";
		
		// When
		LogEventRegister register = registry.getRegister(name);
		
		// Then
		assertThat(register, is(notNullValue()));
	}
	
	@Test
	public void shouldGetRegisterGivenSameName() {
		// Given
		LogEventRegistry registry = new LogEventRegistry();
		String name = "Some Register";
		LogEventRegister expectedRegister = registry.getRegister(name);
		
		// When
		LogEventRegister register = registry.getRegister(name);
		
		// Then
		assertThat(register, is(sameInstance(expectedRegister)));
	}
	
	@Test
	public void shouldGetRegisterGivenDifferentName() {
		// Given
		LogEventRegistry registry = new LogEventRegistry();
		String name = "Some Register";
		String differentName = "Some Different Register";
		LogEventRegister expectedRegister = registry.getRegister(name);
		
		// When
		LogEventRegister register = registry.getRegister(differentName);
		
		// Then
		assertThat(register, is(not(sameInstance(expectedRegister))));
	}
	
}
