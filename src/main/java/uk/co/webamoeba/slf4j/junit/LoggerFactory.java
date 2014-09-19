package uk.co.webamoeba.slf4j.junit;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

/**
 * @author James Kennard
 */
public class LoggerFactory implements ILoggerFactory {

	public Logger getLogger(String name) {
		if (name == null) {
			throw new IllegalArgumentException("name must not be null");
		}
		return new RecordingLogger(name);
	}

}
