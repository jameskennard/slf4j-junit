package uk.co.webamoeba.slf4j.junit.matcher;

import org.hamcrest.Description;
import org.hamcrest.StringDescription;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static uk.co.webamoeba.slf4j.junit.testsupport.DescriptionMatcher.describes;

/**
 * Test for a {@link LogEntryMatcher}
 * 
 * @author James Kennard
 */
public abstract class LogEntryMatcherTest<T extends LogEntryMatcher> {

	@Test
	public void shouldDescribeMismatchGivenIsNotALogEntry() {
		// Given
		Object item = "not a log entry";
		T matcher = aMatcher();
		Description mismatchDescription = new StringDescription();

		// When
		matcher.describeMismatch(item, mismatchDescription);

		// Then
		assertThat(mismatchDescription, describes("The item is not a LogEntry, are you using the matcher correctly?"));
	}

	protected abstract T aMatcher();

}
