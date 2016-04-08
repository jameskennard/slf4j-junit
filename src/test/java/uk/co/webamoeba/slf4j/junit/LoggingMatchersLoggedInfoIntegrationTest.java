package uk.co.webamoeba.slf4j.junit;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Marker;
import org.slf4j.helpers.BasicMarkerFactory;
import uk.co.webamoeba.slf4j.junit.log.Level;
import uk.co.webamoeba.slf4j.junit.log.LogRegistry;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static uk.co.webamoeba.slf4j.junit.LoggingMatchers.loggedInfo;
import static uk.co.webamoeba.slf4j.junit.LoggingMatchers.logger;

/**
 * @author James Kennard
 */
public class LoggingMatchersLoggedInfoIntegrationTest {

	@Before
	public void clearLogs() {
		LogRegistry.getSingleton().clearAll();
	}

	@Test
	public void shouldMatchGivenMessageLogged() {
		// Given
		String loggerName = "A Logger";
		String message = "Some Message";
		logger(loggerName).info(message);

		// When
		assertThat(logger(loggerName), loggedInfo(message));

		// Then
		// no exception thrown
	}

	@Test
	public void shouldNotMatchGivenLoggedWithDifferentMessage() {
		// Given
		String loggerName = "A Logger";
		String message = "Some Message";
		logger(loggerName).info("Some Different Message");

		try {
			// When
			assertThat(logger(loggerName), loggedInfo(message));

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
		logger(loggerName).error("Some Different Message");

		try {
			// When
			assertThat(logger(loggerName), loggedInfo(message));

			// Then
			shouldHaveAsserted();
		} catch (AssertionError e) {
			assertThat(e.getMessage(), containsString(Level.INFO.name()));
		}
	}

	@Test
	public void shouldNotMatchGivenMessageButNothingLogged() {
		// Given
		String loggerName = "A Logger";
		String message = "Some Message";

		try {
			// When
			assertThat(logger(loggerName), loggedInfo(message));

			// Then
			shouldHaveAsserted();
		} catch (AssertionError e) {
		}
	}

	@Test
	public void shouldMatchGivenLoggedWithFormat() {
		// Given
		String loggerName = "A Logger";
		String format = "Format {}";
		Object argument = "Argument";
		logger(loggerName).info(format, argument);

		// When
		assertThat(logger(loggerName), loggedInfo(format, argument));

		// Then
		// no exception thrown
	}

	@Test
	public void shouldNotMatchGivenLoggedWithFormatButDifferentLevel() {
		// Given
		String loggerName = "A Logger";
		String format = "Format {}";
		Object argument = "Argument";
		logger(loggerName).error(format, argument);

		try {
			// When
			assertThat(logger(loggerName), loggedInfo(format, argument));

			// Then
			shouldHaveAsserted();
		} catch (AssertionError e) {
			assertThat(e.getMessage(), containsString(Level.INFO.name()));
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
			assertThat(logger(loggerName), loggedInfo(format, argument));

			// Then
			shouldHaveAsserted();
		} catch (AssertionError e) {
		}
	}

	@Test
	public void shouldMatchGivenLoggedWithThrowable() {
		// Given
		String loggerName = "A Logger";
		String message = "Some Message";
		Throwable throwable = new Throwable();
		logger(loggerName).info(message, throwable);

		// When
		assertThat(logger(loggerName), loggedInfo(message, throwable));

		// Then
		// no exception thrown
	}

	@Test
	public void shouldNotMatchGivenLoggedWithThrowableButDifferentMessage() {
		// Given
		String message = "Some Message";
		Throwable throwable = new Throwable();
		logger("A Logger").info("Some Different Message", throwable);

		try {
			// When
			assertThat(logger("A Logger"), loggedInfo(message, throwable));

			// Then
			shouldHaveAsserted();
		} catch (AssertionError e) {
			assertThat(e.getMessage(), containsString(message));
		}
	}

	@Test
	public void shouldNotMatchGivenLoggedWithDifferentThrowable() {
		// Given
		String message = "Some Message";
		Throwable throwable = new Throwable("some throwable");
		logger("A Logger").info(message, new Throwable("some different throwable"));

		try {
			// When
			assertThat(logger("A Logger"), loggedInfo(message, throwable));

			// Then
			shouldHaveAsserted();
		} catch (AssertionError e) {
			assertThat(e.getMessage(), containsString("some throwable"));
		}
	}

	@Test
	public void shouldNotMatchGivenLoggedWithThrowableButDifferentLevel() {
		// Given
		String message = "Some Message";
		Throwable throwable = new Throwable("some throwable");
		logger("A Logger").error(message, throwable);

		try {
			// When
			assertThat(logger("A Logger"), loggedInfo(message, throwable));

			// Then
			shouldHaveAsserted();
		} catch (AssertionError e) {
			assertThat(e.getMessage(), containsString(Level.INFO.name()));
		}
	}

	@Test
	public void shouldMatchGivenLoggedWithMarker() {
		// Given
		String loggerName = "A Logger";
		String message = "Some Message";
		Marker marker = new BasicMarkerFactory().getMarker("Some Marker");
		logger(loggerName).info(marker, message);

		// When
		assertThat(logger(loggerName), loggedInfo(marker, message));

		// Then
		// no exception thrown
	}

	@Test
	public void shouldNotMatchGivenLoggedWithMarkerButDifferentLevel() {
		// Given
		String message = "Some Message";
		Marker marker = new BasicMarkerFactory().getMarker("Some Marker");
		logger("A Logger").error(marker, message);

		try {
			// When
			assertThat(logger("A Logger"), loggedInfo(marker, message));

			// Then
			shouldHaveAsserted();
		} catch (AssertionError e) {
			assertThat(e.getMessage(), containsString(Level.INFO.name()));
		}
	}

	@Test
	public void shouldNotMatchGivenLoggedWithMarkerButDifferentMessage() {
		// Given
		String loggerName = "A Logger";
		String message = "Some Message";
		Marker marker = new BasicMarkerFactory().getMarker("Some Marker");
		logger(loggerName).info(marker, "Some Different Message");

		try {
			// When
			assertThat(logger(loggerName), loggedInfo(marker, message));

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
		logger(loggerName).info(new BasicMarkerFactory().getMarker("Some Different Marker"), message);

		try {
			// When
			assertThat(logger(loggerName), loggedInfo(marker, message));

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
			assertThat(logger(loggerName), loggedInfo(marker, message));

			// Then
			shouldHaveAsserted();
		} catch (AssertionError e) {
		}
	}

	@Test
	public void shouldMatchGivenLoggedWithMarkerAndThrowable() {
		// Given
		String loggerName = "A Logger";
		String message = "Some Message";
		Marker marker = new BasicMarkerFactory().getMarker("Some Marker");
		Throwable throwable = new Throwable();
		logger(loggerName).info(marker, message, throwable);

		// When
		assertThat(logger(loggerName), loggedInfo(marker, message, throwable));

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
		logger(loggerName).error(marker, message, throwable);

		try {
			// When
			assertThat(logger(loggerName), loggedInfo(marker, message, throwable));

			// Then
			shouldHaveAsserted();
		} catch (AssertionError e) {
			assertThat(e.getMessage(), containsString(Level.INFO.name()));
		}
	}

	@Test
	public void shouldNotMatchGivenLoggedWithMarkerAndThrowableButDifferentMessage() {
		// Given
		String loggerName = "A Logger";
		String message = "Some Message";
		Marker marker = new BasicMarkerFactory().getMarker("Some Marker");
		Throwable throwable = new Throwable();
		logger(loggerName).info(marker, "Some Different Message", throwable);

		try {
			// When
			assertThat(logger(loggerName), loggedInfo(marker, message, throwable));

			// Then
			shouldHaveAsserted();
		} catch (AssertionError e) {
			assertThat(e.getMessage(), containsString(Level.INFO.name()));
		}
	}

	@Test
	public void shouldNotMatchGivenLoggedWithMarkerButDifferentThrowable() {
		// Given
		String loggerName = "A Logger";
		String message = "Some Message";
		Marker marker = new BasicMarkerFactory().getMarker("Some Marker");
		Throwable throwable = new Throwable("some throwable");
		logger(loggerName).info(marker, message, new Throwable("some different throwable"));

		try {
			// When
			assertThat(logger(loggerName), loggedInfo(marker, message, throwable));

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
		logger(loggerName).info(new BasicMarkerFactory().getMarker("Some Different Marker"), message, throwable);

		try {
			// When
			assertThat(logger(loggerName), loggedInfo(marker, message, throwable));

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
			assertThat(logger(loggerName), loggedInfo(marker, message, throwable));

			// Then
			shouldHaveAsserted();
		} catch (AssertionError e) {
		}
	}

	@Test
	public void shouldMatchGivenLoggedWithMarkerAndFormat() {
		// Given
		String loggerName = "A Logger";
		Marker marker = new BasicMarkerFactory().getMarker("Some Marker");
		String format = "Format {}";
		Object argument = "Argument";
		logger(loggerName).info(marker, format, argument);

		// When
		assertThat(logger(loggerName), loggedInfo(marker, format, argument));

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
		logger(loggerName).warn(marker, format, argument);

		try {
			// When
			assertThat(logger(loggerName), loggedInfo(marker, format, argument));

			// Then
			shouldHaveAsserted();
		} catch (AssertionError e) {
			assertThat(e.getMessage(), containsString(Level.INFO.name()));
		}
	}
	
	@Test
	public void shouldNotMatchGivenLoggedWithMarkerAndFormatButDifferentArgument() {
		// Given
		String loggerName = "A Logger";
		Marker marker = new BasicMarkerFactory().getMarker("Some Marker");
		String format = "Format {}";
		Object argument = "Argument";
		logger(loggerName).info(marker, format, "Different Argument");

		try {
			// When
			assertThat(logger(loggerName), loggedInfo(marker, format, argument));

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
		logger(loggerName).info(marker, "Different Format {}", argument);

		try {
			// When
			assertThat(logger(loggerName), loggedInfo(marker, format, argument));

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
		logger(loggerName).info(new BasicMarkerFactory().getMarker("Some Different Marker"), format, argument);

		try {
			// When
			assertThat(logger(loggerName), loggedInfo(marker, format, argument));

			// Then
			shouldHaveAsserted();
		} catch (AssertionError e) {
			assertThat(e.getMessage(), containsString(marker.toString()));
		}
	}

	private static void shouldHaveAsserted() {
		throw new DidNotAssertException();
	}

	public static class DidNotAssertException extends RuntimeException {

		private static final long serialVersionUID = 1L;

	}

}
