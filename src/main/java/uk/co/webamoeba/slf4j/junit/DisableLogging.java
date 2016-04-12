package uk.co.webamoeba.slf4j.junit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.junit.Rule;
import org.junit.Test;

/**
 * Disables Logging for one {@link Test} in a test class where the {@link EnableLogging} test {@link Rule} has been used.
 * <p>
 * This may be of use when:
 * </p>
 * <ul>
 * <li>You expect different behaviour when logging levels are not enabled</li>
 * <li>You have a set of tests which use logging extensively, and you want to reduce the length of time it takes for your tests to run, be explicitly disabling logging for on tests where you do not
 * care about logging</li>
 * </ul>
 * 
 * @author James Kennard
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DisableLogging {

}
