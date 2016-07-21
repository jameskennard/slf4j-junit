package uk.co.webamoeba.slf4j.junit.specification;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.junit.Test;
import org.slf4j.Marker;
import org.slf4j.helpers.BasicMarkerFactory;
import uk.co.webamoeba.slf4j.junit.log.Level;
import uk.co.webamoeba.slf4j.junit.log.LogEntry;
import uk.co.webamoeba.slf4j.junit.log.LogEntry.Message;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Test for {@link GroupLogEntrySpecificationFactory}
 * 
 * @author James Kennard
 */
public class GroupLogEntrySpecificationFactoryTest {

	private GroupLogEntrySpecificationFactory factory = new GroupLogEntrySpecificationFactory();

	@Test
	public void shouldCreateGivenLevel() {
		// Given
		Level level = aLevel();
		Message message = null;
		Throwable throwable = null;
		Marker marker = null;

		// When
		GroupLogEntrySpecification specification = factory.createGroupLogEntrySpecification(level, message, throwable, marker);

		// Then
		assertThat(specification, satisfiesLogEntry().atLevel(level));
	}

	@Test
	public void shouldCreateGivenMessage() {
		// Given
		Level level = null;
		Message message = new LogEntry.StringMessage("message");
		Throwable throwable = null;
		Marker marker = null;

		// When
		GroupLogEntrySpecification specification = factory.createGroupLogEntrySpecification(level, message, throwable, marker);

		// Then
		assertThat(specification, satisfiesLogEntry().withMessage(message));
	}

	@Test
	public void shouldCreateGivenThrowable() {
		// Given
		Level level = null;
		Message message = null;
		Throwable throwable = new Throwable("Some Throwable");
		Marker marker = null;

		// When
		GroupLogEntrySpecification specification = factory.createGroupLogEntrySpecification(level, message, throwable, marker);

		// Then
		assertThat(specification, satisfiesLogEntry().withThrowable(throwable));
	}

	@Test
	public void shouldCreateGivenMarker() {
		// Given
		Level level = null;
		Message message = null;
		Throwable throwable = null;
		Marker marker = new BasicMarkerFactory().getMarker("Some Marker");

		// When
		GroupLogEntrySpecification specification = factory.createGroupLogEntrySpecification(level, message, throwable, marker);

		// Then
		assertThat(specification, satisfiesLogEntry().withMarker(marker));
	}

	private LogEntrySpecificationMatcher satisfiesLogEntry() {
		return new LogEntrySpecificationMatcher();
	}

	private Level aLevel() {
		return Level.INFO;
	}

	@Test
	public void shouldFailToCreateGivenAllNulls() {
		// Given
		Level level = null;
		Message message = null;
		Throwable throwable = null;
		Marker marker = null;

		try {
			// When
			factory.createGroupLogEntrySpecification(level, message, throwable, marker);

			// Then
			fail("Should throw IllegalArgumentException");
		} catch (IllegalArgumentException e) {

		}
	}

	private class LogEntrySpecificationMatcher extends TypeSafeDiagnosingMatcher<LogEntrySpecification> {

		private Level level;

		private Marker marker;

		private Message message;

		private Throwable throwable;

		public void describeTo(Description description) {
			description.appendText("Specification should be satified by: ").appendValue(satisfyingLogEntry());
		}

		public LogEntrySpecificationMatcher atLevel(Level level) {
			this.level = level;
			return this;
		}

		public LogEntrySpecificationMatcher withMessage(Message message) {
			this.message = message;
			return this;
		}

		public LogEntrySpecificationMatcher withThrowable(Throwable throwable) {
			this.throwable = throwable;
			return this;
		}

		public LogEntrySpecificationMatcher withMarker(Marker marker) {
			this.marker = marker;
			return this;
		}

		@Override
		protected boolean matchesSafely(LogEntrySpecification specification, Description mismatchDescription) {
			LogEntry logEntry = satisfyingLogEntry();
			if (!specification.isSatisfiedBy(logEntry)) {
				mismatchDescription.appendText("specification does not match the LogEntry: ").appendValue(logEntry);
				return false;
			}
			return true;
		}

		private LogEntry satisfyingLogEntry() {
			String messageAsString = (message != null) ? message.getMessageAsString() : null;
			LogEntry logEntry = new LogEntry(level, marker, messageAsString, throwable);
			return logEntry;
		}

	}

}
