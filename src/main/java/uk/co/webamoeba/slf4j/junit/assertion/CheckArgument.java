package uk.co.webamoeba.slf4j.junit.assertion;

/**
 * Assertions for checking arguments passed to a method. Any assertions that fail will result in an {@link IllegalArgumentException} being thrown.
 * 
 * @author James Kennard
 */
public class CheckArgument {

	/**
	 * @param argument Argument we want to check is not {@code null}
	 * @param message Message explaining the argument must not be {@code null}
	 * @throws IllegalArgumentException Thrown if the argument is {@code null}
	 */
	public static void isNotNull(Object argument, String message) throws IllegalArgumentException {
		if (argument == null) {
			throw new IllegalArgumentException(message);
		}
	}

}
