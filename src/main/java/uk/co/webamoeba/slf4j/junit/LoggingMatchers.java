package uk.co.webamoeba.slf4j.junit;

import org.hamcrest.Matcher;
import org.slf4j.Logger;
import org.slf4j.Marker;

import uk.co.webamoeba.slf4j.junit.matcher.InfoLogEventMatcher;

/**
 * @author James Kennard
 */
public class LoggingMatchers {

	public static Matcher<Logger> loggedInfo(String message) {
		return new InfoLogEventMatcher(message);
	}
	
	public static Matcher<Logger> loggedInfo(String format, Object... arguments) {
		return new InfoLogEventMatcher(format, arguments);
	}
	
	public static Matcher<Logger> loggedInfo(String message, Throwable throwable) {
		return new InfoLogEventMatcher(message, throwable);
	}
	
	public static Matcher<Logger> loggedInfo(Marker marker, String message) {
		return null;
	}
	
}
