package uk.co.webamoeba.slf4j.junit.matcher;

import java.util.List;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.slf4j.Logger;
import uk.co.webamoeba.slf4j.junit.log.Log;
import uk.co.webamoeba.slf4j.junit.log.LogEntry;
import uk.co.webamoeba.slf4j.junit.log.LogRegistry;
import uk.co.webamoeba.slf4j.junit.logger.RecordingLogger;

/**
 * {@link Matcher} which will match a {@link Logger} (that must be a {@link RecordingLogger}) to which a log entry has been made that matches according to the provided {@link Matcher}&lt;
 * {@link LogEntry}&gt;.
 * 
 * @author James Kennard
 */
public class RecordingLoggerMatcher extends BaseMatcher<Logger> {

	private final Matcher<LogEntry> entryMatcher;

	public RecordingLoggerMatcher(Matcher<LogEntry> entryMatcher) {
		this.entryMatcher = entryMatcher;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean matches(Object item) {
		return matches(item, new Description.NullDescription());
	}

	/**
	 * {@inheritDoc}
	 */
	public void describeMismatch(Object logger, Description mismatchDescription) {
		matches(logger, mismatchDescription);
	}

	// FIXME Add describe to
	public void describeTo(Description description) {
		description.appendText("Logged ").appendDescriptionOf(entryMatcher);
	}

	private boolean matches(Object item, Description mismatchDescription) {
		if (!(item instanceof RecordingLogger)) {
			mismatchDescription.appendText("The logger is not a RecordingLogger, are you using the matcher correctly?");
			return false;
		}
		return matchesSafely((RecordingLogger) item, mismatchDescription);
	}

	private boolean matchesSafely(RecordingLogger logger, Description mismatchDescription) {
		Log register = LogRegistry.getSingleton().getRegister(logger.getName());

		List<LogEntry> entries = register.getEntries();
		for (LogEntry logEntry : entries) {
			if (entryMatcher.matches(logEntry)) {
				return true;
			}
		}
		if (entries.isEmpty()) {
			mismatchDescription
					.appendText("No LogEntries were logged to ").appendValue(logger.getName())
					.appendText("\nWanted ").appendDescriptionOf(entryMatcher);
		} else {
			mismatchDescription
					.appendText("There are LogEntries, but no ").appendDescriptionOf(entryMatcher)
					.appendText(" was logged to ").appendValue(logger.getName());
		}
		return false;
	}

}
