package uk.co.webamoeba.slf4j.junit.context;

import uk.co.webamoeba.slf4j.junit.log.LogRegistry;

/**
 * Context in which the logging is taking place.
 * 
 * @author James Kennard
 */
public class LoggingContext {

	private static final ThreadLocal<LogRegistry> LOG_REGISTRY_FOR_CONTEXT = new ThreadLocal<LogRegistry>();

	public static void enableLoggingInThread() {
		LOG_REGISTRY_FOR_CONTEXT.set(new LogRegistry());
	}

	public static LogRegistry getRegistry() {
		return LOG_REGISTRY_FOR_CONTEXT.get();
	}

	public static boolean loggingIsEnabled() {
		return getRegistry() != null;
	}

}
