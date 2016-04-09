package uk.co.webamoeba.slf4j.junit;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static uk.co.webamoeba.slf4j.junit.LoggingMatchers.logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;

import uk.co.webamoeba.slf4j.junit.log.Level;
import uk.co.webamoeba.slf4j.junit.log.LogRegistry;

/**
 * @author James Kennard
 */
public abstract class LoggingMatchersLoggedIntegrationTest {

	private final Level level;
	
	private final Level differentLevel;

	public LoggingMatchersLoggedIntegrationTest(Level level) {
		this.level = level;
		Level[] levels = Level.values();
		for (int i = 0; i < levels.length; i++) {
			if (levels[i] != level) {
				this.differentLevel = levels[i];
				return;
			}
		}
		throw new IllegalStateException("Could not determine a different level");
	}

	private Level level() {
		return level;
	}

	@Before
	public void clearLogs() {
		LogRegistry.getSingleton().clearAll();
	}

	@Test
	public void shouldMatchGivenMessageLogged() {
		// Given
		String loggerName = "A Logger";
		String message = "Some Message";
		logAtLevel(message, logger(loggerName));

		// When
		assertThat(logger(loggerName), loggedLevel(message));

		// Then
		// no exception thrown
	}

	@Test
	public void shouldNotMatchGivenLoggedWithDifferentMessage() {
		// Given
		String loggerName = "A Logger";
		String message = "Some Message";
		logAtLevel("Some Different Message", logger(loggerName));

		try {
			// When
			assertThat(logger(loggerName), loggedLevel(message));

			// Then
			fail("Should have thrown " + AssertionError.class);
		} catch (AssertionError e) {
			assertThat(e.getMessage(), containsString(message));
		}
	}

	@Test
	public void shouldNotMatchGivenLoggedMessageWithDifferentLevel() {
		// Given
		String loggerName = "A Logger";
		String message = "Some Message";
		logAtDifferentLevel("Some Different Message", logger(loggerName));

		try {
			// When
			assertThat(logger(loggerName), loggedLevel(message));

			// Then
			shouldHaveAsserted();
		} catch (AssertionError e) {
			assertThat(e.getMessage(), containsLevelName());
		}
	}

	@Test
	public void shouldNotMatchGivenMessageButNothingLogged() {
		// Given
		String loggerName = "A Logger";
		String message = "Some Message";

		try {
			// When
			assertThat(logger(loggerName), loggedLevel(message));

			// Then
			shouldHaveAsserted();
		} catch (AssertionError e) {
		}
	}

	private Matcher<Logger> loggedLevel(String message) {
		try {
			String name = "logged" + level.name().charAt(0) + level.name().toLowerCase().substring(1);
			Method method = LoggingMatchers.class.getMethod(name, String.class);
			return (Matcher<Logger>) method.invoke(null, message);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		throw new IllegalStateException();
	}

	public void logAtLevel(String message, Logger logger) {
		try {
			Method method = logger.getClass().getMethod(level.name().toLowerCase(), String.class);
			method.invoke(logger, message);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	private void logAtDifferentLevel(String message, Logger logger) {
		try {
			Method method = logger.getClass().getMethod(level.name().toLowerCase(), String.class);
			method.invoke(logger, message);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	
	private Matcher<String> containsLevelName() {
		return containsString(level().name());
	}
	
	private static void shouldHaveAsserted() {
		throw new DidNotAssertException();
	}

	public static class DidNotAssertException extends RuntimeException {

		private static final long serialVersionUID = 1L;

	}

}
