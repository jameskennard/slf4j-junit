package uk.co.webamoeba.slf4j.junit.matcher;

import org.slf4j.Marker;

import static uk.co.webamoeba.slf4j.junit.log.Level.WARN;

/**
 * @author James Kennard
 */
public class WarningLogEntryMatcher extends LegacyLogEntryMatcher {

	public WarningLogEntryMatcher(String message) {
		super(WARN, message);
	}

	public WarningLogEntryMatcher(Marker marker, String format, Object... arguments) {
		super(WARN, marker, format, arguments);
	}

	public WarningLogEntryMatcher(Marker marker, String message, Throwable throwable) {
		super(WARN, marker, message, throwable);
	}

	public WarningLogEntryMatcher(Marker marker, String message) {
		super(WARN, marker, message);
	}

	public WarningLogEntryMatcher(String format, Object... arguments) {
		super(WARN, format, arguments);
	}

	public WarningLogEntryMatcher(String message, Throwable throwable) {
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
