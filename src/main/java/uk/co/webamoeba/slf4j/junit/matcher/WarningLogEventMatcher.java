package uk.co.webamoeba.slf4j.junit.matcher;

import static uk.co.webamoeba.slf4j.junit.event.Level.WARN;
import org.slf4j.Marker;

/**
 * @author James Kennard
 */
public class WarningLogEventMatcher extends LogEventMatcher {

	public WarningLogEventMatcher(String message) {
		super(WARN, message);
	}

	public WarningLogEventMatcher(Marker marker, String format, Object... arguments) {
		super(WARN, marker, format, arguments);
	}

	public WarningLogEventMatcher(Marker marker, String message, Throwable throwable) {
		super(WARN, marker, message, throwable);
	}

	public WarningLogEventMatcher(Marker marker, String message) {
		super(WARN, marker, message);
	}

	public WarningLogEventMatcher(String format, Object... arguments) {
		super(WARN, format, arguments);
	}

	public WarningLogEventMatcher(String message, Throwable throwable) {
		super(WARN, message, throwable);
	}

	@Override
	protected String describeFunction() {
		return "loggedWarning";
	}

	@Override
	protected String describeLevel() {
		return "warn";
	}

}
