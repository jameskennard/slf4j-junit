package uk.co.webamoeba.slf4j.junit;

import static uk.co.webamoeba.slf4j.junit.event.Level.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static uk.co.webamoeba.slf4j.junit.LoggingMatchers.loggedInfo;
import static uk.co.webamoeba.slf4j.junit.LoggingMatchers.logger;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.slf4j.Logger;

import uk.co.webamoeba.slf4j.junit.event.Level;
import uk.co.webamoeba.slf4j.junit.event.LogEventRegistry;
import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Cucumber step definitions for Assert features
 * 
 * @author James Kennard
 */
public class AssertStepDefinitions {
	
	private Logger logger;
	
	private Matcher<Logger> matcher;

	private Boolean matches;

	private Throwable throwable;
	
	@Before
	public void clearLogs() {
		LogEventRegistry.getSingleton().clearAll();
	}
	
	@Given("^a logger$")
	public void aLogger() {
		logger = logger(AssertStepDefinitions.class);
	}
	
	@Given("^a logger named \"(.*?)\"$")
	public void aLoggerNamed(String loggerName) throws Throwable {
	    logger = logger(loggerName);
	}
	
	@Given("^a String message \"(.*?)\" logged at \"(.*?)\" level$")
	public void aStringMessageLoggedAtLevel(String messageAsString, Level level) {		
		switch (level) {
		case INFO:
			logger.info(messageAsString);
			break;
		case WARN:
			logger.warn(messageAsString);
			break;
		default:
			throw new PendingException("level " + level + " is not yet available");
		}
	}
	
	@Given("^a String message \"(.*?)\" logged with \"(.*?)\" at \"(.*?)\" level$")
	public void aStringMessageLoggedWithLoggerAtLevel(String message, String loggerName, Level level) throws Throwable {
		if (INFO != level) {
			throw new PendingException("level " + level + " is not yet available");
		}
    	logger(loggerName).info(message);
	}
	
	@Given("^a Formatted message \"(.*?)\" with argument \"(.*?)\" logged at \"(.*?)\" level$")
	public void aFormattedMessageWithArgumentLoggedAtLevel(String format, String argument, Level level) throws Throwable {
		if (INFO != level) {
			throw new PendingException("level " + level + " is not yet available");
		}
	    logger.info(format, argument);
	}
	
	@Given("^a log matcher at \"(.*?)\" level with the Formatted message \"(.*?)\" with argument \"(.*?)\"$")
	public void aLogMatcherAtLevelWithFormattedMessage(Level level, String format, String argument) throws Throwable {
		if (INFO != level) {
			throw new PendingException("level " + level + " is not yet available");
		}
	    matcher = loggedInfo(format, argument);
	}
	
	@Given("^a log matcher at \"(.*?)\" level with the String message \"(.*?)\"$")
	public void aLogMatcherAtLevelWithStringMessage(Level level, String message) throws Throwable {
		if (INFO != level) {
			throw new PendingException("level " + level + " is not yet available");
		}
    	matcher = loggedInfo(message);
	}
	
	@Given("^a throwable$")
	public void aThrowable() {
	    throwable = new Throwable("a throwable");
	}
	
	@Given("^the throwable is logged at \"(.*?)\" level with the String message \"(.*?)\"$")
	public void theThrowableIsLoggedAtLevelWithTheStringMessage(Level level, String message) throws Throwable {
		if (INFO != level) {
			throw new PendingException("level " + level + " is not yet available");
		}
		logger.info(message, throwable);
	}
	
	@Given("^a log matcher at \"(.*?)\" level with the throwable and the String message \"(.*?)\"$")
	public void aLogMatcherAtLevelWithTheThrowableAndTheStringMessage(Level level, String message) throws Throwable {
		if (INFO != level) {
			throw new PendingException("level " + level + " is not yet available");
		}
    	matcher = loggedInfo(message, throwable);
	}
	
	@When("^I match the logger$")
	public void iMatchTheLogger() {
	    matches = matcher.matches(logger);
	}
	
	@Then("^it will match$")
	public void itWillMatch() {
		assertThat(matches, is(true));
	}
	
	@Then("^it will not match$")
	public void itWillNotMatch() {
		assertThat(matches, is(false));
	}
	
	@Then("^the mismatch description is:$")
	public void theMismatchDescriptionIs(String mismatchDescription) throws Throwable {
		Description description = new StringDescription();
		matcher.describeMismatch(logger, description);
		assertThat(description.toString(), is(mismatchDescription));
	}
	
}
