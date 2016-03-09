package uk.co.webamoeba.slf4j.junit.testsupport;

import uk.co.webamoeba.slf4j.junit.event.Level;
import uk.co.webamoeba.slf4j.junit.event.LogEvent;

import static uk.co.webamoeba.slf4j.junit.testsupport.LevelTestFactory.aLevel;

/**
 * Factory which creates {@link LogEvent LogEvents} 
 * 
 * @author James Kennard
 */
public class LogEventTestFactory {

	public static LogEvent logEventAtLevel(Level level) {
		return new LogEvent(level, "Some Message");
	}
	
	public static LogEvent logEventWithThrowable(Throwable throwable) {
		return new LogEvent(aLevel(), "Some Message", throwable);
	}
	
}
