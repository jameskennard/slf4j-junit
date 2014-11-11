package uk.co.webamoeba.slf4j.junit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static uk.co.webamoeba.slf4j.junit.LoggingMatchers.logger;

import org.slf4j.Logger;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static uk.co.webamoeba.slf4j.junit.LoggingMatchers.loggedInfo;

/**
 * Cucumber step definitions for Logger features.
 * 
 * @author James Kennard
 */
public class LoggerStepDefinitions {

	private String loggerName;
	
	private Class<?> clazz;

	private Logger logger;

	@Given("^the name \"(.*?)\"$")
	public void theName(String name) throws Throwable {
	    this.loggerName = name;
	}
	
	@Given("^the class \"(.*?)\"$")
	public void theClass(String className) throws Throwable {
	    clazz = Class.forName(className);
	}

	@When("^I call logger with the name$")
	public void iCallLoggerWithTheName() throws Throwable {
		logger = logger(loggerName);
	}
	
	@When("^I call logger with the class$")
	public void iCallLoggerWithTheClass() throws Throwable {
		logger = logger(clazz);
	}

	@Then("^the logger is named \"(.*?)\"$")
	public void theLoggerIsNamed(String loggerName) throws Throwable {
		assertThat(logger.getName(), is(loggerName));
	}
	
	@Then("^I can match logging events against the logger$")
	public void iCanMatchLoggingEventsAgainstTheLogger() throws Throwable {
		String message = "Some Message";
	    logger.info(message);
		assertThat(logger, loggedInfo(message));
	}
	
}
