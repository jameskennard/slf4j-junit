package uk.co.webamoeba.slf4j.junit.testsupport;

import uk.co.webamoeba.slf4j.junit.log.Level;

import static uk.co.webamoeba.slf4j.junit.log.Level.INFO;

/**
 * Factory which creates {@link Level Levels}
 * 
 * @author James Kennard
 */
public class LevelTestFactory {

	/**
	 * @return An arbitrary {@link Level}
	 */
	public static Level aLevel() {
		return INFO;
	}

	/**
	 * @param level {@link Level} from which we want to be different
	 * @return A {@link Level} that is different to the provided {@link Level}
	 */
	public static Level aDifferentLevel(Level level) {
		for (Level candidate : Level.values()) {
			if (candidate != level) {
				return candidate;
			}
		}
		throw new IllegalStateException("Could not find a different level to " + level + ", is there something wrong with the Level enum?");
	}

}
