package uk.co.webamoeba.slf4j.junit.specification;

import org.junit.Test;
import uk.co.webamoeba.slf4j.junit.log.Level;
import uk.co.webamoeba.slf4j.junit.log.LogEntry;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static uk.co.webamoeba.slf4j.junit.testsupport.LevelTestFactory.aDifferentLevel;
import static uk.co.webamoeba.slf4j.junit.testsupport.LevelTestFactory.aLevel;
import static uk.co.webamoeba.slf4j.junit.testsupport.LogEntryTestFactory.logEntryAtLevel;

/**
 * Test for {@link LevelLogEntrySpecification}
 * 
 * @author James Kennard
 */
public class LevelLogEntrySpecificationTest {

	@Test
	public void shouldSatisfy() {
		// Given
		Level level = aLevel();
		LogEntry logEntry = logEntryAtLevel(level);

		LevelLogEntrySpecification specification = new LevelLogEntrySpecification(level);

		// When
		boolean isSatisfied = specification.isSatisfiedBy(logEntry);

		// Then
		assertThat(isSatisfied, is(true));
	}

	@Test
	public void shouldNotSatisfy() {
		// Given
		Level level = aLevel();
		LevelLogEntrySpecification specification = new LevelLogEntrySpecification(level);

		Level differentLevel = aDifferentLevel(level);
		LogEntry logEntry = logEntryAtLevel(differentLevel);

		// When
		boolean isSatisfied = specification.isSatisfiedBy(logEntry);

		// Then
		assertThat(isSatisfied, is(false));
	}

	@Test
	public void shouldDescribeSatisfiedLogEntry() {
		// Given
		Level level = aLevel();
		LevelLogEntrySpecification specification = new LevelLogEntrySpecification(level);

		// When
		String description = specification.describeSatisfiedLogEntry();

		// Then
		assertThat(description, is("at level <" + level + ">"));
	}

	@Test
	public void shouldFailToConstructGivenNull() {
		// Given
		Level level = null;

		try {
			// When
			new LevelLogEntrySpecification(level);

			// Then
			fail("Should throw IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("level must not be null"));
		}
	}

}
