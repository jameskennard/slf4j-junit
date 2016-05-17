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

	/**
	 * @param argument Array argument we want to check has no {@code null} elements
	 * @param message essage explaining the argument must have any {@code null} elements
	 * @throws IllegalArgumentException
	 */
	public static <T> void hasNoNullElements(T[] argument, String message) throws IllegalArgumentException {
		isNotNull(argument, message);
		for (int index = 0; index < argument.length; index++) {
			isNotNull(argument[index], message);
		}
	}
	
	/**
	 * @param argument Array argument we want to check at least one element
	 * @param message Message explaining the argument must have at least one elements
	 * @throws IllegalArgumentException
	 */
	public static <T> void hasElements(T[] argument, String message) throws IllegalArgumentException {
		isNotNull(argument, message);
		if (argument.length == 0) {
			throw new IllegalArgumentException(message);
		}
	}

}
