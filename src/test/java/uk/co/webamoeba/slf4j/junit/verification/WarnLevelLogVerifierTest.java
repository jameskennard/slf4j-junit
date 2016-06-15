package uk.co.webamoeba.slf4j.junit.verification;

import org.slf4j.Marker;
import uk.co.webamoeba.slf4j.junit.log.Level;

/**
 * Test for {@link LogVerifier} which tests the Warn specific methods
 * 
 * @author James Kennard
 */
public class WarnLevelLogVerifierTest extends LevelLogVerifierTest {

	@Override
	protected Level level() {
		return Level.WARN;
	}

	@Override
	protected void loggedLevelMethodIsCalled(LogVerifier verifier, String message) {
		verifier.loggedWarn(message);
	}

	@Override
	protected void loggedLevelMethodIsCalled(LogVerifier verifier, String message, String... arguments) {
		verifier.loggedWarn(message, arguments);
	}

	@Override
	protected void loggedLevelMethodIsCalled(LogVerifier verifier, String message, Throwable throwable) {
		verifier.loggedWarn(message, throwable);
	}

	@Override
	protected void loggedLevelMethodIsCalled(LogVerifier verifier, Marker marker, String message) {
		verifier.loggedWarn(marker, message);
	}

	@Override
	protected void loggedLevelMethodIsCalled(LogVerifier verifier, Marker marker, String message, String... arguments) {
		verifier.loggedWarn(marker, message, arguments);
	}

	@Override
	protected void loggedLevelMethodIsCalled(LogVerifier verifier, Marker marker, String message, Throwable throwable) {
		verifier.loggedWarn(marker, message, throwable);
	}

}
