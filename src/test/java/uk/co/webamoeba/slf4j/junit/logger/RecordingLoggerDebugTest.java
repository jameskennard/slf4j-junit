package uk.co.webamoeba.slf4j.junit.logger;

import org.slf4j.Marker;
import uk.co.webamoeba.slf4j.junit.log.Level;

public class RecordingLoggerDebugTest extends RecordingLoggerLevelTest {

	public RecordingLoggerDebugTest() {
		super(Level.DEBUG);
	}

	@Override
	protected boolean isEnabled(RecordingLogger recordingLogger) {
		return recordingLogger.isDebugEnabled();
	}

	@Override
	protected boolean isEnabled(RecordingLogger recordingLogger, Marker marker) {
		return recordingLogger.isDebugEnabled(marker);
	}

	@Override
	protected void log(RecordingLogger recordingLogger, String message) {
		recordingLogger.debug(message);
	}

	@Override
	protected void log(String format, Object argument, RecordingLogger recordingLogger) {
		recordingLogger.debug(format, argument);
	}

	@Override
	protected void log(String format, Object argument1, Object argument2, RecordingLogger recordingLogger) {
		recordingLogger.debug(format, argument1, argument2);
	}

	@Override
	protected void log(String format, Object argument1, Object argument2, Object argument3, RecordingLogger recordingLogger) {
		recordingLogger.debug(format, argument1, argument2, argument3);
	}

	@Override
	protected void log(String message, Throwable throwable, RecordingLogger recordingLogger) {
		recordingLogger.debug(message, throwable);
	}

	@Override
	protected void log(Marker marker, String message, RecordingLogger recordingLogger) {
		recordingLogger.debug(marker, message);
	}

	@Override
	protected void log(Marker marker, String format, Object argument, RecordingLogger recordingLogger) {
		recordingLogger.debug(marker, format, argument);
	}

	@Override
	protected void log(Marker marker, String format, Object argument1, Object argument2, RecordingLogger recordingLogger) {
		recordingLogger.debug(marker, format, argument1, argument2);
	}

	@Override
	protected void log(Marker marker, String format, Object argument1, Object argument2, Object argument3, RecordingLogger recordingLogger) {
		recordingLogger.debug(marker, format, argument1, argument2, argument3);
	}

	@Override
	protected void log(Marker marker, String message, Throwable throwable, RecordingLogger recordingLogger) {
		recordingLogger.debug(marker, message, throwable);
	}
	
}
