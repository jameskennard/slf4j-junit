package uk.co.webamoeba.slf4j.junit.logger;

import org.slf4j.Marker;
import uk.co.webamoeba.slf4j.junit.log.Level;

public class RecordingLoggerWarnTest extends RecordingLoggerLevelTest {

	public RecordingLoggerWarnTest() {
		super(Level.WARN);
	}

	@Override
	protected boolean isEnabled(RecordingLogger recordingLogger) {
		return recordingLogger.isWarnEnabled();
	}

	@Override
	protected boolean isEnabled(RecordingLogger recordingLogger, Marker marker) {
		return recordingLogger.isWarnEnabled(marker);
	}

	@Override
	protected void log(RecordingLogger recordingLogger, String message) {
		recordingLogger.warn(message);
	}

	@Override
	protected void log(String format, Object argument, RecordingLogger recordingLogger) {
		recordingLogger.warn(format, argument);
	}

	@Override
	protected void log(String format, Object argument1, Object argument2, RecordingLogger recordingLogger) {
		recordingLogger.warn(format, argument1, argument2);
	}

	@Override
	protected void log(String format, Object argument1, Object argument2, Object argument3, RecordingLogger recordingLogger) {
		recordingLogger.warn(format, argument1, argument2, argument3);
	}

	@Override
	protected void log(String message, Throwable throwable, RecordingLogger recordingLogger) {
		recordingLogger.warn(message, throwable);
	}

	@Override
	protected void log(Marker marker, String message, RecordingLogger recordingLogger) {
		recordingLogger.warn(marker, message);
	}

	@Override
	protected void log(Marker marker, String format, Object argument, RecordingLogger recordingLogger) {
		recordingLogger.warn(marker, format, argument);
	}

	@Override
	protected void log(Marker marker, String format, Object argument1, Object argument2, RecordingLogger recordingLogger) {
		recordingLogger.warn(marker, format, argument1, argument2);
	}

	@Override
	protected void log(Marker marker, String format, Object argument1, Object argument2, Object argument3, RecordingLogger recordingLogger) {
		recordingLogger.warn(marker, format, argument1, argument2, argument3);
	}

	@Override
	protected void log(Marker marker, String message, Throwable throwable, RecordingLogger recordingLogger) {
		recordingLogger.warn(marker, message, throwable);
	}
	
}
