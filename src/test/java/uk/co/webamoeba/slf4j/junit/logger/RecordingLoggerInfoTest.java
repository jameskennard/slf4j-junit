package uk.co.webamoeba.slf4j.junit.logger;

import org.slf4j.Marker;
import uk.co.webamoeba.slf4j.junit.log.Level;

public class RecordingLoggerInfoTest extends RecordingLoggerLevelTest {

	public RecordingLoggerInfoTest() {
		super(Level.INFO);
	}

	@Override
	protected boolean isEnabled(RecordingLogger recordingLogger) {
		return recordingLogger.isInfoEnabled();
	}

	@Override
	protected boolean isEnabled(RecordingLogger recordingLogger, Marker marker) {
		return recordingLogger.isInfoEnabled(marker);
	}

	@Override
	protected void log(RecordingLogger recordingLogger, String message) {
		recordingLogger.info(message);
	}

	@Override
	protected void log(String format, Object argument, RecordingLogger recordingLogger) {
		recordingLogger.info(format, argument);
	}

	@Override
	protected void log(String format, Object argument1, Object argument2, RecordingLogger recordingLogger) {
		recordingLogger.info(format, argument1, argument2);
	}

	@Override
	protected void log(String format, Object argument1, Object argument2, Object argument3, RecordingLogger recordingLogger) {
		recordingLogger.info(format, argument1, argument2, argument3);
	}

	@Override
	protected void log(String message, Throwable throwable, RecordingLogger recordingLogger) {
		recordingLogger.info(message, throwable);
	}

	@Override
	protected void log(Marker marker, String message, RecordingLogger recordingLogger) {
		recordingLogger.info(marker, message);
	}

	@Override
	protected void log(Marker marker, String format, Object argument, RecordingLogger recordingLogger) {
		recordingLogger.info(marker, format, argument);
	}

	@Override
	protected void log(Marker marker, String format, Object argument1, Object argument2, RecordingLogger recordingLogger) {
		recordingLogger.info(marker, format, argument1, argument2);
	}

	@Override
	protected void log(Marker marker, String format, Object argument1, Object argument2, Object argument3, RecordingLogger recordingLogger) {
		recordingLogger.info(marker, format, argument1, argument2, argument3);
	}

	@Override
	protected void log(Marker marker, String message, Throwable throwable, RecordingLogger recordingLogger) {
		recordingLogger.info(marker, message, throwable);
	}

}
