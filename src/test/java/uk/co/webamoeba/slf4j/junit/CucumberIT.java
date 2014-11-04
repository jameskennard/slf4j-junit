package uk.co.webamoeba.slf4j.junit;

import static cucumber.api.SnippetType.CAMELCASE;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * Integration tests for slf4j-junit.
 * 
 * @author James Kennard
 */
@RunWith(Cucumber.class)
@CucumberOptions(format = { "pretty", "html:target/cucumber" }, tags = "~@NotReady", snippets = CAMELCASE)
public class CucumberIT {

}
