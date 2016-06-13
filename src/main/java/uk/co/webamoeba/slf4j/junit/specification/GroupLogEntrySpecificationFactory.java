package uk.co.webamoeba.slf4j.junit.specification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Marker;
import uk.co.webamoeba.slf4j.junit.assertion.CheckArgument;
import uk.co.webamoeba.slf4j.junit.log.Level;
import uk.co.webamoeba.slf4j.junit.log.LogEntry;
import uk.co.webamoeba.slf4j.junit.log.LogEntry.Message;

/**
 * Factory that can create a {@link GroupLogEntrySpecification} for a {@link Level}, {@link Message}, {@link Throwable} and {@link Marker}.
 * 
 * @author James Kennard
 */
public class GroupLogEntrySpecificationFactory {

	private static final Map<Level, LevelLogEntrySpecification> LEVEL_SPECIFICATIONS = new HashMap<Level, LevelLogEntrySpecification>();

	static {
		LEVEL_SPECIFICATIONS.put(Level.ERROR, new LevelLogEntrySpecification(Level.ERROR));
		LEVEL_SPECIFICATIONS.put(Level.WARN, new LevelLogEntrySpecification(Level.WARN));
		LEVEL_SPECIFICATIONS.put(Level.INFO, new LevelLogEntrySpecification(Level.INFO));
		LEVEL_SPECIFICATIONS.put(Level.DEBUG, new LevelLogEntrySpecification(Level.DEBUG));
		LEVEL_SPECIFICATIONS.put(Level.TRACE, new LevelLogEntrySpecification(Level.TRACE));
	}

	/**
	 * @param level Optional {@link Level} a {@link LogEntry} must have to satisfy this {@link LogEntrySpecification}
	 * @param message Optional {@link Message} a {@link LogEntry} must have to satisfy this {@link LogEntrySpecification}
	 * @param throwable Optional {@link Throwable} a {@link LogEntry} must have to satisfy this {@link LogEntrySpecification}
	 * @param marker Optional {@link Marker} a {@link LogEntry} must have to satisfy this {@link LogEntrySpecification}
	 * @return {@link GroupLogEntrySpecification} that will be satisfied be a {@link LogEntry} that has any of the passed arguments, any arguments that are {@code null} will be ignore
	 */
	public GroupLogEntrySpecification createGroupLogEntrySpecification(Level level, Message message, Throwable throwable, Marker marker) {
		CheckArgument.hasAtLeastOneThatIsNotNull("At least one argument must not be null", level, message, throwable, marker);

		List<LogEntrySpecification> specifications = new ArrayList<LogEntrySpecification>();
		if (level != null) {
			specifications.add(LEVEL_SPECIFICATIONS.get(level));
		}
		if (message != null) {
			specifications.add(new MessageLogEntrySpecification(message));
		}
		if (throwable != null) {
			specifications.add(new ThrowableLogEntrySpecification(throwable));
		}
		if (marker != null) {
			specifications.add(new MarkerLogEntrySpecification(marker));
		}

		return new GroupLogEntrySpecification(specifications.toArray(new LogEntrySpecification[] {}));
	}

}
