package uk.co.webamoeba.slf4j.junit;

/**
 * @author James Kennard
 */
public class RecordingLogger {

	private final String name;

	public RecordingLogger(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
