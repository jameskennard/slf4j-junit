package uk.co.webamoeba.slf4j.junit.matcher;

import org.hamcrest.Description;
import org.hamcrest.StringDescription;
import org.junit.Test;
import uk.co.webamoeba.slf4j.junit.event.Level;
import uk.co.webamoeba.slf4j.junit.event.LogEvent;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static uk.co.webamoeba.slf4j.junit.testsupport.DescriptionMatcher.describes;
import static uk.co.webamoeba.slf4j.junit.testsupport.LevelTestFactory.aDifferentLevel;
import static uk.co.webamoeba.slf4j.junit.testsupport.LevelTestFactory.aLevel;
import static uk.co.webamoeba.slf4j.junit.testsupport.LogEventTestFactory.logEventAtLevel;

/**
 * Test for {@link LogEventLevelMatcher}
 * 
 * @author James Kennard
 */
public class LogEventLevelMatcherTest {

	@Test
	public void shouldMatch() {
		// Given
		Level level = aLevel();
		LogEvent logEvent = logEventAtLevel(level);

		LogEventLevelMatcher matcher = new LogEventLevelMatcher(level);

		// When
		boolean matches = matcher.matches(logEvent);

		// Then
		assertThat(matches, is(true));
	}

	@Test
	public void shouldNotMatch() {
		// Given
		Level level = aLevel();
		LogEventLevelMatcher matcher = new LogEventLevelMatcher(level);

		Level differentLevel = aDifferentLevel(level);
		LogEvent logEvent = logEventAtLevel(differentLevel);

		// When
		boolean matches = matcher.matches(logEvent);

		// Then
		assertThat(matches, is(false));
	}

	@Test
	public void shouldDescribeMismatch() {
		// Given
		Level level = aLevel();
		LogEventLevelMatcher matcher = new LogEventLevelMatcher(level);

		Level differentLevel = aDifferentLevel(level);
		LogEvent logEvent = logEventAtLevel(differentLevel);

		Description mismatchDescription = new StringDescription();

		// When
		matcher.describeMismatch(logEvent, mismatchDescription);

		// Then
		assertThat(mismatchDescription, describes("wanted <" + level + "> but was <" + differentLevel + ">"));
	}
	
	@Test
	public void shouldDescribe() {
		// Given
		Level level = aLevel();
		LogEventLevelMatcher matcher = new LogEventLevelMatcher(level);
		Description description = new StringDescription();

		// When
		matcher.describeTo(description);

		// Then
		assertThat(description, describes("LogEvent at level <" + level + ">"));
	}

}
