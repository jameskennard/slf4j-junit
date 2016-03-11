package uk.co.webamoeba.slf4j.junit.matcher;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import uk.co.webamoeba.slf4j.junit.log.LogEntry;

/**
 * {@link Matcher} for a {@link LogEntry}
 * 
 * @author James Kennard
 */
public abstract class LogEntryMatcher extends BaseMatcher<LogEntry> {

	/**
	 * {@inheritDoc}
	 */
	public void describeTo(Description description) {
		description.appendText(LogEntry.class.getSimpleName()).appendText(" ");
		describeMatchingLogEntry(description);
	}

	/**
	 * Describes what it is about a {@link LogEntry} that means this {@link Matcher} would {@link Matcher#matches(Object) match} it.
	 * 
	 * @param description The {@link Description} to be appended to.
	 */
	public abstract void describeMatchingLogEntry(Description description);

	public boolean matches(Object item) {
		return matches(item, new Description.NullDescription());
	}

	public void describeMismatch(Object item, Description mismatchDescription) {
		matches(item, mismatchDescription);
	}

	private boolean matches(Object item, Description mismatchDescription) {
		if (!(item instanceof LogEntry)) {
			mismatchDescription
					.appendText("The item is not a ").appendText(LogEntry.class.getSimpleName())
					.appendText(", are you using the matcher correctly?");
			return false;
		}
		return matchesSafely((LogEntry) item, mismatchDescription);
	}

	protected abstract boolean matchesSafely(LogEntry logEntry, Description mismatchDescription);

}
