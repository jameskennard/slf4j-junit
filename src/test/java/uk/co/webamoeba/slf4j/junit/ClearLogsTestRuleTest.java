package uk.co.webamoeba.slf4j.junit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static uk.co.webamoeba.slf4j.junit.LoggingMatchers.clearLogs;
import static uk.co.webamoeba.slf4j.junit.LoggingMatchers.logger;

import java.util.List;

import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import uk.co.webamoeba.slf4j.junit.event.LogEvent;
import uk.co.webamoeba.slf4j.junit.event.LogEventRegistry;

/**
 * @author James Kennard
 */
public class ClearLogsTestRuleTest {

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
		assertThat(loggingEvents(loggerClass).size(), is(0));
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
		assertThat(loggingEvents(loggerClass).size(), is(0));
		assertThat(loggingEvents(anotherLoggerClass).size(), is(1));
	}

	private List<LogEvent> loggingEvents(Class<?> loggerClass) {
		return LogEventRegistry.getSingleton().getRegister(loggerClass.getName()).getLogEvents();
	}
	
}
