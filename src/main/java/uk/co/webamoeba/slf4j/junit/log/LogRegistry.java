package uk.co.webamoeba.slf4j.junit.log;

import java.util.HashMap;
import java.util.Map;
import uk.co.webamoeba.slf4j.junit.context.LoggingContext;

/**
 * A registry from which we can retrieve {@link Log Logs} by their name.
 * 
 * @author James Kennard
 */
public class LogRegistry {

	private static final LogRegistry REGISTRY = new LogRegistry();

	private static final Map<LogKey, Log> LOGS = new HashMap<LogKey, Log>();

	public static LogRegistry getSingleton() {
		return REGISTRY;
	}

	private LogRegistry() {
	}

	/**
	 * @param name The name of the {@link Log} we want to get
	 * @return The Log for the given name
	 */
	public Log getLog(String name) {
		LogKey logKey = logKey(name);
		if (!LOGS.containsKey(logKey)) {
			LOGS.put(logKey, new Log());
		}
		return LOGS.get(logKey);
	}

	private LogKey logKey(String name) {
		return new LogKey(name, getContext().getName());
	}

	/**
	 * {@link Log#clear() Clears} the {@link LogEntry LogEntries} from all of the {@link Log Logs}.
	 */
	public void clearAll() {
		LoggingContext context = getContext();
		for (LogKey logKey : LOGS.keySet()) {
			if (logKey.contextName.equals(context.getName())) {
				LOGS.remove(logKey);
			}
		}
		
		throw new IllegalStateException("Logging is not enabled, have you used the EnableLogging @Rule?");
	}
	
	private static LoggingContext getContext() {
		LoggingContext context = LoggingContext.getContext();
		if (context == null) {
			throw new IllegalStateException("Logging is not enabled, have you used the EnableLogging @Rule?");
		}
		return context;
	}

	private static class LogKey {

		private final String name;

		private final String contextName;

		public LogKey(String name, String contextName) {
			this.name = name;
			this.contextName = contextName;
		}

		@Override
		public int hashCode() {
			return 31 * (name.hashCode() + contextName.hashCode());
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null || getClass() != obj.getClass()) {
				return false;
			}
			LogKey other = (LogKey) obj;
			return this.contextName.equals(other.contextName) && this.name.equals(other.name);
		}

	}

}
