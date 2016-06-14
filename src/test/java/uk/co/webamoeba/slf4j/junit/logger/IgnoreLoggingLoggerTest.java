package uk.co.webamoeba.slf4j.junit.logger;

import org.junit.Test;
import org.slf4j.Marker;
import org.slf4j.helpers.BasicMarkerFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author James Kennard
 */
public class IgnoreLoggingLoggerTest {

	@Test
	public void shouldGetName() {
		// Given
		String expectedName = "a logger";
		IgnoreLoggingLogger logger = new IgnoreLoggingLogger(expectedName);

		// When
		String name = logger.getName();

		// Then
		assertThat(name, is(expectedName));
	}
	
	@Test
	public void shouldDetermineIfErrorEnabled() {
		// Given
		IgnoreLoggingLogger logger = new IgnoreLoggingLogger("a logger");

		// When
		boolean enabled = logger.isErrorEnabled();

		// Then
		assertThat(enabled, is(false));
	}

	@Test
	public void shouldDetermineIfErrorEnabledGivenMarker() {
		// Given
		IgnoreLoggingLogger logger = new IgnoreLoggingLogger("a logger");
		Marker marker = new BasicMarkerFactory().getMarker("some marker");

		// When
		boolean enabled = logger.isErrorEnabled(marker);

		// Then
		assertThat(enabled, is(false));
	}

	@Test
	public void shouldDetermineIfWarnEnabled() {
		// Given
		IgnoreLoggingLogger logger = new IgnoreLoggingLogger("a logger");

		// When
		boolean enabled = logger.isWarnEnabled();

		// Then
		assertThat(enabled, is(false));
	}

	@Test
	public void shouldDetermineIfWarnEnabledGivenMarker() {
		// Given
		IgnoreLoggingLogger logger = new IgnoreLoggingLogger("a logger");
		Marker marker = new BasicMarkerFactory().getMarker("some marker");

		// When
		boolean enabled = logger.isWarnEnabled(marker);

		// Then
		assertThat(enabled, is(false));
	}

	@Test
	public void shouldDetermineIfInfoEnabled() {
		// Given
		IgnoreLoggingLogger logger = new IgnoreLoggingLogger("a logger");

		// When
		boolean enabled = logger.isInfoEnabled();

		// Then
		assertThat(enabled, is(false));
	}

	@Test
	public void shouldDetermineIfInfoEnabledGivenMarker() {
		// Given
		IgnoreLoggingLogger logger = new IgnoreLoggingLogger("a logger");
		Marker marker = new BasicMarkerFactory().getMarker("some marker");

		// When
		boolean enabled = logger.isInfoEnabled(marker);

		// Then
		assertThat(enabled, is(false));
	}

	@Test
	public void shouldDetermineIfDebugEnabled() {
		// Given
		IgnoreLoggingLogger logger = new IgnoreLoggingLogger("a logger");

		// When
		boolean enabled = logger.isDebugEnabled();

		// Then
		assertThat(enabled, is(false));
	}

	@Test
	public void shouldDetermineIfDebugEnabledGivenMarker() {
		// Given
		IgnoreLoggingLogger logger = new IgnoreLoggingLogger("a logger");
		Marker marker = new BasicMarkerFactory().getMarker("some marker");

		// When
		boolean enabled = logger.isDebugEnabled(marker);

		// Then
		assertThat(enabled, is(false));
	}

	@Test
	public void shouldDetermineIfTraceEnabled() {
		// Given
		IgnoreLoggingLogger logger = new IgnoreLoggingLogger("a logger");

		// When
		boolean enabled = logger.isTraceEnabled();

		// Then
		assertThat(enabled, is(false));
	}

	@Test
	public void shouldDetermineIfTraceEnabledGivenMarker() {
		// Given
		IgnoreLoggingLogger logger = new IgnoreLoggingLogger("a logger");
		Marker marker = new BasicMarkerFactory().getMarker("some marker");

		// When
		boolean enabled = logger.isTraceEnabled(marker);

		// Then
		assertThat(enabled, is(false));
	}

	/**
	 * Ensure all of the methods we expect to do nothing, don't explode unexpectedly. This isn'ty really necessary, but gives a little more confidence.
	 */
	@Test
	public void shouldDoNothing() {
		IgnoreLoggingLogger logger = new IgnoreLoggingLogger("a logger");
		Marker marker = new BasicMarkerFactory().getMarker("some marker");
		
		logger.trace("message");
		logger.trace("message []", "argument");
		logger.trace("message {} {}", "argument", "argument");
		logger.trace("message {} {} {}", "argument", "argument", "argument");
		logger.trace("message", new Throwable());
		logger.trace(marker, "message");
		logger.trace(marker, "message []", "argument");
		logger.trace(marker, "message {} {}", "argument", "argument");
		logger.trace(marker, "message {} {}", "argument", "argument", "argument");
		logger.trace(marker, "message", new Throwable());
		
		logger.debug("message");
		logger.debug("message []", "argument");
		logger.debug("message {} {}", "argument", "argument");
		logger.debug("message {} {} {}", "argument", "argument", "argument");
		logger.debug("message", new Throwable());
		logger.debug(marker, "message");
		logger.debug(marker, "message []", "argument");
		logger.debug(marker, "message {} {}", "argument", "argument");
		logger.debug(marker, "message {} {}", "argument", "argument", "argument");
		logger.debug(marker, "message", new Throwable());
		
		logger.info("message");
		logger.info("message []", "argument");
		logger.info("message {} {}", "argument", "argument");
		logger.info("message {} {} {}", "argument", "argument", "argument");
		logger.info("message", new Throwable());
		logger.info(marker, "message");
		logger.info(marker, "message []", "argument");
		logger.info(marker, "message {} {}", "argument", "argument");
		logger.info(marker, "message {} {}", "argument", "argument", "argument");
		logger.info(marker, "message", new Throwable());
		
		logger.warn("message");
		logger.warn("message []", "argument");
		logger.warn("message {} {}", "argument", "argument");
		logger.warn("message {} {} {}", "argument", "argument", "argument");
		logger.warn("message", new Throwable());
		logger.warn(marker, "message");
		logger.warn(marker, "message []", "argument");
		logger.warn(marker, "message {} {}", "argument", "argument");
		logger.warn(marker, "message {} {}", "argument", "argument", "argument");
		logger.warn(marker, "message", new Throwable());
		
		logger.error("message");
		logger.error("message []", "argument");
		logger.error("message {} {}", "argument", "argument");
		logger.error("message {} {} {}", "argument", "argument", "argument");
		logger.error("message", new Throwable());
		logger.error(marker, "message");
		logger.error(marker, "message []", "argument");
		logger.error(marker, "message {} {}", "argument", "argument");
		logger.error(marker, "message {} {}", "argument", "argument", "argument");
		logger.error(marker, "message", new Throwable());
	}

}
