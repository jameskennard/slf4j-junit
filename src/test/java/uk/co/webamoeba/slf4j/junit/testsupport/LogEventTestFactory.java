package uk.co.webamoeba.slf4j.junit.testsupport;

import uk.co.webamoeba.slf4j.junit.event.Level;
import uk.co.webamoeba.slf4j.junit.event.LogEvent;

/**
 * Factory which creates {@link LogEvent LogEvents} 
 * 
 * @author James Kennard
 */
public class LogEventTestFactory {

	public static LogEvent logEventAtLevel(Level level) {
		return new LogEvent(level, "Some Message");
	}
	
}
