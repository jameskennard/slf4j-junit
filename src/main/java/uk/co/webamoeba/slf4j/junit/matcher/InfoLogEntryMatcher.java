package uk.co.webamoeba.slf4j.junit.matcher;

import java.util.logging.Level;

import org.slf4j.Marker;
import uk.co.webamoeba.slf4j.junit.log.LogEntry;
import uk.co.webamoeba.slf4j.junit.log.LogEntry.FormattedMessage;
import uk.co.webamoeba.slf4j.junit.log.LogEntry.StringMessage;

import static uk.co.webamoeba.slf4j.junit.log.Level.INFO;

/**
 * Matcher capable of matching {@link Level#INFO} {@link LogEntry LogEntries} with a specific message.
 * <p>
 * Messages may be expressed as a plain {@link String} or as a format and zero or more argument {@link Object Objects}.
 * The matcher will match regardless of how the logger was called. For example, the following would both match:
 * </p>
 * 
 * <pre>
 * logger.info(&quot;Some Info&quot;);
 * assertThat(logger, new InfoLogEntryMatcher(&quot;Some {}&quot;, &quot;Info&quot;));
 * 
 * logger.info(&quot;Some Format {}&quot;, &quot;Some Argument&quot;);
 * assertThat(logger, new InfoLogEntryMatcher(&quot;Some Format Some Argument&quot;));
 * </pre>
 * 
 * @author James Kennard
 */
public class InfoLogEntryMatcher extends LogEntryMatcher {

	/**
	 * @param message The message we want the {@link LogEntry} to have
	 */
	public InfoLogEntryMatcher(String message) {
		super(INFO, null, new StringMessage(message), null);
	}

	/**
	 * @param format The format of the message we want the {@link LogEntry} to have
	 * @param arguments The arguments we want to use to describe the parts of the message from the format
	 */
	public InfoLogEntryMatcher(String format, Object... arguments) {
		super(INFO, null, new FormattedMessage(format, arguments), null);
	}

	/**
	 * @param message The message we want the {@link LogEntry} to have
	 * @param throwable The {@link Throwable} we are logging
	 */
	public InfoLogEntryMatcher(String message, Throwable throwable) {
		super(INFO, null, new StringMessage(message), throwable);
	}

	public InfoLogEntryMatcher(Marker marker, String message) {
		super(INFO, marker, new StringMessage(message), null);
	}
	
	public InfoLogEntryMatcher(Marker marker, String message, Throwable throwable) {
		super(INFO, marker, new StringMessage(message), throwable);
	}
	
	public InfoLogEntryMatcher(Marker marker, String format, Object... arguments) {
		super(INFO, marker, new FormattedMessage(format, arguments), null);
	}
	
	@Override
	protected String describeFunction() {
		return "loggedInfo";
	}
	
	@Override
	protected String describeLevel() {
		return "info";
	}

}
