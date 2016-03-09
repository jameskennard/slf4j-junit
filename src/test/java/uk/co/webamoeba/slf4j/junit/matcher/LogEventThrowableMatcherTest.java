package uk.co.webamoeba.slf4j.junit.matcher;

import org.hamcrest.Description;
import org.hamcrest.StringDescription;
import org.junit.Test;
import uk.co.webamoeba.slf4j.junit.event.LogEvent;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static uk.co.webamoeba.slf4j.junit.testsupport.DescriptionMatcher.describes;
import static uk.co.webamoeba.slf4j.junit.testsupport.LogEventTestFactory.logEventWithThrowable;

/**
 * Test for {@link LogEventThrowableMatcher}
 * 
 * @author James Kennard
 */
public class LogEventThrowableMatcherTest {

	@Test
	public void shouldMatch() {
		// Given
		Throwable throwable = aThrowable();
		LogEvent logEvent = logEventWithThrowable(throwable);

		LogEventThrowableMatcher matcher = new LogEventThrowableMatcher(throwable);

		// When
		boolean matches = matcher.matches(logEvent);

		// Then
		assertThat(matches, is(true));
	}


	@Test
	public void shouldNotMatch() {
		// Given
		Throwable throwable = aThrowable();
		LogEventThrowableMatcher matcher = new LogEventThrowableMatcher(throwable);

		Throwable differentThrowable = aDifferentThrowable(throwable);
		LogEvent logEvent = logEventWithThrowable(differentThrowable);

		// When
		boolean matches = matcher.matches(logEvent);

		// Then
		assertThat(matches, is(false));
	}

	@Test
	public void shouldDescribeMismatch() {
		// Given
		Throwable throwable = aThrowable();
		LogEventThrowableMatcher matcher = new LogEventThrowableMatcher(throwable);

		Throwable differentThrowable = aDifferentThrowable(throwable);
		LogEvent logEvent = logEventWithThrowable(differentThrowable);

		Description mismatchDescription = new StringDescription();

		// When
		matcher.describeMismatch(logEvent, mismatchDescription);

		// Then
		assertThat(mismatchDescription, describes("wanted <" + throwable + "> but was <" + differentThrowable + ">"));
	}
	
	@Test
	public void shouldDescribe() {
		// Given
		Throwable throwable = aThrowable();
		LogEventThrowableMatcher matcher = new LogEventThrowableMatcher(throwable);
		Description description = new StringDescription();

		// When
		matcher.describeTo(description);

		// Then
		assertThat(description, describes("LogEvent with Throwable <" + throwable + ">"));
	}

	private static Throwable aThrowable() {
		return new Throwable("A Throwable");
	}
	
	private static Throwable aDifferentThrowable(Throwable throwable) {
		return new Throwable("Different to: " + throwable.getMessage());
	}
}
