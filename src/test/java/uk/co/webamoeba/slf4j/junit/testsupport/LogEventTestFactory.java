package uk.co.webamoeba.slf4j.junit.testsupport;

import org.slf4j.Marker;
import uk.co.webamoeba.slf4j.junit.event.Level;
import uk.co.webamoeba.slf4j.junit.event.LogEvent;
import uk.co.webamoeba.slf4j.junit.event.LogEvent.Message;

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

	public static LogEvent logEventWithMessage(Message message) {
		return new LogEvent(aLevel(), message.getMessageAsString());
	}
	
	public static LogEvent logEventWithMarker(Marker marker) {
		return new LogEvent(aLevel(), marker, "Some Message");
	}
	
}
