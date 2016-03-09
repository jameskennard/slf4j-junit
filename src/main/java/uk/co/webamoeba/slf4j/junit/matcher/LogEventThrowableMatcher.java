package uk.co.webamoeba.slf4j.junit.matcher;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import uk.co.webamoeba.slf4j.junit.event.LogEvent;

/**
 * Matcher capable of matching {@link LogEvent LogEvents} based on their {@link Throwable}
 * 
 * @author James Kennard
 */
public class LogEventThrowableMatcher extends TypeSafeDiagnosingMatcher<LogEvent> {

	private final Throwable throwable;

	/**
	 * @param throwable {@link Throwable} we want matched {@link LogEvent LogEvents} to have
	 */
	public LogEventThrowableMatcher(Throwable throwable) {
		this.throwable = throwable;
	}

	/**
	 * {@inheritDoc}
	 */
	public void describeTo(Description description) {
		description.appendText(LogEvent.class.getSimpleName()).appendText(" with Throwable ").appendValue(throwable);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean matchesSafely(LogEvent logEvent, Description mismatchDescription) {
		if (!throwable.equals(logEvent.getThrowable())) {
			mismatchDescription
					.appendText("wanted ").appendValue(throwable)
					.appendText(" but was ").appendValue(logEvent.getThrowable());
			return false;
		}
		return true;
	}

}
