package uk.co.webamoeba.slf4j.junit.verification;

import org.slf4j.Marker;
import uk.co.webamoeba.slf4j.junit.log.Level;

/**
 * Test for {@link LogVerifier} which tests the Error specific methods
 * 
 * @author James Kennard
 */
public class ErrorLevelLogVerifierTest extends LevelLogVerifierTest {

	@Override
	protected Level level() {
		return Level.ERROR;
	}

	@Override
	protected void loggedLevelMethodIsCalled(LogVerifier verifier, String message) {
		verifier.loggedError(message);
	}

	@Override
	protected void loggedLevelMethodIsCalled(LogVerifier verifier, String message, String... arguments) {
		verifier.loggedError(message, (Object[]) arguments);
	}

	@Override
	protected void loggedLevelMethodIsCalled(LogVerifier verifier, String message, Throwable throwable) {
		verifier.loggedError(message, throwable);
	}

	@Override
	protected void loggedLevelMethodIsCalled(LogVerifier verifier, Marker marker, String message) {
		verifier.loggedError(marker, message);
	}

	@Override
	protected void loggedLevelMethodIsCalled(LogVerifier verifier, Marker marker, String message, String... arguments) {
		verifier.loggedError(marker, message, (Object[]) arguments);
	}

	@Override
	protected void loggedLevelMethodIsCalled(LogVerifier verifier, Marker marker, String message, Throwable throwable) {
		verifier.loggedError(marker, message, throwable);
	}

}
