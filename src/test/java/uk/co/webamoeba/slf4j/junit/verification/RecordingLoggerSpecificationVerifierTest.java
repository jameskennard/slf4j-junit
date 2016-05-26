package uk.co.webamoeba.slf4j.junit.verification;

import java.util.Collections;
import org.junit.Test;
import uk.co.webamoeba.slf4j.junit.log.Log;
import uk.co.webamoeba.slf4j.junit.log.LogEntry;
import uk.co.webamoeba.slf4j.junit.logger.RecordingLogger;
import uk.co.webamoeba.slf4j.junit.specification.GroupLogEntrySpecification;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static uk.co.webamoeba.slf4j.junit.testsupport.LogEntryTestFactory.aLogEntry;

/**
 * Test for {@link RecordingLoggerSpecificationVerifier}.
 * 
 * @author James Kennard
 */
public class RecordingLoggerSpecificationVerifierTest {

	private RecordingLoggerSpecificationVerifier verifier = new RecordingLoggerSpecificationVerifier();

	@Test
	public void shouldVerifyRecordingLoggerSatisfiesSpecification() {
		// Given
		RecordingLogger logger = mock(RecordingLogger.class);
		LogEntry logEntry = aLogEntry();
		givenLoggerWillReturnLogEntries(logger, logEntry);

		GroupLogEntrySpecification specification = mock(GroupLogEntrySpecification.class);
		given(specification.isSatisfiedBy(logEntry)).willReturn(true);

		// When
		verifier.verifyRecordingLoggerSatisfiesSpecification(logger, specification);

		// Then
		// no AssertionError thrown
	}

	@Test
	public void shouldFailToVerifyRecordingLoggerSatisfiesSpecificationGivenNoLogEntriesSatisfySpecification() {
		// Given
		RecordingLogger logger = mock(RecordingLogger.class);
		LogEntry logEntry = aLogEntry();
		givenLoggerWillReturnLogEntries(logger, logEntry);

		GroupLogEntrySpecification specification = mock(GroupLogEntrySpecification.class);
		given(specification.isSatisfiedBy(logEntry)).willReturn(false);

		try {
			// When
			verifier.verifyRecordingLoggerSatisfiesSpecification(logger, specification);

			// Then
			fail();
		} catch (AssertionError e) {
			assertThat(e.getMessage(), containsString("There are LogEntries, but no LogEntry"));
		}
	}

	@Test
	public void shouldNotVerifyLoggedLevelGivenNothingWasLogged() {
		// Given
		RecordingLogger logger = mock(RecordingLogger.class);
		String logName = "Some Log Name";
		given(logger.getName()).willReturn(logName);
		givenLoggerWillReturnNoLogEntries(logger);

		GroupLogEntrySpecification specification = mock(GroupLogEntrySpecification.class);

		try {
			// When
			verifier.verifyRecordingLoggerSatisfiesSpecification(logger, specification);

			// Then
			fail();
		} catch (AssertionError e) {
			assertThat(e.getMessage(), is("No LogEntries were logged to \"" + logName + "\""));
		}
	}

	private static void givenLoggerWillReturnNoLogEntries(RecordingLogger logger) {
		Log log = mock(Log.class);
		given(logger.getLog()).willReturn(log);
		given(logger.getLog().getEntries()).willReturn(Collections.<LogEntry> emptyList());
	}

	private static void givenLoggerWillReturnLogEntries(RecordingLogger logger, LogEntry logEntry) {
		Log log = mock(Log.class);
		given(logger.getLog()).willReturn(log);
		given(logger.getLog().getEntries()).willReturn(Collections.singletonList(logEntry));
	}
}
