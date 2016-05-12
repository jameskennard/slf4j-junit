package uk.co.webamoeba.slf4j.junit.log;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A registry from which we can retrieve {@link Log Logs} by their name.
 * 
 * @author James Kennard
 */
public class LogRegistry {

	private Map<LogKey, Log> logs = new ConcurrentHashMap<LogRegistry.LogKey, Log>();

	/**
	 * @param name The name of the {@link Log} we want to get
	 * @return The Log for the given name
	 */
	public synchronized Log getLog(String name) {
		LogKey logKey = logKey(name);
		if (!logs.containsKey(logKey)) {
			logs.put(logKey, new Log());
		}
		return logs.get(logKey);
	}

	private LogKey logKey(String name) {
		return new LogKey(name);
	}

	private static class LogKey {

		private final String name;

		public LogKey(String name) {
			this.name = name;
		}

		@Override
		public int hashCode() {
			return 31 * (name.hashCode());
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
			return this.name.equals(other.name);
		}

	}

}
