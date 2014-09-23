package uk.co.webamoeba.slf4j.junit.matcher;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.slf4j.Logger;

/**
 * @author James Kennard
 */
public class InfoLogEventMatcher extends TypeSafeDiagnosingMatcher<Logger> {

	public void describeTo(Description description) {
	}

	@Override
	protected boolean matchesSafely(Logger item, Description mismatchDescription) {
		return false;
	}

}
