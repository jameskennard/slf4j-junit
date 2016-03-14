package uk.co.webamoeba.slf4j.junit.matcher;

import org.hamcrest.Description;
import org.hamcrest.StringDescription;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import uk.co.webamoeba.slf4j.junit.log.LogEntry;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willAnswer;
import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static uk.co.webamoeba.slf4j.junit.testsupport.DescriptionMatcher.describes;
import static uk.co.webamoeba.slf4j.junit.testsupport.LogEntryTestFactory.aLogEntry;

/**
 * Test for {@link ConfigurableLogEntryMatcher}
 * 
 * @author James Kennard
 */
public class ConfigurableLogEntryMatcherTest {

	@Test
	public void shouldMatch() {
		// Given
		ConfigurableLogEntryMatcher matcher = new ConfigurableLogEntryMatcher();
		LogEntry logEntry = aLogEntry();

		// When
		boolean matches = matcher.matches(logEntry);

		// Then
		assertThat(matches, is(true));
	}

	@Test
	public void shouldNotMatchGivenIsNotALogEntry() {
		// Given
		ConfigurableLogEntryMatcher matcher = new ConfigurableLogEntryMatcher();
		Object item = "not a log entry";

		// When
		boolean matches = matcher.matches(item);

		// Then
		assertThat(matches, is(false));
	}

	@Test
	public void shouldNotMatchGivenLogEntryMatcherDoesNotMatch() {
		// Given
		LogEntry logEntry = aLogEntry();
		LogEntryMatcher logEntryMatcher = aLogEntryMatcherThatOnlyDoesNotMatch(logEntry);
		ConfigurableLogEntryMatcher matcher = new ConfigurableLogEntryMatcher(logEntryMatcher);

		// When
		boolean matches = matcher.matches(logEntry);

		// Then
		assertThat(matches, is(false));
	}

	private static LogEntryMatcher aLogEntryMatcherThatOnlyDoesNotMatch(LogEntry logEntry) {
		LogEntryMatcher matcher = mock(LogEntryMatcher.class);
		given(matcher.matches(argThat(is(not(logEntry))))).willReturn(true);
		given(matcher.matches(logEntry)).willReturn(false);
		return matcher;
	}

	@Test
	public void shouldDescribeMismatchGivenIsNotALogEntry() {
		// Given
		ConfigurableLogEntryMatcher matcher = new ConfigurableLogEntryMatcher();
		Description description = new StringDescription();
		Object item = "not a log entry";

		// When
		matcher.describeMismatch(item, description);

		// Then
		assertThat(description, describes("The item is not a LogEntry, are you using the matcher correctly?"));
	}
	
	@Test
	public void shouldDescribeMismatchGivenLogEntryMatcherDoesNotMatch() {
		// Given
		LogEntry logEntry = aLogEntry();
		LogEntryMatcher logEntryMatcher = aLogEntryMatcherThatOnlyDoesNotMatch(logEntry);
		final String expectedMismatchDescription = "wanted <x> but was <y>";
		givenMismatchForLogEntryDescribedAs(logEntryMatcher, logEntry, expectedMismatchDescription);
		
		ConfigurableLogEntryMatcher matcher = new ConfigurableLogEntryMatcher(logEntryMatcher);
		Description description = new StringDescription();

		// When
		matcher.describeMismatch(logEntry, description);

		// Then
		assertThat(description, describes(expectedMismatchDescription));
	}

	private static void givenMismatchForLogEntryDescribedAs(LogEntryMatcher logEntryMatcher, LogEntry logEntry, final String expectedMismatchDescription) {
		willAnswer(new Answer<Void>() {

			public Void answer(InvocationOnMock invocation) throws Throwable {
				Description description = (Description) invocation.getArguments()[1];
				description.appendText(expectedMismatchDescription);
				return null;
			}
		}).given(logEntryMatcher).describeMismatch(eq(logEntry), argThat(any(Description.class)));
	}

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
