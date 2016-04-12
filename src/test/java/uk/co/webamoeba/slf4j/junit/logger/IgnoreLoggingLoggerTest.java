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

}
