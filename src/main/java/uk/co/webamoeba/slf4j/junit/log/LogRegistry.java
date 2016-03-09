package uk.co.webamoeba.slf4j.junit.log;

import java.util.HashMap;
import java.util.Map;

/**
 * A registry from which we can retrieve {@link Log Logs} by their name.
 * 
 * @author James Kennard
 */
public class LogRegistry {

	private static final LogRegistry REGISTRY = new LogRegistry();

	private static final Map<String, Log> LOGS = new HashMap<String, Log>();

	public static LogRegistry getSingleton() {
		return REGISTRY;
	}

	private LogRegistry() {
	}

	/**
	 * @param name The name of the {@link Log} we want to get
	 * @return The Log for the given name
	 */
	public Log getRegister(String name) {
		if (!LOGS.containsKey(name)) {
			LOGS.put(name, new Log());
		}
		return LOGS.get(name);
	}

	/**
	 * {@link Log#clear() Clears} the {@link LogEntry LogEntries} from all of the {@link Log Logs}.
	 */
	public void clearAll() {
		for (Log register : LOGS.values()) {
			register.clear();
		}
	}

}
