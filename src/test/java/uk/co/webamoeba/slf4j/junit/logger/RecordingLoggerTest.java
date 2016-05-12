package uk.co.webamoeba.slf4j.junit.logger;

import org.junit.Test;
import uk.co.webamoeba.slf4j.junit.log.LogRegistry;

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
		LogRegistry registry = new LogRegistry();
		RecordingLogger recordingLogger = new RecordingLogger(expectedName, registry);

		// When
		String name = recordingLogger.getName();

		// Then
		assertThat(name, is(expectedName));
	}

}
