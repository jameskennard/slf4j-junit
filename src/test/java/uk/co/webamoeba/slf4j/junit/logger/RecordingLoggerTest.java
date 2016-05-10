package uk.co.webamoeba.slf4j.junit.logger;

import org.junit.Test;
import org.slf4j.Logger;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Test for {@link RecordingLogger}, also refer to {@link RecordingLoggerLevelTest}
 * 
 * @author James Kennard
 */
public class RecordingLoggerTest {

	@Test
	public void shouldGetName() {
		// Given
		String expectedName = "expected name";
		RecordingLogger recordingLogger = new RecordingLogger(expectedName);

		// When
		String name = recordingLogger.getName();

		// Then
		assertThat(name, is(expectedName));
	}

	@Test
	public void shouldImplementLogger() {
		// Given
		RecordingLogger recordingLogger = new RecordingLogger("a recording logger");

		// When
		boolean isLogger = Logger.class.isInstance(recordingLogger);

		// Then
		assertThat(isLogger, is(true));
	}

}
