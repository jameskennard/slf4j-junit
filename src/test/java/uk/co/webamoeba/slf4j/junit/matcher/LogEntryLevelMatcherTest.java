package uk.co.webamoeba.slf4j.junit.matcher;

import org.hamcrest.Description;
import org.hamcrest.StringDescription;
import org.junit.Test;
import uk.co.webamoeba.slf4j.junit.log.Level;
import uk.co.webamoeba.slf4j.junit.log.LogEntry;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static uk.co.webamoeba.slf4j.junit.testsupport.DescriptionMatcher.describes;
import static uk.co.webamoeba.slf4j.junit.testsupport.LevelTestFactory.aDifferentLevel;
import static uk.co.webamoeba.slf4j.junit.testsupport.LevelTestFactory.aLevel;
import static uk.co.webamoeba.slf4j.junit.testsupport.LogEntryTestFactory.logEntryAtLevel;

/**
 * Test for {@link LogEntryLevelMatcher}
 * 
 * @author James Kennard
 */
public class LogEntryLevelMatcherTest extends LogEntryMatcherTest<LogEntryLevelMatcher> {

	@Test
	public void shouldMatch() {
		// Given
		Level level = aLevel();
		LogEntry logEntry = logEntryAtLevel(level);

		LogEntryLevelMatcher matcher = new LogEntryLevelMatcher(level);

		// When
		boolean matches = matcher.matches(logEntry);

		// Then
		assertThat(matches, is(true));
	}

	@Test
	public void shouldNotMatch() {
		// Given
		Level level = aLevel();
		LogEntryLevelMatcher matcher = new LogEntryLevelMatcher(level);

		Level differentLevel = aDifferentLevel(level);
		LogEntry logEntry = logEntryAtLevel(differentLevel);

		// When
		boolean matches = matcher.matches(logEntry);

		// Then
		assertThat(matches, is(false));
	}

	@Test
	public void shouldDescribeMismatch() {
		// Given
		Level level = aLevel();
		LogEntryLevelMatcher matcher = new LogEntryLevelMatcher(level);

		Level differentLevel = aDifferentLevel(level);
		LogEntry logEntry = logEntryAtLevel(differentLevel);

		Description mismatchDescription = new StringDescription();

		// When
		matcher.describeMismatch(logEntry, mismatchDescription);

		// Then
		assertThat(mismatchDescription, describes("wanted <" + level + "> but was <" + differentLevel + ">"));
	}

	@Test
	public void shouldDescribe() {
		// Given
		Level level = aLevel();
		LogEntryLevelMatcher matcher = new LogEntryLevelMatcher(level);
		Description description = new StringDescription();

		// When
		matcher.describeTo(description);

		// Then
		assertThat(description, describes("LogEntry at level <" + level + ">"));
	}

	@Test
	public void shouldDescribeMatchingLogEntry() {
		// Given
		Level level = aLevel();
		LogEntryLevelMatcher matcher = new LogEntryLevelMatcher(level);
		Description description = new StringDescription();

		// When
		matcher.describeTo(description);

		// Then
		assertThat(description, describes("LogEntry at level <" + level + ">"));
	}

	@Override
	protected LogEntryLevelMatcher aMatcher() {
		return new LogEntryLevelMatcher(aLevel());
	}

}
