package uk.co.webamoeba.slf4j.junit.specification;

import org.junit.Test;
import uk.co.webamoeba.slf4j.junit.log.LogEntry;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static uk.co.webamoeba.slf4j.junit.testsupport.LogEntryTestFactory.aLogEntry;

/**
 * Test for {@link GroupLogEntrySpecification}
 * 
 * @author James Kennard
 */
public class GroupLogEntrySpecificationTest {

	@Test
	public void shouldSatisfy() {
		// Given
		LogEntrySpecification specification = mock(LogEntrySpecification.class);
		LogEntry logEntry = aLogEntry();
		given(specification.isSatisfiedBy(logEntry)).willReturn(true);

		GroupLogEntrySpecification specifications = new GroupLogEntrySpecification(specification);

		// When
		boolean satisfied = specifications.isSatisfiedBy(logEntry);

		// Then
		assertThat(satisfied, is(true));
	}

	@Test
	public void shouldNotSatisfy() {
		// Given
		LogEntrySpecification specification = mock(LogEntrySpecification.class);
		LogEntry logEntry = aLogEntry();
		given(specification.isSatisfiedBy(logEntry)).willReturn(false);

		GroupLogEntrySpecification specifications = new GroupLogEntrySpecification(specification);

		// When
		boolean satisfied = specifications.isSatisfiedBy(logEntry);

		// Then
		assertThat(satisfied, is(false));
	}

	@Test
	public void shouldSatisfyGivenMoreThanOneSpecification() {
		// Given
		LogEntrySpecification aSpecification = mock(LogEntrySpecification.class);
		LogEntrySpecification anotherSpecification = mock(LogEntrySpecification.class);
		LogEntry logEntry = aLogEntry();
		given(aSpecification.isSatisfiedBy(logEntry)).willReturn(true);
		given(anotherSpecification.isSatisfiedBy(logEntry)).willReturn(true);

		GroupLogEntrySpecification specifications = new GroupLogEntrySpecification(aSpecification, anotherSpecification);

		// When
		boolean satisfied = specifications.isSatisfiedBy(logEntry);

		// Then
		assertThat(satisfied, is(true));
	}

	@Test
	public void shouldNotSatisfyGivenMoreThanOneSpecification() {
		// Given
		LogEntrySpecification satisfiedSpecification = mock(LogEntrySpecification.class);
		LogEntrySpecification unsatisfiedSpecification = mock(LogEntrySpecification.class);
		LogEntry logEntry = aLogEntry();
		given(satisfiedSpecification.isSatisfiedBy(logEntry)).willReturn(true);
		given(unsatisfiedSpecification.isSatisfiedBy(logEntry)).willReturn(false);

		GroupLogEntrySpecification specifications = new GroupLogEntrySpecification(satisfiedSpecification, unsatisfiedSpecification);

		// When
		boolean satisfied = specifications.isSatisfiedBy(logEntry);

		// Then
		assertThat(satisfied, is(false));
	}

	@Test
	public void shouldDescribeSatisfiedLogEntry() {
		// Given
		LogEntrySpecification specification = mock(LogEntrySpecification.class);
		String expectedDescription = "with something or other";
		given(specification.describeSatisfiedLogEntry()).willReturn(expectedDescription);

		GroupLogEntrySpecification specifications = new GroupLogEntrySpecification(specification);

		// When
		String description = specifications.describeSatisfiedLogEntry();

		// Then
		assertThat(description, is(expectedDescription));
	}

	@Test
	public void shouldDescribeSatisfiedLogEntryGivenMoreThanOneSpecification() {
		// Given
		LogEntrySpecification aSpecification = mock(LogEntrySpecification.class);
		LogEntrySpecification anotherSpecification = mock(LogEntrySpecification.class);
		String expectedDescriptionPartOne = "part one";
		String expectedDescriptionPartTwo = "part two";
		given(aSpecification.describeSatisfiedLogEntry()).willReturn(expectedDescriptionPartOne);
		given(anotherSpecification.describeSatisfiedLogEntry()).willReturn(expectedDescriptionPartTwo);

		GroupLogEntrySpecification specifications = new GroupLogEntrySpecification(aSpecification, anotherSpecification);

		// When
		String description = specifications.describeSatisfiedLogEntry();

		// Then
		assertThat(description, is(expectedDescriptionPartOne + " " + expectedDescriptionPartTwo));
	}

	@Test
	public void shouldFailToConstructGivenNull() {
		// Given
		LogEntrySpecification specification = null;

		try {
			// When
			new GroupLogEntrySpecification(specification);

			// Then
			fail("Should throw IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("specifications must not be null"));
		}
	}

	@Test
	public void shouldFailToConstructGivenNoSpecifications() {
		// Given

		try {
			// When
			new GroupLogEntrySpecification();

			// Then
			fail("Should throw IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("at least one specification must be provided"));
		}
	}

}
