package uk.co.webamoeba.slf4j.junit.logger;

import org.slf4j.Logger;
import org.slf4j.Marker;
import uk.co.webamoeba.slf4j.junit.log.Level;
import uk.co.webamoeba.slf4j.junit.log.Log;
import uk.co.webamoeba.slf4j.junit.log.LogEntry;
import uk.co.webamoeba.slf4j.junit.log.LogRegistry;

import static uk.co.webamoeba.slf4j.junit.log.Level.INFO;

/**
 * Records calls made to the {@link Logger} against the related singleton {@link Log}. This allows
 * assertion to be made regarding the what has and has not been logged.
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
		return true;
	}

	public boolean isWarnEnabled(Marker marker) {
		return true;
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
		log(new LogEntry(Level.WARN, message));
	}

	public void warn(String format, Object argument) {
		log(new LogEntry(Level.WARN, format, new Object[] { argument }));
	}
	
	public void warn(String format, Object arg1, Object arg2) {
		log(new LogEntry(Level.WARN, format, new Object[] { arg1, arg2 }));
	}

	public void warn(String format, Object... arguments) {
		log(new LogEntry(Level.WARN, format, arguments));
	}

	public void warn(String message, Throwable throwable) {
		log(new LogEntry(Level.WARN, message, throwable));
	}

	public void warn(Marker marker, String message) {
		log(new LogEntry(Level.WARN, marker, message));
	}

	public void warn(Marker marker, String format, Object argument) {
		log(new LogEntry(Level.WARN, marker, format, new Object[] { argument }));
	}

	public void warn(Marker marker, String format, Object arg1, Object arg2) {
		log(new LogEntry(Level.WARN, marker, format, new Object[] { arg1, arg2 }));
	}

	public void warn(Marker marker, String format, Object... arguments) {
		log(new LogEntry(Level.WARN, marker, format, arguments));
	}

	public void warn(Marker marker, String message, Throwable throwable) {
		log(new LogEntry(Level.WARN, marker, message, throwable));
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

	private void log(LogEntry logEntry) {
		LogRegistry.getSingleton().getRegister(name).register(logEntry);
	}

}
