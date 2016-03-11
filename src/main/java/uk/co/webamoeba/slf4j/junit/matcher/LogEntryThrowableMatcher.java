package uk.co.webamoeba.slf4j.junit.matcher;

import org.hamcrest.Description;
import uk.co.webamoeba.slf4j.junit.log.LogEntry;

/**
 * Matcher capable of matching {@link LogEntry LogEntries} based on their {@link Throwable}
 * 
 * @author James Kennard
 */
public class LogEntryThrowableMatcher extends LogEntryMatcher {

	private final Throwable throwable;

	/**
	 * @param throwable {@link Throwable} we want matched {@link LogEntry LogEntries} to have
	 */
	public LogEntryThrowableMatcher(Throwable throwable) {
		this.throwable = throwable;
	}

	@Override
	public void describeMatchingLogEntry(Description description) {
		description.appendText("with Throwable ").appendValue(throwable);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean matchesSafely(LogEntry logEntry, Description mismatchDescription) {
		if (!throwable.equals(logEntry.getThrowable())) {
			mismatchDescription
					.appendText("wanted ").appendValue(throwable)
					.appendText(" but was ").appendValue(logEntry.getThrowable());
			return false;
		}
		return true;
	}

}
