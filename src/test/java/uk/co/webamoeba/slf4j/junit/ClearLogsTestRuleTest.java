package uk.co.webamoeba.slf4j.junit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static uk.co.webamoeba.slf4j.junit.LoggingMatchers.clearLogs;
import static uk.co.webamoeba.slf4j.junit.LoggingMatchers.enableLogging;
import static uk.co.webamoeba.slf4j.junit.LoggingMatchers.logger;

import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import uk.co.webamoeba.slf4j.junit.log.LogEntry;
import uk.co.webamoeba.slf4j.junit.log.LogRegistry;

/**
 * @author James Kennard
 */
public class ClearLogsTestRuleTest {

	@Rule
	public EnableLogging enableLogging = enableLogging();
	
	@Test
	public void shouldClearAllLogs() throws Throwable {
		// Given
		Statement base = mock(Statement.class);
		Description description = Description.createTestDescription("SomeClass", "SomeTest");
		Class<? extends ClearLogsTestRuleTest> loggerClass = getClass();
		logger(loggerClass).info("Some Info Message");

		// When
		clearLogs().apply(base, description).evaluate();

		// Then
		verify(base).evaluate();
		assertThat(logEntries(loggerClass).size(), is(0));
	}

	@Test
	public void shouldClearLogsGivenClass() throws Throwable {
		// Given
		Class<?> loggerClass = getClass();
		Class<?> anotherLoggerClass = String.class;
		Statement base = mock(Statement.class);
		Description description = Description.createTestDescription("SomeClass", "SomeTest");
		logger(loggerClass).info("Some Info Message");
		logger(anotherLoggerClass).info("Another Info Message");

		// When
		clearLogs(loggerClass).apply(base, description).evaluate();

		// Then
		verify(base).evaluate();
		assertThat(logEntries(loggerClass).size(), is(0));
		assertThat(logEntries(anotherLoggerClass).size(), is(1));
	}

	private List<LogEntry> logEntries(Class<?> loggerClass) {
		return LogRegistry.getSingleton().getLog(loggerClass.getName()).getEntries();
	}

}
