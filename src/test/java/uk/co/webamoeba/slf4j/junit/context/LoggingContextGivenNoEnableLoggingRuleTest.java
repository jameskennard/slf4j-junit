package uk.co.webamoeba.slf4j.junit.context;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Test for {@link LoggingContext}
 * 
 * @author James Kennard
 */
public class LoggingContextGivenNoEnableLoggingRuleTest {

	
	@Test
	public void shouldNotGetRegistry() {
		assertNull(LoggingContext.getRegistry());
	}
	
	@Test
	public void shouldDetermineLoggingIsNotEnabled() {
		assertThat(LoggingContext.loggingIsEnabled(), is(false));
	}
}
