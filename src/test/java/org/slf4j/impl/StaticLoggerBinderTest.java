package org.slf4j.impl;

import org.junit.Test;
import org.slf4j.ILoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

/**
 * @author James Kennard
 */
public class StaticLoggerBinderTest {

	@Test
	public void shouldGetSingleton() {
		assertThat(StaticLoggerBinder.getSingleton(), is(notNullValue()));
	}

	@Test
	public void shouldGetSingletonGivenCalledMoreThanOnce() {
		StaticLoggerBinder singletonInstance = StaticLoggerBinder.getSingleton();
		StaticLoggerBinder anotherSingletonInstance = StaticLoggerBinder.getSingleton();
		assertThat(singletonInstance, is(sameInstance(anotherSingletonInstance)));
	}

	@Test
	public void shouldImplementLoggerFactoryBinder() {
		// Given
		StaticLoggerBinder binder = StaticLoggerBinder.getSingleton();

		// When
		boolean isLoggerFactoryBinder = LoggerFactoryBinder.class.isInstance(binder);

		// Then
		assertThat(isLoggerFactoryBinder, is(true));
	}

	@Test
	public void shouldGetLoggerFactory() {
		// Given
		StaticLoggerBinder binder = StaticLoggerBinder.getSingleton();
		String classString = binder.getLoggerFactoryClassStr();

		// When
		ILoggerFactory factory = binder.getLoggerFactory();

		// Then
		assertThat(factory, is(notNullValue()));
		assertThat(factory.getClass().getName(), is(classString));
	}

	@Test
	public void shouldGetLoggerFactoryClassStr() {
		// Given
		StaticLoggerBinder binder = StaticLoggerBinder.getSingleton();
		String expectedClassString = "uk.co.webamoeba.slf4j.junit.logger.LoggerFactory";

		// When
		String classString = binder.getLoggerFactoryClassStr();

		// Then
		assertThat(classString, is(expectedClassString));
	}

}
