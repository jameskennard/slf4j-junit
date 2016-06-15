package uk.co.webamoeba.slf4j.junit.verification;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Marker;
import org.slf4j.helpers.BasicMarkerFactory;
import uk.co.webamoeba.slf4j.junit.log.Level;
import uk.co.webamoeba.slf4j.junit.log.LogEntry.StringMessage;
import uk.co.webamoeba.slf4j.junit.logger.RecordingLogger;
import uk.co.webamoeba.slf4j.junit.specification.GroupLogEntrySpecification;
import uk.co.webamoeba.slf4j.junit.specification.GroupLogEntrySpecificationFactory;

import static org.mockito.Answers.RETURNS_DEEP_STUBS;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Test for {@link LogVerifier} which tests level specific methods
 * 
 * @author James Kennard
 */
@RunWith(MockitoJUnitRunner.class)
public abstract class LevelLogVerifierTest {

	@InjectMocks
	private LogVerifier verifier;

	@Mock
	private GroupLogEntrySpecificationFactory factory;

	@Mock(answer = RETURNS_DEEP_STUBS)
	private RecordingLogger logger;

	@Mock
	private RecordingLoggerSpecificationVerifier recordingLoggerSpecificationVerifier;

	@Test
	public void shouldVerifyLoggedStringMessage() {
		// Given
		Level level = level();
		String message = aMessage();
		GroupLogEntrySpecification specification = mock(GroupLogEntrySpecification.class);
		given(factory.createGroupLogEntrySpecification(level, new StringMessage(message), null, null)).willReturn(specification);

		// When
		loggedLevelMethodIsCalled(verifier, message);

		// Then
		verify(recordingLoggerSpecificationVerifier).verifyRecordingLoggerSatisfiesSpecification(logger, specification);
	}

	@Test
	public void shouldVerifyLoggedStringMessageWithArguments() {
		// Given
		Level level = level();
		String message = "Message about {}";
		String argument = "an argument";
		StringMessage stringMessage = new StringMessage("Message about an argument");

		GroupLogEntrySpecification specification = mock(GroupLogEntrySpecification.class);
		given(factory.createGroupLogEntrySpecification(level, stringMessage, null, null)).willReturn(specification);

		// When
		loggedLevelMethodIsCalled(verifier, message, argument);

		// Then
		verify(recordingLoggerSpecificationVerifier).verifyRecordingLoggerSatisfiesSpecification(logger, specification);
	}

	@Test
	public void shouldVerifyLoggedStringMessageAndThrowable() {
		// Given
		Level level = level();
		String message = aMessage();
		Throwable throwable = new Throwable("Some Throwable");

		GroupLogEntrySpecification specification = mock(GroupLogEntrySpecification.class);
		given(factory.createGroupLogEntrySpecification(level, new StringMessage(message), throwable, null)).willReturn(specification);

		// When
		loggedLevelMethodIsCalled(verifier, message, throwable);

		// Then
		verify(recordingLoggerSpecificationVerifier).verifyRecordingLoggerSatisfiesSpecification(logger, specification);
	}

	@Test
	public void shouldVerifyLoggedMarkerAndStringMessage() {
		// Given
		Level level = level();
		Marker marker = aMarker();
		String message = aMessage();
		GroupLogEntrySpecification specification = mock(GroupLogEntrySpecification.class);
		given(factory.createGroupLogEntrySpecification(level, new StringMessage(message), null, marker)).willReturn(specification);

		// When
		loggedLevelMethodIsCalled(verifier, marker, message);

		// Then
		verify(recordingLoggerSpecificationVerifier).verifyRecordingLoggerSatisfiesSpecification(logger, specification);
	}

	@Test
	public void shouldVerifyLoggedMarkerAndStringMessageWithArguments() {
		// Given
		Level level = level();
		Marker marker = aMarker();
		String message = "Message about {}";
		String argument = "an argument";
		StringMessage stringMessage = new StringMessage("Message about an argument");

		GroupLogEntrySpecification specification = mock(GroupLogEntrySpecification.class);
		given(factory.createGroupLogEntrySpecification(level, stringMessage, null, marker)).willReturn(specification);

		// When
		loggedLevelMethodIsCalled(verifier, marker, message, argument);

		// Then
		verify(recordingLoggerSpecificationVerifier).verifyRecordingLoggerSatisfiesSpecification(logger, specification);
	}

	@Test
	public void shouldVerifyLoggedMarkerAndStringMessageAndThrowable() {
		// Given
		Level level = level();
		Marker marker = aMarker();
		String message = aMessage();
		Throwable throwable = new Throwable("Some Throwable");

		GroupLogEntrySpecification specification = mock(GroupLogEntrySpecification.class);
		given(factory.createGroupLogEntrySpecification(level, new StringMessage(message), throwable, marker)).willReturn(specification);

		// When
		loggedLevelMethodIsCalled(verifier, marker, message, throwable);

		// Then
		verify(recordingLoggerSpecificationVerifier).verifyRecordingLoggerSatisfiesSpecification(logger, specification);
	}

	protected abstract Level level();

	protected abstract void loggedLevelMethodIsCalled(LogVerifier verifier, String message);

	protected abstract void loggedLevelMethodIsCalled(LogVerifier verifier, String message, String... arguments);

	protected abstract void loggedLevelMethodIsCalled(LogVerifier verifier, String message, Throwable thowable);

	protected abstract void loggedLevelMethodIsCalled(LogVerifier verifier, Marker marker, String message);

	protected abstract void loggedLevelMethodIsCalled(LogVerifier verifier, Marker marker, String message, String... arguments);

	protected abstract void loggedLevelMethodIsCalled(LogVerifier verifier, Marker marker, String message, Throwable thowable);

	private String aMessage() {
		return "Some Message";
	}

	private Marker aMarker() {
		return new BasicMarkerFactory().getMarker("Some Marker");
	}

}
