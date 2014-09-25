package uk.co.webamoeba.slf4j.junit.matcher;

import java.util.logging.Level;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.slf4j.Logger;

import uk.co.webamoeba.slf4j.junit.RecordingLogger;
import uk.co.webamoeba.slf4j.junit.event.LogEvent;
import uk.co.webamoeba.slf4j.junit.event.LogEvent.FormattedMessage;
import uk.co.webamoeba.slf4j.junit.event.LogEvent.Message;
import uk.co.webamoeba.slf4j.junit.event.LogEvent.StringMessage;
import uk.co.webamoeba.slf4j.junit.event.LogEventRegister;
import uk.co.webamoeba.slf4j.junit.event.LogEventRegistry;

/**
 * Matcher capable of matching {@link Level#INFO} logging events with a specific message.
 * <p>
 * Messages may be expressed as a plain {@link String} or as a format and zero or more argument {@link Object Objects}.
 * The matcher will match regardless of how the logger was called. For example, the following would both match:
 * </p>
 * <pre>
 * logger.info("Some Info");
 * assertThat(logger, new InfoLogEventMatcher("Some {}", "Info"));
 * 
 * logger.info("Some Format {}", "Some Argument");
 * assertThat(logger, new InfoLogEventMatcher("Some Format Some Argument"));
 * </pre>
 * 
 * @author James Kennard
 */
public class InfoLogEventMatcher extends BaseMatcher<Logger> {

	private Message message;

	/**
	 * @param message The message we want the {@link LogEvent} to have.
	 */
	public InfoLogEventMatcher(String message) {
		this.message = new StringMessage(message);
	}

	/**
	 * @param format The format of the message we want the {@link LogEvent} to have.
	 * @param arguments The arguments we want to use to describe the parts of the message from the format
	 */
	public InfoLogEventMatcher(String format, Object... arguments) {
		this.message = new FormattedMessage(format, arguments);
	}

	public boolean matches(Object item) {
		if (!RecordingLogger.class.isAssignableFrom(item.getClass())) {
			return false;
		}
		return matches((RecordingLogger) item, new Description.NullDescription());
	}

	public void describeTo(Description description) {
		description.appendText("loggedInfo(");
		if (message.getClass() == FormattedMessage.class) {
			FormattedMessage formattedMessage = (FormattedMessage) message;
			description.appendValue(formattedMessage.getFormat());
			for (Object argument : formattedMessage.getArguments()) {
				description.appendText(", ").appendValue(argument);
			}
		} else {
			description.appendValue(message.getMessage());
		}
		description.appendText(")");
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
			if (logEvent.getMessage().equals(message.getMessage())) {
				return true;
			}
		}
		mismatchDescription.appendText("info to ").appendValue(logger.getName()).appendText(" with message ")
				.appendValue(message.getMessage()).appendText(" was not logged ");
		return false;
	}
}
