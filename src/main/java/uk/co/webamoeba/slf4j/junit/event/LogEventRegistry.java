package uk.co.webamoeba.slf4j.junit.event;

import java.util.HashMap;
import java.util.Map;

/**
 * A registry from which we can retrieve {@link LogEventRegister LogEventRegisters} by their name.
 * 
 * @author James Kennard
 */
public class LogEventRegistry {

	private static final LogEventRegistry LOG_EVENT_REGISTRY = new LogEventRegistry();

	private static final Map<String, LogEventRegister> LOG_EVENT_REGISTERS = new HashMap<String, LogEventRegister>();

	public static LogEventRegistry getSingleton() {
		return LOG_EVENT_REGISTRY;
	}

	private LogEventRegistry() {
	}

	/**
	 * @param name
	 *            The name of the {@link LogEventRegister} we want to get
	 * @return The LogEventRegister for the given name
	 */
	public LogEventRegister getRegister(String name) {
		if (!LOG_EVENT_REGISTERS.containsKey(name)) {
			LOG_EVENT_REGISTERS.put(name, new LogEventRegister());
		}
		return LOG_EVENT_REGISTERS.get(name);
	}

	/**
	 * {@link LogEventRegister#clear() Clears} the {@link LogEvent LogEvents} from all of the {@link LogEventRegister
	 * LogEventRegisters}.
	 */
	public void clearAll() {
		for (LogEventRegister register : LOG_EVENT_REGISTERS.values()) {
			register.clear();
		}
	}

}
