package uk.co.webamoeba.slf4j.junit.matcher;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import uk.co.webamoeba.slf4j.junit.event.Level;
import uk.co.webamoeba.slf4j.junit.event.LogEvent;
import uk.co.webamoeba.slf4j.junit.event.LogEvent.Message;

/**
 * Matcher capable of matching {@link LogEvent LogEvents} based on their {@link Level}
 * 
 * @author James Kennard
 */
public class LogEventMessageMatcher extends TypeSafeDiagnosingMatcher<LogEvent> {

	private final Message message;

	/**
	 * @param message {@link Message} we want matched {@link LogEvent LogEvents} to have
	 */
	public LogEventMessageMatcher(Message message) {
		this.message = message;
	}

	/**
	 * {@inheritDoc}
	 */
	public void describeTo(Description description) {
		description
				.appendText(LogEvent.class.getSimpleName())
				.appendText(" with message ")
				.appendValue(message.getMessageAsString());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean matchesSafely(LogEvent logEvent, Description mismatchDescription) {
		if (!logEvent.getMessage().equals(message)) {
			mismatchDescription
					.appendText("wanted ").appendValue(message.getMessageAsString())
					.appendText(" but was ").appendValue(logEvent.getMessage().getMessageAsString());
			return false;
		}
		return true;
	}

}
