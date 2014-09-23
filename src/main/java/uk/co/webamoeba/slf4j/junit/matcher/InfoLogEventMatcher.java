package uk.co.webamoeba.slf4j.junit.matcher;

import java.util.logging.Level;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.slf4j.Logger;

import uk.co.webamoeba.slf4j.junit.RecordingLogger;
import uk.co.webamoeba.slf4j.junit.event.LogEvent;
import uk.co.webamoeba.slf4j.junit.event.LogEventRegister;
import uk.co.webamoeba.slf4j.junit.event.LogEventRegistry;

/**
 * Matcher capable of matching {@link Level#INFO} logging events with a specific message.
 * 
 * @author James Kennard
 */
public class InfoLogEventMatcher extends BaseMatcher<Logger> {

	private String message;

	/**
	 * @param message The message we want the {@link LogEvent} to have.
	 */
	public InfoLogEventMatcher(String message) {
		this.message = message;
	}

	public boolean matches(Object item) {
		if (!RecordingLogger.class.isAssignableFrom(item.getClass())) {
			return false;
		}
		return matches((RecordingLogger) item, new Description.NullDescription());
	}

	public void describeTo(Description description) {
		// TODO Auto-generated method stub
	}

	@Override
	public void describeMismatch(Object item, Description description) {
		matches(item, description);
	}

	private boolean matches(Object item, Description mismatchDescription) {
		if (!RecordingLogger.class.isAssignableFrom(item.getClass())) {
			mismatchDescription
					.appendText("the item is not a test logger, it looks like you are not using the matcher correctly ");
			return false;
		}
		return matches((RecordingLogger) item, mismatchDescription);
	}

	private boolean matches(RecordingLogger logger, Description mismatchDescription) {
		LogEventRegister register = LogEventRegistry.getSingleton().getRegister(logger.getName());
		for (LogEvent logEvent : register.getLogEvents()) {
			if (logEvent.getMessage().equals(message)) {
				return true;
			}
		}
		mismatchDescription.appendText("info to ").appendValue(logger.getName()).appendText(" with message ")
				.appendValue(message).appendText(" was not logged ");
		return false;
	}
}
