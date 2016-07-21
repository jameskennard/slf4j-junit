package uk.co.webamoeba.slf4j.junit.specification;

import org.junit.Test;
import uk.co.webamoeba.slf4j.junit.log.LogEntry;
import uk.co.webamoeba.slf4j.junit.testsupport.LogEntryTestFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Test for {@link ThrowableLogEntrySpecification}
 * 
 * @author James Kennard
 */
public class ThrowableLogEntrySpecificationTest {

	@Test
	public void shouldSatisfy() {
		// Given
		Throwable throwable = aThrowable();
		LogEntry logEntry = LogEntryTestFactory.logEntryWithThrowable(throwable);

		ThrowableLogEntrySpecification specification = new ThrowableLogEntrySpecification(throwable);

		// When
		boolean isSatisfied = specification.isSatisfiedBy(logEntry);

		// Then
		assertThat(isSatisfied, is(true));
	}

	@Test
	public void shouldNotSatisfy() {
		// Given
		Throwable throwable = aThrowable();
		LogEntry logEntry = LogEntryTestFactory.logEntryWithThrowable(throwable);

		Throwable differentThrowable = aDifferentThrowable();
		ThrowableLogEntrySpecification specification = new ThrowableLogEntrySpecification(differentThrowable);

		// When
		boolean isSatisfied = specification.isSatisfiedBy(logEntry);

		// Then
		assertThat(isSatisfied, is(false));
	}

	@Test
	public void shouldDescribeSatisfiedLogEntry() {
		// Given
		String messageText = "Some Message";
		Throwable throwable = new Throwable(messageText);
		ThrowableLogEntrySpecification specification = new ThrowableLogEntrySpecification(throwable);

		// When
		String description = specification.describeSatisfiedLogEntry();

		// Then
		assertThat(description, is("with throwable <" + messageText + ">"));
	}

	@Test
	public void shouldFailToConstructGivenNull() {
		// Given
		Throwable throwable = null;

		try {
			// When
			new ThrowableLogEntrySpecification(throwable);

			// Then
			fail("Should throw IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("throwable must not be null"));
		}
	}

	private static Throwable aThrowable() {
		return new Throwable("A Throwable");
	}

	private static Throwable aDifferentThrowable() {
		return new Throwable("A Different Throwable");
	}

}
