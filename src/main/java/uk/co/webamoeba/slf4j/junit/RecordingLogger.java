package uk.co.webamoeba.slf4j.junit;

import org.slf4j.Logger;
import org.slf4j.Marker;

import uk.co.webamoeba.slf4j.junit.event.LogEvent;
import uk.co.webamoeba.slf4j.junit.event.LogEventRegistry;

/**
 * @author James Kennard
 */
public class RecordingLogger implements Logger {

	private final String name;

	public RecordingLogger(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public boolean isTraceEnabled() {
		return false;
	}

	public boolean isTraceEnabled(Marker marker) {
		return false;
	}

	public boolean isDebugEnabled() {
		return false;
	}

	public boolean isDebugEnabled(Marker marker) {
		return false;
	}

	public boolean isInfoEnabled() {
		return true;
	}

	public boolean isInfoEnabled(Marker marker) {
		return true;
	}

	public boolean isWarnEnabled() {
		return false;
	}

	public boolean isWarnEnabled(Marker marker) {
		return false;
	}

	public boolean isErrorEnabled() {
		return false;
	}

	public boolean isErrorEnabled(Marker marker) {
		return false;
	}

	public void trace(String message) {
	}

	public void trace(String format, Object arg) {
	}

	public void trace(String format, Object arg1, Object arg2) {
	}

	public void trace(String format, Object... arguments) {
	}

	public void trace(String message, Throwable thowable) {
	}

	public void trace(Marker marker, String message) {
	}

	public void trace(Marker marker, String format, Object arg) {
	}

	public void trace(Marker marker, String format, Object arg1, Object arg2) {
	}

	public void trace(Marker marker, String format, Object... argArray) {
	}

	public void trace(Marker marker, String message, Throwable thowable) {
	}

	public void debug(String message) {
	}

	public void debug(String format, Object arg) {
	}

	public void debug(String format, Object arg1, Object arg2) {
	}

	public void debug(String format, Object... arguments) {
	}

	public void debug(String message, Throwable thowable) {
	}

	public void debug(Marker marker, String message) {
	}

	public void debug(Marker marker, String format, Object arg) {
	}

	public void debug(Marker marker, String format, Object arg1, Object arg2) {
	}

	public void debug(Marker marker, String format, Object... arguments) {
	}

	public void debug(Marker marker, String message, Throwable thowable) {
	}

	public void info(String message) {
		logEvent(new LogEvent(message));
	}

	public void info(String format, Object arg) {
		logEvent(new LogEvent(format, new Object[]{arg}));
	}

	public void info(String format, Object arg1, Object arg2) {
		logEvent(new LogEvent(format, new Object[] {arg1, arg2}));
	}

	public void info(String format, Object... arguments) {
		logEvent(new LogEvent(format, arguments));
	}

	public void info(String message, Throwable thowable) {
		logEvent(new LogEvent(message, thowable));
	}

	public void info(Marker marker, String message) {
		logEvent(new LogEvent(marker, message));
	}

	public void info(Marker marker, String format, Object arg) {
		throw new UnsupportedOperationException();
	}

	public void info(Marker marker, String format, Object arg1, Object arg2) {
		throw new UnsupportedOperationException();
	}

	public void info(Marker marker, String format, Object... arguments) {
		throw new UnsupportedOperationException();
	}

	public void info(Marker marker, String message, Throwable throwable) {
		logEvent(new LogEvent(marker, message, throwable));
	}

	public void warn(String message) {
	}

	public void warn(String format, Object arg) {
	}

	public void warn(String format, Object... arguments) {
	}

	public void warn(String format, Object arg1, Object arg2) {
	}

	public void warn(String message, Throwable thowable) {
	}

	public void warn(Marker marker, String message) {
	}

	public void warn(Marker marker, String format, Object arg) {
	}

	public void warn(Marker marker, String format, Object arg1, Object arg2) {
	}

	public void warn(Marker marker, String format, Object... arguments) {
	}

	public void warn(Marker marker, String message, Throwable thowable) {
	}

	public void error(String message) {
	}

	public void error(String format, Object arg) {
	}

	public void error(String format, Object arg1, Object arg2) {
	}

	public void error(String format, Object... arguments) {
	}

	public void error(String message, Throwable thowable) {
	}

	public void error(Marker marker, String message) {
	}

	public void error(Marker marker, String format, Object arg) {
	}

	public void error(Marker marker, String format, Object arg1, Object arg2) {
	}

	public void error(Marker marker, String format, Object... arguments) {
	}

	public void error(Marker marker, String message, Throwable thowable) {
	}
	
	private void logEvent(LogEvent logEvent) {
		LogEventRegistry.getSingleton().getRegister(name).register(logEvent);
	}

}
