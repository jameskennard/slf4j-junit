package uk.co.webamoeba.slf4j.junit.log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A register containing a history of {@link LogEntry LogEntries}.
 * 
 * @author James Kennard
 */
public class Log {

	private List<LogEntry> logEntries = new ArrayList<LogEntry>();

	/**
	 * Registers a {@link LogEntry} in the {@link Log}. The order in which the {@link LogEntry LogEntries} are registered is significant.
	 * 
	 * @param logEntry The {@link LogEntry} to register
	 */
	public void register(LogEntry logEntry) {
		if (logEntry == null) {
			throw new IllegalArgumentException("logEntry must not be null");
		}
		logEntries.add(logEntry);
	}

	/**
	 * @return A {@link List} of the {@link LogEntry LogEntries} in the order they were {@link #register(LogEntry) registered}.
	 */
	public List<LogEntry> getEntries() {
		return Collections.unmodifiableList(logEntries);
	}

	/**
	 * Clears the {@link Log}. That is to say, all {@link LogEntry LogEntries} that have been {@link #register(LogEntry) registered} against this {@link Log} will be removed from the {@link Log}.
	 */
	public void clear() {
		logEntries.clear();
	}

}
