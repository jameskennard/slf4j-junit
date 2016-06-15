package uk.co.webamoeba.slf4j.junit.verification;

import org.slf4j.Marker;
import uk.co.webamoeba.slf4j.junit.log.Level;

/**
 * Test for {@link LogVerifier} which tests the Trace specific methods
 * 
 * @author James Kennard
 */
public class TraceLevelLogVerifierTest extends LevelLogVerifierTest {

	@Override
	protected Level level() {
		return Level.TRACE;
	}

	@Override
	protected void loggedLevelMethodIsCalled(LogVerifier verifier, String message) {
		verifier.loggedTrace(message);
	}

	@Override
	protected void loggedLevelMethodIsCalled(LogVerifier verifier, String message, String... arguments) {
		verifier.loggedTrace(message, arguments);
	}

	@Override
	protected void loggedLevelMethodIsCalled(LogVerifier verifier, String message, Throwable throwable) {
		verifier.loggedTrace(message, throwable);
	}

	@Override
	protected void loggedLevelMethodIsCalled(LogVerifier verifier, Marker marker, String message) {
		verifier.loggedTrace(marker, message);
	}

	@Override
	protected void loggedLevelMethodIsCalled(LogVerifier verifier, Marker marker, String message, String... arguments) {
		verifier.loggedTrace(marker, message, arguments);
	}

	@Override
	protected void loggedLevelMethodIsCalled(LogVerifier verifier, Marker marker, String message, Throwable throwable) {
		verifier.loggedTrace(marker, message, throwable);
	}

}
