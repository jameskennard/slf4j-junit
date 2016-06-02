package uk.co.webamoeba.slf4j.junit;

import org.junit.rules.TestRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.webamoeba.slf4j.junit.context.LoggingContext;
import uk.co.webamoeba.slf4j.junit.logger.RecordingLogger;
import uk.co.webamoeba.slf4j.junit.specification.GroupLogEntrySpecificationFactory;
import uk.co.webamoeba.slf4j.junit.verification.LogVerifier;
import uk.co.webamoeba.slf4j.junit.verification.RecordingLoggerSpecificationVerifier;

/**
 * {@link LogVerification} enables verification of logging made with SLF4J.
 * 
 * <h1>Getting Started</h1>
 * <p>We create a normal JUnit test, and we add a JUnit {@link TestRule} in order to enable the verification of logging within the test.</p>
 * <pre>
 * import org.junit.Rule;
 * 
 * import static uk.co.webamoeba.slf4j.junit.LogVerification.enableLogging;
 * 
 * public class SomeTest {
 * 
 * 	{@code @Rule}
 * 	public EnableLogging enableLogging = enableLogging();
 * 
 * }
 * </pre>
 * <p>We can now create a test, and verify that some logging has taken place:</p>
 * <pre>
 * import static uk.co.webamoeba.slf4j.junit.LogVerification.verifyLogger;
 * import static uk.co.webamoeba.slf4j.junit.log.Level.INFO;
 * import static uk.co.webamoeba.slf4j.junit.log.Level.WARN;
 * 
 * ...
 * 
 * public void shouldDoSomething() {
 * 	// Given
 * 	String someArgument = "Hello everyone!";
 * 
 * 	// When
 * 	something.doSomething(someArgument);
 * 
 * 	// Then
 * 	verifyLogger(Something.class)
 * 			.logged(INFO, "I saw the 'Hello'")
 * 			.logged(WARN, "But I did not see the 'Goodbye'");;
 * }
 * </pre>
 * 
 * @author James Kennard
 */
public class LogVerification {
	
	private static final GroupLogEntrySpecificationFactory FACTORY = new GroupLogEntrySpecificationFactory();
	
	private static final RecordingLoggerSpecificationVerifier RECORDING_LOGGER_SPECIFICATION_VERIFIER = new RecordingLoggerSpecificationVerifier();

	/**
	 * Gets an {@link EnableLogging} {@link TestRule} for use in a JUnit test where we want to  verify some logging.
	 * <pre>
	 * @Rule
	 * public EnableLogging enableLogging = LogVerification.enableLogging();
	 * </pre>
	 * 
	 * @return An {@link EnableLogging} {@link TestRule}
	 */
	public static EnableLogging enableLogging() { 
		return EnableLogging.enableLogging();
	}
	
	/**
	 * Obtain an {@link LogVerifier} in order to verify that the {@link Logger} with the specified name logged something.
	 * 
	 * @param name Name of the {@link Logger} to which we want to verify something was logged
	 * @return An {@link LogVerifier} which allows us to verify certain things were logged
	 */
	public static LogVerifier verifyLogger(String name) {
		if (!LoggingContext.loggingIsEnabled()) {
			throw new IllegalStateException("Logging is not enabled, have you used the LogVerification#enableLogging() @Rule?");
		}
		Logger logger = LoggerFactory.getLogger(name);
		if (!(logger instanceof RecordingLogger)) {
			throw new IllegalStateException("Something went wrong, logging is enabled, but the Logger is not a recording logger");
		}
		
		return new LogVerifier(FACTORY, (RecordingLogger) logger, RECORDING_LOGGER_SPECIFICATION_VERIFIER);
	}

	/**
	 * Obtain an {@link LogVerifier} in order to verify that the {@link Logger} for the specified {@link Class} logged something. 
	 *  
	 * @param clazz {@link Class} used to create a name for the {@link Logger} for  which we want to verify something was logged
	 * @return An {@link LogVerifier} which allows us to verify certain things were logged
	 */
	public static LogVerifier verifyLogger(Class<?> clazz) {
		return verifyLogger(clazz.getName());
	}
}
