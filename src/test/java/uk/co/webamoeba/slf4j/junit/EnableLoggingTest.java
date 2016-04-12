package uk.co.webamoeba.slf4j.junit;

import java.lang.annotation.Annotation;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.webamoeba.slf4j.junit.EnableLogging.EnableLoggingStatement;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.willAnswer;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * @author James Kennard
 */
public class EnableLoggingTest {

//	@Rule
	public EnableLogging enableLogging = EnableLogging.enableLogging();

	@Test
	public void shouldApply() throws Throwable {
		// Given
		Statement base = mock(Statement.class);
		Description description = Description.createTestDescription("Some Class", "Some Method");

		// When
		Statement statement = EnableLogging.enableLogging().apply(base, description);

		// Then
		assertThat(statement, is(instanceOf(EnableLoggingStatement.class)));
		assertThatStatementWillEvaluateBase(statement, base);
		assertThatStatementWillEnableLogging(base, statement);
		assertThatStatementWillRethrowBaseExceptions(base, statement);
	}

	@Test
	@DisableLogging
	public void shouldNotApplyGivenHasDisableLoggingAnnotation() throws Throwable {
		// Given
		Statement base = mock(Statement.class);
		DisableLogging annotation = disableLoggingAnnotation();
		Description description = Description.createTestDescription("Some Class", "Some Method", annotation);

		// When
		Statement statement = enableLogging.apply(base, description);

		// Then
		assertThat(statement, is(base));
	}

	private static DisableLogging disableLoggingAnnotation() {
		return new DisableLogging() {

			public Class<? extends Annotation> annotationType() {
				return DisableLogging.class;
			}
		};
	}
	
	private static void assertThatStatementWillEnableLogging(Statement base, Statement statement) throws Throwable {
		willAnswer(new Answer<Void>() {

			public Void answer(InvocationOnMock invocation) throws Throwable {
				Logger logger = LoggerFactory.getLogger(EnableLoggingTest.class);
				logger.info("Logged Some Information");
				return null;
			}
		}).given(base).evaluate();
		statement.evaluate();
		Mockito.reset(base);
	}
	
	private static void assertThatStatementWillEvaluateBase(Statement statement, Statement base) throws Throwable {
		statement.evaluate();
		verify(base).evaluate();
	}

	private static void assertThatStatementWillRethrowBaseExceptions(Statement base, Statement statement) throws Throwable {
		Throwable throwable = new Throwable();
		willThrow(throwable).given(base).evaluate();
		try {
			statement.evaluate();
			fail("Should have rethrown exception");
		} catch (Throwable t) {
			assertThat(t, is(throwable));
		}
		Mockito.reset(base);
	}

}
