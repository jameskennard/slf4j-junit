package uk.co.webamoeba.slf4j.junit.logger;

import org.slf4j.Marker;
import uk.co.webamoeba.slf4j.junit.log.Level;

public class RecordingLoggerErrorTest extends RecordingLoggerLevelTest {

	public RecordingLoggerErrorTest() {
		super(Level.ERROR);
	}

	@Override
	protected boolean isEnabled(RecordingLogger recordingLogger) {
		return recordingLogger.isErrorEnabled();
	}

	@Override
	protected boolean isEnabled(RecordingLogger recordingLogger, Marker marker) {
		return recordingLogger.isErrorEnabled(marker);
	}

	@Override
	protected void log(RecordingLogger recordingLogger, String message) {
		recordingLogger.error(message);
	}

	@Override
	protected void log(String format, Object argument, RecordingLogger recordingLogger) {
		recordingLogger.error(format, argument);
	}

	@Override
	protected void log(String format, Object argument1, Object argument2, RecordingLogger recordingLogger) {
		recordingLogger.error(format, argument1, argument2);
	}

	@Override
	protected void log(String format, Object argument1, Object argument2, Object argument3, RecordingLogger recordingLogger) {
		recordingLogger.error(format, argument1, argument2, argument3);
	}

	@Override
	protected void log(String message, Throwable throwable, RecordingLogger recordingLogger) {
		recordingLogger.error(message, throwable);
	}

	@Override
	protected void log(Marker marker, String message, RecordingLogger recordingLogger) {
		recordingLogger.error(marker, message);
	}

	@Override
	protected void log(Marker marker, String format, Object argument, RecordingLogger recordingLogger) {
		recordingLogger.error(marker, format, argument);
	}

	@Override
	protected void log(Marker marker, String format, Object argument1, Object argument2, RecordingLogger recordingLogger) {
		recordingLogger.error(marker, format, argument1, argument2);
	}

	@Override
	protected void log(Marker marker, String format, Object argument1, Object argument2, Object argument3, RecordingLogger recordingLogger) {
		recordingLogger.error(marker, format, argument1, argument2, argument3);
	}

	@Override
	protected void log(Marker marker, String message, Throwable throwable, RecordingLogger recordingLogger) {
		recordingLogger.error(marker, message, throwable);
	}

}
