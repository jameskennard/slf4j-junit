package uk.co.webamoeba.slf4j.junit.event;

import static uk.co.webamoeba.slf4j.junit.event.Level.INFO;

import java.util.Collections;

import static org.junit.Assert.assertNotNull;
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
	public void shouldGetSingleton() {
		assertNotNull(LogEventRegistry.getSingleton());
	}
	
	@Test
	public void shouldGetSingletonGivenCalledTwice() {
		assertThat(LogEventRegistry.getSingleton(), is(sameInstance(LogEventRegistry.getSingleton())));
	}
	
	@Test
	public void shouldGetRegister() {
		// Given
		LogEventRegistry registry = LogEventRegistry.getSingleton();
		String name = "Some Register";
		
		// When
		LogEventRegister register = registry.getRegister(name);
		
		// Then
		assertThat(register, is(notNullValue()));
	}
	
	@Test
	public void shouldGetRegisterGivenSameName() {
		// Given
		LogEventRegistry registry = LogEventRegistry.getSingleton();
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
		LogEventRegistry registry = LogEventRegistry.getSingleton();
		String name = "Some Register";
		String differentName = "Some Different Register";
		LogEventRegister expectedRegister = registry.getRegister(name);
		
		// When
		LogEventRegister register = registry.getRegister(differentName);
		
		// Then
		assertThat(register, is(not(sameInstance(expectedRegister))));
	}
	
	@Test
	public void shouldClearAll() {
		// Given
		LogEventRegistry registry = LogEventRegistry.getSingleton();
		LogEventRegister register = registry.getRegister("Some Register");
		register.register(new LogEvent(INFO, "Some Log Event"));
		
		// When
		registry.clearAll();
		
		// Then
		assertThat(register.getLogEvents(), is(Collections.<LogEvent> emptyList()));
	}
}
