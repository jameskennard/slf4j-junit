package uk.co.webamoeba.slf4j.junit.matcher;

import org.hamcrest.Description;
import org.hamcrest.StringDescription;
import org.junit.Test;
import uk.co.webamoeba.slf4j.junit.log.LogEntry;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static uk.co.webamoeba.slf4j.junit.testsupport.DescriptionMatcher.describes;
import static uk.co.webamoeba.slf4j.junit.testsupport.LogEntryTestFactory.logEntryWithThrowable;

/**
 * Test for {@link LogEntryThrowableMatcher}
 * 
 * @author James Kennard
 */
public class LogEntryThrowableMatcherTest extends LogEntryMatcherTest<LogEntryThrowableMatcher> {

	@Test
	public void shouldMatch() {
		// Given
		Throwable throwable = aThrowable();
		LogEntry logEntry = logEntryWithThrowable(throwable);

		LogEntryThrowableMatcher matcher = new LogEntryThrowableMatcher(throwable);

		// When
		boolean matches = matcher.matches(logEntry);

		// Then
		assertThat(matches, is(true));
	}

	@Test
	public void shouldNotMatch() {
		// Given
		Throwable throwable = aThrowable();
		LogEntryThrowableMatcher matcher = new LogEntryThrowableMatcher(throwable);

		Throwable differentThrowable = aDifferentThrowable(throwable);
		LogEntry logEntry = logEntryWithThrowable(differentThrowable);

		// When
		boolean matches = matcher.matches(logEntry);

		// Then
		assertThat(matches, is(false));
	}

	@Test
	public void shouldDescribeMismatch() {
		// Given
		Throwable throwable = aThrowable();
		LogEntryThrowableMatcher matcher = new LogEntryThrowableMatcher(throwable);

		Throwable differentThrowable = aDifferentThrowable(throwable);
		LogEntry logEntry = logEntryWithThrowable(differentThrowable);

		Description mismatchDescription = new StringDescription();

		// When
		matcher.describeMismatch(logEntry, mismatchDescription);

		// Then
		assertThat(mismatchDescription, describes("wanted <" + throwable + "> but was <" + differentThrowable + ">"));
	}

	@Test
	public void shouldDescribe() {
		// Given
		Throwable throwable = aThrowable();
		LogEntryThrowableMatcher matcher = new LogEntryThrowableMatcher(throwable);
		Description description = new StringDescription();

		// When
		matcher.describeTo(description);

		// Then
		assertThat(description, describes("LogEntry with Throwable <" + throwable + ">"));
	}

	@Test
	public void shouldDescribeMatchingLogEntry() {
		// Given
		Throwable throwable = aThrowable();
		LogEntryThrowableMatcher matcher = new LogEntryThrowableMatcher(throwable);
		Description description = new StringDescription();

		// When
		matcher.describeMatchingLogEntry(description);

		// Then
		assertThat(description, describes("with Throwable <" + throwable + ">"));
	}

	private static Throwable aThrowable() {
		return new Throwable("A Throwable");
	}

	private static Throwable aDifferentThrowable(Throwable throwable) {
		return new Throwable("Different to: " + throwable.getMessage());
	}

	@Override
	protected LogEntryThrowableMatcher aMatcher() {
		return new LogEntryThrowableMatcher(aThrowable());
	}
}
