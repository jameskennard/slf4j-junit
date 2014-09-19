package uk.co.webamoeba.slf4j.junit.event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A register containing a history of {@link LogEvent LogEvents}.
 * 
 * @author James Kennard
 */
public class LogEventRegister {

	private List<LogEvent> logEvents = new ArrayList<LogEvent>();

	/**
	 * Registers a {@link LogEvent} in the {@link LogEventRegister}. The order in which events are registered is
	 * significant.
	 * 
	 * @param logEvent
	 *            The {@link LogEvent} to register
	 */
	public void register(LogEvent logEvent) {
		if (logEvent == null) {
			throw new IllegalArgumentException("logEvent must not be null");
		}
		logEvents.add(logEvent);
	}

	/**
	 * @return A {@link List} of the {@link LogEvent LogEvents} in the order they were {@link #register(LogEvent)
	 *         registered}.
	 */
	public List<LogEvent> getLogEvents() {
		return Collections.unmodifiableList(logEvents);
	}

}
