package uk.co.webamoeba.slf4j.junit.matcher;

import org.hamcrest.Description;
import org.hamcrest.StringDescription;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.willAnswer;
import static org.mockito.Mockito.mock;
import static uk.co.webamoeba.slf4j.junit.testsupport.DescriptionMatcher.describes;

/**
 * Test for {@link ConfigurableLogEntryMatcher}
 * 
 * @author James Kennard
 */
public class ConfigurableLogEntryMatcherTest {

	@Test
	public void shouldDescribe() {
		// Given
		ConfigurableLogEntryMatcher matcher = new ConfigurableLogEntryMatcher();
		Description description = new StringDescription();

		// When
		matcher.describeTo(description);

		// Then
		assertThat(description, describes("LogEntry"));
	}

	@Test
	public void shouldDescribeGivenOneLogEntryMatcher() {
		// Given
		LogEntryMatcher logEntryMatcher = logEntryMatcherThatDescribesMatchingLogEntryAs("something");
		ConfigurableLogEntryMatcher matcher = new ConfigurableLogEntryMatcher(logEntryMatcher);
		Description description = new StringDescription();

		// When
		matcher.describeTo(description);

		// Then
		assertThat(description, describes("LogEntry something"));
	}

	@Test
	public void shouldDescribeGivenMoreThanOneLogEntryMatcher() {
		// Given
		LogEntryMatcher aLogEntryMatcher = logEntryMatcherThatDescribesMatchingLogEntryAs("something");
		LogEntryMatcher anotherLogEntryMatcher = logEntryMatcherThatDescribesMatchingLogEntryAs("something else");

		ConfigurableLogEntryMatcher matcher = new ConfigurableLogEntryMatcher(aLogEntryMatcher, anotherLogEntryMatcher);
		Description description = new StringDescription();

		// When
		matcher.describeTo(description);

		// Then
		assertThat(description, describes("LogEntry something and something else"));
	}

	private static LogEntryMatcher logEntryMatcherThatDescribesMatchingLogEntryAs(final String text) {
		LogEntryMatcher configuredMatcher = mock(LogEntryMatcher.class);

		willAnswer(new Answer<Void>() {

			public Void answer(InvocationOnMock invocation) throws Throwable {
				Description description = (Description) invocation.getArguments()[0];
				description.appendText(text);
				return null;
			}
		}).given(configuredMatcher).describeMatchingLogEntry(Matchers.argThat(any(Description.class)));
		return configuredMatcher;
	}

}
