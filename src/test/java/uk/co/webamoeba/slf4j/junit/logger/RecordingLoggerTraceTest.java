package uk.co.webamoeba.slf4j.junit.logger;

import org.slf4j.Marker;
import uk.co.webamoeba.slf4j.junit.log.Level;

public class RecordingLoggerTraceTest extends RecordingLoggerLevelTest {

	public RecordingLoggerTraceTest() {
		super(Level.TRACE);
	}

	@Override
	protected boolean isEnabled(RecordingLogger recordingLogger) {
		return recordingLogger.isTraceEnabled();
	}

	@Override
	protected boolean isEnabled(RecordingLogger recordingLogger, Marker marker) {
		return recordingLogger.isTraceEnabled(marker);
	}

	@Override
	protected void log(RecordingLogger recordingLogger, String message) {
		recordingLogger.trace(message);
	}

	@Override
	protected void log(String format, Object argument, RecordingLogger recordingLogger) {
		recordingLogger.trace(format, argument);
	}

	@Override
	protected void log(String format, Object argument1, Object argument2, RecordingLogger recordingLogger) {
		recordingLogger.trace(format, argument1, argument2);
	}

	@Override
	protected void log(String format, Object argument1, Object argument2, Object argument3, RecordingLogger recordingLogger) {
		recordingLogger.trace(format, argument1, argument2, argument3);
	}

	@Override
	protected void log(String message, Throwable throwable, RecordingLogger recordingLogger) {
		recordingLogger.trace(message, throwable);
	}

	@Override
	protected void log(Marker marker, String message, RecordingLogger recordingLogger) {
		recordingLogger.trace(marker, message);
	}

	@Override
	protected void log(Marker marker, String format, Object argument, RecordingLogger recordingLogger) {
		recordingLogger.trace(marker, format, argument);
	}

	@Override
	protected void log(Marker marker, String format, Object argument1, Object argument2, RecordingLogger recordingLogger) {
		recordingLogger.trace(marker, format, argument1, argument2);
	}

	@Override
	protected void log(Marker marker, String format, Object argument1, Object argument2, Object argument3, RecordingLogger recordingLogger) {
		recordingLogger.trace(marker, format, argument1, argument2, argument3);
	}

	@Override
	protected void log(Marker marker, String message, Throwable throwable, RecordingLogger recordingLogger) {
		recordingLogger.trace(marker, message, throwable);
	}

}
