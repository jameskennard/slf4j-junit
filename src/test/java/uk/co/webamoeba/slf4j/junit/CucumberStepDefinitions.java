package uk.co.webamoeba.slf4j.junit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static uk.co.webamoeba.slf4j.junit.LoggingMatchers.loggedInfo;
import static uk.co.webamoeba.slf4j.junit.LoggingMatchers.logger;

import org.hamcrest.Matcher;
import org.slf4j.Logger;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CucumberStepDefinitions {

	private Logger logger;
	
	private Matcher<Logger> matcher;

	private boolean matches;
	
	@Given("^a logger$")
	public void aLogger() {
		logger = logger(CucumberStepDefinitions.class);
	}
	
	@Given("^a String message \"(.*?)\" logged at info level$")
	public void aStringMessageLoggedAtInfoLevel(String messageAsString) {
		logger.info(messageAsString);
	}
	
	@Given("^a loggedInfo matcher with the String message \"(.*?)\"$")
	public void aLoggedInfoMatcherWithTheStringMessage(String messageAsString) {
		matcher = loggedInfo(messageAsString);
	}
	
	@When("^I match the logger$")
	public void iMatchTheLogger() {
	    matches = matcher.matches(logger);
	}
	
	@Then("^it will match$")
	public void itWillMatch() {
		assertThat(matches, is(true));
	}
	
}
