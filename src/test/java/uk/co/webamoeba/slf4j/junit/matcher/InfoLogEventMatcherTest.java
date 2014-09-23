package uk.co.webamoeba.slf4j.junit.matcher;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matcher;
import org.junit.Test;

/**
 * @author James Kennard
 */
public class InfoLogEventMatcherTest {

	@Test
	public void shouldInstantiate() {
		new InfoLogEventMatcher();
	}
	
	@Test
	public void shouldImplementMatcher() {
		assertThat(new InfoLogEventMatcher(), is(instanceOf(Matcher.class)));
	}
	
}
