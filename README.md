# slf4j-junit

<img src="https://travis-ci.org/jameskennard/slf4j-junit.svg?branch=develop" />
<img src="http://img.shields.io/badge/license-MIT-green.svg" />
[![Coverage Status](https://img.shields.io/codecov/c/github/jameskennard/slf4j-junit.svg)](https://codecov.io/github/jameskennard/slf4j-junit)

slf4j-junit enables verification of logging made with SLF4J.

<h1>Getting Started</h1>
<p>We create a normal JUnit test, and we add a JUnit TestRule in order to enable the verification of logging within the test.</p>
<pre>
import org.junit.Rule;

import static uk.co.webamoeba.slf4j.junit.LogVerification.enableLogging;

public class SomeTest {

 	@Rule
 	public EnableLogging enableLogging = enableLogging();
 
}
</pre>
<p>We can now create a test, and verify that some logging has taken place:</p>
<pre>
import static uk.co.webamoeba.slf4j.junit.LogVerification.verifyLogger;
import static uk.co.webamoeba.slf4j.junit.log.Level.INFO;

...

public void shouldDoSomething() {
	// Given
	String someArgument = "Hello everyone!";

	// When
	something.doSomething(someArgument);

	// Then
	verifyLogger(Something.class).logged(INFO, "I saw the 'Hello everyone!'");
}
</pre>
