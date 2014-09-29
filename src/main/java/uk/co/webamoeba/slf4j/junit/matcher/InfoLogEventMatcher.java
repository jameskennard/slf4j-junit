package uk.co.webamoeba.slf4j.junit.matcher;

import java.util.logging.Level;

import org.slf4j.Marker;

import uk.co.webamoeba.slf4j.junit.event.LogEvent;
import uk.co.webamoeba.slf4j.junit.event.LogEvent.FormattedMessage;
import uk.co.webamoeba.slf4j.junit.event.LogEvent.StringMessage;

/**
 * Matcher capable of matching {@link Level#INFO} logging events with a specific message.
 * <p>
 * Messages may be expressed as a plain {@link String} or as a format and zero or more argument {@link Object Objects}.
 * The matcher will match regardless of how the logger was called. For example, the following would both match:
 * </p>
 * 
 * <pre>
 * logger.info(&quot;Some Info&quot;);
 * assertThat(logger, new InfoLogEventMatcher(&quot;Some {}&quot;, &quot;Info&quot;));
 * 
 * logger.info(&quot;Some Format {}&quot;, &quot;Some Argument&quot;);
 * assertThat(logger, new InfoLogEventMatcher(&quot;Some Format Some Argument&quot;));
 * </pre>
 * 
 * @author James Kennard
 */
public class InfoLogEventMatcher extends LogEventMatcher {

	/**
	 * @param message The message we want the {@link LogEvent} to have
	 */
	public InfoLogEventMatcher(String message) {
		super(null, new StringMessage(message), null);
	}

	/**
	 * @param format The format of the message we want the {@link LogEvent} to have
	 * @param arguments The arguments we want to use to describe the parts of the message from the format
	 */
	public InfoLogEventMatcher(String format, Object... arguments) {
		super(null, new FormattedMessage(format, arguments), null);
	}

	/**
	 * @param message The message we want the {@link LogEvent} to have
	 * @param throwable The {@link Throwable} we are logging
	 */
	public InfoLogEventMatcher(String message, Throwable throwable) {
		super(null, new StringMessage(message), throwable);
	}

	public InfoLogEventMatcher(Marker marker, String message) {
		super(marker, new StringMessage(message), null);
	}
	
	public InfoLogEventMatcher(Marker marker, String message, Throwable throwable) {
		super(marker, new StringMessage(message), throwable);
	}
	
	public InfoLogEventMatcher(Marker marker, String format, Object... arguments) {
		super(marker, new FormattedMessage(format, arguments), null);
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
