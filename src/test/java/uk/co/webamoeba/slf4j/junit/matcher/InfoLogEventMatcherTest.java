package uk.co.webamoeba.slf4j.junit.matcher;

import org.slf4j.Logger;
import org.slf4j.Marker;


/**
 * @author James Kennard
 */
public class InfoLogEventMatcherTest extends LogEventMatcherTest {

	@Override
	protected InfoLogEventMatcher matcher() {
		return new InfoLogEventMatcher("Message");
	}
	
	@Override
	protected InfoLogEventMatcher matcher(String message) {
		return new InfoLogEventMatcher(message);
	}
	
	@Override
	protected LogEventMatcher matcher(String format, String argument) {
		return new InfoLogEventMatcher(format, argument);
	}
	
	@Override
	protected InfoLogEventMatcher matcher(Marker marker, String message) {
		return new InfoLogEventMatcher(marker, message);
	}
	
	@Override
	protected InfoLogEventMatcher matcher(String message, Throwable throwable) {
		return new InfoLogEventMatcher(message, throwable);
	}
	
	@Override
	protected InfoLogEventMatcher matcher(Marker marker, String message, Throwable throwable) {
		return new InfoLogEventMatcher(marker, message, throwable);
	}
	
	@Override
	protected InfoLogEventMatcher matcher(Marker marker, String format, String argument) {
		return new InfoLogEventMatcher(marker, format, argument);
	}
	
	@Override
	protected void log(String message, Logger logger) {
		logger.info(message);
	}
	
	@Override
	protected void log(String format, String argument, Logger logger) {
		logger.info(format, argument);
	}
	
	@Override
	protected InfoLogEventMatcher log(String format, String argument) {
		return new InfoLogEventMatcher(format, argument);
	}
	
	@Override
	protected void log(Marker marker, String message, Logger logger) {
		logger.info(marker, message);
	}
	
	@Override
	protected void log(String message, Throwable throwable, Logger logger) {
		logger.info(message, throwable);
	}
	
	@Override
	protected void log(Marker marker, String message, Throwable throwable, Logger logger) {
		logger.info(marker, message, throwable);
	}
	
	@Override
	protected void log(Marker marker, String format, String argument, Logger logger) {
		logger.info(marker, format, argument);
	}
	
	@Override
	protected String expectedDescribeLevel() {
		return "info";
	}
	
	@Override
	protected String expectedDescribeFunction() {
		return "loggedInfo";
	}

}
