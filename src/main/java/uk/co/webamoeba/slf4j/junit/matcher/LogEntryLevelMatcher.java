package uk.co.webamoeba.slf4j.junit.matcher;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import uk.co.webamoeba.slf4j.junit.log.Level;
import uk.co.webamoeba.slf4j.junit.log.LogEntry;

/**
 * Matcher capable of matching {@link LogEntry LogEntries} based on their {@link Level}
 * 
 * @author James Kennard
 */
public class LogEntryLevelMatcher extends TypeSafeDiagnosingMatcher<LogEntry> {

	private final Level level;

	/**
	 * @param level {@link Level} we want matched {@link LogEntry LogEntries} to have
	 */
	public LogEntryLevelMatcher(Level level) {
		this.level = level;
	}

	/**
	 * {@inheritDoc}
	 */
	public void describeTo(Description description) {
		description.appendText(LogEntry.class.getSimpleName()).appendText(" at level ").appendValue(level);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean matchesSafely(LogEntry logEntry, Description mismatchDescription) {
		if (logEntry.getLevel() != level) {
			mismatchDescription
					.appendText("wanted ").appendValue(level)
					.appendText(" but was ").appendValue(logEntry.getLevel());
			return false;
		}
		return true;
	}

}
