package uk.co.webamoeba.slf4j.junit;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import uk.co.webamoeba.slf4j.junit.context.LoggingContext;
import uk.co.webamoeba.slf4j.junit.log.LogRegistry;

/**
 * {@link TestRule} which must be used if you want to test logging in a test.
 * 
 * @see DisableLogging
 * @author James Kennard
 */
public class EnableLogging implements TestRule {

	private static final EnableLogging ENABLE_LOGGING = new EnableLogging();

	public static EnableLogging enableLogging() {
		return ENABLE_LOGGING;
	}

	public Statement apply(Statement base, Description description) {
		if (!loggingIsDisabledForTest(description)) {
			return new EnableLoggingStatement(base);
		}
		return base;
	}

	private boolean loggingIsDisabledForTest(Description description) {
		return description.getAnnotation(DisableLogging.class) != null;
	}

	public class EnableLoggingStatement extends Statement {

		private final Statement statement;

		private Future<?> future;

		private volatile Throwable throwable;

		private EnableLoggingStatement(Statement statement) {
			this.statement = statement;
		}

		@Override
		public void evaluate() throws Throwable {
			ExecutorService executorService = runInThread();
			try {
				waitTillFinished();
			} finally {
				executorService.shutdown();
			}
			rethrowAssertionsAndErrors();
		}

		private ExecutorService runInThread() {
			ExecutorService result = Executors.newSingleThreadExecutor();
			future = result.submit(new Runnable() {

				public void run() {
					LoggingContext.enableLoggingInThread();
					try {
						statement.evaluate();
					} catch (Throwable throwable) {
						EnableLoggingStatement.this.throwable = throwable;
					}
					// FIXME
//					LogRegistry.getSingleton().clearAll();
				}
			});
			return result;
		}

		private void waitTillFinished() {
			try {
				future.get();
			} catch (ExecutionException shouldNotHappen) {
				throw new IllegalStateException(shouldNotHappen);
			} catch (InterruptedException shouldNotHappen) {
				throw new IllegalStateException(shouldNotHappen);
			}
		}

		private void rethrowAssertionsAndErrors() throws Throwable {
			if (throwable != null) {
				throw throwable;
			}
		}
	}

}
