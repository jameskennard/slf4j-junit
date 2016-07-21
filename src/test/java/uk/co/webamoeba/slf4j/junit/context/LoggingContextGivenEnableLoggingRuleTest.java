package uk.co.webamoeba.slf4j.junit.context;

import org.junit.Rule;
import org.junit.Test;
import uk.co.webamoeba.slf4j.junit.DisableLogging;
import uk.co.webamoeba.slf4j.junit.EnableLogging;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static uk.co.webamoeba.slf4j.junit.LogVerification.enableLogging;

/**
 * Test for {@link LoggingContext}
 * 
 * @author James Kennard
 */
public class LoggingContextGivenEnableLoggingRuleTest {

	@Rule
	public EnableLogging enableLogging = enableLogging();

	@Test
	public void shouldGetRegistry() {
		assertNotNull(LoggingContext.getRegistry());
	}

	@Test
	public void shouldGetRegistryGivenCalledTwice() {
		assertThat(LoggingContext.getRegistry(), is(sameInstance(LoggingContext.getRegistry())));
	}

	@Test
	@DisableLogging
	public void shouldNotGetRegistryGivenDisableLoggingAnnotation() {
		assertNull(LoggingContext.getRegistry());
	}

	@Test
	public void shouldDetermineLoggingIsEnabled() {
		assertThat(LoggingContext.loggingIsEnabled(), is(true));
	}

	@Test
	@DisableLogging
	public void shouldDetermineLoggingIsNotEnabledGivenDisableLoggingAnnotation() {
		assertThat(LoggingContext.loggingIsEnabled(), is(false));
	}
}
