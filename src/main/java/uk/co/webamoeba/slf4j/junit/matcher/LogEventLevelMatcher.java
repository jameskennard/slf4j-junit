package uk.co.webamoeba.slf4j.junit.matcher;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import uk.co.webamoeba.slf4j.junit.event.Level;
import uk.co.webamoeba.slf4j.junit.event.LogEvent;

/**
 * Matcher capable of matching {@link LogEvent LogEvents} based on their {@link Level}
 * 
 * @author James Kennard
 */
public class LogEventLevelMatcher extends TypeSafeDiagnosingMatcher<LogEvent> {

	private final Level level;

	/**
	 * @param level {@link Level} we want matched {@link LogEvent LogEvents} to have
	 */
	public LogEventLevelMatcher(Level level) {
		this.level = level;
	}

	/**
	 * {@inheritDoc}
	 */
	public void describeTo(Description description) {
		description.appendText(LogEvent.class.getSimpleName()).appendText(" at level ").appendValue(level);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean matchesSafely(LogEvent logEvent, Description mismatchDescription) {
		if (logEvent.getLevel() != level) {
			mismatchDescription
					.appendText("wanted ").appendValue(level)
					.appendText(" but was ").appendValue(logEvent.getLevel());
			return false;
		}
		return true;
	}

}
