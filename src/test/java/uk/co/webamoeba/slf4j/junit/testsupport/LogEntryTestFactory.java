package uk.co.webamoeba.slf4j.junit.testsupport;

import java.util.List;
import org.slf4j.Marker;
import uk.co.webamoeba.slf4j.junit.log.Level;
import uk.co.webamoeba.slf4j.junit.log.LogEntry;
import uk.co.webamoeba.slf4j.junit.log.LogRegistry;
import uk.co.webamoeba.slf4j.junit.log.LogEntry.Message;

import static uk.co.webamoeba.slf4j.junit.testsupport.LevelTestFactory.aLevel;

/**
 * Factory which creates {@link LogEntry LogEntries}
 * 
 * @author James Kennard
 */
public class LogEntryTestFactory {

	public static List<LogEntry> logEntries(String name) {
		return LogRegistry.getSingleton().getRegister(name).getEntries();
	}
	
	public static LogEntry aLogEntry() {
		return new LogEntry(aLevel(), "Some Message");
	}
	
	public static LogEntry logEntryAtLevel(Level level) {
		return new LogEntry(level, "Some Message");
	}

	public static LogEntry logEntryWithThrowable(Throwable throwable) {
		return new LogEntry(aLevel(), "Some Message", throwable);
	}

	public static LogEntry logEntryWithMessage(Message message) {
		return new LogEntry(aLevel(), message.getMessageAsString());
	}

	public static LogEntry logEntryWithMarker(Marker marker) {
		return new LogEntry(aLevel(), marker, "Some Message");
	}

}
