package uk.co.webamoeba.slf4j.junit.logger;

import org.slf4j.Logger;
import org.slf4j.Marker;
import uk.co.webamoeba.slf4j.junit.log.Log;
import uk.co.webamoeba.slf4j.junit.log.LogEntry;
import uk.co.webamoeba.slf4j.junit.log.LogRegistry;

import static uk.co.webamoeba.slf4j.junit.log.Level.DEBUG;
import static uk.co.webamoeba.slf4j.junit.log.Level.ERROR;
import static uk.co.webamoeba.slf4j.junit.log.Level.INFO;
import static uk.co.webamoeba.slf4j.junit.log.Level.TRACE;
import static uk.co.webamoeba.slf4j.junit.log.Level.WARN;

/**
 * Records calls made to the {@link Logger} against the related singleton {@link Log}. This allows assertions to be made regarding what has and has not been logged.
 * 
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
		return true;
	}

	public boolean isTraceEnabled(Marker marker) {
		return true;
	}

	public boolean isDebugEnabled() {
		return true;
	}

	public boolean isDebugEnabled(Marker marker) {
		return true;
	}

	public boolean isInfoEnabled() {
		return true;
	}

	public boolean isInfoEnabled(Marker marker) {
		return true;
	}

	public boolean isWarnEnabled() {
		return true;
	}

	public boolean isWarnEnabled(Marker marker) {
		return true;
	}

	public boolean isErrorEnabled() {
		return true;
	}

	public boolean isErrorEnabled(Marker marker) {
		return true;
	}

	public void trace(String message) {
		log(new LogEntry(TRACE, message));
	}

	public void trace(String format, Object arg) {
		log(new LogEntry(TRACE, format, new Object[] { arg }));
	}

	public void trace(String format, Object arg1, Object arg2) {
		log(new LogEntry(TRACE, format, new Object[] { arg1, arg2 }));
	}

	public void trace(String format, Object... arguments) {
		log(new LogEntry(TRACE, format, arguments));
	}

	public void trace(String message, Throwable thowable) {
		log(new LogEntry(TRACE, message, thowable));
	}

	public void trace(Marker marker, String message) {
		log(new LogEntry(TRACE, marker, message));
	}

	public void trace(Marker marker, String format, Object arg) {
		log(new LogEntry(TRACE, marker, format, new Object[] { arg }));
	}

	public void trace(Marker marker, String format, Object arg1, Object arg2) {
		log(new LogEntry(TRACE, marker, format, new Object[] { arg1, arg2 }));
	}

	public void trace(Marker marker, String format, Object... arguments) {
		log(new LogEntry(TRACE, marker, format, arguments));
	}

	public void trace(Marker marker, String message, Throwable throwable) {
		log(new LogEntry(TRACE, marker, message, throwable));
	}

	public void debug(String message) {
		log(new LogEntry(DEBUG, message));
	}

	public void debug(String format, Object arg) {
		log(new LogEntry(DEBUG, format, new Object[] { arg }));
	}

	public void debug(String format, Object arg1, Object arg2) {
		log(new LogEntry(DEBUG, format, new Object[] { arg1, arg2 }));
	}

	public void debug(String format, Object... arguments) {
		log(new LogEntry(DEBUG, format, arguments));
	}

	public void debug(String message, Throwable thowable) {
		log(new LogEntry(DEBUG, message, thowable));
	}

	public void debug(Marker marker, String message) {
		log(new LogEntry(DEBUG, marker, message));
	}

	public void debug(Marker marker, String format, Object arg) {
		log(new LogEntry(DEBUG, marker, format, new Object[] { arg }));
	}

	public void debug(Marker marker, String format, Object arg1, Object arg2) {
		log(new LogEntry(DEBUG, marker, format, new Object[] { arg1, arg2 }));
	}

	public void debug(Marker marker, String format, Object... arguments) {
		log(new LogEntry(DEBUG, marker, format, arguments));
	}

	public void debug(Marker marker, String message, Throwable throwable) {
		log(new LogEntry(DEBUG, marker, message, throwable));
	}

	public void info(String message) {
		log(new LogEntry(INFO, message));
	}

	public void info(String format, Object arg) {
		log(new LogEntry(INFO, format, new Object[] { arg }));
	}

	public void info(String format, Object arg1, Object arg2) {
		log(new LogEntry(INFO, format, new Object[] { arg1, arg2 }));
	}

	public void info(String format, Object... arguments) {
		log(new LogEntry(INFO, format, arguments));
	}

	public void info(String message, Throwable thowable) {
		log(new LogEntry(INFO, message, thowable));
	}

	public void info(Marker marker, String message) {
		log(new LogEntry(INFO, marker, message));
	}

	public void info(Marker marker, String format, Object argument) {
		log(new LogEntry(INFO, marker, format, new Object[] { argument }));
	}

	public void info(Marker marker, String format, Object arg1, Object arg2) {
		info(marker, format, new Object[] { arg1, arg2 });
	}

	public void info(Marker marker, String format, Object... arguments) {
		log(new LogEntry(INFO, marker, format, arguments));
	}

	public void info(Marker marker, String message, Throwable throwable) {
		log(new LogEntry(INFO, marker, message, throwable));
	}

	public void warn(String message) {
		log(new LogEntry(WARN, message));
	}

	public void warn(String format, Object argument) {
		log(new LogEntry(WARN, format, new Object[] { argument }));
	}

	public void warn(String format, Object arg1, Object arg2) {
		log(new LogEntry(WARN, format, new Object[] { arg1, arg2 }));
	}

	public void warn(String format, Object... arguments) {
		log(new LogEntry(WARN, format, arguments));
	}

	public void warn(String message, Throwable throwable) {
		log(new LogEntry(WARN, message, throwable));
	}

	public void warn(Marker marker, String message) {
		log(new LogEntry(WARN, marker, message));
	}

	public void warn(Marker marker, String format, Object argument) {
		log(new LogEntry(WARN, marker, format, new Object[] { argument }));
	}

	public void warn(Marker marker, String format, Object arg1, Object arg2) {
		log(new LogEntry(WARN, marker, format, new Object[] { arg1, arg2 }));
	}

	public void warn(Marker marker, String format, Object... arguments) {
		log(new LogEntry(WARN, marker, format, arguments));
	}

	public void warn(Marker marker, String message, Throwable throwable) {
		log(new LogEntry(WARN, marker, message, throwable));
	}

	public void error(String message) {
		log(new LogEntry(ERROR, message));
	}

	public void error(String format, Object arg) {
		log(new LogEntry(ERROR, format, new Object[] { arg }));
	}

	public void error(String format, Object arg1, Object arg2) {
		log(new LogEntry(ERROR, format, new Object[] { arg1, arg2 }));
	}

	public void error(String format, Object... arguments) {
		log(new LogEntry(ERROR, format, arguments));
	}

	public void error(String message, Throwable thowable) {
		log(new LogEntry(ERROR, message, thowable));
	}

	public void error(Marker marker, String message) {
		log(new LogEntry(ERROR, marker, message));
	}

	public void error(Marker marker, String format, Object arg) {
		log(new LogEntry(ERROR, marker, format, new Object[] { arg }));
	}

	public void error(Marker marker, String format, Object arg1, Object arg2) {
		log(new LogEntry(ERROR, marker, format, new Object[] { arg1, arg2 }));
	}

	public void error(Marker marker, String format, Object... arguments) {
		log(new LogEntry(ERROR, marker, format, arguments));
	}

	public void error(Marker marker, String message, Throwable thowable) {
		log(new LogEntry(ERROR, marker, message, thowable));
	}

	private void log(LogEntry logEntry) {
		LogRegistry.getSingleton().getRegister(name).register(logEntry);
	}

}
