package uk.co.webamoeba.slf4j.junit.verification;

import java.util.List;
import uk.co.webamoeba.slf4j.junit.log.LogEntry;
import uk.co.webamoeba.slf4j.junit.logger.RecordingLogger;
import uk.co.webamoeba.slf4j.junit.specification.LogEntrySpecification;

/**
 * Verifies a {@link RecordingLogger} can satisfy a {@link LogEntrySpecification} with one of its recorded {@link LogEntry Log Entries}.
 * 
 * @author James Kennard
 */
public class RecordingLoggerSpecificationVerifier {

	/**
	 * @param logger {@link RecordingLogger} from which we want to verify a {@link LogEntry} satisfies the {@link LogEntrySpecification}
	 * @param specification {@link LogEntrySpecification} that one of the {@link LogEntry Log Entries} needs to satisfy
	 * @throws AssertionError Thrown if no {@link LogEntry} from the {@link RecordingLogger} is able to satisfy the {@link LogEntrySpecification}
	 */
	public void verifyRecordingLoggerSatisfiesSpecification(RecordingLogger logger, LogEntrySpecification specification) throws AssertionError {
		List<LogEntry> entries = logger.getLog().getEntries();
		if (entries.isEmpty()) {
			throw new AssertionError("No LogEntries were logged to \"" + logger.getName() + "\"");
		}

		boolean specificationIsSatisfiedByALogEntry = specificationIsSatisfiedByALogEntry(specification, entries);
		if (!specificationIsSatisfiedByALogEntry) {
			throw new AssertionError("There are LogEntries, but no LogEntry " + specification.describeSatisfiedLogEntry()
					+ " was logged to " + logger.getName());
		}
	}

	private boolean specificationIsSatisfiedByALogEntry(LogEntrySpecification specification, List<LogEntry> entries) {
		for (LogEntry logEntry : entries) {
			if (specification.isSatisfiedBy(logEntry)) {
				return true;
			}
		}
		return false;
	}

}
