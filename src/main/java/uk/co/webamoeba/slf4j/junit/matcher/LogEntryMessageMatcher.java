package uk.co.webamoeba.slf4j.junit.matcher;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import uk.co.webamoeba.slf4j.junit.log.Level;
import uk.co.webamoeba.slf4j.junit.log.LogEntry;
import uk.co.webamoeba.slf4j.junit.log.LogEntry.Message;

/**
 * Matcher capable of matching {@link LogEntry LogEntries} based on their {@link Level}
 * 
 * @author James Kennard
 */
public class LogEntryMessageMatcher extends TypeSafeDiagnosingMatcher<LogEntry> {

	private final Message message;

	/**
	 * @param message {@link Message} we want matched {@link LogEntry LogEntries} to have
	 */
	public LogEntryMessageMatcher(Message message) {
		this.message = message;
	}

	/**
	 * {@inheritDoc}
	 */
	public void describeTo(Description description) {
		description
				.appendText(LogEntry.class.getSimpleName())
				.appendText(" with message ")
				.appendValue(message.getMessageAsString());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean matchesSafely(LogEntry logEntry, Description mismatchDescription) {
		if (!logEntry.getMessage().equals(message)) {
			mismatchDescription
					.appendText("wanted ").appendValue(message.getMessageAsString())
					.appendText(" but was ").appendValue(logEntry.getMessage().getMessageAsString());
			return false;
		}
		return true;
	}

}
