package uk.co.webamoeba.slf4j.junit;

import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
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
	
	// TODO make RecordingLogger implement org.slf4j.Logger
	
}
