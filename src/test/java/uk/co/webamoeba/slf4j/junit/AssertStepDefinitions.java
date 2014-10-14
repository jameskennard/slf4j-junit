package uk.co.webamoeba.slf4j.junit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static uk.co.webamoeba.slf4j.junit.LoggingMatchers.loggedInfo;
import static uk.co.webamoeba.slf4j.junit.LoggingMatchers.logger;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.slf4j.Logger;

import uk.co.webamoeba.slf4j.junit.event.LogEventRegistry;
import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AssertStepDefinitions {
	
	private Logger logger;
	
	private Matcher<Logger> matcher;

	private Boolean matches;
	
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
	public void aStringMessageLoggedAtLevel(String messageAsString, String level) {
		if (!"info".equals(level)) {
			throw new PendingException();
		}
		logger.info(messageAsString);
	}
	
	@Given("^a Formatted message \"(.*?)\" with argument \"(.*?)\" logged at \"(.*?)\" level$")
	public void aFormattedMessageWithArgumentLoggedAtLevel(String format, String argument, String level) throws Throwable {
		if ("info".equals(level)) {
	    	logger.info(format, argument);
	    } else {
	    	throw new PendingException("matcher for level " + level + " is not yet available");
	    }
	}
	
	@Given("^a log matcher at \"(.*?)\" level with the Formatted message \"(.*?)\" with argument \"(.*?)\"$")
	public void aLogMatcherAtLevelWithFormattedMessage(String level, String format, String argument) throws Throwable {
	    if ("info".equals(level)) {
	    	matcher = loggedInfo(format, argument);
	    } else {
	    	throw new PendingException("matcher for level " + level + " is not yet available");
	    }
	}
	
	@Given("^a log matcher at \"(.*?)\" level with the String message \"(.*?)\"$")
	public void aLogMatcherAtLevelWithStringMessage(String level, String message) throws Throwable {
		if ("info".equals(level)) {
	    	matcher = loggedInfo(message);
	    } else {
	    	throw new PendingException("matcher for level " + level + " is not yet available");
	    }
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
