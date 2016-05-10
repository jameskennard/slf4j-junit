package uk.co.webamoeba.slf4j.junit;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.helpers.BasicMarkerFactory;
import uk.co.webamoeba.slf4j.junit.log.Level;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static uk.co.webamoeba.slf4j.junit.LoggingMatchers.enableLogging;
import static uk.co.webamoeba.slf4j.junit.LoggingMatchers.logger;

/**
 * @author James Kennard
 */
public abstract class LoggingMatchersLoggedLevelIntegrationTest {

	private Level level;

	public LoggingMatchersLoggedLevelIntegrationTest(Level level) {
		this.level = level;
	}
	
	@Rule
	public EnableLogging enableLogging = enableLogging();

	private Level level() {
		return level;
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

	protected abstract Matcher<Logger> loggedLevel(String message);

	protected abstract void logAtLevel(String message, Logger logger);

	protected abstract void logAtDifferentLevel(String msg, Logger logger);

	@Test
	public void shouldMatchGivenLoggedWithFormat() {
		// Given
		String loggerName = "A Logger";
		String format = "Format {}";
		Object argument = "Argument";
		logAtLevel(format, argument, logger(loggerName));

		// When
		assertThat(logger(loggerName), loggedLevel(format, argument));

		// Then
		// no exception thrown
	}

	@Test
	public void shouldNotMatchGivenLoggedWithFormatButDifferentLevel() {
		// Given
		String loggerName = "A Logger";
		String format = "Format {}";
		Object argument = "Argument";
		logAtDifferentLevel(format, argument, logger(loggerName));

		try {
			// When
			assertThat(logger(loggerName), loggedLevel(format, argument));

			// Then
			shouldHaveAsserted();
		} catch (AssertionError e) {
			assertThat(e.getMessage(), containsLevelName());
		}
	}

	@Test
	public void shouldNotMatchGivenFormatButNothingLogged() {
		// Given
		String loggerName = "A Logger";
		String format = "Format {}";
		Object argument = "Argument";

		try {
			// When
			assertThat(logger(loggerName), loggedLevel(format, argument));

			// Then
			shouldHaveAsserted();
		} catch (AssertionError e) {
		}
	}

	protected abstract Matcher<Logger> loggedLevel(String format, Object... arguments);

	protected abstract void logAtLevel(String format, Object argument, Logger logger);

	protected abstract void logAtDifferentLevel(String format, Object argument, Logger logger);

	@Test
	public void shouldMatchGivenLoggedWithThrowable() {
		// Given
		String loggerName = "A Logger";
		String message = "Some Message";
		Throwable throwable = new Throwable();
		logAtLevel(message, throwable, logger(loggerName));

		// When
		assertThat(logger(loggerName), loggedLevel(message, throwable));

		// Then
		// no exception thrown
	}

	@Test
	public void shouldNotMatchGivenLoggedWithThrowableButDifferentMessage() {
		// Given
		String loggerName = "A Logger";
		String message = "Some Message";
		Throwable throwable = new Throwable();
		logAtLevel("Some Different Message", throwable, logger(loggerName));

		try {
			// When
			assertThat(logger(loggerName), loggedLevel(message, throwable));

			// Then
			shouldHaveAsserted();
		} catch (AssertionError e) {
			assertThat(e.getMessage(), containsString(message));
		}
	}

	@Test
	public void shouldNotMatchGivenLoggedWithDifferentThrowable() {
		// Given
		String loggerName = "A Logger";
		String message = "Some Message";
		Throwable throwable = new Throwable("some throwable");
		logAtLevel(message, new Throwable("some different throwable"), logger(loggerName));

		try {
			// When
			assertThat(logger(loggerName), loggedLevel(message, throwable));

			// Then
			shouldHaveAsserted();
		} catch (AssertionError e) {
			assertThat(e.getMessage(), containsString("some throwable"));
		}
	}

	@Test
	public void shouldNotMatchGivenLoggedWithThrowableButDifferentLevel() {
		// Given
		String loggerName = "A Logger";
		String message = "Some Message";
		Throwable throwable = new Throwable("some throwable");
		logAtDifferentLevel(message, throwable, logger(loggerName));

		try {
			// When
			assertThat(logger(loggerName), loggedLevel(message, throwable));

			// Then
			shouldHaveAsserted();
		} catch (AssertionError e) {
			assertThat(e.getMessage(), containsLevelName());
		}
	}

	protected abstract Matcher<Logger> loggedLevel(String message, Throwable throwable);

	protected abstract void logAtLevel(String message, Throwable throwable, Logger logger);

	protected abstract void logAtDifferentLevel(String message, Throwable throwable, Logger logger);

	@Test
	public void shouldMatchGivenLoggedWithMarker() {
		// Given
		String loggerName = "A Logger";
		Marker marker = new BasicMarkerFactory().getMarker("Some Marker");
		String message = "Some Message";
		logAtLevel(marker, message, logger(loggerName));

		// When
		assertThat(logger(loggerName), loggedLevel(marker, message));

		// Then
		// no exception thrown
	}

	@Test
	public void shouldNotMatchGivenLoggedWithMarkerButDifferentLevel() {
		// Given
		String loggerName = "A Logger";
		Marker marker = new BasicMarkerFactory().getMarker("Some Marker");
		String message = "Some Message";
		logAtDifferentLevel(message, marker, logger(loggerName));

		try {
			// When
			assertThat(logger(loggerName), loggedLevel(marker, message));

			// Then
			shouldHaveAsserted();
		} catch (AssertionError e) {
			assertThat(e.getMessage(), containsLevelName());
		}
	}

	@Test
	public void shouldNotMatchGivenLoggedWithMarkerButDifferentMessage() {
		// Given
		String loggerName = "A Logger";
		Marker marker = new BasicMarkerFactory().getMarker("Some Marker");
		String message = "Some Message";
		logAtLevel(marker, "Some Different Message", logger(loggerName));

		try {
			// When
			assertThat(logger(loggerName), loggedLevel(marker, message));

			// Then
			shouldHaveAsserted();
		} catch (AssertionError e) {
			assertThat(e.getMessage(), containsString(message));
		}
	}

	@Test
	public void shouldNotMatchGivenLoggedWithMarkerButDifferentMarker() {
		// Given
		String loggerName = "A Logger";
		String message = "Some Message";
		Marker marker = new BasicMarkerFactory().getMarker("Some Marker");
		logAtLevel(new BasicMarkerFactory().getMarker("Some Different Marker"), message, logger(loggerName));

		try {
			// When
			assertThat(logger(loggerName), loggedLevel(marker, message));

			// Then
			shouldHaveAsserted();
		} catch (AssertionError e) {
			assertThat(e.getMessage(), containsString(marker.toString()));
		}
	}

	@Test
	public void shouldNotMatchGivenLoggedWithMarkerButNothingLogged() {
		// Given
		String loggerName = "A Logger";
		String message = "Some Message";
		Marker marker = new BasicMarkerFactory().getMarker("Some Marker");

		try {
			// When
			assertThat(logger(loggerName), loggedLevel(marker, message));

			// Then
			shouldHaveAsserted();
		} catch (AssertionError e) {
		}
	}

	protected abstract Matcher<Logger> loggedLevel(Marker marker, String message);

	protected abstract void logAtLevel(Marker marker, String message, Logger logger);

	protected abstract void logAtDifferentLevel(String message, Marker marker, Logger logger);

	@Test
	public void shouldMatchGivenLoggedWithMarkerAndThrowable() {
		// Given
		String loggerName = "A Logger";
		String message = "Some Message";
		Marker marker = new BasicMarkerFactory().getMarker("Some Marker");
		Throwable throwable = new Throwable();
		logAtLevel(marker, message, throwable, logger(loggerName));

		// When
		assertThat(logger(loggerName), loggedLevel(marker, message, throwable));

		// Then
		// no exception thrown
	}

	@Test
	public void shouldNotMatchGivenLoggedWithMarkerAndThrowableButDifferentLevel() {
		// Given
		String loggerName = "A Logger";
		String message = "Some Message";
		Marker marker = new BasicMarkerFactory().getMarker("Some Marker");
		Throwable throwable = new Throwable();
		logAtDifferentLevel(marker, message, throwable, logger(loggerName));

		try {
			// When
			assertThat(logger(loggerName), loggedLevel(marker, message, throwable));

			// Then
			shouldHaveAsserted();
		} catch (AssertionError e) {
			assertThat(e.getMessage(), containsLevelName());
		}
	}

	@Test
	public void shouldNotMatchGivenLoggedWithMarkerAndThrowableButDifferentMessage() {
		// Given
		String loggerName = "A Logger";
		String message = "Some Message";
		Marker marker = new BasicMarkerFactory().getMarker("Some Marker");
		Throwable throwable = new Throwable();
		logAtLevel(marker, "Some Different Message", throwable, logger(loggerName));

		try {
			// When
			assertThat(logger(loggerName), loggedLevel(marker, message, throwable));

			// Then
			shouldHaveAsserted();
		} catch (AssertionError e) {
			assertThat(e.getMessage(), containsLevelName());
		}
	}

	@Test
	public void shouldNotMatchGivenLoggedWithMarkerButDifferentThrowable() {
		// Given
		String loggerName = "A Logger";
		String message = "Some Message";
		Marker marker = new BasicMarkerFactory().getMarker("Some Marker");
		Throwable throwable = new Throwable("some throwable");
		logAtLevel(marker, message, new Throwable("some different throwable"), logger(loggerName));

		try {
			// When
			assertThat(logger(loggerName), loggedLevel(marker, message, throwable));

			// Then
			shouldHaveAsserted();
		} catch (AssertionError e) {
			assertThat(e.getMessage(), containsString(throwable.toString()));
		}
	}

	@Test
	public void shouldNotMatchGivenLoggedWithThrowableButDifferentMarker() {
		// Given
		String loggerName = "A Logger";
		String message = "Some Message";
		Marker marker = new BasicMarkerFactory().getMarker("Some Marker");
		Throwable throwable = new Throwable("some throwable");
		logAtLevel(new BasicMarkerFactory().getMarker("Some Different Marker"), message, throwable, logger(loggerName));

		try {
			// When
			assertThat(logger(loggerName), loggedLevel(marker, message, throwable));

			// Then
			shouldHaveAsserted();
		} catch (AssertionError e) {
			assertThat(e.getMessage(), containsString(marker.toString()));
		}
	}

	@Test
	public void shouldNotMatchGivenLoggedWithMarkerAndThrowableButNothingLogged() {
		// Given
		String loggerName = "A Logger";
		String message = "Some Message";
		Marker marker = new BasicMarkerFactory().getMarker("Some Marker");
		Throwable throwable = new Throwable("some throwable");

		try {
			// When
			assertThat(logger(loggerName), loggedLevel(marker, message, throwable));

			// Then
			shouldHaveAsserted();
		} catch (AssertionError e) {
		}
	}

	protected abstract Matcher<Logger> loggedLevel(Marker marker, String message, Throwable throwable);

	protected abstract void logAtLevel(Marker marker, String message, Throwable throwable, Logger logger);
	
	protected abstract void logAtDifferentLevel(Marker marker, String message, Throwable throwable, Logger logger);

	@Test
	public void shouldMatchGivenLoggedWithMarkerAndFormat() {
		// Given
		String loggerName = "A Logger";
		Marker marker = new BasicMarkerFactory().getMarker("Some Marker");
		String format = "Format {}";
		Object argument = "Argument";
		logAtLevel(marker, format, argument, logger(loggerName));

		// When
		assertThat(logger(loggerName), loggedLevel(marker, format, argument));

		// Then
		// no exception thrown
	}

	@Test
	public void shouldNotMatchGivenLoggedWithMarkerAndFormatButDifferentLevel() {
		// Given
		String loggerName = "A Logger";
		Marker marker = new BasicMarkerFactory().getMarker("Some Marker");
		String format = "Format {}";
		Object argument = "Argument";
		logAtDifferentLevel(marker, format, argument, logger(loggerName));

		try {
			// When
			assertThat(logger(loggerName), loggedLevel(marker, format, argument));

			// Then
			shouldHaveAsserted();
		} catch (AssertionError e) {
			assertThat(e.getMessage(), containsLevelName());
		}
	}

	@Test
	public void shouldNotMatchGivenLoggedWithMarkerAndFormatButDifferentArgument() {
		// Given
		String loggerName = "A Logger";
		Marker marker = new BasicMarkerFactory().getMarker("Some Marker");
		String format = "Format {}";
		Object argument = "Argument";
		logAtLevel(marker, format, "Different Argument", logger(loggerName));

		try {
			// When
			assertThat(logger(loggerName), loggedLevel(marker, format, argument));

			// Then
			shouldHaveAsserted();
		} catch (AssertionError e) {
			assertThat(e.getMessage(), containsString(argument.toString()));
		}
	}

	@Test
	public void shouldNotMatchGivenLoggedWithMarkerButDifferentFormat() {
		// Given
		String loggerName = "A Logger";
		Marker marker = new BasicMarkerFactory().getMarker("Some Marker");
		String format = "Format {}";
		Object argument = "Argument";
		logAtLevel(marker, "Different Format {}", argument, logger(loggerName));

		try {
			// When
			assertThat(logger(loggerName), loggedLevel(marker, format, argument));

			// Then
			shouldHaveAsserted();
		} catch (AssertionError e) {
			assertThat(e.getMessage(), containsString("Format"));
		}
	}

	@Test
	public void shouldNotMatchGivenLoggedWithFormatButDifferentMarker() {
		// Given
		String loggerName = "A Logger";
		Marker marker = new BasicMarkerFactory().getMarker("Some Marker");
		String format = "Format {}";
		Object argument = "Argument";
		logAtLevel(new BasicMarkerFactory().getMarker("Some Different Marker"), format, argument, logger(loggerName));

		try {
			// When
			assertThat(logger(loggerName), loggedLevel(marker, format, argument));

			// Then
			shouldHaveAsserted();
		} catch (AssertionError e) {
			assertThat(e.getMessage(), containsString(marker.toString()));
		}
	}

	protected abstract Matcher<Logger> loggedLevel(Marker marker, String format, Object... arguments);

	protected abstract void logAtLevel(Marker marker, String format, Object argument, Logger logger);

	protected abstract void logAtDifferentLevel(Marker marker, String format, Object argument, Logger logger);

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
