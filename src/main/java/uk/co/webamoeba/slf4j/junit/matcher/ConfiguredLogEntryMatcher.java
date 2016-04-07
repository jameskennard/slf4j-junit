package uk.co.webamoeba.slf4j.junit.matcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import uk.co.webamoeba.slf4j.junit.log.LogEntry;

/**
 * {@link Matcher} for a {@link LogEntry} that is configured.
 * 
 * @author James Kennard
 */
public interface ConfiguredLogEntryMatcher extends Matcher<LogEntry> {

	/**
	 * Describes what it is about a {@link LogEntry} that means this {@link Matcher} would {@link Matcher#matches(Object) match} it.
	 * 
	 * @param description The {@link Description} to be appended to.
	 */
	void describeMatchingLogEntry(Description description);
	
}
