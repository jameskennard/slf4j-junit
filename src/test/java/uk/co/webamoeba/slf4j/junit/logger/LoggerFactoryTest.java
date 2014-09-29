package uk.co.webamoeba.slf4j.junit.logger;

import static org.junit.Assert.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

import uk.co.webamoeba.slf4j.junit.logger.LoggerFactory;

/**
 * @author James Kennard
 */
public class LoggerFactoryTest {

	@Test
	public void shouldInstantiate() {
		new LoggerFactory();
	}

	@Test
	public void shouldImplementILoggerFactory() {
		// Given
		LoggerFactory loggerFactory = new LoggerFactory();

		// When
		boolean isILoggerFactory = ILoggerFactory.class.isInstance(loggerFactory);

		// Then
		assertThat(isILoggerFactory, is(true));
	}

	@Test
	public void shouldFailToGetLoggerGivenNullName() {
		// Given
		LoggerFactory loggerFactory = new LoggerFactory();
		String name = null;

		try {
			// When
			loggerFactory.getLogger(name);

			// Then
			fail("Shold throw IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("name must not be null"));
		}
	}

	@Test
	public void shouldGetLogger() {
		// Given
		LoggerFactory loggerFactory = new LoggerFactory();
		String name = "Some Logger";

		// When
		Logger logger = loggerFactory.getLogger(name);

		// Then
		assertThat(logger.getName(), is(name));
	}

}
