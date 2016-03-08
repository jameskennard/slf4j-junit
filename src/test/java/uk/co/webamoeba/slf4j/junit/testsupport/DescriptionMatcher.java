package uk.co.webamoeba.slf4j.junit.testsupport;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

/**
 * {@link Matcher} for {@link Description Descriptions}
 * 
 * @author James Kennard
 */
public class DescriptionMatcher extends TypeSafeDiagnosingMatcher<Description> {

	private final String value;

	/**
	 * @param value Value we want to match
	 */
	public static DescriptionMatcher describes(String value) {
		return new DescriptionMatcher(value);
	}
	
	/**
	 * @param value Value we want to match
	 */
	private DescriptionMatcher(String value) {
		this.value = value;
	}

	/**
	 * {@inheritDoc}
	 */
	public void describeTo(Description description) {
		description.appendText("Description with value: ").appendValue(value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean matchesSafely(Description description, Description mismatchDescription) {
		if (!value.equals(description.toString())) {
			mismatchDescription
					.appendText(" wanted value ").appendValue(value)
					.appendText(" but found ").appendValue(description.toString());
			return false;
		}
		return true;
	}

}
