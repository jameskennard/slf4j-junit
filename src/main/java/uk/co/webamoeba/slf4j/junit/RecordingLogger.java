package uk.co.webamoeba.slf4j.junit;

import org.slf4j.Marker;

/**
 * @author James Kennard
 */
public class RecordingLogger {

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
		return false;
	}
	
	public boolean isInfoEnabled(Marker marker) {
		return false;
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

}
