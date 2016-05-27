package uk.co.webamoeba.slf4j.junit;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import uk.co.webamoeba.slf4j.junit.log.Level;
import uk.co.webamoeba.slf4j.junit.verification.OnGoingLogVerifier;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static uk.co.webamoeba.slf4j.junit.log.Level.INFO;

public class LogVerificationTest {

	@Rule
	public EnableLogging enableLogging = LogVerification.enableLogging();

	@Test
	public void shouldGetOnGoingLogVerifierWhenVerifyLogger() {
		// Given
		String name = "some-logger";

		// When
		OnGoingLogVerifier verifier = LogVerification.verifyLogger(name);

		// Then
		assertThat(verifier, verifiesLogging(name));
	}

	@Test
	public void shouldGetOnGoingLogVerifierWhenVerifyLoggerAndLoggerIdentifiedByClass() {
		// Given
		Class<?> clazz = getClass();

		// When
		OnGoingLogVerifier verifier = LogVerification.verifyLogger(clazz);

		// Then
		assertThat(verifier, verifiesLogging(clazz.getName()));
	}

	@Test
	@DisableLogging
	public void shouldFailToVerifyLoggerGivenLoggingIsNotEnabled() {
		// Given
		String name = "some-logger";

		try {
			// When
			LogVerification.verifyLogger(name);

			// Then
			fail("Should throw exceptionClass");
		} catch (IllegalStateException e) {
			assertThat(e.getMessage(), is("Logging is not enabled, have you used the LogVerification#enableLogging() @Rule?"));
		}
	}

	@Test
	@DisableLogging
	public void shouldFailToVerifyLoggerGivenLoggingIsNotEnabledAndLoggerIdentifiedByClass() {
		// Given
		Class<?> clazz = getClass();

		try {
			// When
			LogVerification.verifyLogger(clazz);

			// Then
			fail("Should throw exceptionClass");
		} catch (IllegalStateException e) {
			assertThat(e.getMessage(), is("Logging is not enabled, have you used the LogVerification#enableLogging() @Rule?"));
		}
	}

	private static Matcher<OnGoingLogVerifier> verifiesLogging(final String name) {
		return new TypeSafeDiagnosingMatcher<OnGoingLogVerifier>() {

			public void describeTo(Description description) {
				description.appendText("OnGoingLogVerifier verifies logging");
			}

			@Override
			protected boolean matchesSafely(OnGoingLogVerifier verifier, Description mismatchDescription) {
				Level level = INFO;
				String aLoggedMessage = "Logged Message";
				String anUnloggedMessage = "Unlogged Message";
				LoggerFactory.getLogger(name).info(aLoggedMessage);

				if (!verifiesWasLogged(verifier, level, aLoggedMessage)) {
					mismatchDescription.appendText("Failed to verify a message that had been logged, was logged");
					return false;
				}
				if (verifiesWasLogged(verifier, level, anUnloggedMessage)) {
					mismatchDescription.appendText("Verified a message that was not logged, had been logged");
					return false;
				}
				return true;
			}

			private boolean verifiesWasLogged(OnGoingLogVerifier verifier, Level level, String aLoggedMessage) {
				try {
					verifier.logged(level, aLoggedMessage);
				} catch (AssertionError e) {
					return false;
				}
				return true;
			}
		};
	}

}
