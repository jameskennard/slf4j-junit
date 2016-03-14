package uk.co.webamoeba.slf4j.junit.matcher;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import uk.co.webamoeba.slf4j.junit.log.LogEntry;

/**
 * {@link Matcher} that can be configured with additional {@link LogEntryMatcher LogEntryMatchers}.
 * 
 * @author James Kennard
 */
public class ConfigurableLogEntryMatcher extends BaseMatcher<LogEntry> {

	private LogEntryMatcher[] logEntryMatchers;

	/**
	 * @param logEntryMatchers The {@link LogEntryMatcher LogEntryMatchers} which must also match for this {@link Matcher} to match a {@link LogEntry}
	 */
	public ConfigurableLogEntryMatcher(LogEntryMatcher... logEntryMatchers) {
		this.logEntryMatchers = logEntryMatchers;
	}

	public void describeTo(Description description) {
		description.appendText(LogEntry.class.getSimpleName());
		if (logEntryMatchers.length > 0) {
			description.appendText(" ");
			describeLogEntry(description);
		}
	}

	private void describeLogEntry(Description description) {
		for (int logEntryIndex = 0; logEntryIndex < logEntryMatchers.length; logEntryIndex++) {
			logEntryMatchers[logEntryIndex].describeMatchingLogEntry(description);
			if (isNotLastLogEntryIndex(logEntryIndex)) {
				description.appendText(" and ");
			}
		}
	}

	private boolean isNotLastLogEntryIndex(int logEntryIndex) {
		return logEntryIndex < logEntryMatchers.length - 1;
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
	@Override
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
		for (LogEntryMatcher matcher : logEntryMatchers) {
			if (!matcher.matches(item)) {
				matcher.describeMismatch(item, mismatchDescription);
				return false;
			}
		}
		return true;
	}

}
