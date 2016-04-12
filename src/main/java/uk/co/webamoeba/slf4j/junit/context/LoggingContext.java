package uk.co.webamoeba.slf4j.junit.context;

public class LoggingContext {

	private static final ThreadLocal<LoggingContext> CONTEXT = new ThreadLocal<LoggingContext>() {

		@Override
		protected LoggingContext initialValue() {
			return new LoggingContext();
		}

	};

	private final Thread thread;

	private LoggingContext() {
		thread = null;
	}

	private LoggingContext(Thread thread) {
		this.thread = thread;
	}

	public static void enableLoggingInThread() {
		CONTEXT.set(new LoggingContext(Thread.currentThread()));
	}
	
	public static LoggingContext getContext() {
		return CONTEXT.get();
	}

	public String getName() {
		return thread.getName();
	}

	public boolean loggingIsEnabled() {
		return thread != null;
	}

}
