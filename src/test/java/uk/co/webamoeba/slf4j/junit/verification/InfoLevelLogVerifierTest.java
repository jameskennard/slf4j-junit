package uk.co.webamoeba.slf4j.junit.verification;

import org.slf4j.Marker;
import uk.co.webamoeba.slf4j.junit.log.Level;

/**
 * Test for {@link LogVerifier} which tests the Info specific methods
 * 
 * @author James Kennard
 */
public class InfoLevelLogVerifierTest extends LevelLogVerifierTest {

	@Override
	protected Level level() {
		return Level.INFO;
	}

	@Override
	protected void loggedLevelMethodIsCalled(LogVerifier verifier, String message) {
		verifier.loggedInfo(message);
	}

	@Override
	protected void loggedLevelMethodIsCalled(LogVerifier verifier, String message, String... arguments) {
		verifier.loggedInfo(message, (Object[]) arguments);
	}

	@Override
	protected void loggedLevelMethodIsCalled(LogVerifier verifier, String message, Throwable throwable) {
		verifier.loggedInfo(message, throwable);
	}

	@Override
	protected void loggedLevelMethodIsCalled(LogVerifier verifier, Marker marker, String message) {
		verifier.loggedInfo(marker, message);
	}

	@Override
	protected void loggedLevelMethodIsCalled(LogVerifier verifier, Marker marker, String message, String... arguments) {
		verifier.loggedInfo(marker, message, (Object[]) arguments);
	}

	@Override
	protected void loggedLevelMethodIsCalled(LogVerifier verifier, Marker marker, String message, Throwable throwable) {
		verifier.loggedInfo(marker, message, throwable);
	}

}
