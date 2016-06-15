package uk.co.webamoeba.slf4j.junit.verification;

import org.slf4j.Marker;
import uk.co.webamoeba.slf4j.junit.log.Level;

/**
 * Test for {@link LogVerifier} which tests the Debug specific methods
 * 
 * @author James Kennard
 */
public class DebugLevelLogVerifierTest extends LevelLogVerifierTest {

	@Override
	protected Level level() {
		return Level.DEBUG;
	}

	@Override
	protected void loggedLevelMethodIsCalled(LogVerifier verifier, String message) {
		verifier.loggedDebug(message);
	}

	@Override
	protected void loggedLevelMethodIsCalled(LogVerifier verifier, String message, String... arguments) {
		verifier.loggedDebug(message, arguments);
	}

	@Override
	protected void loggedLevelMethodIsCalled(LogVerifier verifier, String message, Throwable throwable) {
		verifier.loggedDebug(message, throwable);
	}

	@Override
	protected void loggedLevelMethodIsCalled(LogVerifier verifier, Marker marker, String message) {
		verifier.loggedDebug(marker, message);
	}

	@Override
	protected void loggedLevelMethodIsCalled(LogVerifier verifier, Marker marker, String message, String... arguments) {
		verifier.loggedDebug(marker, message, arguments);
	}

	@Override
	protected void loggedLevelMethodIsCalled(LogVerifier verifier, Marker marker, String message, Throwable throwable) {
		verifier.loggedDebug(marker, message, throwable);
	}

}
