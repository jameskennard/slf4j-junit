package uk.co.webamoeba.slf4j.junit;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import uk.co.webamoeba.slf4j.junit.event.LogEvent;
import uk.co.webamoeba.slf4j.junit.event.LogEventRegistry;

/**
 * JUnit {@link TestRule} that will clear any recorded {@link LogEvent LogEvents}. This may be used in one of two forms,
 * you can either clear all of the logs by constructing the rule with the default constructor. Or you can clear a
 * specified log by providing the class for the logger.
 * 
 * @author James Kennard
 */
public class ClearLogsTestRule implements TestRule {

	private Class<?> loggerClass;

	public ClearLogsTestRule(Class<?> loggerClass) {
		this.loggerClass = loggerClass;
	}

	public ClearLogsTestRule() {
	}

	public Statement apply(Statement base, Description description) {
		return statement(base);
	}

	private Statement statement(final Statement base) {
		return new Statement() {
			@Override
			public void evaluate() throws Throwable {
				clearLogs();
				base.evaluate();
			}
		};
	}

	private void clearLogs() {
		LogEventRegistry registry = LogEventRegistry.getSingleton();
		if (clearAll()) {
			registry.clearAll();
		} else {
			registry.getRegister(loggerName()).clear();
		}
	}

	private String loggerName() {
		return loggerClass.getName();
	}

	private boolean clearAll() {
		return loggerClass == null;
	}

}
